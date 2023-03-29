package fguiraldelli.epicchallenge.repository;

import fguiraldelli.epicchallenge.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByIdCard(String idCard);

    @Modifying
    @Query("delete from Customer b where b.idCard=:idCard")
    void deleteByIdCard(@Param("idCard") String idCard);
}
