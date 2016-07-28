package com.jd.lobo.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.math.NumberUtils;

public final class JerseyUtils {
	private JerseyUtils() {
	}

	private static final byte[] byteArrayObject = new byte[0];

	/**
	 * 从Response对象中读取String内容（UTF-8编码格式），然后关闭与之相关的Client对象。
	 * 
	 */
	public static String readStringAndClose(Response resp, Client client) {

		return readStringAndClose(resp, client, null);
	}

	public static String readStringAndClose(Response resp, Client client,
			String charsetName) {

		if (resp == null) {
			return null;
		}

		if (!resp.hasEntity()) {
			/*if (client != null) {
				client.close();
			}*/
			return null;
		}
		byte[] bytes = resp.readEntity(byteArrayObject.getClass());
		
		/*if (client != null) {
			client.close();
		}*/

		Charset charset = null;
		if (charsetName != null) {
			try {
				charset = Charset.forName(charsetName);
			} catch (Exception e) {
			}
		}

		if (charset == null) {
			try {
				charset = Charset.forName("UTF-8");
			} catch (Exception e) {
			}
		}

		return new String(bytes, charset);
	}

	public static String getIpFromRequest(HttpServletRequest httpServletRequest) {
		String ip = httpServletRequest.getHeader("x-forwarded-for");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = httpServletRequest.getRemoteAddr();
		}

		return ip;
	}

	public static String getCookieFromRequest(
			HttpServletRequest httpServletRequest, String name) {
		Cookie[] cookies = httpServletRequest.getCookies();

		if (cookies == null) {
			return null;
		}

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(name)) {
				return cookie.getValue();
			}
		}

		return null;
	}
	
	public static String getQueryParameterFromRequest(HttpServletRequest httpServletRequest, String paramName){
		return httpServletRequest.getParameter(paramName);
	}
	
	public static byte[] readByteAndClose(Response resp, Client client) {

		if (resp == null) {
			return null;
		}

		if (!resp.hasEntity()) {
			/*if (client != null) {
				client.close();
			}*/
			return null;
		}

		byte[] bytes = resp.readEntity(byteArrayObject.getClass());
		

		/*if (client != null) {
			client.close();
		}*/
		return bytes;
	}

	public static String getStringFromRequest(
			HttpServletRequest httpServletRequest, String name,
			String defaultValue) {
		String str = httpServletRequest.getParameter(name);
		String value = null;
		try {
			if (str != null) {
				byte[] bytes = str.getBytes("ISO-8859-1");
				value = new String(bytes);
			}
		} catch (UnsupportedEncodingException e) {
		}
		return value == null ? defaultValue : value;
	}

	public static double getLongFromRequest(
			HttpServletRequest httpServletRequest, String name,
			double defaultValue) {
		String s = httpServletRequest.getParameter(name);
		return NumberUtils.toDouble(s, defaultValue);
	}

	public static long getLongFromRequest(
			HttpServletRequest httpServletRequest, String name,
			long defaultValue) {
		String s = httpServletRequest.getParameter(name);
		return NumberUtils.toLong(s, defaultValue);
	}

	public static int getIntegerFromRequest(
			HttpServletRequest httpServletRequest, String name, int defaultValue) {
		String s = httpServletRequest.getParameter(name);
		return NumberUtils.toInt(s, defaultValue);
	}

	public static boolean getBooleanFromRequest(
			HttpServletRequest httpServletRequest, String name,
			boolean defaultValue) {
		String s = httpServletRequest.getParameter(name);
		return s == null ? defaultValue : Boolean.parseBoolean(s);
	}

	public static double getDoubleFromRequest(
			HttpServletRequest httpServletRequest, String name,
			double defaultValue) {
		String s = httpServletRequest.getParameter(name);
		return s == null ? defaultValue : Double.parseDouble(s);
	}

}
