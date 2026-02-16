package com.distributedlog.system.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class IndirectSaleTransaction {
    int quantity;
    Double amount;
    String contractId;
    String payee;
    String wholesaler;
    String productId;

}
