package fguiraldelli.epicchallenge.controller;

import fguiraldelli.epicchallenge.dto.UserDto;
import fguiraldelli.epicchallenge.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Register User REST API",
            description = "Register User REST API is used to save a new user in the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<UserDto> registerCustomer(@Valid @RequestBody UserDto user) {

        UserDto savedUser = userService.registerUser(user);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update User REST API",
            description = "Update User REST API is used to update a user in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,
                                                      @Valid @RequestBody UserDto user) {

        user.setCustomer_id_user(userId);
        UserDto updatedCustomer = userService.updateUser(user);
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
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
}
