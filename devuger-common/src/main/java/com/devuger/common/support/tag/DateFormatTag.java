package com.devuger.common.support.tag;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class DateFormatTag extends TagSupport
{
	private static final long serialVersionUID = -4523450261010080425L;

	private String format;
	private String dateString;
	private static final String regx = "^[0-9]*$";

	@Override 
	public int doStartTag()
	{
		JspWriter out = pageContext.getOut();
		try {
			SimpleDateFormat fromFormat = new SimpleDateFormat();
			SimpleDateFormat toFormat = new SimpleDateFormat();
			
			Pattern pattern  = Pattern.compile(regx);
			Matcher matcher = pattern.matcher(dateString);

			if(matcher.matches())
			{
				switch (dateString.length()) {
				case 6: {
					fromFormat.applyPattern("yyyymm");
					toFormat.applyPattern("yyyy.mm");

					Date date = fromFormat.parse(dateString);
					out.print(toFormat.format(date));
				} break;
				case 8: {
					fromFormat.applyPattern("yyyymmdd");
					toFormat.applyPattern("yyyy.mm.dd");

					Date date = fromFormat.parse(dateString);
					out.print(toFormat.format(date));
				} break;
				default:
					out.print(dateString);
					break;
				}
			} else {
				out.print(dateString);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SKIP_BODY;
	}

	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

}
