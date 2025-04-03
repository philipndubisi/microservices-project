package com.philcode.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
@Schema(
        name = "Response",
        description = "Schema to hold successful response information"
)
@Data @AllArgsConstructor
public class ResponseDto {
    @Schema(
            description = "status code in the response")
    private String statusCode;
    @Schema(
            description = "status message in the response"
    )
    private String statusMsg;
}
