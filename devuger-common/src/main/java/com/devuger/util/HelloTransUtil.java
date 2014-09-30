package com.devuger.util;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;

public class HelloTransUtil 
{
	private final static Calendar cal = Calendar.getInstance();
	private final static String defaultFormat = "YYYY.MM.DD";
	private static final int[]	MONTHS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	public static String toHtml( String str )
	{
		str = str.replaceAll( "&", "&amp;");
		str = str.replaceAll( "<", "&lt;" );
		str = str.replaceAll( ">", "&gt;" );
		str = str.replaceAll( "'", "&#39;" );
		str = str.replaceAll( "\"", "&quot;" );
		str = str.replaceAll( "\\x28", "&#40;" );
		str = str.replaceAll( "\\x29", "&#41;" );
		str = str.replaceAll( "\r\n", "<br>" );
		str = str.replaceAll( "\n", "<br>" );
		str = str.replaceAll( " ", "&nbsp;");
		str = str.replaceAll( "\t", "&nbsp;&nbsp;&nbsp;&nbsp;" );
        return str;
	}
	
	public static String toInputValue( String str )
	{
		str = str.replaceAll( "&", "&amp;" );
        str = str.replaceAll( "<", "&lt;" );
		str = str.replaceAll( ">", "&gt;" );
		str = str.replaceAll( "'", "&#39;" );
		str = str.replaceAll( "\"", "&quot;" );
        return str;
	}
	
	public static String regNoToBirthday( String regNo )
	{
		if( HelloCheckUtil.isKoreanRegNo(regNo) )
		{
			String birthYmd = null;
			if( regNo.charAt(6) < '3')
				birthYmd = "19" + regNo.substring(0,2);
			else
				birthYmd = "20" + regNo.substring(0,2);
			
			birthYmd += regNo.substring(2,6);
			if( !HelloCheckUtil.isValidYmd( birthYmd ) )
				return "";
			return birthYmd;
		}
		else if( HelloCheckUtil.isForeignRegNo(regNo) )
		{
			String birthYmd = null;
			if( regNo.charAt(6) < '7')
				birthYmd = "19" + regNo.substring(0,2);
			else
				birthYmd = "20" + regNo.substring(0,2);
			
			birthYmd += regNo.substring(2,6);
			if( !HelloCheckUtil.isValidYmd( birthYmd ) )
				return "";
			return birthYmd;
		}
		else
			return "";
	}
	
	/**
	 * 주민번호로 만 나이를 구한다.
	 * 
	 * @param regNo		주민번호
	 * @return			만 나이. -1이면 오류.
	 */
	public static int toAge( String regNo )
	{
		String birthYmd = regNoToBirthday(regNo);
		if( birthYmd.length() != 8 )
			return -1;
		
		Date birthDate = HelloTransUtil.toYmdDate( birthYmd );
		Calendar cal = Calendar.getInstance();
		cal.setTime( birthDate );
		
		int birthYear  = cal.get( Calendar.YEAR );
		int birthMonth = cal.get( Calendar.MONTH ) + 1;
		int birthDay   = cal.get( Calendar.DATE );
		
		return toAge( birthYear, birthMonth, birthDay );
	}
	
	/**
	 * 
	 * @param birthYear
	 * @param birthMonth
	 * @param birthDay
	 * @return
	 */
	public static int toAge( int birthYear, int birthMonth, int birthDay )
	{
		Calendar cal = Calendar.getInstance();
		
		// 오늘
		int nowYear  = cal.get( Calendar.YEAR );
		int nowMonth = cal.get( Calendar.MONTH ) + 1;
		int nowDay   = cal.get( Calendar.DATE );
		
		int age = nowYear - birthYear;
		if( nowMonth > birthMonth )
			return age;
		if( nowMonth < birthMonth )
			return age - 1;
		if( nowDay < birthDay )
			return age - 1;
		return age;	
	}
	
	/**
	 * String src의 fromIdx부터 toIdx까지를 ch로 변경한다.
	 * @param src		원본 String
	 * @param fromIdx	시작 index
	 * @param toIdx		끝 index
	 * @param ch		변경할 문자
	 * @return
	 */
    public static String replace( String src, int fromIdx, int toIdx, char ch )
    {
    	int len = src.length();
    	
    	if( fromIdx < 0 || toIdx < 0 )
    		return src;
    	
    	if( fromIdx > toIdx )
    		return src;
    	
    	if( fromIdx >= len || toIdx >= len )
    		return src;
    	
    	StringBuilder builder = new StringBuilder(src);
    	for( int i = fromIdx; i <= toIdx; i++ )
    		builder.setCharAt(i, ch);
    	
    	return builder.toString();
    }
    
