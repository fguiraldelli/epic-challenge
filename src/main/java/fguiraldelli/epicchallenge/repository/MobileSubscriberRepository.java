package fguiraldelli.epicchallenge.repository;

import fguiraldelli.epicchallenge.entity.MobileSubscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface MobileSubscriberRepository extends JpaRepository<MobileSubscriber, Long> {

    Optional<MobileSubscriber> findByMsisdn(String number);


}
