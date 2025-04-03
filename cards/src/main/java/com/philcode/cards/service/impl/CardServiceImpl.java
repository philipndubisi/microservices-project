package com.philcode.cards.service.impl;

import com.philcode.cards.constants.CardsConstants;
import com.philcode.cards.dto.CardsDto;
import com.philcode.cards.entity.Cards;
import com.philcode.cards.exception.CardAlreadyExistsException;
import com.philcode.cards.exception.ResourceNotFoundException;
import com.philcode.cards.mapper.CardsMapper;
import com.philcode.cards.repository.CardsRepository;
import com.philcode.cards.service.CardService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardsRepository cardsRepository;
    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> optionalCards = cardsRepository.findByMobileNumber(mobileNumber);
        if (optionalCards.isPresent()){
                throw new CardAlreadyExistsException("Card already exists with the given mobile number " + mobileNumber);
        }
            cardsRepository.save(createNewCard(mobileNumber));
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardNumber", cardsDto.getCardNumber())
        );
        CardsMapper.mapToCards(cardsDto);
        cardsRepository.save(cards);
        return true;
    }
    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards getCard = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Cards", "mobileNumber", mobileNumber ));
      return CardsMapper.mapToCardsDto(getCard);
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Cards", "mobileNumber", mobileNumber)
        );
        cardsRepository.deleteById(cards.getCardId());
        return true;
    }

    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }
}
