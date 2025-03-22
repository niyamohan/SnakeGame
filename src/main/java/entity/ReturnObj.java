package entity;

import java.util.Map;

public class ReturnObj {
	private String status;
	private String msg;
	private Map<String, String> result;

	public static ReturnObj buildSuccess() {
		ReturnObj returnObj = new ReturnObj();
		returnObj.setStatus("200");
		returnObj.setMsg("success");
		return returnObj;
	}

	public static ReturnObj buildFault() {
		ReturnObj returnObj = new ReturnObj();
		returnObj.setStatus("400");
		returnObj.setMsg("fault");
		return returnObj;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public ReturnObj() {
		super();
	}

	public ReturnObj(String status, String msg, Map<String, String> result) {
		super();
		this.status = status;
		this.msg = msg;
		this.result = result;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, String> getResult() {
		return result;
	}

	public void setResult(Map<String, String> result) {
		this.result = result;
	}

}
