package com.philcode.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
@Schema(
        name = "Customer",
        description = "Schema to hold customer and account information"

)
public class CustomerDto {
    @Schema(
            description = "Name of the customer", example = "Philip Ndubisi"
    )
    @NotEmpty(message = "Name can not be empty")
    @Size(min = 5, max = 50, message = "The length of the customer name should be between 5 and 50")
    private String name;

    @NotEmpty(message = "Email address can not be empty")
    @Email(message = "Email address should be a valid value")
    private String email;
    @Pattern(
            regexp = "^\\d{11}$",
            message = "Mobile number must be exactly 11 digits"
    )
    private String mobileNumber;

    private AccountsDto accountsDTO;

    public CustomerDto() {
    }

    public AccountsDto getAccountsDTO() {
        return accountsDTO;
    }

    public void setAccountsDTO(AccountsDto accountsDTO) {
        this.accountsDTO = accountsDTO;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
