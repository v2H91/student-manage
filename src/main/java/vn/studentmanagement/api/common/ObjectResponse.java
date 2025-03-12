package vn.studentmanagement.api.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ObjectResponse {
    private int httpStatus;
    private Object data;
    private String messageId;
    private Object errorMessage;
}