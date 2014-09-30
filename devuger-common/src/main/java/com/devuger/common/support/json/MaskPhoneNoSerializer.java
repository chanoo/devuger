package com.devuger.common.support.json;

import java.io.IOException;

import com.devuger.util.HelloTransUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class MaskPhoneNoSerializer extends JsonSerializer<String> {

	@Override
	public void serialize(String mobilePhoneNo, JsonGenerator jsonGenerator,
			SerializerProvider serializerProvider) throws IOException,
			JsonProcessingException {
		// TODO Auto-generated method stub
		jsonGenerator.writeString(HelloTransUtil.toMaskedMobilePhoneNo(mobilePhoneNo));
	}
}
