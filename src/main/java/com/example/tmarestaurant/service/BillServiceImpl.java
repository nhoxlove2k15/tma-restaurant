package com.example.tmarestaurant.service;

import com.example.tmarestaurant.dto.mapper;
import com.example.tmarestaurant.dto.request.BillRequestDto;
import com.example.tmarestaurant.dto.response.BillResponseDto;
import com.example.tmarestaurant.model.Bill;
import com.example.tmarestaurant.model.BillDetail;
import com.example.tmarestaurant.repository.BillDetailRepository;
import com.example.tmarestaurant.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BillServiceImpl implements BillService{
    private final BillRepository billRepository ;
    private final BillDetailRepository billDetailRepository;

    @Autowired
    public BillServiceImpl(BillRepository billRepository, BillDetailRepository billDetailRepository) {
        this.billRepository = billRepository;
        this.billDetailRepository = billDetailRepository;
    }

    @Override
    public BillResponseDto addBill(BillRequestDto billRequestDto) {
        BillDetail billDetail = new BillDetail();
        billDetail = mapper.billDetailRequestToBillDetail(billRequestDto.getBillDetails());


        billDetailRepository.save(billDetail);
        Bill bill = new Bill();
//        bill.setBillDetails(mapper.billDetailRequestToBillDetail(billRequestDto.getBillDetails()));
        bill.setTotalprice(billRequestDto.getTotalPrice());
        bill.setUserId(billRequestDto.getUserId());

        bill.setBillDetailsId(billRequestDto.getBillDetails().getId());


        billRepository.save(bill);


        return mapper.billToBillResponseDto(bill);
    }

    @Override
    public BillResponseDto getBillById(Long billId) {

        return mapper.billToBillResponseDto(getBill(billId));
    }

    @Override
    public Bill getBill(Long billId) {
        Bill bill = billRepository.findById(billId).orElseThrow(
                () -> new IllegalArgumentException("bill with id " + billId + " could not be found")
        );
        return bill;

    }

    @Override
    public List<BillResponseDto> getBills() {
        List<Bill> bills = StreamSupport
                .stream(billRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
        return mapper.billsToResponseDtos(bills);
    }

    @Override
    public BillResponseDto deleteBill(Long billId) {
        Bill bill = getBill(billId);
        billRepository.deleteById(billId);
        return mapper.billToBillResponseDto(bill);
    }

    @Override
    public BillResponseDto editBill(Long billId, BillRequestDto billRequestDto) {
//        Long billDetailId = billRequestDto.getBillDetails().getId();
        BillDetail billDetailToEdit = billDetailRepository.findById(billId - 1 ).orElseThrow(
                () -> new IllegalArgumentException("billdetail with id " + billId + " could not be found")
        );
        System.out.println("bill details : " + billDetailToEdit.toString() );
        billDetailToEdit.setDiscount(billRequestDto.getBillDetails().getDiscount());
        billDetailRepository.save( billDetailToEdit);
        // for fun
        Bill billToEdit = getBill(billId);
//        billToEdit.getBillDetails().setDiscount(billRequestDto.getBillDetails().getDiscount());
        billRepository.save(billToEdit);
        return mapper.billToBillResponseDto(billToEdit);
    }
}
