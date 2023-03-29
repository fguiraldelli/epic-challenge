package fguiraldelli.epicchallenge.controller;

import fguiraldelli.epicchallenge.dto.MobileSubscriberDto;
import fguiraldelli.epicchallenge.service.MobileSubscriberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/mobiles")
public class MobileSubscriberController {

    private MobileSubscriberService mobileSubscriberService;

    public MobileSubscriberController(MobileSubscriberService mobileSubscriberService) {
        this.mobileSubscriberService = mobileSubscriberService;
    }

    @Operation(
            summary = "Creates Mobile Subscriber REST API",
            description = "Creates Mobile Subscriber API is used to save a mobile subscriber in the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<MobileSubscriberDto> registerCustomer(@Valid @RequestBody MobileSubscriberDto mobile) {

        MobileSubscriberDto savedMobile = mobileSubscriberService.createMobileSubscriber(mobile);

        return new ResponseEntity<>(savedMobile, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get All Mobiles REST API",
            description = "Get All Mobiles REST API is used to get all mobiles from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<List<MobileSubscriberDto>> getAllUsers() {
        List<MobileSubscriberDto> mobileSubscriberDtos = mobileSubscriberService.getAllMobileSubscribers();
        return new ResponseEntity<>(mobileSubscriberDtos, HttpStatus.OK);
    }
}
