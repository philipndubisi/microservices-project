package com.philcode.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Response",
        description = "Schema to hold successful response"
)
public class ResponseDto {
    private String statusCode;
    private String statusMessage;

    public ResponseDto(String statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

}
