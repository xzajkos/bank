package com.xzajkos.bank.Service;

import com.xzajkos.bank.Dto.AccountDto;
import com.xzajkos.bank.Exception.AccountIdNotFoundException;
import com.xzajkos.bank.Mapper.AccountDtoMapper;
import com.xzajkos.bank.Model.Account;
import com.xzajkos.bank.Model.OperationResponse;
import com.xzajkos.bank.Model.TransferResponse;
import com.xzajkos.bank.Repository.AccountRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountDtoMapper accountDtoMapper;

    public AccountServiceImpl(AccountRepository accountRepository, AccountDtoMapper accountDtoMapper) {
        this.accountRepository = accountRepository;
        this.accountDtoMapper = accountDtoMapper;
    }

    @Override
    public OperationResponse createAccount(@NonNull Account account) {
        if(accountRepository.existsById(account.getId())) {
            return new OperationResponse("CREATE_ACCOUNT", "FAILED", new Date());
        }
        else{
            accountRepository.save(account);
            return new OperationResponse("CREATE_ACCOUNT", "SUCCESS", new Date());
        }
    }

    @Override
    public OperationResponse updateAccount(Account account) {
        if(!accountRepository.existsById(account.getId())) {
            return new OperationResponse("UPDATE_ACCOUNT", "FAILED", new Date());
        }
        else{
            accountRepository.save(account);
            return new OperationResponse("UPDATE_ACCOUNT", "SUCCESS", new Date());
        }

    }

    @Override
    public OperationResponse deleteAccount(Account account) {
        if(!accountRepository.existsById(account.getId())) {
            return new OperationResponse("DELETE_ACCOUNT", "FAILED", new Date());
        }
        else{
            accountRepository.deleteById(account.getId());
            return new OperationResponse("DELETE_ACCOUNT", "SUCCESS", new Date());
        }
    }

    @Override
    public TransferResponse transferMoney(long fromAccountId, String toAccountNumber, BigDecimal amount) throws AccountIdNotFoundException {
        if(!accountRepository.existsById(fromAccountId)) {
            throw new AccountIdNotFoundException(fromAccountId, "Account not found");
        }
        else {
            Account from = accountRepository.findById(fromAccountId).get();
            Account to = accountRepository.findByAccountNumber(toAccountNumber);
            to.setBalance(to.getBalance().add(amount));
            accountRepository.save(to);
            from.setBalance(from.getBalance().subtract(amount));
            accountRepository.save(from);
            return new TransferResponse(fromAccountId, to.getId(), amount, new Date());
        }

    }

    @Override
    public AccountDto getAccount(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        return accountDtoMapper.apply(account);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return List.of(accountDtoMapper.apply((Account) accounts));
    }
}