    /**
     * Date형 날짜를 'YYYY.MM.DD' format의 문자열로 반환한다.
     * @param date
     * @return
     */
    public static String toDateString(Date date)
    {
    	return toDateString(date, defaultFormat);
    }
 
    /**
     * 날짜를 입력된 포맷(format)의 문자열로 반환한다.
     * 
     * @param date		날짜
     * @param format	YY:연도, MM:월, DD:일, HH:시간, MI:분, SS:초, MS:1/1000초
     * @return
     */
    public static String toDateString( Date date, String format )
	{
    	if( date == null )
    		return "";
    	
		cal.setTime(date);
		
		String upperFormat = format.toUpperCase();
		
		int year  = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day   = cal.get(Calendar.DAY_OF_MONTH);
		int hour  = cal.get(Calendar.HOUR_OF_DAY);
		int min   = cal.get(Calendar.MINUTE);
		int sec   = cal.get(Calendar.SECOND);
		int msec  = cal.get(Calendar.MILLISECOND);
		
		String sYear  = String.valueOf(year);
		String sMonth = HelloUtil.lPad( String.valueOf(month), 2, "0" );
		String sDay   = HelloUtil.lPad( String.valueOf(day), 2, "0" );
		String sHour  = HelloUtil.lPad( String.valueOf(hour), 2, "0" );
		String sMin   = HelloUtil.lPad( String.valueOf(min), 2, "0" );
		String sSec   = HelloUtil.lPad( String.valueOf(sec), 2, "0" );
		String sMSec  = HelloUtil.lPad( String.valueOf(msec), 3, "0" );
		
		String val = upperFormat.replaceAll("YYYY", sYear);
		val = val.replaceAll("YY", sYear);		
		val = val.replaceAll("MM", sMonth);
		val = val.replaceAll("DD", sDay);
		val = val.replaceAll("HH", sHour);
		val = val.replaceAll("MI", sMin);
		val = val.replaceAll("SS", sSec);
		val = val.replaceAll("MS", sMSec);
		
		return val;
	}
    
    /**
     * YYYYMMDD 형태의 문자열을 YYYY.MM.DD 형태로 반환한다.
     * 
     * @param ymd	YYYYMMDD 형태의 문자열
     * @return
     */
    public static String toYmdString( String ymd )
    {
    	return toYmdString( ymd, '.' );
    }
    
    /**
     * YYYYMMDD 형태의 문자열을 YYYY(구분자)MM(구분자)DD 형태로 반환한다.
     * 
     * @param ymd			YYYYMMDD 형태의 문자열
     * @param delimiter		구분자
     * @return
     */
    public static String toYmdString( String ymd, char delimiter )
    {
    	if( ymd == null || ymd.length() != 8 )
    		return ymd;

    	StringBuilder build = new StringBuilder( ymd );
    	build.insert(4, delimiter);
    	build.insert(7, delimiter);
    	
    	return build.toString();
    }
    
    /**
     * YYYYMM 형태의 문자열을 YYYY.MM 형태로 반환한다.
     * 
     * @param ym	YYYYMM 형태의 문자열
     * @return
     */
    public static String toYmString( String ym )
    {
    	return toYmString( ym, '.');
    }
    
    private static boolean isValidDate( int year, int month, int day )
    {
    	if( year <= 0 ) 
			return false;
		if( month < 1 || month > 12 ) 
			return false;
		if( day < 1 || day > MONTHS[month] ) 
			return false;
		if( month == 2 && day == 29 ) 
		{
			if( (year%4) != 0 )	
				return false;
			else if( (year%100)==0 && (year%400)!=0 )	
				return false;
		}

		return true;
    }
    
    private static boolean isValidTime( int hour, int min, int sec )
    {
		if( hour > 23 || min > 59 || sec > 59 ) 
			return false;

		return true;
    }
    
    /**
     * YYYYMM 형태의 문자열을 YYYY(구분자)MM 형태로 반환한다.
     * 
     * @param ym			YYYYMM 형태의 문자열
     * @param delimiter		구분자
     * @return
     */
    public static String toYmString( String ym, char delimiter )
    {
    	if( ym == null || ym.length() != 6 )
    		return ym;

    	StringBuilder build = new StringBuilder( ym );
    	build.insert(4, delimiter);
    	
    	return build.toString();
    }
    
