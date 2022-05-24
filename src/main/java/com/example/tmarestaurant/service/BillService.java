package com.example.tmarestaurant.service;

import com.example.tmarestaurant.dto.request.BillRequestDto;
import com.example.tmarestaurant.dto.response.BillResponseDto;
import com.example.tmarestaurant.model.Bill;

import java.util.List;

public interface BillService {
    public BillResponseDto addBill(BillRequestDto billRequestDto);
    public BillResponseDto getBillById(Long billId);
    public Bill getBill(Long billId);
    public List<BillResponseDto> getBills();
    public BillResponseDto deleteBill(Long billId);
    public BillResponseDto editBill(Long billId, BillRequestDto billRequestDto);

}
