package com.philcode.cards.mapper;

import com.philcode.cards.dto.CardsDto;
import com.philcode.cards.entity.Cards;

public class CardsMapper {

    public static Cards mapToCards(CardsDto cardsDto) {
        return Cards.builder()
                .mobileNumber(cardsDto.getMobileNumber())
                .cardNumber(cardsDto.getCardNumber())
                .cardType(cardsDto.getCardType())
                .totalLimit(cardsDto.getTotalLimit())
                .amountUsed(cardsDto.getAmountUsed())
                .availableAmount(cardsDto.getAvailableAmount())
                .build();
    }

    public static CardsDto mapToCardsDto(Cards cards) {
        return new CardsDto(
                cards.getMobileNumber(),
                cards.getCardNumber(),
                cards.getCardType(),
                cards.getTotalLimit(),
                cards.getAmountUsed(),
                cards.getAvailableAmount()
        );
    }
}

