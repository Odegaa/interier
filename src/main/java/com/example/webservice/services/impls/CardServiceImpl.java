package com.example.webservice.services.impls;

import com.example.webservice.models.Card;
import com.example.webservice.models.templates.Status;
import com.example.webservice.repositories.CardRepository;
import com.example.webservice.services.CardService;
import com.example.webservice.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public Card getCardById(Long cardId) {
        return cardRepository.findById(cardId).orElse(null);
    }

    @Override
    public Card getCardByCardNumber(String cardNumber) {
        Card byCardNumber = cardRepository.findByCardNumber(cardNumber);
        if (byCardNumber == null) {
            return null;
        }
        return cardRepository.getCardByCardNumber(cardNumber);
    }

    @Override
    public ApiResponse addCard(Card card) {
        boolean byCardNumber = cardRepository.existsByCardNumber(card.getCardNumber());
        if (byCardNumber) {
            return new ApiResponse("This card number already exist!", false);
        }
        Card newCard = new Card();
        newCard.setUserForCard(card.getUserForCard());
        newCard.setCardNumber(card.getCardNumber());
        newCard.setCvcCode(card.getCvcCode());
        newCard.setStatus(Status.ACTIVE);
        cardRepository.save(newCard);
        return new ApiResponse("Card successfully SAVED!", true);
    }

    @Override
    public ApiResponse updateCardById(Long cardId, Card card) {
        Optional<Card> optionalCard = cardRepository.findById(cardId);
        if (optionalCard.isPresent()) {
            boolean byCardNumber = cardRepository.existsByCardNumber(card.getCardNumber());
            if (byCardNumber) {
                return new ApiResponse("This card number already exist!", false);
            }
            Card updatingCard = optionalCard.get();
            updatingCard.setUserForCard(card.getUserForCard());
            updatingCard.setCardNumber(card.getCardNumber());
            updatingCard.setCvcCode(card.getCvcCode());
            updatingCard.setStatus(Status.ACTIVE);
            return new ApiResponse("Card successfully UPDATED!", true);
        }
        return new ApiResponse("Card not found!", false);
    }

    @Override
    public ApiResponse deleteCard(Long cardId) {
        Optional<Card> optional = cardRepository.findById(cardId);
        if (optional.isPresent()) {
            cardRepository.deleteById(cardId);
            return new ApiResponse("This card successfully DELETED!", true);
        }
        return new ApiResponse("This card number not found!", false);
    }
}