package org.crowdcomputer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.UriBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.crowdcomputer.utils.Error;
import org.crowdcomputer.utils.RestCaller;
import org.crowdcomputer.utils.staticvalues.Endpoints;
import org.crowdcomputer.utils.staticvalues.Platforms;
import org.crowdcomputer.utils.staticvalues.RewardPlatforms;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class CroCoClient {
	RestCaller caller = null;
	private Logger log = LogManager.getLogger(this.getClass());

	public CroCoClient(String app_token, String user_token) {
		log.debug("URL is " + Endpoints.CROCO);
		caller = new RestCaller(app_token, user_token);

	}

	public JSONObject createProcess(String title, String description) {
		HashMap<Object, Object> pars = new HashMap<Object, Object>();
		pars.put("title", title);
		pars.put("description", description);
		return (JSONObject) caller.postCall(
				UriBuilder.fromUri(Endpoints.PROCESS_CREATE).build(), pars);
	}

	public JSONObject createReward(String platform, Double value,
			String reward_strategy) {
		HashMap<Object, Object> pars = new HashMap<Object, Object>();
		pars.put("platform", platform);
		pars.put("quantity", value);
		pars.put("strategy", reward_strategy);
		return (JSONObject) caller.postCall(
				UriBuilder.fromUri(Endpoints.REWARD_CREATE).build(), pars);

	}

	@SuppressWarnings("unchecked")
	private JSONObject createHumanTask(Long process, String title,
			String description, Date deadline, Integer number_of_instances,
			String page_url, String platform, Double reward,
			String reward_strategy, String validation_process,
			String reward_platform,
			@SuppressWarnings("rawtypes") HashMap parameters) {

		JSONObject reward_json = createReward(reward_platform, reward,
				reward_strategy);
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
		pars.put("platform", platform);
		pars.put("validation", validation_process);
		if (parameters != null) {
			JSONObject parameters_json = new JSONObject();
			parameters_json.putAll(parameters);
			pars.put("parameters", parameters_json.toJSONString());
		} else
			pars.put("parameters", "{}");

		return (JSONObject) caller.postCall(
				UriBuilder.fromUri(Endpoints.CCTASK_CREATE).build(process),
				pars);
	}

	public JSONObject validate(Long task_instances, Object validation) {
		HashMap<Object, Object> pars = new HashMap<Object, Object>();
		pars.put("validation", validation);
		return (JSONObject) caller.putCall(
				UriBuilder.fromUri(Endpoints.VALIDATE).build(task_instances),
				pars);
	}

	public JSONObject createCCTask(Long process, String title,
			String description, Date deadline, Integer number_of_instances,
			String page_url, Double reward, String reward_platform,
			String reward_strategy, String validation_process, HashMap pars) {

		return createHumanTask(process, title, description, deadline,
				number_of_instances, page_url, Platforms.CROWDCOMPUTER, reward,
				reward_strategy, validation_process, reward_platform, pars);

	}

	public JSONObject createAMTTask(Long process, String title,
			String description, Date deadline, Integer number_of_instances,
			String page_url, Double reward, String reward_strategy,
			String validation_process, HashMap pars) {
		return createHumanTask(process, title, description, deadline,
				number_of_instances, page_url,
				Platforms.AMAZON_MECHANICAL_TURK, reward, reward_strategy,
				validation_process, RewardPlatforms.DOLLARS, pars);

	}

	public JSONObject splitData(String data, String operation, String N,
			String M) {
		HashMap<Object, Object> pars = new HashMap<Object, Object>();
		pars.put("data", data);
		pars.put("operation", operation);
		pars.put("n", N);
		pars.put("m", M);
		log.debug("data is : " + data);
		return (JSONObject) caller.postCall(
				UriBuilder.fromUri(Endpoints.DATA_SPLIT).build(), pars);
	}

	public JSONObject mergeData(String data) {
		HashMap<Object, Object> pars = new HashMap<Object, Object>();
		pars.put("data", data);
		// pars.put("field", field);
		return (JSONObject) caller.postCall(
				UriBuilder.fromUri(Endpoints.DATA_MERGE).build(), pars);
	}

	public JSONObject joinObject(String data, String field) {
		HashMap<Object, Object> pars = new HashMap<Object, Object>();
		pars.put("data", data);
		pars.put("field", field);
		log.debug("data : " + data);
		log.debug("field: " + field);
		return (JSONObject) caller.postCall(
				UriBuilder.fromUri(Endpoints.OBJECT_MERGE).build(), pars);
	}

	@SuppressWarnings("unchecked")
	public JSONObject splitObject(String data, List<String> shared,
			List<String> fields) {
		HashMap<Object, Object> pars = new HashMap<Object, Object>();
		pars.put("data", data);
		JSONArray fiedls_js = new JSONArray();
		fiedls_js.addAll(fields);
		pars.put("fields", fiedls_js.toJSONString());
		JSONArray shared_js = new JSONArray();
		shared_js.addAll(shared);
		pars.put("shared", shared_js);
		return (JSONObject) caller.postCall(
				UriBuilder.fromUri(Endpoints.OBJECT_SPLIT).build(), pars);
	}

	public JSONObject mergeObject(String data, String field) {
		HashMap<Object, Object> pars = new HashMap<Object, Object>();
		pars.put("data", data);
		pars.put("field", field);
		return (JSONObject) caller.postCall(
				UriBuilder.fromUri(Endpoints.OBJECT_MERGE).build(), pars);
	}

	@SuppressWarnings("unchecked")
	public JSONObject filterData(String data, String field, String operator,
			String value) {
		HashMap<Object, Object> pars = new HashMap<Object, Object>();
		pars.put("data", data);
		HashMap<Object, Object> condition = new HashMap<Object, Object>();
		condition.put("field", field);
		condition.put("operator", operator);
		condition.put("value", value);
		JSONArray conditions = new JSONArray();
		JSONObject condition_js = new JSONObject();
		condition_js.putAll(condition);
		conditions.add(condition_js);
		pars.put("conditions", conditions.toJSONString());
		return (JSONObject) caller.postCall(
				UriBuilder.fromUri(Endpoints.DATA_FILTER).build(), pars);
	}

	public JSONObject startTask(Long id, String data, String name) {
		// string or json?
		HashMap<Object, Object> pars = new HashMap<Object, Object>();
		log.debug("data {} -> {}", data, ((JSONArray)JSONValue.parse(data)));
		pars.put("data", ((JSONArray)JSONValue.parse(data)).toJSONString());
		pars.put("name", name);
		return (JSONObject) caller.putCall(
				UriBuilder.fromUri(Endpoints.TASK_START).build(id), pars);
	}

	public JSONObject getResult(Long id) {
		return (JSONObject) caller.getCall(UriBuilder.fromUri(
				Endpoints.TASK_RESULTS).build(id));
	}

	public JSONObject getStatus(Long id) {
		return (JSONObject) caller.getCall(UriBuilder.fromUri(
				Endpoints.TASK_STATUS).build(id));

	}

}
