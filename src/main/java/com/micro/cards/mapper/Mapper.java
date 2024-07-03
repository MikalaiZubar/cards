package com.micro.cards.mapper;

import com.micro.cards.dto.CardDto;
import com.micro.cards.entity.Card;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public Card mapDtoToModel(CardDto cardDto) {
        Card card = new Card();
        mapDtoToModel(cardDto, card);
        return card;
    }

    public void mapDtoToModel(CardDto cardDto, Card cardModel) {
        cardModel.setCardNumber(cardDto.getCardNumber());
        cardModel.setCardType(cardDto.getCardType());
        cardModel.setAvailableAmount(cardDto.getAvailableAmount());
        cardModel.setAmountUsed(cardDto.getAmountUsed());
        cardModel.setMobileNumber(cardDto.getMobileNumber());
        cardModel.setTotalLimit(cardDto.getTotalLimit());
    }

    public CardDto mapModelToDto(Card card) {
        CardDto cardDto = new CardDto();
        mapModelToDto(card, cardDto);
        return cardDto;
    }

    public void mapModelToDto(Card card, CardDto cardDto) {
        cardDto.setCardNumber(card.getCardNumber());
        cardDto.setCardType(card.getCardType());
        cardDto.setAvailableAmount(card.getAvailableAmount());
        cardDto.setAmountUsed(card.getAmountUsed());
        cardDto.setMobileNumber(card.getMobileNumber());
        cardDto.setTotalLimit(card.getTotalLimit());
    }
}
