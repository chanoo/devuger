package com.allat.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class AllatAPI {

	private static final String G_LANG	= "JAVA";
	private static final String G_VER	= "1.0.3";
	private static final String G_HOST	= "tx.allat.co.kr";
	private static final int    G_HTTPS	= 443;

	//---------------------[ Send SMS ] ---------------------//
	public HashMap<String, String> reqCall( HashMap<String, String> hmReq ) {
		HashMap<String, String> hmRet = null;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

		try {

			hmReq.put("lang", G_LANG);
			hmReq.put("ver", G_VER);
			hmReq.put("req_date", formatter.format(cal.getTime()) );
			String sReqMsg = setValue( hmReq );
			hmRet = getValue( sendReq(sReqMsg) );

		} catch( Exception e ) {
			hmRet = getValue("ret_cd=1000\nret_msg=Exception:" + e.getMessage() + "\n");
		}

		return hmRet;
	}

	//---------------------[ Connect & Send Client Data ] ---------------------//
	private String sendReq( String sReqMsg ) {
		StringBuffer sSendMsg = new StringBuffer(1024);
		StringBuffer sResult = new StringBuffer(1024);
		String sBuffer;

		SSLSocketFactory sslFactory = null;
		SSLSocket sslSoc = null;
		PrintWriter sOut = null;
		BufferedReader sIn = null;

		try {

			sslFactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
			sslSoc = (SSLSocket)sslFactory.createSocket(G_HOST, G_HTTPS);
			sOut = new PrintWriter(sslSoc.getOutputStream(), true);
			sIn  = new BufferedReader(new InputStreamReader(sslSoc.getInputStream()));

		} catch( UnknownHostException ue ) {
			return ("ret_cd=1021\nret_msg=Exception:"+ue.getMessage()+"\n");
		} catch( IOException ie ) {
			return ("ret_cd=1022\nret_msg=Exception:"+ie.getMessage()+"\n");
		} catch( Exception e ) {
			return ("ret_cd=1023\nret_msg=Exception:"+e.getMessage()+"\n");
		}

		try {

			sSendMsg.append("POST /api HTTP/1.0\r\n")
					.append("Host: ").append(G_HOST).append(':').append(G_HTTPS).append("\r\n")
					.append("Content-type: application/x-www-form-urlencoded\r\n")
					.append("Content-length: ").append(sReqMsg.getBytes().length).append("\r\n")
					.append("Accept: */*\r\n")
					.append("\r\n")
					.append(sReqMsg).append("\r\n")
					.append("\r\n");
			sOut.println(sSendMsg.toString());

			// Skip Header
			while( (sBuffer = sIn.readLine()).length() != 0 );

			while( (sBuffer = sIn.readLine()) != null ) {
				sResult.append(sBuffer).append("\n");
			}

		} catch( Exception e ) {
			return ("ret_cd=1020\n"+"ret_msg=Socket Connect Error:"+e.getMessage()+"\n");
		} finally {
			try {
				if( sOut != null ) sOut.close();
				if( sIn != null ) sIn.close();
				if( sslSoc != null ) sslSoc.close();
			} catch( Exception ef ) {}
		}
		return sResult.toString();
	}

	//---------------------[ Result Text Convert To Hash ] ---------------------//
	private HashMap getValue( String sResultText ) {
		HashMap hmRet = new HashMap();
		String sKey = null, sVal = null;

		StringTokenizer sRowToken = new StringTokenizer(sResultText,"\n");
		while( sRowToken != null && sRowToken.hasMoreTokens() ) {
			String sTmpToken = sRowToken.nextToken();
			if( sTmpToken == null || sTmpToken.equals("null") ) continue;
			StringTokenizer sColToken = new StringTokenizer(sTmpToken,"=");
			for( int i=0; i<2; i++ ) {
				if( i == 0 ) {
					if( sColToken.hasMoreTokens() ) sKey = sColToken.nextToken().trim();
					else	sKey = "";
				} else {
					if( sColToken.hasMoreTokens() ) sVal = sColToken.nextToken().trim();
					else	sVal = "";
				}
			}
			hmRet.put(sKey,sVal);
		}

		if( hmRet.get("ret_cd") == null ) {
			hmRet.put("ret_cd", "1010");
			if( sResultText == null || sResultText.equals("") )
				hmRet.put("ret_msg", "결과에 대한 정보가 없습니다.");
			else
				hmRet.put("ret_msg", sResultText);
		}
		return hmRet;
	}

	//--------------------- [ Hash To Req Msg ] ---------------------//
	private String setValue( HashMap hm ) throws UnsupportedEncodingException {
		StringBuffer sResult = new StringBuffer(1024);
		String sKey;

		if( hm == null )	return null;

		Iterator ir = hm.keySet().iterator();
		sResult.append("head=00000000&");
		while( ir.hasNext() ) {
			sKey = (String)ir.next();
			sResult.append(sKey).append('=').append(URLEncoder.encode((String)hm.get(sKey), "euc-kr")).append('&');
		}

		return sResult.toString();
	}
}
