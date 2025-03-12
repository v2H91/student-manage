package vn.studentmanagement.api.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseApi extends ResponseEntity<ObjectResponse> {
    public ResponseApi(Object data) {
        super(new ObjectResponse(HttpStatus.OK.value(), data, "Success", ""), HttpStatus.OK);
    }


}
