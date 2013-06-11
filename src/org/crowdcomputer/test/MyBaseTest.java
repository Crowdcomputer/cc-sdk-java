package org.crowdcomputer.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.HashMap;

import javax.ws.rs.core.UriBuilder;

import org.crowdcomputer.CroCoClient;
import org.crowdcomputer.utils.RestCaller;
import org.crowdcomputer.utils.staticvalues.Platforms;
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
		RestCaller rc = new RestCaller(app_id, user_id);
		JSONObject obj =  (JSONObject) rc.getCall(UriBuilder.fromUri("http://httpbin.org/get").build());
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
		RestCaller rc = new RestCaller(app_id, user_id);
		JSONObject obj =  (JSONObject) rc.getCall(UriBuilder.fromUri("http://httpbin.org/get").build());
		assertThat("this test for app",""+obj.get("error"),Matchers.containsString("error"));
	}
	
	@Test
	public void testPostCorrect(){
		String app_id="a";
		String user_id="b";
		RestCaller rc = new RestCaller(app_id, user_id);
		HashMap<Object, Object> mvhm = new HashMap<Object, Object>();
		mvhm.put("test","test");
		JSONObject obj =  (JSONObject) rc.postCall(UriBuilder.fromUri("http://httpbin.org/post/").build(),mvhm);
		JSONObject data = (JSONObject) JSONValue.parse(""+obj.get("data"));
		assertEquals("this is the test for POST", data.get("test"), "test");
		
	}
	
	@Test
	public void testPutCorrect(){
		String app_id="a";
		String user_id="b";
		RestCaller rc = new RestCaller(app_id, user_id);
		HashMap<Object, Object> mvhm = new HashMap<Object, Object>();
		mvhm.put("test","test");
		JSONObject obj =  (JSONObject) rc.putCall(UriBuilder.fromUri("http://httpbin.org/put/").build(),mvhm);
		JSONObject data = (JSONObject) JSONValue.parse(""+obj.get("data"));
		assertEquals("this is the test for PUT", data.get("test"), "test");
		
	}
	@Test
	public void testReward(){
		String app_id="b88a23b3e50947179955fc8b7579c1bd	";
		String user_id="6c4714e14abcaf3bdd2f7798b865f9c530d35b1b";
		CroCoClient cclient = new CroCoClient(app_id, user_id);
		JSONObject obj = cclient.createReward("CCM", 1.0);
		assertEquals(1.0,Double.parseDouble(""+obj.get("quantity")),0.0);	
	}
	
	@Test
	public void testTask(){
		String app_id="b88a23b3e50947179955fc8b7579c1bd";
		String user_id="859848836298bd8ff7b9f106bf2aa1a30678f5c7";
		CroCoClient cclient = new CroCoClient(app_id, user_id);
		JSONObject obj_process = cclient.createProcess("process_api", "description");
		Long pk = (Long) obj_process.get("id");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 7);
		JSONObject obj_task = cclient.createCCTask(pk,"title", "description", cal.getTime() , 1, "http://example.com", 1.0, Platforms.CROWDCOMPUTER);
		assertEquals("title is the same?","title",obj_task.get("title"));	
	}
	@Test
	public void testTaskWithoutDeadline(){
		String app_id="9514e07816194cae9d0531aa5fbb9c9c	";
		String user_id="859848836298bd8ff7b9f106bf2aa1a30678f5c7";
		CroCoClient cclient = new CroCoClient(app_id, user_id);
		JSONObject obj_process = cclient.createProcess("process_api", "description");
		Long pk = (Long) obj_process.get("id");
		JSONObject obj_task = cclient.createCCTask(pk,"title", "description", null , 1, "http://example.com", 1.0, Platforms.CROWDCOMPUTER);
		assertEquals("title is the same?","title",obj_task.get("title"));	
	}
	@Test
	public void testStartTask(){
		String app_id="8e60300e58924e898535ffa57155175f";
		String user_id="6c4714e14abcaf3bdd2f7798b865f9c530d35b1b";
		CroCoClient cclient = new CroCoClient(app_id, user_id);
		JSONObject start_task = cclient.startTask(20L, "[]");
		System.out.println(start_task);
		String result = ""+start_task.get("result");
//		JSONObject obj_task = cclient.createCCTask(pk,"title", "description", null , 1, "http://example.com", 1.0, Platforms.CROWDCOMPUTER);
		assertEquals("title is the same?","ok",result);	
	}
}
