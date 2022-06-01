package com.example.tmarestaurant.service;

import com.example.tmarestaurant.dto.request.BillRequestDto;
import com.example.tmarestaurant.dto.response.BillResponseDto;
import com.example.tmarestaurant.model.Bill;
import com.example.tmarestaurant.model.BillDetail;
import java.util.List;

public interface BillService {
    BillResponseDto addBill(BillRequestDto billRequestDto);
    BillResponseDto getBillById(Long billId);
    Bill getBill(Long billId);
    List<Bill> getBillsByUser(Long userId);
    List<BillResponseDto> getBills();
    double caculateTotalPrice(BillDetail billDetail);
    void deleteBill(Long billId);
    void editBill(Long billId, BillRequestDto billRequestDto);
    List<BillResponseDto> sortBillByOrderedTime(String mode);
}
