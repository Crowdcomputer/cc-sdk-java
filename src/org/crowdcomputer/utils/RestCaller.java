package org.crowdcomputer.utils;

import java.net.URI;
import java.util.HashMap;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class RestCaller {

	ClientRequestFilter auth = null;
	private Logger log = LogManager.getLogger(this.getClass());

	/**
	 * Init, requires Auth Class instance
	 * 
	 * @param auth
	 */
	public RestCaller(String app_token, String user_token) {
		this.auth = new Auth(app_token,user_token);
	}

	/**
	 * Implements the GET call,
	 * 
	 * @param url
	 *            The URL to call.
	 * @return The answer of the GET call: a JSONObject or JSONArray
	 */
	public Object getCall(URI uri) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(uri);
		target.register(auth);
		Response response = target.request(MediaType.APPLICATION_JSON_TYPE).get();

		if (response.getStatus()<200 || response.getStatus()>=300 ) {
			log.debug("Error " + response.getStatus());
			return Error.createError("HTTP error " + response.getStatus());
		}
		String output = "" + response.readEntity(String.class);
		Object json_output = JSONValue.parse(output);
        if (json_output!=null) {
            log.debug("Output " + json_output.toString());
            return json_output;
        }
        else {
            log.debug("Output empty, code is {}", response.getStatus());
            return JSONValue.parse("{}");
        }

	}

	/**
	 * Implements the POST call.
	 * 
	 * @param url
	 *            The URL where to send the POST.
	 * @param parameters
	 *            the HashMap containing the parameters to send.
	 * @return The answer of the POST call: a JSONObject or JSONArray.
	 */
	@SuppressWarnings("unchecked")
	public Object postCall(URI uri, HashMap<Object, Object> parameters) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(uri);
		target.register(auth);
//		Form form = new Form(parameters);
		JSONObject input = new JSONObject();   
		input.putAll(parameters);
		Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
			    .post(Entity.entity(input.toJSONString(),MediaType.APPLICATION_JSON_TYPE));
		if (response.getStatus()<200 || response.getStatus()>=300 ) {
			log.debug("Error " + response.getStatus());
			return Error.createError("HTTP error " + response.getStatus());
		}
		String output = "" + response.readEntity(String.class);
		Object json_output = JSONValue.parse(output);
        if (json_output!=null) {
            log.debug("Output " + json_output.toString());
            return json_output;
        }
        else {
            log.debug("Output empty, code is {}", response.getStatus());
            return JSONValue.parse("{}");
        }

	}
	

	/**
	 * Implements the PUT call.
	 * 
	 * @param url
	 *            The URL where to send the POST.
	 * @param parameters
	 *            the HashMap containing the parameters to send.
	 * @return The answer of the POST call: a JSONObject or JSONArray.
	 */
	@SuppressWarnings("unchecked")
	public Object putCall(URI uri, HashMap<Object, Object> parameters) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(uri);
		target.register(auth);
//		Form form = new Form(parameters);
		JSONObject input = new JSONObject();   
		input.putAll(parameters);
		Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
			    .put(Entity.entity(input.toJSONString(),MediaType.APPLICATION_JSON_TYPE));
		if (response.getStatus()<200 || response.getStatus()>=300 ) {
			log.debug("Error " + response.getStatus());
			return Error.createError("HTTP error " + response.getStatus());
		}
		String output = "" + response.readEntity(String.class);
		Object json_output = JSONValue.parse(output);
        if (json_output!=null) {
            log.debug("Output " + json_output.toString());
            return json_output;
        }
        else {
            log.debug("Output empty, code is {}", response.getStatus());
            return JSONValue.parse("{}");
        }
	}
}