    /**
     * YYYYMMDD 형태의 문자열을 Date로 변환한다.
     * 
     * @param ymd	YYYYMMDD 형태의 문자열
     * @return		올바른 형식이 아니면 null을 반환한다. 올바를 경우, Date type을 반환
     */
    public static Date toYmdDate( String ymd )
    {
    	if( ymd == null || ymd.length() != 8 )
    		return null;
    	
    	int year = Integer.parseInt( ymd.substring(0, 4) );
    	int month = Integer.parseInt( ymd.substring(4, 6));
    	int day = Integer.parseInt( ymd.substring(6, 8) );
    	
    	if( !isValidDate( year, month, day ) )
    		return null;
    	
    	Calendar cal = Calendar.getInstance();
    	cal.set( year, month-1, day);
    	return cal.getTime();
    }
    
    /**
     * YYYYMMDDHHMISS 형태의 문자열을 Date로 변환한다.
     * 
     * @param ymdhms	YYYYMMDDHHMISS 형태의 문자열
     * @return			올바른 형식이 아니면 null을 반환한다. 올바를 경우, Date type을 반환
     */
    public static Date toYmdhmsDate( String ymdhms)
    {
    	if( ymdhms == null || ymdhms.length() != 14 )
    		return null;
    	
    	int year = Integer.parseInt( ymdhms.substring(0, 4) );
    	int month = Integer.parseInt( ymdhms.substring(4, 6));
    	int day = Integer.parseInt( ymdhms.substring(6, 8) );
    	int hour = Integer.parseInt( ymdhms.substring(8, 10) );
    	int min = Integer.parseInt( ymdhms.substring(10, 12) );
    	int sec = Integer.parseInt( ymdhms.substring(12, 14) );
    	
    	if( !isValidDate( year, month, day ) )
    		return null;
    	if( !isValidTime( hour, min, sec ) )
    		return null;
    	
    	Calendar cal = Calendar.getInstance();
    	cal.set( year, month-1, day, hour, min, sec );
    	return cal.getTime();
    }
    
    /**
     * 법인등록번호 형태로 문자열을 반환한다.
     * 
     * @param corpRegNo
     * @return
     */
    public static String toCorpRegNo(String corpRegNo)
    {
        return toCorpRegNo(corpRegNo, '-');
    }

    /**
     * 법인등록번호 형태로 문자열을 반환한다.
     * 
     * @param corpRegNo		법인등록번호
     * @param delimiter		구분자
     * @return
     */
    public static String toCorpRegNo(String corpRegNo, char delimiter)
    {
        if(corpRegNo == null)
            return "";
        
        StringBuilder build = new StringBuilder( corpRegNo );
        if(corpRegNo.length() == 13)
        	build.insert(6, delimiter);

        return build.toString();
    }
    
    
    /**
     * 사업자등록번호 형태로 문자열을 반환한다.
     * 
     * @param bizRegNo
     * @return
     */
    public static String toBizRegNo(String bizRegNo)
    {
        return toBizRegNo(bizRegNo, '-');
    }

    /**
     * 사업자등록번호 형태로 문자열을 반환한다.
     * @param bizRegNo
     * @param delimiter
     * @return
     */
    public static String toBizRegNo(String bizRegNo, char delimiter)
    {
        if( bizRegNo == null )
        	return "";

        // 012345678901234567890
        // 123-45-67890
        
        StringBuilder build = new StringBuilder( bizRegNo );
        if(bizRegNo.length() == 10)
        {
        	build.insert(3, delimiter);
        	build.insert(6, delimiter);
        }

        return build.toString();
    }
    
    public static String toRegNo(String regNo)
    {
    	return toRegNo(regNo, '-');
    }
    
    /**
     * 주민등록번호 형태로 문자열을 반환한다.
     * 
     * @param regNo		주민등록번호
     * @param delimiter		구분자
     * @return
     */
    public static String toRegNo(String regNo, char delimiter)
    {
        if(regNo == null)
            return "";
        
        StringBuilder build = new StringBuilder( regNo );
        if(regNo.length() == 13)
        	build.insert(6, delimiter);

        return build.toString();
    }
    
