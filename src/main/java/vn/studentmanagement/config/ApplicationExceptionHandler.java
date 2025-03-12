package vn.studentmanagement.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import vn.studentmanagement.api.common.AppBusinessError;
import vn.studentmanagement.api.common.ApplicationException;

@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(value = {ApplicationException.class})
    public ResponseEntity<BaseResponse<Void>> handleBusinessException(ApplicationException exception, ServletWebRequest webRequest) {
        BaseResponse<Void> data;
        if (exception.getMessage() != null) {
            data = this.getFailedResponse(webRequest, exception.getErrorCode(), exception.getMessage());
        } else {
            data = this.getFailedResponse(webRequest, exception.getErrorCode());
        }
        return this.getResponseEntity(exception.getErrorCode(), data);
    }

    private ResponseEntity<BaseResponse<Void>> getResponseEntity(AppBusinessError errorCode,
                                                                 BaseResponse<Void> data) {
        var status = errorCode.getHttpStatus();
        return new ResponseEntity<>(data, status);
    }
    private BaseResponse<Void> getFailedResponse(ServletWebRequest webRequest, AppBusinessError errorCode, String
            errorMessage) {
        var data = BaseResponse.ofFailed(errorCode, errorMessage);
        return this.addRequestId(webRequest, data);
    }

    private BaseResponse<Void> addRequestId(ServletWebRequest webRequest, BaseResponse<Void> data) {
        data.getMeta().setRequestId(this.getRequestId(webRequest));
        return data;
    }

    private BaseResponse<Void> getFailedResponse(ServletWebRequest webRequest, AppBusinessError errorCode) {
        var data = BaseResponse.ofFailed(errorCode, errorCode.getMessage());
        return this.addRequestId(webRequest, data);
    }

    private String getRequestId(ServletWebRequest webRequest) {
        return webRequest.getHeader("X-Request-ID");
    }

}
