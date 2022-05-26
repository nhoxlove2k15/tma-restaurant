package com.example.tmarestaurant.dto.request;

import com.example.tmarestaurant.model.BillDetail;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BillRequestDto {
    @JsonAlias(value = "user_id")
    private Long userId;
    @JsonAlias(value = "total_price")
    private double totalPrice;
    @JsonAlias(value = "bill_details")
    private BillDetailRequestDto billDetails;

}
