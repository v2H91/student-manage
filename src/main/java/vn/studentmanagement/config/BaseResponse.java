package vn.studentmanagement.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import vn.studentmanagement.api.common.AppBusinessError;
import vn.studentmanagement.api.common.ApplicationException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "meta",
        "data"
})
public class BaseResponse<T> {
    public static final String OK_CODE = "200";
    public static final String BAD_REQUEST_CODE = "400";
    private T data;
    private Metadata meta = new Metadata();

    public static <T> BaseResponse<T> ofSucceeded(T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.data = data;
        response.meta.code = OK_CODE;
        return response;
    }

    public static <T> BaseResponse<T> ofSucceededWithRequestId(T data, String requestId) {
        BaseResponse<T> response = new BaseResponse<>();
        response.data = data;
        response.meta.code = OK_CODE;
        response.meta.requestId = requestId;
        return response;
    }

    public static <T> BaseResponse<T> ofSucceeded(T data, String message) {
        BaseResponse<T> response = new BaseResponse<>();
        response.data = data;
        response.meta.message = message;
        response.meta.code = OK_CODE;
        return response;
    }

    public static <T> BaseResponse<T> ofSucceeded(String message) {
        BaseResponse<T> response = new BaseResponse<>();
        response.meta.message = message;
        response.meta.code = OK_CODE;
        return response;
    }

    public static <T> BaseResponse<List<T>> ofSucceeded(List<T> data, int page, int pageSize, long total) {
        BaseResponse<List<T>> response = new BaseResponse<>();
        response.data = data;
        response.meta.code = OK_CODE;
        response.meta.page = page;
        response.meta.size = pageSize;
        response.meta.total = total;
        return response;
    }

    public static <T> BaseResponse<T> ofSucceeded() {
        BaseResponse<T> response = new BaseResponse<>();
        response.meta.code = OK_CODE;
        return response;
    }

    public static BaseResponse<Void> ofFailed(AppBusinessError errorCode) {
        return ofFailed(errorCode, null);
    }

    public static BaseResponse<Void> ofFailed(AppBusinessError errorCode, String message) {
        return ofFailed(errorCode, message, null);
    }

    public static BaseResponse<Void> ofFailed(AppBusinessError errorCode, String message, List<FieldViolation> errors) {
        BaseResponse<Void> response = new BaseResponse<>();
        response.meta.code = String.valueOf(errorCode.getCode());
        response.meta.message = (message != null) ? message : errorCode.getMessage();
        response.meta.errors = (errors != null) ? errors : null;
        return response;
    }

    public static <T> BaseResponse<T> ofFailed(String code, String message, List<ObjectError> errors) {
        BaseResponse<T> response = new BaseResponse<>();
        List<FieldViolation> fieldViolations = new ArrayList<>();

        for (ObjectError error : errors) {
            fieldViolations.add(new FieldViolation("", error.getDefaultMessage()));
        }
        response.meta.code = code;
        response.meta.message = message;
        response.meta.errors = (errors != null) ? fieldViolations : null;

        return response;
    }

    public static BaseResponse<Void> ofFailed(ApplicationException exception) {
        return ofFailed(exception.getErrorCode(), exception.getMessage());
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Metadata {
        private String code;
        private Integer page;
        private Integer size;
        private Long total;
        private List<FieldViolation> errors;
        private String message;
        private String requestId;
    }
}
