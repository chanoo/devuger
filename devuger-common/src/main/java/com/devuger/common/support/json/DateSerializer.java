package com.devuger.common.support.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DateSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date date, JsonGenerator jsonGenerator,
			SerializerProvider serializerProvider) throws IOException,
			JsonProcessingException {
		// TODO Auto-generated method stub
		if(date == null) return;
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String dateString = dateFormat.format(date);
		jsonGenerator.writeString(dateString);
	}
}
