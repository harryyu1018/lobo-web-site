package com.jd.lobo.util;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JsonUtils {
	private JsonUtils() {
	}

	static private Logger logger = LoggerFactory.getLogger(JsonUtils.class);

	static private ObjectMapper mapper;

	static {
		mapper = new ObjectMapper();

		mapper.configure(
				DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(
				DeserializationConfig.Feature.FAIL_ON_NULL_FOR_PRIMITIVES,
				false);
		mapper.configure(
				DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
				true);

		// mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, false);
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		// mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS,
		// true);
		// mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);

		mapper.setSerializationInclusion(Inclusion.NON_NULL);
	}

	static public String writeString(Object obj) {
		if (obj == null) {
			return null;
		}

		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}

		return null;
	}

	static public JsonNode readTree(String json) {
		if (json == null) {
			return null;
		}

		try {
			return mapper.readTree(json);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}

		return null;
	}

	static public <T> T readObject(JsonNode node, Class<T> objectClass) {
		if (node == null) {
			return null;
		}

		try {
			return mapper.readValue(node, objectClass);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}

		return null;
	}

	static public <T> T readObject(JsonNode node, TypeReference<T> typeRef) {
		if (node == null) {
			return null;
		}

		try {
			return mapper.readValue(node, typeRef);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}

		return null;
	}

	static public <T> T readObject(String json, Class<T> objectClass) {
		if (json == null) {
			return null;
		}

		try {
			return mapper.readValue(json, objectClass);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}

		return null;
	}

	static public <T> T readObject(String json, TypeReference<T> typeRef) {
		if (json == null) {
			return null;
		}

		try {
			return mapper.readValue(json, typeRef);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}

		return null;
	}

}
