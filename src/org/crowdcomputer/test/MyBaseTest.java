package org.crowdcomputer.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.crowdcomputer.utils.Auth;
import org.crowdcomputer.utils.RestCaller;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.Test;


public class MyBaseTest {

	@Test
	public void testGetCorrect() {
		// http://httpbin.org/ip
		String app_id="a";
		String user_id="b";
		RestCaller rc = new RestCaller(new Auth(app_id, user_id));
		JSONObject obj =  (JSONObject) rc.getCall("http://httpbin.org/get");
		JSONObject headers = (JSONObject) obj.get("headers");
		assertEquals("this test for app",headers.get("App-Id"),app_id);
		assertEquals("this test for user",headers.get("Authorization"),"Token "+user_id);
		assertEquals("this is the test for Host", obj.get("url"), "http://httpbin.org/get");
		
	}
	
	@Test
	public void testGetWrong() {
		// http://httpbin.org/ip
		String app_id="a";
		String user_id="b";
		RestCaller rc = new RestCaller(new Auth(app_id, user_id));
		JSONObject obj =  (JSONObject) rc.getCall("http://httpbin.org/get/");
		assertThat("this test for app",""+obj.get("error"),Matchers.containsString("error"));
	}
	
	@Test
	public void testPostCorrect(){
		String app_id="a";
		String user_id="b";
		RestCaller rc = new RestCaller(new Auth(app_id, user_id));
		HashMap<Object, Object> mvhm = new HashMap<Object, Object>();
		mvhm.put("test","test");
		JSONObject obj =  (JSONObject) rc.postCall("http://httpbin.org/post",mvhm);
		JSONObject data = (JSONObject) JSONValue.parse(""+obj.get("data"));
		assertEquals("this is the test for POST", data.get("test"), "test");
		
	}
	
	@Test
	public void testPutCorrect(){
		String app_id="a";
		String user_id="b";
		RestCaller rc = new RestCaller(new Auth(app_id, user_id));
		HashMap<Object, Object> mvhm = new HashMap<Object, Object>();
		mvhm.put("test","test");
		JSONObject obj =  (JSONObject) rc.putCall("http://httpbin.org/put",mvhm);
		JSONObject data = (JSONObject) JSONValue.parse(""+obj.get("data"));
		assertEquals("this is the test for PUT", data.get("test"), "test");
		
	}

}
