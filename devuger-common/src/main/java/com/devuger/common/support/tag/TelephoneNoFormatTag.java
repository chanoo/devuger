package com.devuger.common.support.tag;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

public class TelephoneNoFormatTag extends TagSupport
{
	private static final long serialVersionUID = -4523450261010080425L;

	private transient static Logger logger = Logger.getLogger(TelephoneNoFormatTag.class);

	private String phoneNo;
	private static final String REGX = "[^0-9]";

	@Override 
	public int doStartTag()
	{
		JspWriter out = pageContext.getOut();
		try {
			phoneNo = phoneNo.replaceAll(REGX, "");

			String[] phoneNos = new String[3];
			if(phoneNo.length() == 10) {
				
				if(phoneNo.startsWith("02")) {

					phoneNos[0] = phoneNo.substring(0, 2);
					phoneNos[1] = phoneNo.substring(2, 5);
					phoneNos[2] = phoneNo.substring(5);

					out.print(String.format("%s-%s-%s", phoneNos[0], phoneNos[1], phoneNos[2]));
					
				} else {
					
					phoneNos[0] = phoneNo.substring(0, 3);
					phoneNos[1] = phoneNo.substring(3, 6);
					phoneNos[2] = phoneNo.substring(6);

					out.print(String.format("%s-%s-%s", phoneNos[0], phoneNos[1], phoneNos[2]));

				}

			} else if(phoneNo.length() == 11) {
				
				phoneNos[0] = phoneNo.substring(0, 3);
				phoneNos[1] = phoneNo.substring(3, 7);
				phoneNos[2] = phoneNo.substring(7);

				out.print(String.format("%s-%s-%s", phoneNos[0], phoneNos[1], phoneNos[2]));
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		}

		return SKIP_BODY;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
}
