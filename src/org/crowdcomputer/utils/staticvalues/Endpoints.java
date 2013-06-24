package org.crowdcomputer.utils.staticvalues;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Endpoints {
//	private static final Properties prop = loadProperties();
//	public static String CROCO = prop.getProperty("croco.base.url", "");
	public static String CROCO = "http://localhost:8000/api/";

	public static String PROCESS_CREATE = CROCO + "process/create/";
	public static String CCTASK_CREATE = CROCO
			+ "process/{arg1}/task/human/create/";
	// public static String AMTTASK_CREATE = CROCO + "task/turk/create/";
	public static String DATA_MERGE = CROCO + "task/data/merge/";
	public static String DATA_SPLIT = CROCO + "task/data/split/";
	public static String DATA_FILTER = CROCO + "task/data/filter/";
	public static String OBJECT_SPLIT = CROCO + "task/object/split/";
	public static String OBJECT_MERGE = CROCO + "task/object/merge/";

	public static String REWARD_CREATE = CROCO + "reward/create/";
	public static String TASK_START = CROCO + "task/{arg1}/start/";
	public static String TASK_STATUS = CROCO + "task/{arg1}/status/";
	public static String TASK_RESULTS = CROCO + "task/{arg1}/results/";

//	private static final Properties loadProperties() {
//		System.out.println("load");
//		Properties testProperties = new Properties();
//		FileInputStream testPropertiesInput;
//		try {
//			testPropertiesInput = new FileInputStream("./croco-sdk.properties");
//			if (null != testPropertiesInput) {
//				try {
//					try {
//						testProperties.load(testPropertiesInput);
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				} finally {
//					try {
//						testPropertiesInput.close();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		return testProperties;
//
//	}

}
