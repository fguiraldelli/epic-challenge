package fguiraldelli.epicchallenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fguiraldelli.epicchallenge.dto.CustomerDto;
import fguiraldelli.epicchallenge.entity.Customer;
import fguiraldelli.epicchallenge.exception.ResourceNotFoundException;
import fguiraldelli.epicchallenge.mapper.CustomerMapper;
import fguiraldelli.epicchallenge.service.CustomerService;
import fguiraldelli.epicchallenge.service.MobileSubscriberService;
import fguiraldelli.epicchallenge.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private MobileSubscriberService mobileSubscriberService;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private Customer customer;
    private CustomerDto customerDto;

    @BeforeEach
    void setUp() {

        customer = Customer.builder()
                .customerIdOwner(1L)
                .idCard("ab256xd")
                .name("Albert Einstein")
                .surname("Einstein")
                .address("Berkeley University")
                .build();

        customerDto = CustomerMapper.mapsToCustomerDto(customer);
    }

    @Test
    void registerCustomer() throws Exception {

        //given

        given(customerService.registerCustomer(any(CustomerDto.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        //when

        ResultActions response =
                mockMvc
                        .perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto)));

        //then
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idCard",
                        is(customerDto.getIdCard())))
                .andExpect(jsonPath("$.name",
                        is(customerDto.getName())))
                .andExpect(jsonPath("$.surname",
                        is(customerDto.getSurname())));
    }

    @Test
    void updateCustomer() throws Exception {

        CustomerDto updatedCustomerDto = CustomerDto.builder()
                .id(1L)
                .idCard("ab256xd")
                .name("Hugo Einstein")
                .surname("HEinstein")
                .address("Munchen, Germany")
                .build();

        given(customerService.updateCustomer(any(CustomerDto.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        //when
        ResultActions response = mockMvc.perform(put("/api/v1/customers/{id}", customer.getIdCard())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedCustomerDto)));

        //then
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(updatedCustomerDto.getName())))
                .andExpect(jsonPath("$.surname", is(updatedCustomerDto.getSurname())))
                .andExpect(jsonPath("$.address", is(updatedCustomerDto.getAddress())));
    }

    @Test
    void updateCustomerResourceNotFound() throws Exception {

        CustomerDto updatedCustomerDto = CustomerDto.builder()
                .id(1L)
                .idCard("ab256xd")
                .name("Hugo Einstein")
                .surname("HEinstein")
                .address("Munchen, Germany")
                .build();

        given(customerService.updateCustomer(any(CustomerDto.class)))
                .willThrow(ResourceNotFoundException.class);

        //when
        ResultActions response = mockMvc.perform(put("/api/v1/customers/{id}", customerDto.getIdCard())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedCustomerDto)));

        //then
        response.andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteCustomer() throws Exception {

        willDoNothing().given(customerService).deleteCustomer(customer.getIdCard());

        //when
        ResultActions response = mockMvc.perform(delete("/api/v1/customers/{id}", customer.getIdCard()));

        //then
        response.andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    void deleteCustomerResourceNotFound() throws Exception {

        willThrow(ResourceNotFoundException.class).given(customerService).deleteCustomer(customer.getIdCard());

        //when
        ResultActions response = mockMvc.perform(delete("/api/v1/customers/{id}", customer.getIdCard()));

        //then
        response.andExpect(status().isNotFound())
                .andDo(print());

    }
}