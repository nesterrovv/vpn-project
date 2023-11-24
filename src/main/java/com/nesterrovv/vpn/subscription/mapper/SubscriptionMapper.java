package com.nesterrovv.vpn.subscription.mapper;

import com.nesterrovv.vpn.subscription.dto.SubscriptionDto;
import com.nesterrovv.vpn.subscription.entity.Subscription;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionMapper {

    public SubscriptionDto entityToDto(Subscription subscription) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(subscription, SubscriptionDto.class);
    }

    public Subscription dtoToEntity(SubscriptionDto subscriptionDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(subscriptionDto, Subscription.class);
    }

}
