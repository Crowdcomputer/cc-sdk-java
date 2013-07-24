package org.crowdcomputer.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.crowdcomputer.utils.staticvalues.Endpoints;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Snipets {

	/**
	 * @param args
	 */
	private Logger log = LogManager.getLogger(this.getClass());

	public void testLog(){
		log.debug("debug");
		log.info("info");
		log.warn("warn");
		log.error("error");
		log.fatal("fatal");
		log.trace("error");
	}
	
	public void convert(String data){
		log.debug("data {} -> {}", data, ((JSONArray)JSONValue.parse(data)));

	}
	public void create(){
		JSONObject obj = new JSONObject();
		obj.put("a", "a");
		obj.put("b", "a");
		obj.put("c", "a");
		JSONArray arr = new JSONArray();
		arr.add(obj);
		log.debug(arr);
	}

	
	public void pollingBheaviour(String pars1) {
		try {
			System.out.println(pars1);
			Thread.sleep(1000);
			System.out.println(pars1);

			Thread.sleep(5000);

			System.out.println(pars1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		

//		Properties prop = new Properties();
//		try {
//			// set the properties value
//			prop.setProperty("croco_url", "http://localhost:8080");
//
//			// save properties to project root folder
//			prop.store(new FileOutputStream("config.properties"), null);
//
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
		
//		try {
//			prop.load(new FileInputStream("config.properties"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(prop.getProperty("croco_url"));
		new Snipets().testLog();
		new Snipets().create();
		new Snipets().convert("[{lastname=my lastname, birthday=this should not be there, name=my name}] ");

		// Snipets snipets = new Snipets();
		// snipets.pollingBheaviour("test");
		// snipets.date("0y 0mo 0w 365d 0h 0m 0s");
	}

}
