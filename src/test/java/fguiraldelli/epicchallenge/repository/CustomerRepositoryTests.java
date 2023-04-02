package fguiraldelli.epicchallenge.repository;

import fguiraldelli.epicchallenge.entity.Customer;
import fguiraldelli.epicchallenge.integration.AbstractContainerBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTests extends AbstractContainerBaseTest {

    @Autowired
    private CustomerRepository customerRepository;

    //given
    private Customer customer;

    @BeforeEach
    public void setup() {
        customer = Customer.builder()
                .idCard("ab256xd")
                .name("Albert Einstein")
                .surname("Einstein")
                .address("Berkeley University")
                .build();
    }
    @DisplayName("JUnit test for save customer operation")
    @Test
    public void givenCustomerObject_whenSave_thenReturnSavedCustomer() {

        //when
        Customer savedCustomer = customerRepository.save(customer);

        //then
        assertThat(savedCustomer).isNotNull();
        assertThat(savedCustomer.getCustomerIdOwner()).isGreaterThan(0);

    }

    @DisplayName("JUnit test for find by id card a customer operation")
    @Test
    public void findCustomerByIdCardTest() {

        //given
        customerRepository.save(customer);

        //when
        Customer existedCustomer = customerRepository.findByIdCard(customer.getIdCard()).get();

        //then
        assertThat(existedCustomer).isNotNull();
        assertThat(existedCustomer.getIdCard().equals(customer.getIdCard()));

    }

    @DisplayName("JUnit test for delete customer by id card operation")
    @Test
    public void deleteCustomerByIdCardTest() {

        //given
        customerRepository.save(customer);

        //when
        customerRepository.deleteByIdCard(customer.getIdCard());
        Optional<Customer> deletedCustomer = customerRepository.findByIdCard(customer.getIdCard());

        //then
        assertThat(deletedCustomer).isEmpty();

    }


}
