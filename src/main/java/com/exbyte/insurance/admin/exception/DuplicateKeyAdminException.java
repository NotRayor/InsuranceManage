package com.exbyte.insurance.admin.exception;

public class DuplicateKeyAdminException extends RuntimeException {

	public DuplicateKeyAdminException() {
		super("중복된 ID 값 요청 오류");
	}

    public DuplicateKeyAdminException(Throwable cause) {
        super(cause);
    }
}
