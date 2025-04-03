package com.philcode.cards.service;

import com.philcode.cards.dto.CardsDto;

public interface CardService {
    void createCard(String mobileNumber);
    boolean updateCard(CardsDto cardsDto);
    CardsDto fetchCard(String mobileNumber);
    boolean deleteCard(String mobileNumber);
}
