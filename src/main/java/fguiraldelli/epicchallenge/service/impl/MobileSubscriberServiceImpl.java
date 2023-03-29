package fguiraldelli.epicchallenge.service.impl;

import fguiraldelli.epicchallenge.dto.MobileSubscriberDto;
import fguiraldelli.epicchallenge.entity.MobileSubscriber;
import fguiraldelli.epicchallenge.exception.CustomerAlreadyExistsException;
import fguiraldelli.epicchallenge.mapper.MobileSubscriberMapper;
import fguiraldelli.epicchallenge.repository.MobileSubscriberRepository;
import fguiraldelli.epicchallenge.service.MobileSubscriberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MobileSubscriberServiceImpl implements MobileSubscriberService {

    private MobileSubscriberRepository mobileSubscriberRepository;
    @Override
    public MobileSubscriberDto createMobileSubscriber(MobileSubscriberDto mobileSubscriberDto) {
        Optional<MobileSubscriber> optionalMobileSubscriber = mobileSubscriberRepository.findByMsisdn(mobileSubscriberDto.getMsisdn());

        if(optionalMobileSubscriber.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already exists in database");
        }

        mobileSubscriberDto.setService_start_date(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));

        MobileSubscriber newMobileSubscriber = MobileSubscriberMapper.mapsToMobileSubscriber(mobileSubscriberDto);

        MobileSubscriber savedMobileSubscriber = mobileSubscriberRepository.save(newMobileSubscriber);

        return MobileSubscriberMapper.mapsToMobileSubscriberDto(savedMobileSubscriber);
    }

    @Override
    public MobileSubscriberDto updateMobileSubscriber(MobileSubscriberDto mobileSubscriberDto) {
        return null;
    }

    @Override
    public List<MobileSubscriberDto> getAllMobileSubscribers() {
        List<MobileSubscriber> users = mobileSubscriberRepository.findAll();
        return users.stream().map((user) -> MobileSubscriberMapper.mapsToMobileSubscriberDto(user))
                .collect(Collectors.toList());
    }
}
