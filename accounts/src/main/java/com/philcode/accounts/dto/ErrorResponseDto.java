package com.philcode.accounts.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
@Schema(
        name = "Response",
        description = "Schema to hold error response"
)
public class ErrorResponseDto {
    private String apiPath;
    private HttpStatus errorCode;
    private String errorMessage;
    private LocalDateTime errorTime;

    public ErrorResponseDto() {
    }

    public ErrorResponseDto(String apiPath, HttpStatus errorCode, String errorMessage, LocalDateTime errorTime) {
        this.apiPath = apiPath;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorTime = errorTime;
    }

    public String getApiPath() {
        return apiPath;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public LocalDateTime getErrorTime() {
        return errorTime;
    }
}
