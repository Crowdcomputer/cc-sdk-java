package org.crowdcomputer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.ws.rs.core.UriBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.crowdcomputer.utils.Error;
import org.crowdcomputer.utils.RestCaller;
import org.crowdcomputer.utils.staticvalues.Endpoints;
import org.crowdcomputer.utils.staticvalues.Platforms;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class CroCoClient {
	RestCaller caller = null;
	private Logger log = LogManager.getLogger(this.getClass());

	public CroCoClient(String app_token, String user_token) {
		caller = new RestCaller(app_token, user_token);

	}

	public JSONObject createProcess(String title, String description) {
		HashMap<Object, Object> pars = new HashMap<Object, Object>();
		pars.put("title", title);
		pars.put("description", description);
		return (JSONObject) caller.postCall(
				UriBuilder.fromUri(Endpoints.PROCESS_CREATE).build(), pars);
	}

	public JSONObject createReward(String platform, Double value) {
		HashMap<Object, Object> pars = new HashMap<Object, Object>();
		pars.put("platform", platform);
		pars.put("quantity", value);
		return (JSONObject) caller.postCall(
				UriBuilder.fromUri(Endpoints.REWARD_CREATE).build(), pars);

	}

	private JSONObject createHumanTask(Long process, String title,
			String description, Date deadline, Integer number_of_instances,
			String page_url, String platform, Double reward,
			String reward_platform) {
		
		JSONObject reward_json = createReward(reward_platform, reward);
		Long id_reward = (Long) reward_json.get("id");
		log.debug("reward_id" + id_reward);
		HashMap<Object, Object> pars = new HashMap<Object, Object>();
		pars.put("title", title);
		pars.put("description", description);
		pars.put("reward", id_reward);
		if (deadline != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pars.put("date_deadline", sdf.format(deadline));
		}
		pars.put("number_of_instances", number_of_instances);
		pars.put("page_url", page_url);

		return (JSONObject) caller.postCall(
				UriBuilder.fromUri(Endpoints.CCTASK_CREATE).build(process),
				pars);
	}

	public JSONObject createCCTask(Long process, String title,
			String description, Date deadline, Integer number_of_instances,
			String page_url, Double reward, String reward_platform) {

		return createHumanTask(process, title, description, deadline,
				number_of_instances, page_url, Platforms.CROWDCOMPUTER, reward,
				reward_platform);

	}

	public JSONObject createAMTTask(Long process, String title,
			String description, Date deadline, Integer number_of_instances,
			String page_url, Double reward, String reward_platform) {
		return createHumanTask(process, title, description, deadline,
				number_of_instances, page_url,
				Platforms.AMAZON_MECHANICAL_TURK, reward, reward_platform);

	}

	public JSONObject splitData(String data, String operation) {
		HashMap<Object, Object> pars = new HashMap<Object, Object>();
		pars.put("data", data);
		pars.put("operation", operation);
		return (JSONObject) caller.postCall(UriBuilder.fromUri(Endpoints.DATA_SPLIT).build(), pars);
	}

	public JSONObject mergeData(String data, String field) {
		HashMap<Object, Object> pars = new HashMap<Object, Object>();
		pars.put("data", data);
		pars.put("field", field);
		return (JSONObject) caller.postCall(UriBuilder.fromUri(Endpoints.DATA_MERGE).build(), pars);
	}


	public JSONObject filterData() {
		// TODO: implement
		return Error.createError("not implmented yet");
	}

	public JSONObject splitObject() {
		// TODO: implement
		return Error.createError("not implmented yet");
	}

	public JSONObject filterObject() {
		// TODO: implement
		return Error.createError("not implmented yet");
	}

	public JSONObject startTask(Long id, String data) {
//		string or json?
		HashMap<Object, Object> pars = new HashMap<Object, Object>();
		pars.put("data", ((JSONArray) JSONValue.parse(data)));
		return (JSONObject) caller.putCall(UriBuilder.fromUri(Endpoints.TASK_START).build(id), pars);
	}

	public JSONObject getResult(Long id) {
		return (JSONObject) caller.getCall(UriBuilder.fromUri(Endpoints.TASK_RESULTS).build(id));
	}

	public JSONObject getStatus(Long id) {
		return (JSONObject) caller.getCall(UriBuilder.fromUri(Endpoints.TASK_STATUS).build(id));

	}

}
