package com.xzajkos.bank.Service;

import com.xzajkos.bank.Dto.AccountDto;
import com.xzajkos.bank.Model.Account;
import com.xzajkos.bank.Model.OperationResponse;
import com.xzajkos.bank.Model.TransferResponse;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    OperationResponse createAccount(Account account);
    OperationResponse updateAccount(Account account);
    OperationResponse deleteAccount(Account account);
    TransferResponse transferMoney(long fromAccountId, String toAccountNumber, BigDecimal amount);
    AccountDto getAccount(String accountNumber);
    List<AccountDto> getAllAccounts();
}