    public static String toMaskedMobilePhoneNo(String mobilePhoneNo) {
    	
    	mobilePhoneNo = mobilePhoneNo.replaceAll("[^0-9]", "");

		if(mobilePhoneNo == null)
			return "";
		if(mobilePhoneNo.length() == 10) {
			String no1 = mobilePhoneNo.substring(0, 3);
			String no2 = mobilePhoneNo.substring(4, 6);
			String no3 = mobilePhoneNo.substring(6);

			mobilePhoneNo = String.format("%s-X%s-%s", no1, no2, no3);
		} else if(mobilePhoneNo.length() == 11) {
			String no1 = mobilePhoneNo.substring(0, 3);
			String no2 = mobilePhoneNo.substring(5, 7);
			String no3 = mobilePhoneNo.substring(7);

			mobilePhoneNo = String.format("%s-XX%s-%s", no1, no2, no3);
		} else {
			mobilePhoneNo = "서비스 해지";
		}
		
		return mobilePhoneNo;
    }
    
    public static String toMaskedRegNo(String regNo)
    {
    	return toMaskedRegNo(regNo, '-');
    }
    
    public static String toMaskedRegNo(String regNo, char delimiter )
    {
    	regNo = replace(regNo, 6, 12, '*');
    	return toRegNo(regNo, delimiter);
    }
    
    /**
     * 우편번호 형태로 문자열을 반환한다.
     * 
     * @param zipCd		우편번호
     * @return
     */
    public static String toZipCd(String zipCd )
    {
    	if( zipCd == null )
    		return "";
    	if( zipCd.length() < 3 )
    		return zipCd;
    	
    	return zipCd.substring(0, 3) + "-" + zipCd.substring(3);
    }
    
    /**
     * 전화번호 형태로 구분자(-)를 넣어 반환한다.
     * 
     * @param telNo 전화번호
     * @return
     */
    public static String toTelNo(String telNo)
    {
        return toTelNo(telNo, '-');
    }

    /**
     * '*'표로 전화번호 형태로 구분자(delimiter)를 넣어 문자열을 반환한다.
     * 
     * @param telNo			전화번호
     * @param delimiter		구분자
     * @return
     */
    public static String toTelNo(String telNo, char delimiter)
    {
        if(telNo == null)
            return "";
        
        telNo = telNo.replaceAll("[^0-9]", "");
        StringBuilder build = new StringBuilder( telNo );

        if(telNo.length() == 9)
        {
        	build.insert(2, delimiter);
        	build.insert(6, delimiter);
        }
        else if(telNo.length() == 10)
        {
            if(telNo.indexOf("02") == 0)
            {
            	build.insert(2, delimiter);
            	build.insert(7, delimiter);
            }
            else
            {
            	build.insert(3, delimiter);
            	build.insert(7, delimiter);
            }
        } 
        else if(telNo.length() == 11)
        {
        	build.insert(3, delimiter);
        	build.insert(8, delimiter);
        }
        return build.toString();
    }
    
    /**
     * '*'표로 가려진 전화번호 형태로 구분자(-)를 넣어 문자열을 반환한다.
     * 
     * @param telNo		전화번호
     * @param i			번호를 가리기 시작할 문자의 인덱스(0부터 시작)
     * @param j			번호를 가릴 마지막 문자의 인덱스(0부터 시작)
     * @return
     */
    public static String toMaskedTelNo( String telNo, int i, int j )
    {
    	return toMaskedTelNo( telNo, i, j, '-' );
    }
    
    /**
     * '*'표로 가려진 전화번호 형태로 구분자(delimiter)를 넣어 문자열을 반환한다.
     * 
     * @param telNo			전화번호
     * @param i				번호를 가리기 시작할 문자의 인덱스(0부터 시작)
     * @param j				번호를 가릴 마지막 문자의 인덱스(0부터 시작)
     * @param delimiter		구분자
     * @return
     */
    public static String toMaskedTelNo(String telNo, int i, int j, char delimiter)
    {
        if(telNo == null)
            return "";
        
        telNo = replace(telNo, i, j, '*');
        return toTelNo( telNo, delimiter );
    }
    
    /**
     * 카드번호 형태로 구분자(-)를 넣어 문자열을 반환한다.
     * 
     * @param cardNo	카드번호
     * @return
     */
    public static String toCardNo(String cardNo)
    {
        return toCardNo(cardNo, '-');
    }

    /**
     * 카드번호 형태로 구분자(delimiter)를 넣어 문자열을 반환한다.
     * 
     * @param cardNo		카드번호
     * @param delimiter		구분자
     * @return
     */
    public static String toCardNo(String cardNo, char delimiter)
    {
        if(cardNo == null)
            return "";

        int len = cardNo.length();
        StringBuilder build = new StringBuilder( cardNo );
        
        if( len == 16)
        {
        	build.insert(4, delimiter);
        	build.insert(9, delimiter);
        	build.insert(14, delimiter);
        }
        else if( len == 15)
        {
        	build.insert(4, delimiter);
        	build.insert(11, delimiter);
        }
        else if( len == 14)
        {
        	build.insert(6, delimiter);
        	build.insert(9, delimiter);
        }
        
        return build.toString();
    }

