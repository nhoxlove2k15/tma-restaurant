package com.example.tmarestaurant.controlller;

import com.example.tmarestaurant.dto.request.BillRequestDto;

import com.example.tmarestaurant.dto.response.BillResponseDto;
import com.example.tmarestaurant.service.BillService;
import com.example.tmarestaurant.utils.RestaurantResponse;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {
    private final BillService billService;


    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }
    @PostMapping("/addBill")
    public RestaurantResponse<BillResponseDto> addBill(@RequestBody final BillRequestDto billRequestDto) {
        BillResponseDto billResponseDto = billService.addBill(billRequestDto);
        RestaurantResponse response = new RestaurantResponse(billResponseDto,"Add successfully", HttpStatus.OK);
        return response;
    }
    @GetMapping("get/{id}")
    public RestaurantResponse<BillResponseDto> getBill(@PathVariable final Long id) {
        BillResponseDto billResponseDto = billService.getBillById(id);
        RestaurantResponse response = new RestaurantResponse(billResponseDto,"Add successfully", HttpStatus.OK);
        return response;
    }
    @GetMapping("/getAll")
    public RestaurantResponse<List<BillResponseDto>> getBills() {
        List<BillResponseDto> billResponseDtos = billService.getBills();
        RestaurantResponse response = new RestaurantResponse(billResponseDtos,"Add successfully", HttpStatus.OK);
        return response;
    }
    @DeleteMapping("/delete/{id}")
    public RestaurantResponse deleteBill(@PathVariable final Long id) throws JSONException {
        BillResponseDto billResponseDto = billService.deleteBill(id);
        RestaurantResponse response = new RestaurantResponse(null,"delete sucessfully", HttpStatus.OK);
        return response;
    }
    @PostMapping("/edit/{id}")
    public RestaurantResponse editBill(@PathVariable final Long id,
                                       @RequestBody final BillRequestDto billRequestDto) {

        BillResponseDto billResponseDto = billService.editBill(id,billRequestDto);
        RestaurantResponse response = new RestaurantResponse(null,"update sucessfully", HttpStatus.OK);

        return response;
    }
    
}
