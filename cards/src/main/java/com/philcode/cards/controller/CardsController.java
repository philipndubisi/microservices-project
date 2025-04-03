package com.philcode.cards.controller;

import com.philcode.cards.constants.CardsConstants;
import com.philcode.cards.dto.CardsDto;
import com.philcode.cards.dto.ErrorResponseDto;
import com.philcode.cards.dto.ResponseDto;
import com.philcode.cards.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Cards service",
        description = "CRUD operations for managing cards"
)
@RestController
@RequestMapping(
        path = "/api",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@Validated
@AllArgsConstructor
public class CardsController {

    private final CardService cardService;
    @Operation(
            summary = "Create card REST API",
            description = "REST API to create new card in my Bank operation"
    )
    @ApiResponses({
            @ApiResponse(
                  responseCode = "201",
                  description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status internal server error",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            )}

    )
    @PostMapping("/cards")
    public ResponseEntity<ResponseDto> createCard(@Valid @RequestParam
                                                      @Pattern(
                                                              regexp = "^\\d{11}$",
                                                              message = "Mobile number must be exactly 11 digits"
                                                      ) String mobileNumber){
        cardService.createCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
    }
    @Operation(
            summary = "Update Card details REST API",
            description = "REST API to update card details based on mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            )
    })
    @PutMapping("/cards")
    public ResponseEntity<ResponseDto> updateCardDetails(@Valid @RequestBody CardsDto cardsDto){
      boolean isUpdated =  cardService.updateCard(cardsDto);
        if (isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto((CardsConstants.STATUS_417), CardsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Fetch card REST API",
            description = "REST API to fetch card details based on mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status internal server error",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            )}

    )
    @GetMapping("/cards")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestParam
                                                         @Pattern(
                                                                 regexp = "^\\d{11}$"
                                                                 , message = "Mobile number must be exactly 11 digits")
                                                         String mobileNumber){
        CardsDto cardsDto = cardService.fetchCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }

    @Operation(
            summary = "Delete Card Details REST API",
            description = "REST API to delete Card details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("/cards")
    public ResponseEntity<ResponseDto> deleteCardDetails(@Valid @RequestParam
                                                             @Pattern(
                                                                     regexp = "^\\d{11}$"
                                                                     , message = "Mobile number must be exactly 11 digits")String mobileNumber){
        boolean isDeleted = cardService.deleteCard(mobileNumber);
        if (isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE));
        }
    }
}
