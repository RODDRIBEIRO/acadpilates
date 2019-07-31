package br.com.ribeiro.security.util;

import java.io.IOException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author thiago.soares
 */
public class JacksonUtil {

	private static ObjectMapper mapper;

	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	private JacksonUtil() {
		super();
	}

	private static void initializeMapper() {
		if (mapper == null) {

			mapper = new ObjectMapper();

			mapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX, true);
			mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);

			mapper.setDateFormat(format);

			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			mapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

			mapper.setSerializationInclusion(Include.NON_NULL);
			mapper.setSerializationInclusion(Include.NON_EMPTY);

		}
	}

	public static ObjectMapper getObjectMapper() {
		initializeMapper();
		return mapper;
	}

	public static <T> T fromJson(String json, Class<T> clazz) {
		try {

			initializeMapper();

			return mapper.readValue(json, clazz);

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static String toJson(Object obj) {

		try {

			initializeMapper();

			return mapper.writeValueAsString(obj);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static String toQueryParams(Object obj) {
		initializeMapper();
		return mapper.convertValue(obj, UriFormat.class).toString();
	}

}

class UriFormat {

	private StringBuilder builder = new StringBuilder();

	@JsonAnySetter
	public void addToUri(String name, Object property) {
		if (builder.length() > 0) {
			builder.append("&");
		}
		builder.append(name).append("=").append(property);
	}

	@Override
	public String toString() {
		return builder.toString();
	}
}
