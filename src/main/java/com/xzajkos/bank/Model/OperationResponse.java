package com.xzajkos.bank.Model;

import java.util.Date;

public record OperationResponse (String operation, String status, Date operationDate){
}
