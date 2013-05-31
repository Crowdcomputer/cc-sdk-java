package org.crowdcomputer.utils;

import org.json.simple.JSONObject;

public class Error {

	@SuppressWarnings("unchecked")
	public static JSONObject createError(String error){
		JSONObject ret = new JSONObject();
		ret.put("error", error);
		return ret;
	}
}
