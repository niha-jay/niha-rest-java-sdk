package com.niha.rest.client;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@Getter @Setter
public class ResponseMessage<D> {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(RestCall.class);

    private String message;
    private int code;
    private HttpStatus httpStatus;
    private Optional<D> data;

    public static <D> ResponseMessage of(ResponseEntity<D> response) {
        HttpStatus httpStatus = response.getStatusCode();
        ResponseMessage<D> responseMessage = new ResponseMessage();
        switch (httpStatus) {
            case OK:
            case CREATED:
                LOG.info("Resource created");
                responseMessage.setHttpStatus(httpStatus);
                responseMessage.setData(Optional.ofNullable(response.getBody()));
                return responseMessage;
            case NO_CONTENT:
                LOG.info("Resource deleted");
                responseMessage.setHttpStatus(httpStatus);
                return responseMessage;
            case BAD_REQUEST:
                LOG.error("Bad request");
                responseMessage.setHttpStatus(httpStatus);
                return responseMessage;
            case UNAUTHORIZED:
                LOG.error("Unauthorized request");
                responseMessage.setHttpStatus(httpStatus);
                return responseMessage;
            case FORBIDDEN:
                LOG.error("Forbidden request");
                responseMessage.setHttpStatus(httpStatus);
                return responseMessage;
            default:
                LOG.error("Forbidden request");
                responseMessage.setHttpStatus(httpStatus);
                return responseMessage;
        }
    }
}
