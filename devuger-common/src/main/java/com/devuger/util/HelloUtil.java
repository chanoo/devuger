package com.devuger.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class HelloUtil {
	
	/**
	 * 파일 경로
	 * 
	 * @param date
	 * @param id
	 * @return
	 */
	public static String getAttachmentPath(Date date, Long id) {
		
		String attachmentDirPath = getAttachmentDirPath(date);

		return String.format("/%s/%s", attachmentDirPath, id.toString());
	}

	/**
	 * 파일이 있는 폴더 경로
	 * 
	 * @param date
	 * @return
	 */
	public static String getAttachmentDirPath(Date date) {
		
		GregorianCalendar today = new GregorianCalendar();
		today.setTime(date);
		
		int year  = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH) + 1;

		return String.format("%s/%d/%d", "/data", year, month);
	}

	public static String getIp() {		
		  HttpServletRequest request = null;
          ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
         
          if( attributes != null)
        	  	request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
          
          if(request == null)
        	  throw new IllegalStateException("No servlet context.");    
      
		String remoteAddr = request.getHeader("X-Forwarded-For");
		if (remoteAddr == null)
			remoteAddr = request.getRemoteAddr();
		return remoteAddr; // tomcat이 apache proxy를 이용하기 때문에
							// request.getRemoteAddr()로 못구한다.
	}

	private static final String gvSpecialKey = "*+-./@_";

	public static String formatYMD(String yyyymmdd, String delimiter) {
		return yyyymmdd.substring(0, 4) + delimiter + yyyymmdd.substring(4, 6)
				+ delimiter + yyyymmdd.substring(6, 8);
	}

	public static String replaceHtmlTags(String src, String[] srcTags,
			String[] replTags) {
		StringBuffer buf = new StringBuffer();
		buf.append(src);

		int i = 0;
		while (i < buf.length()) {
			if (buf.charAt(i) == '<') {
				i = replaceHtmlTag(buf, i, srcTags, replTags);
				continue;
			}

			i++;
		}

		return buf.toString();
	}

	private static int replaceHtmlTag(StringBuffer buf, int idx,
			String[] srcTags, String[] replTags) {
		for (int i = 0; i < srcTags.length; i++) {
			if (buf.length() < idx + srcTags[i].length() + 1)
				continue;

			// Start tag 제거
			if (buf.substring(idx, idx + srcTags[i].length() + 1).toLowerCase()
					.equals("<" + srcTags[i].toLowerCase())) {
				int start = idx;
				int end = idx + srcTags[i].length() + 1;
				while (end < buf.length()) {
					if (buf.charAt(end) == '>')
						break;

					end++;
				}

				if (replTags[i].toLowerCase().equals("br")) {
					buf.replace(start, ++end, "<br />");
					return idx + 6;
				} else if (replTags[i].length() > 0) {
					buf.replace(start, ++end, "<" + replTags[i] + ">");
					return idx + replTags[i].length() + 2;
				} else {
					buf.replace(start, ++end, "");
					return idx;
				}
			}
			// End tag 제거
			else if (buf.substring(idx, idx + srcTags[i].length() + 2)
					.toLowerCase().equals("</" + srcTags[i].toLowerCase())) {
				int start = idx;
				int end = idx + srcTags[i].length() + 2;
				while (end < buf.length()) {
					if (buf.charAt(end) == '>')
						break;

					end++;
				}

				if (replTags[i].toLowerCase().equals("br")) {
					buf.replace(start, ++end, "<br />");
					return idx + 6;
				} else if (replTags[i].length() > 0) {
					buf.replace(start, ++end, "</" + replTags[i] + ">");
					return idx + replTags[i].length() + 3;
				} else {
					buf.replace(start, ++end, "");
					return idx;
				}
			}
		}

		return idx + 1;
	}

	public static Object loadInstance(String className)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();

		if (classLoader == null)
			classLoader = HelloUtil.class.getClassLoader();

		Class<?> cls;
		cls = classLoader.loadClass(className);
		Object obj;

		obj = cls.newInstance();
		return obj;
	}

	/**
	 * 
	 * 입력된 fileName을 Class path에서 찾아 Full path name을 리턴한다.
	 * 
	 * @param fileName
	 *            파일명
	 * @return 찾았으면 file의 full path name. 못 찾으면 null
	 */
	@Deprecated
	public static String findFileInClassPath(String fileName) {
		ClassLoader classLoader = HelloUtil.class.getClassLoader();
		URL url = classLoader.getResource(fileName);
		if (url != null)
			return url.getFile();

		return null;
	}

	public static String getResourceFileName(String resourceName)
			throws ClassNotFoundException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();

		if (loader == null)
			loader = ClassLoader.getSystemClassLoader();

		if (loader == null)
			throw new ClassNotFoundException("class loader is not found");

		URL url = loader.getResource(resourceName);
		if (url != null)
			return url.getFile();

		return null;
	}

	public static String readTextResource(String resourceName)
			throws ClassNotFoundException, IOException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();

		if (loader == null)
			loader = ClassLoader.getSystemClassLoader();

		if (loader == null)
			throw new ClassNotFoundException("class loader is not found");

		InputStream stream = loader.getResourceAsStream(resourceName);
		if (stream == null)
			return "Resource not found(" + resourceName + ")";

		byte[] buf = new byte[1024];
		StringBuffer data = new StringBuffer();
		int readSize = 0;

		while ((readSize = stream.read(buf)) > 0)
			data.append(new String(buf, 0, readSize));
		stream.close();
		return data.toString();
	}

	public static String rPad(String src, int size, String padStr) {
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		byte[] bSrc = src.getBytes();
		byte[] bPadStr = padStr.getBytes();

		buf.write(bSrc, 0, bSrc.length);
		while (buf.size() < size)
			buf.write(bPadStr, 0, bPadStr.length);

		return new String(buf.toByteArray(), 0, size);
	}

	public static String lPad(String src, int size, String padStr) {
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		byte[] bSrc = src.getBytes();
		byte[] bPadStr = padStr.getBytes();

		int leftSize = size - bSrc.length;
		if (leftSize < 1)
			return new String(bSrc, 0, size);

		while (buf.size() < leftSize)
			buf.write(bPadStr, 0, bPadStr.length);

		return new String(buf.toByteArray(), 0, leftSize) + src;
	}

	public static String extractFileName(String fullPathName) {
		int len, i;

		len = fullPathName.length() - 1;
		for (i = len; i > -1; i--) {
			if (fullPathName.charAt(i) == '/' || fullPathName.charAt(i) == '\\')
				break;
		}

		return fullPathName.substring(i + 1);
	}

	public static String encodeRequestParam(String value) {
		String colonCode = "%3A";
		String slashCode = "%2F";
		String equalCode = "%3D";
		String empCode = "%26";
		String percentCode = "%25";
		String plusCode = "%2B";
		String ascii13 = "";
		String ascii10 = "%0A";

		String str = value.replaceAll("%", percentCode);
		str = str.replaceAll(":", colonCode);
		str = str.replaceAll("/", slashCode);
		str = str.replaceAll("&", empCode);
		str = str.replaceAll("=", equalCode);
		str = str.replaceAll("\\+", plusCode);
		str = str.replaceAll(" ", "+");
		str = str.replaceAll("\r", ascii13);
		str = str.replaceAll("\n", ascii10);

		return str;
	}

	public static String encodeBase64(String input) {
		String retValue = null;

		try {
			byte[] inputBytes = input.getBytes();
			BASE64Encoder encoder = new BASE64Encoder();
			retValue = encoder.encode(inputBytes).replaceAll("\r", "")
					.replaceAll("\n", "");
		} catch (Exception e) {
		}
		return retValue;
	}

	public static String decodeBase64(String input) {
		String retValue = null;
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			retValue = new String(decoder.decodeBuffer(input));
		} catch (Exception e) {
		}
		return retValue;
	}

	public static String encodeBase64(String input, String charSet) {
		String retValue = null;

		try {
			byte[] inputBytes = input.getBytes(charSet);
			BASE64Encoder encoder = new BASE64Encoder();
			retValue = encoder.encode(inputBytes).replaceAll("\r", "")
					.replaceAll("\n", "");
		} catch (Exception e) {
		}
		return retValue;
	}

	public static String decodeBase64(String input, String charSet) {
		String retValue = null;
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			retValue = new String(decoder.decodeBuffer(input), charSet);
		} catch (Exception e) {
		}
		return retValue;
	}

	public static String escapeJavascript(String s) {
		String retValue = "";
		String value = null;
		char[] letter = s.toCharArray();
		for (int j = 0; j < letter.length; j++) {
			if (gvSpecialKey.indexOf(letter[j]) > -1)
				value = letter[j] + "";
			else if (letter[j] >= '0' && letter[j] <= '9')
				value = letter[j] + "";
			else if (letter[j] >= 'A' && letter[j] <= 'Z')
				value = letter[j] + "";
			else if (letter[j] >= 'a' && letter[j] <= 'z')
				value = letter[j] + "";
			else {
				value = Integer.toHexString(letter[j]);
				value = value.toUpperCase();
				if (value.length() > 2)
					value = "%u" + value;
				else
					value = "%" + value;
			}
			retValue = retValue + value;
		}
		return retValue;
	}

	public static String unescapeJavascript(String s) {
		String letter = "";
		char c;
		int i = 0;
		int len = s.length();

		while (i < len) {
			c = s.charAt(i);
			if (c != '%') {
				letter += c;
				i++;
			} else {
				c = s.charAt(++i);
				if (c == 'u') {
					letter += (char) Integer.parseInt(
							s.substring(i + 1, i + 5), 16);
					i += 5;
				} else {
					letter += (char) Integer
							.parseInt(s.substring(i, i + 2), 16);
					i += 2;
				}
			}
		}

		return letter;
	}

	public static String escapeString(String s) {
		s = s.replaceAll("&", "&amp;");
		s = s.replaceAll("<", "&lt;");
		s = s.replaceAll(">", "&gt;");
		s = s.replaceAll("'", "&#39;");
		s = s.replaceAll("\"", "&quot;");
		s = s.replaceAll("\\x28", "&#40;");
		s = s.replaceAll("\\x29", "&#41;");
		return s;
	}

	public static String charsetEncode(String str, String charsetName)
			throws UnsupportedEncodingException {
		if (str == null)
			return null;

		return charsetEncode(str, null, charsetName);
	}

	public static String charsetEncode(String str, String orgCharsetName,
			String newCharsetName) throws UnsupportedEncodingException {
		if (str == null)
			return null;

		if (orgCharsetName == null || orgCharsetName.length() == 0)
			return new String(str.getBytes(), newCharsetName);

		return new String(str.getBytes(orgCharsetName), newCharsetName);
	}

	public static Object deepCopy(Object orig) {
		Object obj = null;
		try {
			// Write the object out to a byte array
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(orig);
			out.flush();
			out.close();

			// Make an input stream from the byte array and read
			// a copy of the object back in.
			ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
			obj = in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		return obj;
	}

	/**
	 * MD5 Digest
	 * 
	 * @param is
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public static String getMD5Sum(InputStream is)
			throws NoSuchAlgorithmException, IOException {

		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] buffer = new byte[8192];
		int read = 0;
		while ((read = is.read(buffer)) > 0) {
			md.update(buffer, 0, read);
		}

		byte[] md5sum = md.digest();
		BigInteger bigInt = new BigInteger(1, md5sum);

		return bigInt.toString(16);
	}

	/** @return lexical similarity value in the range [0,1] */
	public static double compareStrings(String str1, String str2) {
		ArrayList<String> pairs1 = wordLetterPairs(str1.toUpperCase());
		ArrayList<String> pairs2 = wordLetterPairs(str2.toUpperCase());
		int intersection = 0;
		int union = pairs1.size() + pairs2.size();
		for (int i = 0; i < pairs1.size(); i++) {
			Object pair1 = pairs1.get(i);
			for (int j = 0; j < pairs2.size(); j++) {
				Object pair2 = pairs2.get(j);
				if (pair1.equals(pair2)) {
					intersection++;
					pairs2.remove(j);
					break;
				}
			}
		}
		return (2.0 * intersection) / union;
	}

	/** @return an ArrayList of 2-character Strings. */
	private static ArrayList<String> wordLetterPairs(String str) {
		ArrayList<String> allPairs = new ArrayList<String>();
		// Tokenize the string and put the tokens/words into an array
		String[] words = str.split("\\s");
		// For each word
		for (int w = 0; w < words.length; w++) {
			// Find the pairs of characters
			String[] pairsInWord = letterPairs(words[w]);
			for (int p = 0; p < pairsInWord.length; p++) {
				allPairs.add(pairsInWord[p]);
			}
		}
		return allPairs;
	}

	private static String[] letterPairs(String str) {
		int numPairs = str.length() - 1;
		String[] pairs = new String[numPairs];
		for (int i = 0; i < numPairs; i++) {
			pairs[i] = str.substring(i, i + 2);
		}
		return pairs;
	}
}
