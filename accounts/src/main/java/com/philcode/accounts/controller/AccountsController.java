package com.philcode.accounts.controller;

import com.philcode.accounts.dto.CustomerDto;
import com.philcode.accounts.dto.ErrorResponseDto;
import com.philcode.accounts.dto.ResponseDto;
import com.philcode.accounts.constants.AccountsConstants;
import com.philcode.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Tag(
        name = "CRUD REST API for Accounts Microservice in EasyBank application",
        description = "CRUD REST API in easy bank application to Create, fetch, update and delete account details"
)
@RestController
@Validated
@RequestMapping(
        path = "/api"
        ,produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountsController {
    private final IAccountsService iAccountsService;
    @Operation(
            summary = "Create Account REST API",
            description = "REST API to creat new customer and account inside EasyBank"
    )
    @ApiResponse(
          responseCode = "201",
          description =  "HTTP status CREATED"
    )
    @PostMapping("/accounts")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerdto){
    iAccountsService.createAccount(customerdto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }
    @GetMapping("/accounts")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam @Pattern(regexp = "^\\d{11}$", message = "Mobile number must be exactly 11 digits")String mobileNumber){
        CustomerDto customerDTO = iAccountsService.fetchAccountDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
    }
    @Operation(
            summary = "Update Account REST API",
            description = "REST API to update new customer and account inside EasyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description =  "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description =  "HTTP status Internal server error",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            )}

    )
    @PutMapping("/accounts")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDTO){
        boolean isUpdated = iAccountsService.updateAccountDetails(customerDTO);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.MESSAGE_200, AccountsConstants.STATUS_200));
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }    @Operation(
            summary = "Delete Account REST API",
            description = "REST API to delete new customer and account inside EasyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description =  "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description =  "HTTP status Internal server error"
            )}
    )


    @DeleteMapping("/accounts")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam @Pattern(regexp = "^\\d{11}$", message = "Mobile number must be exactly 11 digits")String mobileNumber){
        boolean isDeleted = iAccountsService.deleteAccountDetails(mobileNumber);
        if (isDeleted){
            return ResponseEntity.status(HttpStatus.OK)
                                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));

        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }


    public AccountsController(IAccountsService iAccountsService) {
        this.iAccountsService = iAccountsService;
    }
}
