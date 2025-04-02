package com.philcode.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(
        name = "Accounts",
        description = "Schema to hold account information"
)
public class AccountsDto {
    @NotNull(message = "Account number cannot be null")
    @Pattern(regexp = "\\d{10}", message = "Account number must be exactly 10 digits")
    private Long accountNumber;

    @NotBlank(message = "Account type cannot be empty")
    @Size(min = 3, max = 20, message = "Account type must be between 3 and 20 characters")
    private String accountType;

    @NotBlank(message = "Branch address cannot be empty")
    @Size(min = 5, max = 50, message = "Branch address must be between 5 and 50 characters")
    private String branchAddress;

    public AccountsDto(Long accountNumber, String accountType, String branchAddress) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.branchAddress = branchAddress;
    }

    public AccountsDto() {
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }
}
