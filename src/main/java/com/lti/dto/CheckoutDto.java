package com.lti.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutDto {

    private double numberOfItems;
    private double savingAmount;
    private double discountedAmount;
}
