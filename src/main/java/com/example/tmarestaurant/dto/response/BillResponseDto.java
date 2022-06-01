package com.example.tmarestaurant.dto.response;

import com.example.tmarestaurant.model.BillDetail;
import com.example.tmarestaurant.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
public class BillResponseDto {
    private Long id ;
    private BillDetail billDetails;
    private double totalprice;
}
