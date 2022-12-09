package com.example.webservice.services;

import com.example.webservice.models.Card;
import com.example.webservice.utils.ApiResponse;

import java.util.List;

public interface CardService {

    List<Card> getAllCards();

    Card getCardById(Long cardId);

    Card getCardByCardNumber(String cardNumber);

    ApiResponse addCard(Card card);

    ApiResponse updateCardById(Long cardId, Card card);

    ApiResponse deleteCard(Long cardId);
}