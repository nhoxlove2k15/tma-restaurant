package com.example.tmarestaurant.controlller;

import com.example.tmarestaurant.dto.request.BillRequestDto;

import com.example.tmarestaurant.dto.response.BillResponseDto;
import com.example.tmarestaurant.service.BillService;
import com.example.tmarestaurant.utils.MyConstant;
import com.example.tmarestaurant.utils.RestaurantResponse;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {
    private final BillService billService;
    private BillResponseDto billResponseDto;
    private RestaurantResponse response;


    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }
    @PostMapping("/addBill")
    public RestaurantResponse<BillResponseDto> addBill(@RequestBody final BillRequestDto billRequestDto) {
        try {
            billResponseDto = billService.addBill(billRequestDto);
        } catch (Exception e) {
            response = new RestaurantResponse(false,e.getMessage(),null);
            return response;
        }
        response = new RestaurantResponse(true, MyConstant.ACTION_CREATE , MyConstant.BILL_ENTITY, billRequestDto.getId());
        return response;
    }
    @GetMapping("get/{id}")
    public RestaurantResponse<BillResponseDto> getBill(@PathVariable final Long id) {
        try {
            billResponseDto = billService.getBillById(id);
        } catch (Exception e) {
            response = new RestaurantResponse(false, e.getMessage(), null);
            return response;

        }
        response = new RestaurantResponse(true, MyConstant.ACTION_GET , MyConstant.BILL_ENTITY, billResponseDto);
        return response;
    }
    @GetMapping("/getAll")
    public RestaurantResponse<List<BillResponseDto>> getBills() {
        List<BillResponseDto> billResponseDtos = new ArrayList<>();
        try {
            billResponseDtos = billService.getBills();

        } catch (Exception e) {
            response = new RestaurantResponse(false,e.getMessage(), null);
            return response;
        }
        response = new RestaurantResponse(true, MyConstant.ACTION_GET , MyConstant.BILL_ENTITY, billResponseDtos.size() , billResponseDtos);

        return response;
    }
    @GetMapping("/getAll/{mode}")
    public RestaurantResponse<List<BillResponseDto>> sortBillsByOrderedTime(@PathVariable final String mode) {
        List<BillResponseDto> billResponseDtos = new ArrayList<>();
        System.out.println("============================================ bill controller:");
        try {
            billResponseDtos = billService.sortBillByOrderedTime(mode);

        } catch (Exception e) {
            response = new RestaurantResponse(false,e.getMessage(), null);
            return response;
        }
        response = new RestaurantResponse(true, MyConstant.ACTION_GET , MyConstant.BILL_ENTITY, billResponseDtos.size() , billResponseDtos);

        return response;
    }
    @DeleteMapping("/delete/{id}")
    public RestaurantResponse deleteBill(@PathVariable final Long id) throws JSONException {

        try {
            billService.deleteBill(id);
        } catch (Exception e) {
            response = new RestaurantResponse(false,e.getMessage(), null);
            return response;
        }
        response = new RestaurantResponse(true, MyConstant.ACTION_DELETE , MyConstant.BILL_ENTITY, null);
        return response;
    }
    @PatchMapping("/edit/{id}")
    public RestaurantResponse editBill(@PathVariable final Long id,
                                       @RequestBody final BillRequestDto billRequestDto) {
        try {
            billService.editBill(id,billRequestDto);
        } catch (Exception e) {
            response = new RestaurantResponse(false,e.getMessage(), null);
            return response;
        }
        response = new RestaurantResponse(true, MyConstant.ACTION_UPDATE , MyConstant.BILL_ENTITY, id);
        return response;

    }
    
}
