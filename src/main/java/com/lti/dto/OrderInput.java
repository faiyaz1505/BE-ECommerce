package com.lti.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInput {

    private String fullName;
    private String fullAddress;
    private String contactNumber;
    private String alternateContactNumber;
    private List<OrderProductQuantity> orderProductQuantityList;

}
