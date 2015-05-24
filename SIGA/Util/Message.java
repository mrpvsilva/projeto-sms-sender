package Util;

public class Message {

	private boolean success;
	private String exception;
	private String message;

	public Message() {
		setSuccess(true);
	}

	public Message(boolean success, String exception, String msg) {
		setSuccess(success);
		setException(exception);
		setMessage(msg);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
