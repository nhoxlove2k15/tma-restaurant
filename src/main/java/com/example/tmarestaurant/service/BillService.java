package com.example.tmarestaurant.service;

import com.example.tmarestaurant.dto.request.BillRequestDto;
import com.example.tmarestaurant.dto.response.BillResponseDto;
import com.example.tmarestaurant.model.Bill;

import java.util.List;
import java.util.Set;


public interface BillService {
    BillResponseDto addBill(BillRequestDto billRequestDto);
    BillResponseDto getBillById(Long billId);
    Bill getBill(Long billId);
    List<Bill> getBillsByUser(Long userId);
    List<BillResponseDto> getBills();

    void deleteBill(Long billId);
    void editBill(Long billId, BillRequestDto billRequestDto);

}
