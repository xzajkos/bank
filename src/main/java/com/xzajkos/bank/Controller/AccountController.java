package com.xzajkos.bank.Controller;

import com.xzajkos.bank.Dto.AccountDto;
import com.xzajkos.bank.Exception.AccountIdNotFoundException;
import com.xzajkos.bank.Model.Account;
import com.xzajkos.bank.Model.OperationResponse;
import com.xzajkos.bank.Model.TransferResponse;
import com.xzajkos.bank.Service.AccountService;
import com.xzajkos.bank.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    private final UserService userService;

    public AccountController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<OperationResponse> createAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.createAccount(account));
    }

    @PatchMapping("/update")
    public ResponseEntity<OperationResponse> updateAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.updateAccount(account));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<OperationResponse> deleteAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.deleteAccount(account));
    }

    @PostMapping("{id}/transfer/{accountNumber}")
    public ResponseEntity<TransferResponse> transferMoney(@PathVariable("id") long id,
                                                          @PathVariable("accountNumber") String accountNumber,
                                                          @RequestBody BigDecimal amount) throws AccountIdNotFoundException {
        return ResponseEntity.ok(accountService.transferMoney(id, accountNumber, amount));
    }

    @GetMapping("{accountNumber}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable("accountNumber") String accountNumber) {
        return ResponseEntity.ok(accountService.getAccount(accountNumber));
    }

    @GetMapping("/getAllAccounts")
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }
}
