package br.com.tutorial.dto;

public class Message
{	
	private String success;
	private String warning;
	private String error;
	private String info;
	
	public String getSuccess() {
		return success;
	}
	public Message setSuccess(String success) {
		this.success = success;
		return this;
	}
	public String getWarning() {
		return warning;
	}
	public Message setWarning(String warning) {
		this.warning = warning;
		return this;
	}
	public String getError() {
		return error;
	}
	public Message setError(String error) {
		this.error = error;
		return this;
	}
	public String getInfo() {
		return info;
	}
	public Message setInfo(String info) {
		this.info = info;
		return this;
	}
}
