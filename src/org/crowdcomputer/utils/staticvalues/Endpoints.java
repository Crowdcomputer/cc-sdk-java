package org.crowdcomputer.utils.staticvalues;

public class Endpoints {
	public static String CROCO = "http://localhost:8000/api/";
	public static String PROCESS_CREATE = CROCO + "process/create/";
	public static String CCTASK_CREATE = CROCO + "process/{arg1}/task/human/create/";
//	public static String AMTTASK_CREATE = CROCO + "task/turk/create/";
	public static String DATA_MERGE = CROCO + "task/data/merge/";
	public static String DATA_SPLIT = CROCO + "task/data/split/";
	public static String REWARD_CREATE = CROCO + "reward/create/";
	public static String TASK_START = CROCO + "task/{arg1}/start/";
	public static String TASK_STATUS = CROCO + "task/{arg1}/status/";
	public static String TASK_RESULTS = CROCO + "task/{arg1}/results/";



}
