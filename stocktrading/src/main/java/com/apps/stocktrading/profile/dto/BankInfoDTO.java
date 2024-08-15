package com.apps.stocktrading.profile.dto;

public class BankInfoDTO {
    private String bank;
    private String userBankName;
    private String userBankAccount;

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getUserBankName() {
        return userBankName;
    }

    public void setUserBankName(String userBankName) {
        this.userBankName = userBankName;
    }

    public String getUserBankAccount() {
        return userBankAccount;
    }

    public void setUserBankAccount(String userBankAccount) {
        this.userBankAccount = userBankAccount;
    }
}
