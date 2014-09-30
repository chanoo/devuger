package com.devuger.util;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloCheckUtil 
{	
	public static boolean isTelNo( String telNo )
	{
		if( telNo == null )
			return false;
		
		telNo = telNo.replaceAll("[^0-9]", "");
		if( telNo.length() < 9 || telNo.length() > 11 )
			return false;
		
		return true;
	}
	
	public static boolean isYN( String str )
	{
		if( str == null || str.length() != 1 )
			return false;
		
		if( str.equals("Y") || str.equals("N") )
			return true;
		return false;
	}
	
	/**
	 * str이 숫자로만 구성되어 있는지 검사
	 * 
	 * @param str	
	 * @return
	 */
	public static boolean isNumber(String str) 
	{
		if( str == null || str.length() == 0 )
			return false;
		
		char ch = '0';
		for( int i = 0; i < str.length(); i++ )
		{
			ch = str.charAt(i);
			if( ch < '0' || ch > '9' )
				return false;
		}
		return true;
	}

	/**
	 * 한국인/외국인 주민번호로 맞는지 검사
	 * 
	 * @param regNo		내국인 주민번호 또는 외국인번호
	 * @return
	 */
	public static boolean isRegNo( String regNo )
	{
		if( !isKoreanRegNo(regNo) && !isForeignRegNo(regNo) )
			return false;
		return true;
	}
	
	/**
	 * 한국인 주민번호 검사
	 * 
	 * @param regNo		주민번호. 숫자만 입력해야 한다.
	 * @return
	 */
	public static boolean isKoreanRegNo(String regNo)
	{
		if(regNo == null || regNo.length() != 13 )
			return false;

		if( !isNumber( regNo ) )
			return false;

		int[] regNoDigits = {2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5};

		int[] buf = new int[13];
		int sum = 0;
		for (int i=0; i<12; i++)
		{
			buf[i] = regNo.charAt(i) - '0';
			sum += buf[i] * regNoDigits[i];
		}
		buf[12] = regNo.charAt(12) - '0';
		int chkNum = (11 - sum % 11) % 10;
		if (chkNum != buf[12] )
			return false;

		if( buf[6] < 1 || buf[6] > 4 )
			return false;
		
		String birthYmd = null;
		if( buf[6] < 3)
			birthYmd = "19" + regNo.substring(0,2);
		else
			birthYmd = "20" + regNo.substring(0,2);
		
		birthYmd += regNo.substring(2,6);
		if( !isValidYmd( birthYmd ) )
			return false;
		
		return true;
	}
	
	/**
	 * 외국인 등록번호인지 확인
	 * 
	 * @param regNo		외국인등록번호
	 * @return
	 */
	public static boolean isForeignRegNo(String regNo)
	{
		if(regNo == null || regNo.length() != 13 )
			return false;

		if( !isNumber( regNo ) )
			return false;
		
		
		int sum = 0;
	    int odd = 0;
	    
	    int[] buf = new int[13];
		
	    for(int i = 0; i < 13; i++)
            buf[i] = regNo.charAt(i) - '0';   
	    
	    odd = buf[7] * 10 + buf[8];
	    
	    if(odd % 2 != 0)
	    	return  false; 
	    
	    if( (buf[11] != 6) && (buf[11] != 7) && (buf[11] != 8) && (buf[11] != 9) )
	    	return false;
	    
	    int[] multipliers = {2,3,4,5,6,7,8,9,2,3,4,5};
		
	    for(int i=0; i<12; i++)
	    	sum += buf[i] * multipliers[i];
	    
	    sum = 11-(sum%11);
	    
	    if(sum >= 10)
	    	sum -= 10;
	    
	    sum += 2;
	    
	    if (sum >= 10)
	    	sum -= 10;
	    
	    if (sum != buf[12])
	    	return false;

		if( buf[6] < 5 || buf[6] > 8 )
			return false;
		
		String birthYmd = null;
		if( buf[6] < 7)
			birthYmd = "19" + regNo.substring(0,2);
		else
			birthYmd = "20" + regNo.substring(0,2);
		
		birthYmd += regNo.substring(2,6);
		if( !isValidYmd( birthYmd ) )
			return false;
		
		return true;
	}
	
	public static boolean isValidYmd( String ymd )
	{
		Date birthDate = HelloTransUtil.toYmdDate( ymd );
		if( birthDate == null )
			return false;
		return true;
	}
	
	/**
	 * 사업자등록번호가 맞는지 확인
	 * 
	 * @param bizRegNo	사업자등록번호
	 * @return
	 */
	public static boolean isBizRegNo(String bizRegNo)
	{
		if( bizRegNo == null || bizRegNo.length() != 10 )
			return false;
		
		int sum = 0;
		int checknum = 0;
		int[] anum = new int[10];
		int[] chknum = {1,3,7,1,3,7,1,3,5};
		
		for(int i = 0; i < 10; i++)
			anum[i] = bizRegNo.charAt(i) -'0';
		
		for(int i = 0; i < 9; i++)
			sum += anum[i] * chknum[i];
		
		sum += ((anum[8]*5)/10);
		checknum = (10 - sum % 10) %10;
		if(checknum != anum[9])
			return false;
		return true;
	}
	
	/**
	 * 법인등록번호 검사
	 * 
	 * @param corpRegNo		법인등록번호
	 * @return
	 */
	public static boolean isCorpRegNo(String corpRegNo)
	{
		if(corpRegNo == null || corpRegNo.length() != 13 )
			return false;

		int[] ChkDgt = {1,2,1,2,1,2,1,2,1,2,1,2};
		int[] nBupin = new int[13];
		int i;
		for( i=0; i < 13; i++ )
			nBupin[i] = corpRegNo.charAt(i) - '0';

		int  lV1 = 0;
		int  nV2 = 0;
		int  nV3 = 0;

		for( i = 0 ; i < 12 ; i++) {
			lV1 = nBupin[i] * ChkDgt[i];
			if(lV1 >= 10)
				nV2 += lV1 % 10;
			else 
				nV2 += lV1;
		}
		 
		nV3 = nV2 % 10;
		 
		if( nV3 > 0 )
			nV3 = 10 - nV3;
		else
			nV3 = 0;

		if( nBupin[12] != nV3) 
			return false;

		return true;
	}
	
	/**
	 * 주민등록번호로 14세 미만인지 여부를 검사한다.
	 * 
	 * @param regNo		주민등록번호, 외국인등록번호
	 * @return			true: 14세 미만
	 * @throws NeoException 
	 */
	public static boolean isUnder14( String regNo )
	{
		if( HelloTransUtil.toAge( regNo ) < 14 )
			return true;
		return false;
	}
	
	/**
	 * 생년월일로 14세 미만인지를 검사한다.
	 * 
	 * @param birthYear		생년
	 * @param birthMonth	생월
	 * @param birthDay		생일
	 * @return				true: 14세 미만
	 */
	public static boolean isUnder14( int birthYear, int birthMonth, int birthDay )
	{
		if( HelloTransUtil.toAge( birthYear, birthMonth, birthDay ) < 14 )
			return true;
		return false;
	}
	
	public static boolean isValidEmail( String email )
	{
		Pattern p=Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		 
		Matcher m=p.matcher(email);
		if( !m.matches() )
			return false;
		
		String[] terms = email.split("@");
		if( terms.length < 2 )
			return false;
		for( int i = 0; i < terms.length; i++ )
		{
			if( terms[i].length() == 0 )
				return false;
		}
		
		String[] domain = terms[1].split("\\.");
		if( domain.length < 2 )
			return false;
		for( int i = 0; i < domain.length; i++ )
		{
			if( domain[i].length() == 0 )
				return false;
		}
		return true;
	}
	
	private static final String WB_ANDROID = "Android";
	private static final String WB_IPHONE = "iPhone OS";
	private static final String WB_OPERA = "Opera";
	private static final String WB_FIREFOX = "Firefox";
	private static final String WB_CHROMEFRAME = "chromeframe";
	private static final String WB_CHROME = "Chrome";
	private static final String WB_SAFARI = "Safari";
	private static final String WB_SAFARI_VER = "Version";
	private static final String WB_MSIE = "MSIE";
	
	/**
	 * 브라우저 정보를 구한다.
	 * 
	 * @param userAgent
	 * @return		브라우저명/버전(소수점 2째자리까지만)
	 */
	public static String checkBrowserInfo( String userAgent )
	{
		int idx;
		StringBuilder builder = new StringBuilder();
		
		// 안드로이드
		if( (idx = userAgent.indexOf(WB_ANDROID)) > -1 )
		{
			builder.append( WB_ANDROID + "/");
			idx += WB_ANDROID.length() + 1;
			builder.append( getBrowserVersion( userAgent, idx ) );
			return builder.toString();
		}
		
		// iphone
		if( (idx = userAgent.indexOf(WB_IPHONE)) > -1 )
		{
			builder.append( WB_IPHONE + "/");
			idx += WB_IPHONE.length() + 1;
			builder.append( getBrowserVersion( userAgent, idx ) );
			return builder.toString();
		}
		
		// opera
		if( (idx = userAgent.indexOf(WB_OPERA)) > -1 )
		{
			builder.append( WB_OPERA + "/");
			idx += WB_OPERA.length() + 1;
			builder.append( getBrowserVersion( userAgent, idx ) );
			return builder.toString();
		}
		
		// firefox
		if( (idx = userAgent.indexOf(WB_FIREFOX)) > -1 )
		{
			builder.append( WB_FIREFOX + "/");
			idx += WB_FIREFOX.length() + 1;
			builder.append( getBrowserVersion( userAgent, idx ) );
			return builder.toString();
		}
		
		// chromeframe
		if( (idx = userAgent.indexOf(WB_CHROMEFRAME)) > -1 )
		{
			builder.append( WB_CHROMEFRAME + "/");
			idx += WB_CHROMEFRAME.length() + 1;
			builder.append( getBrowserVersion( userAgent, idx ) );
			return builder.toString();
		}
		
		// chrome
		if( (idx = userAgent.indexOf(WB_CHROME)) > -1 )
		{
			builder.append( WB_CHROME + "/");
			idx += WB_CHROME.length() + 1;
			builder.append( getBrowserVersion( userAgent, idx ) );
			return builder.toString();
		}
		
		// safari
		if( userAgent.indexOf(WB_SAFARI) > -1 && (idx = userAgent.indexOf(WB_SAFARI_VER)) > -1 )
		{
			builder.append( WB_SAFARI + "/");
			idx += WB_SAFARI_VER.length() + 1;
			builder.append( getBrowserVersion( userAgent, idx ) );
			return builder.toString();
		}
		
		// msie
		if( (idx = userAgent.indexOf(WB_MSIE)) > -1 )
		{
			builder.append( WB_MSIE + "/");
			idx += WB_MSIE.length() + 1;
			builder.append( getBrowserVersion( userAgent, idx ) );
			return builder.toString();
		}
		
		// 기타
		
		return "ETC";
	}
	
	/**
	 * 브라우저의 버전을 소수점 2째자리까지 구한다.
	 * 
	 * @param userAgent
	 * @param fromIdx
	 * @return
	 */
	private static String getBrowserVersion( String userAgent, int fromIdx )
	{
		StringBuilder version = new StringBuilder();
		int dotCnt = 0;
		for( int i = fromIdx; i < userAgent.length(); i++ )
		{
			char c = userAgent.charAt( i );
			if( c == '_' ) 
				c = '.';
			
			if( c != '.' && ( c < '0' || c > '9' ) )
				break;
			
			if( c == '.' )
				dotCnt++;

			if( dotCnt > 1 )
				break;
			
			version.append(c);
		}
		
		return version.toString();
	}
	
	public static boolean isMobileDevice( String userAgent )
	{
		// 안드로이드
		if( userAgent.indexOf(WB_ANDROID) > -1 )
			return true;		
		// iphone
		if( userAgent.indexOf(WB_IPHONE) > -1 )
			return true;
		
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main( String[] args )
	{
		String mobilePhoneNo = "010-3880-3833";
		mobilePhoneNo = mobilePhoneNo.replaceAll("[^0-9]", "");
		System.out.println(mobilePhoneNo);
		
		int certNo = 329;
		String format = String.format("%04d", certNo);
		System.out.println(format);
	}

  public static boolean minLength(String text, int min) {
    // TODO Auto-generated method stub
    if(text != null && text.length() > min)
      return true;
    
    return false;
  }

  public static boolean isLength(String text, int min, int max) {
    // TODO Auto-generated method stub
    if(text != null && text.length() > min && text.length() < max)
      return true;
    
    return false;
  }
}
