package com.devuger.common.support.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class PhoneNoSerializer extends JsonSerializer<String> {

	@Override
	public void serialize(String mobilePhoneNo, JsonGenerator jsonGenerator,
			SerializerProvider serializerProvider) throws IOException,
			JsonProcessingException {
		// TODO Auto-generated method stub
		if(mobilePhoneNo == null)
			return;
		
		if(mobilePhoneNo.length() == 9) {

			String no1 = mobilePhoneNo.substring(0, 2);
			String no2 = mobilePhoneNo.substring(2, 5);
			String no3 = mobilePhoneNo.substring(5);

			mobilePhoneNo = String.format("%s-%s-%s", no1, no2, no3);

		} else if(mobilePhoneNo.length() == 10) {

			String no1 = mobilePhoneNo.substring(0, 3);
			String no2 = mobilePhoneNo.substring(3, 6);
			String no3 = mobilePhoneNo.substring(6);

			if(mobilePhoneNo.indexOf("02") > 0) {

				no1 = mobilePhoneNo.substring(0, 2);
				no2 = mobilePhoneNo.substring(2, 6);
				no3 = mobilePhoneNo.substring(6);

			}

			mobilePhoneNo = String.format("%s-%s-%s", no1, no2, no3);
		} else if(mobilePhoneNo.length() == 11) {
			String no1 = mobilePhoneNo.substring(0, 3);
			String no2 = mobilePhoneNo.substring(3, 7);
			String no3 = mobilePhoneNo.substring(7);

			mobilePhoneNo = String.format("%s-%s-%s", no1, no2, no3);
		}
		jsonGenerator.writeString(mobilePhoneNo);
	}
}
