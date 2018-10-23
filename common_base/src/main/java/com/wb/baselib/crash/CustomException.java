package com.wb.baselib.crash;

/**
 * 异常类
 * @author 王卜
 *
 */
public class CustomException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public CustomException() {
		super();
	}

	public CustomException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public CustomException(String detailMessage) {
		super(detailMessage);
	}

	public CustomException(Throwable throwable) {
		super(throwable);
	}

}
