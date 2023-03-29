package fguiraldelli.epicchallenge.controller;

import fguiraldelli.epicchallenge.dto.CustomerDto;
import fguiraldelli.epicchallenge.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private CustomerService customerService;

    @Operation(
            summary = "Register Customer REST API",
            description = "Register User REST API is used to save a new customer in the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<CustomerDto> registerCustomer(@Valid @RequestBody CustomerDto customer) {

        CustomerDto savedCustomer = customerService.registerCustomer(customer);

        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update Customer REST API",
            description = "Update Customer REST API is used to update a customer in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping("{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("id") String customerIdCard,
                                                      @Valid @RequestBody CustomerDto customer) {

        customer.setIdCard(customerIdCard);
        CustomerDto updatedCustomer = customerService.updateCustomer(customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @Operation(
            summary = "Delete Customer REST API",
            description = "Delete a Customer REST API is used to delete a customer in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") String customerIdCard) {
        customerService.deleteCustomer(customerIdCard);
        return ResponseEntity.ok("User deleted successfully");
    }
}
