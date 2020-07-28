package prj;

import java.io.Serializable;

public class AjaxCallResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -462998940185600985L;
	private boolean successful = true;
	private String message = null;
	private Object data = null;
	public boolean isSuccessful() {
		return successful;
	}
	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public AjaxCallResult(){
		this(null, true, null);
	}
	public AjaxCallResult(boolean successful,String message){
		this(null, successful, message);
	}
	public AjaxCallResult(Object data){
		this(data, true, null);
	}
	public AjaxCallResult(Object data,boolean successful,String message){
		setSuccessful(successful);
		setMessage(message);
		setData(data);
	}	
}
