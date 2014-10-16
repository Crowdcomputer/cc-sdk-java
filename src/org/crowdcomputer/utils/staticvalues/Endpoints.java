package org.crowdcomputer.utils.staticvalues;

public class Endpoints {
    // private static final Properties prop = loadProperties();
    // public static String CROCO = prop.getProperty("croco.base.url", "");
    public static String CROCO = "http://localhost:8000/api/";
    //	public static String CROCO = "http://new.crowdcomputer.org/api/";
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
    public static String VALIDATE = CROCO + "taskinstance/{arg1}/validate/";


    public static String T_CROCO = "http://localhost:8000/api2/";

    //    public static String T_CROCO = "http://new.crowdcomputer.org/api2/";
    public static String T_TASK_START = T_CROCO + "task/{arg1}/start/";
    public static String T_TASK_STOP = T_CROCO + "task/{arg1}/stop/";
    public static String T_TASK_FINISH = T_CROCO + "task/{arg1}/finish/";
    public static String T_INSTANCE = T_CROCO + "task/{arg1}/instance/";
    public static String T_INSTANCE_START = T_CROCO + "task/{arg1}/instance/{arg2}/start/";
    public static String T_INSTANCE_STOP = T_CROCO + "task/{arg1}/instance/{arg2}/stop/";
    public static String T_QUALITY_SET = T_CROCO + "task/{arg1}/instance/{arg2}/quality_set/";
    public static String T_QUALITY_GET = T_CROCO + "task/{arg1}/instance/{arg2}/quality_get/";
    public static String T_REWARD_REJECT = T_CROCO + "task/{arg1}/instance/{arg2}/reward_reject/";
    public static String T_REWARD_GIVE = T_CROCO + "task/{arg1}/instance/{arg2}/reward_give/";
    // private static final Properties loadProperties() {
    //
    // Properties testProperties = new Properties();
    // InputStream testPropertiesInput;
    // try {
    //
    //
    // testPropertiesInput =
    // Endpoints.class.getResourceAsStream("/croco-sdk.properties");
    // if (null != testPropertiesInput) {
    // try {
    // try {
    // testProperties.load(testPropertiesInput);
    // } catch (IOException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // } finally {
    // try {
    // testPropertiesInput.close();
    // } catch (IOException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // }
    // }
    // } catch (Exception e1) {
    // // TODO Auto-generated catch block
    // e1.printStackTrace();
    // testProperties.setProperty("croco.base.url", "http://localhost:8080/");
    // }
    // return testProperties;
    //
    // }

}
