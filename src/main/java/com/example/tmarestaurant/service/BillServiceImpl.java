package com.example.tmarestaurant.service;

import com.example.tmarestaurant.dto.mapper;
import com.example.tmarestaurant.dto.request.BillRequestDto;
import com.example.tmarestaurant.dto.response.BillResponseDto;
import com.example.tmarestaurant.model.Bill;
import com.example.tmarestaurant.model.BillDetail;
import com.example.tmarestaurant.repository.BillDetailRepository;
import com.example.tmarestaurant.repository.BillRepository;
import com.example.tmarestaurant.utils.MenuOrigin;
import com.example.tmarestaurant.utils.MyConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

//    @Transactional
    @Override
    public BillResponseDto addBill(BillRequestDto billRequestDto) {


        BillDetail billDetail = mapper.billDetailRequestToBillDetail(billRequestDto.getBillDetails());
//        System.out.println("============================================ bill serivce" + billDetail.getMenuOrigin());

        Bill bill = new Bill();
//        bill.setId(2L);
        bill.setId(billRequestDto.getId());
        bill.setTotalprice(billRequestDto.getTotalPrice());
        bill.getUser().setId(billRequestDto.getUserId());
        bill.getBillDetails().setId(billDetail.getId());
        bill.setTotalprice(caculateTotalPrice(billDetail));
//        System.out.println("============================================ bill serivce" + bill.getBillDetails().getId() );

        try {
            billRepository.save(bill);


        } catch (Exception e) {
            throw new IllegalArgumentException(MyConstant.ERR_WRONG_DATABASE + MyConstant.BILL_ENTITY);
        }
        try {
            billDetailRepository.save(billDetail);

        } catch (Exception e) {
            throw new IllegalArgumentException(MyConstant.ERR_WRONG_DATABASE + MyConstant.BILL_DETAILS_ENTITY);
        }

        return mapper.billToBillResponseDto(bill);
    }

    @Override
    public BillResponseDto getBillById(Long billId) {

        return mapper.billToBillResponseDto(getBill(billId));
    }

    @Override
    public Bill getBill(Long billId) {
        Bill bill = billRepository.findById(billId).orElseThrow(
                () -> new IllegalArgumentException(MyConstant.ERR_GET_ENTITY + MyConstant.BILL_ENTITY)
        );
        return bill;

    }

    @Override
    public List<Bill> getBillsByUser(Long userId) {
        List<Bill> bills = StreamSupport
                .stream(billRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
        List<Bill> results = new ArrayList<>();
        for (Bill bill : bills) {
            if (bill.getUser().getId() == userId) {
                bill.setUser(null);
                results.add(bill);
            }
        }
        return results;
    }

    @Override
    public List<BillResponseDto> getBills() {
        List<Bill> bills = StreamSupport
                .stream(billRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
        return mapper.billsToResponseDtos(bills);
    }

    @Override
    public double caculateTotalPrice(BillDetail billDetail) {
        double sum = 0 ;
        for (MenuOrigin menuOrigin : billDetail.getMenuOrigin()) {
            sum += menuOrigin.getQuantity() * menuOrigin.getPrice();
        }
        sum = sum - sum * billDetail.getDiscount();
        return  sum;
    }

//    @Override
//    public double getTotalPrice() {
//        List<Bill> bills = billRepository.findAll().set
//        return 0;
//    }

    @Override
    public void deleteBill(Long billId) {
        try {
            billRepository.deleteById(billId);
        } catch (Exception e) {
            throw new IllegalArgumentException(MyConstant.ERR_WRONG_DATABASE + MyConstant.BILL_ENTITY);
        }

    }

    @Override
    public void editBill(Long billId, BillRequestDto billRequestDto) {
//        Long billDetailId = billRequestDto.getBillDetails().getId();
        BillDetail billDetailToEdit = billDetailRepository.findById(billId - 1 ).orElseThrow(
                () -> new IllegalArgumentException(MyConstant.ERR_GET_ENTITY + MyConstant.BILL_DETAILS_ENTITY)
        );

        billDetailToEdit.setDiscount(billRequestDto.getBillDetails().getDiscount());
        try {
            billDetailRepository.save( billDetailToEdit);
        } catch (Exception e) {
            throw new IllegalArgumentException(MyConstant.ERR_WRONG_DATABASE + MyConstant.BILL_ENTITY);
        }
    }

    // admin features
    @Override
    public List<BillResponseDto> sortBillByOrderedTime(String mode) {
        Sort.Direction modeSort;
        if (mode.equals("asc")) {
            modeSort = Sort.Direction.ASC;
        } else modeSort = Sort.Direction.DESC;
        List<Bill> bills = StreamSupport
                .stream(billRepository.findAll(Sort.by(modeSort,"orderedTime")).spliterator(),false)
                .collect(Collectors.toList());
        return mapper.billsToResponseDtos(bills);
    }
}
