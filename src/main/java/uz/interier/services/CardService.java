package uz.interier.services;

import uz.interier.models.Card;
import uz.interier.utils.ApiResponse;

import java.util.List;

public interface CardService {

    List<Card> getAllCards();

    Card getCardById(Long cardId);

    Card getCardByCardNumber(String cardNumber);

    ApiResponse addCard(Card card);

    ApiResponse updateCardById(Long cardId, Card card);

    ApiResponse deleteCard(Long cardId);
}