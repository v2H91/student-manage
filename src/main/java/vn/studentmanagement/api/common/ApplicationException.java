package vn.studentmanagement.api.common;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {
    private AppBusinessError errorCode;

    public ApplicationException(AppBusinessError errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApplicationException(AppBusinessError errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ApplicationException(AppBusinessError error, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = error;
    }
}
