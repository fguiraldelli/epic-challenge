package fguiraldelli.epicchallenge.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import fguiraldelli.epicchallenge.dto.CustomerDto;
import fguiraldelli.epicchallenge.entity.Customer;
import fguiraldelli.epicchallenge.mapper.CustomerMapper;
import fguiraldelli.epicchallenge.repository.CustomerRepository;
import fguiraldelli.epicchallenge.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CustomerControllerITContainers extends AbstractContainerBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerService customerService;

    private Customer customer;

    private CustomerDto customerDto;

    @BeforeEach
    void setup() {
        customerRepository.deleteAll();

        customer = Customer.builder()
                .idCard("ab256xd")
                .name("Albert Einstein")
                .surname("Einstein")
                .address("Berkeley University")
                .build();

        customerDto = CustomerMapper.mapsToCustomerDto(customer);
    }
    @Test
    void registerCustomer() throws Exception {

        //when

        ResultActions response = mockMvc.perform(post("/api/v1/customers")
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

        customerRepository.save(customer);

        //when
        ResultActions response = mockMvc.perform(put("/api/v1/customers/{id}", customerDto.getIdCard())
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

        Customer savedCustomer = customerRepository.save(customer);

        CustomerDto savedCustomerDto = CustomerMapper.mapsToCustomerDto(savedCustomer);


        //when
        ResultActions response = mockMvc.perform(delete("/api/v1/customers/{id}", savedCustomerDto.getIdCard()));

        //then
        response.andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    void deleteCustomerResourceNotFound() throws Exception {


        //when
        ResultActions response = mockMvc.perform(delete("/api/v1/customers/{id}", customer.getIdCard()));

        //then
        response.andExpect(status().isNotFound())
                .andDo(print());

    }
}
