package com.micro.cards.service;

import com.micro.cards.dto.CardDto;

public interface CardsService {

    void createCard(String mobileNumber);

    CardDto getCard(String mobileNumber);

    boolean updateCard(CardDto cardDto);

    void deleteCard(CardDto cardDto);
}
