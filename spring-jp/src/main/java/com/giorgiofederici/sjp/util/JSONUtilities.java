package com.giorgiofederici.sjp.util;

import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JSONUtilities {

	private final static Logger LOGGER = Logger.getLogger(JSONUtilities.class);

	public static String JSONListConverter(List<?> listToConvert) throws JsonProcessingException {

		ObjectMapper objectMapper = new ObjectMapper();
		// Set pretty printing of json
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

		String jsonString = objectMapper.writeValueAsString(listToConvert);

		LOGGER.debug("JSONListConverter: " + jsonString);

		return jsonString;
	}

}
