package com.apps.stocktrading.profile.dto;

import java.util.Date;

public class ProfileDTO {
    private String username;

    private String email;

    private String phoneNumber;

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private long accountBalance;
    private BankInfoDTO bankInfo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public long getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(long accountBalance) {
        this.accountBalance = accountBalance;
    }

    public BankInfoDTO getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(BankInfoDTO bankInfo) {
        this.bankInfo = bankInfo;
    }
}
