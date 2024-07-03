package com.micro.cards.service.impl;

import com.micro.cards.constants.CardsConstants;
import com.micro.cards.dto.CardDto;
import com.micro.cards.entity.Card;
import com.micro.cards.exception.ResourceAlreadyExistsException;
import com.micro.cards.exception.ResourceNotFoundException;
import com.micro.cards.mapper.Mapper;
import com.micro.cards.repository.CardsRepository;
import com.micro.cards.service.CardsService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.SplittableRandom;

@Service
@RequiredArgsConstructor
public class CardsServiceImpl implements CardsService {

    private final CardsRepository cardsRepository;
    private final Mapper mapper;

    @Override
    public void createCard(String mobileNumber) {
        cardsRepository.findByMobileNumber(mobileNumber).ifPresent(card -> {
                    throw new ResourceAlreadyExistsException("Card already exists for mobile number - " + mobileNumber);
                }
        );
        Card newCard = createNewCard(mobileNumber);
        cardsRepository.save(newCard);
    }

    @Override
    public CardDto getCard(String mobileNumber) {
        CardDto cardDto = new CardDto();
        cardsRepository.findByMobileNumber(mobileNumber)
                .ifPresentOrElse(card -> mapper.mapModelToDto(card, cardDto),
                        () -> {
                            throw new ResourceNotFoundException("Card not found for mobile number " + mobileNumber);
                        }
                );
        return cardDto;
    }

    @Override
    public boolean updateCard(final CardDto cardDto) {
        boolean isUpdated = false;
        cardsRepository.findByMobileNumber(cardDto.getMobileNumber())
                .ifPresentOrElse(card -> {
                            mapper.mapDtoToModel(cardDto, card);
                            cardsRepository.save(card);
                        },
                        () -> {
                            throw new ResourceNotFoundException("Card not found for mobile number " + cardDto.getMobileNumber());
                        });
        isUpdated = true;
        return isUpdated;
    }

    @Override
    public void deleteCard(CardDto cardDto) {
        cardsRepository.findByMobileNumber(cardDto.getMobileNumber())
                .ifPresentOrElse(card -> {
                            cardsRepository.delete(card);
                        },
                        () -> {
                            throw new ResourceNotFoundException("Card not found for mobile number " + cardDto.getMobileNumber());
                        });
    }

    private Card createNewCard(String mobileNumber) {
        Card card = new Card();
        card.setMobileNumber(mobileNumber);
        card.setCardType(CardsConstants.CREDIT_CARD);
        SplittableRandom splittableRandom = new SplittableRandom();
        long cardNumber = splittableRandom.nextLong(4500000000000000L, 4599999999999999L);
        card.setCardNumber(String.valueOf(cardNumber));
        card.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        return card;
    }
}
