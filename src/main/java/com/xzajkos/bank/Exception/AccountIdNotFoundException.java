package com.xzajkos.bank.Exception;

public class AccountIdNotFoundException extends Exception {
    private final long AccountId;
    private final String errorMessage;

    public AccountIdNotFoundException(long AccountId, String errorMessage) {
        this.AccountId = AccountId;
        this.errorMessage = errorMessage;
    }
}
