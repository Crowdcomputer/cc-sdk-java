package org.crowdcomputer.test;

import org.crowdcomputer.utils.staticvalues.Endpoints;

public class Snipets {

	/**
	 * @param args
	 */

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
		System.out.println(Endpoints.DATA_MERGE);
		System.out.println(Endpoints.DATA_SPLIT);
		System.out.println(Endpoints.DATA_MERGE);

		// Snipets snipets = new Snipets();
		// snipets.pollingBheaviour("test");
		// snipets.date("0y 0mo 0w 365d 0h 0m 0s");
	}

}
