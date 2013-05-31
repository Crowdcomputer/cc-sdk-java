package org.crowdcomputer.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

/**
 * This class provides the basics for authentications.
 * 
 * @author Stefano Tranquillini
 */
public class Auth implements ClientRequestFilter{
	private String app_token = "";
	private String user_token = "";

	public Auth(String app, String user) {
		this.app_token = app;
		this.user_token = user;
	}
	
	public void filter(ClientRequestContext crc) throws IOException {
		  	final Map<String, List<Object>> headers = crc.getHeaders();
		  	List<Object> auth=new ArrayList<Object>();
		  	auth.add("Token " + user_token);
	        headers.put("Authorization", auth);
	        List<Object> app=new ArrayList<Object>();
		  	app.add("" + app_token);
	        headers.put("APP_ID", app);
	}
}
