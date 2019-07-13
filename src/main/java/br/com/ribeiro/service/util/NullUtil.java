package br.com.ribeiro.service.util;

import java.time.Instant;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * Utility class for generating random Strings.
 */
public final class NullUtil {

	public static Boolean isEmpty(String data) {
		return StringUtils.isEmpty(data);
	}

	public static Boolean isNotEmpty(String data) {
		return StringUtils.isNotEmpty(data);
	}

	public static Boolean isEmpty(List<?> data) {
		return data == null || data.isEmpty();
	}

	public static Boolean isNotEmpty(List<?> data) {
		return !isEmpty(data);
	}

	public static Boolean isNotEmpty(Long data) {
		return data != null;
	}

	public static Boolean isEmpty(Long data) {
		return data == null;
	}

	public static Boolean isNotEmpty(Integer data) {
		return data != null;
	}

	public static Boolean isEmpty(Integer data) {
		return !isNotEmpty(data);
	}

	public static Boolean isNotEmpty(Float data) {
		return data != null;
	}

	public static Boolean isEmpty(Float data) {
		return !isNotEmpty(data);
	}

	public static Boolean isNotEmpty(Double data) {
		return data != null;
	}

	public static Boolean isEmpty(Double data) {
		return data == null;
	}

	public static Boolean isNotEmpty(Boolean data) {
		return data != null;
	}

	public static Boolean isNotEmpty(Instant data) {
		return data != null;
	}

	public static Boolean isEmpty(Instant data) {
		return !isNotEmpty(data);
	}
}