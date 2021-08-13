package com.github.aviii04.urlshortner.domain.exception;

/**
 * @author Avinash Thakur
 * 
 * Common place to hold all custom error message.
 */
public enum UrlException {
	
	MAX_RETRY_EXCEEDED("USE0001", "Maximum attempt to create short URL exceeded");

	private String errCode;
	private String errMsg;
	
	/**
	 * @param errCode - Uniquely identify error message
	 * @param errMsg - Error message
	 */
	UrlException(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public String getErrCode() {
		return errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}
	
	public String getErrMsgWithCode() {
		return errCode + ": " + errMsg;
	}

}