    /**
     * '*'표로 가려진 카드번호 형태로 구분자(-)를 넣어 문자열을 반환한다.
     * 
     * @param cardNo	카드번호
     * @param i			번호를 가리기 시작할 문자의 인덱스(0부터 시작)
     * @param j			번호를 가릴 마지막 문자의 인덱스(0부터 시작)
     * @return
     */
    public static String toMaskedCardNo(String cardNo, int i, int j)
    {
    	return toMaskedCardNo(cardNo, i, j, '-');
    }
    
    /**
     * '*'표로 가려진 카드번호 형태로 구분자(delimiter)를 넣어 문자열을 반환한다.
     * 
     * @param cardNo
     * @param i				번호를 가리기 시작할 문자의 인덱스(0부터 시작)
     * @param j				번호를 가릴 마지막 문자의 인덱스(0부터 시작)
     * @param delimiter		구분자
     * @return
     */
    public static String toMaskedCardNo(String cardNo, int i, int j, char delimiter)
    {
        if(cardNo == null)
            return "";
        
        cardNo = replace(cardNo, i, j, '*');
        return toCardNo( cardNo, delimiter );
    }
    
    public static String toCurrency( long num )
    {
    	return NumberFormat.getInstance().format(num);
    }

    /**
     * 입력된 문자열을 size로 줄여서 뒤에 appendStr을 덧붙여 리턴한다.
     * 
     * @param str
     * @param maxLength
     * @param appendedStr
     * @return
     */
    public static String toShortString( String str, int maxLen, String appendedStr )
    {
    	if( str == null || str.length() == 0 )
    		return "";
    	
    	StringBuilder builder = new StringBuilder();
    	int 	totLen = 0;
    	int		i;
    	int		len;
    	char	c;
    	
    	for( i = 0; i < str.length() && totLen < maxLen; i++ )
    	{
    		c = str.charAt(i);
    		len = ( c + "" ).getBytes().length;
    		totLen += (len == 1)? 1 : 2;
    		if( totLen > maxLen )
    			break;
    		
    		builder.append( c );
    	}
    	builder.append(appendedStr);    	
    	return builder.toString();    	
    }
    
    /**
     * 입력된 문자열을 size로 줄여서 뒤에 "..."을 덧붙여 리턴한다.
     * 
     * @param str
     * @param size
     * @return
     */
    public static String toShortString( String str, int maxLen )
    {
    	return toShortString( str, maxLen, "..." );
    }
    
    public static Object nvl( Object value, Object newValue )
    {
    	if( value == null )
    		return newValue;
		return value;
    }
    
    public static String removeTag( String htmlString )
    {
    	return htmlString.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");  
    }
    
    public static Date toLastDate( Date date )
    {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		return cal.getTime();
    }

    public static String toLastYmd( String ym )
    {
    	if( ym == null )
    		return null;
    	
    	String ymd = ym + "01";
    	if( !HelloCheckUtil.isValidYmd(ymd) )
    		return null;
    	
    	Date date = toLastDate( toYmdDate(ymd) );
    	return toDateString(date, "YYYYMMDD");
    }

	public static String subStringBytes(String str, int byteLength) {
		// String 을 byte 길이 만큼 자르기.
		int length = str.length();
		int retLength = 0;
		int tempSize = 0;
		int asc;
		for (int i = 1; i <= length; i++) {
			asc = str.charAt(i - 1);
			if (asc > 127) {
				if (byteLength >= tempSize + 2) {
					tempSize += 2;
					retLength++;
				} else {
					return str.substring(0, retLength);
				}
			} else {
				if (byteLength > tempSize) {
					tempSize++;
					retLength++;
				}
			}
		}
		return str.substring(0, retLength);
	}

    public static void main( String[] args )
    {
    	String message = "[스마트쿠폰] 2개 적립, 주소를 따라가주세요. http://cupon.entbrain.kr/u/Z6pWMh";
    	message = HelloTransUtil.subStringBytes(message, 80);
    	System.out.println(message);
    	
    	String str = "제목에는 이런 것들을 넣어 주세요. 아시겠죠?";
    	System.out.println( HelloTransUtil.toShortString(str, 13, "..."));
    	
    	str = "안1녕@하-";
    	System.out.println( HelloTransUtil.toShortString(str, 13, "..."));
    }
}
