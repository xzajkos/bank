package com.xzajkos.bank.Model;

import java.math.BigDecimal;
import java.util.Date;

public record TransferResponse(long sourceAccountId, long destinationAccountId, BigDecimal amount, Date transactionDate) {

}
