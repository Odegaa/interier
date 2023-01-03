package uz.interier.controllers;

import uz.interier.models.Card;
import uz.interier.services.CardService;
import uz.interier.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    private final CardService service;

    @Autowired
    public CardController(CardService service) {
        this.service = service;
    }

    @PostMapping
    public HttpEntity<?> addCard(@RequestBody Card card) {
        ApiResponse apiResponse = service.addCard(card);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.CREATED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        return ResponseEntity.ok(service.getAllCards());
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<Card> getCardById(@PathVariable Long cardId) {
        return ResponseEntity.ok(service.getCardById(cardId));
    }

    @GetMapping("/{cardNumber}")
    public ResponseEntity<Card> getCardByCardNumber(@PathVariable String cardNumber) {
        return ResponseEntity.ok(service.getCardByCardNumber(cardNumber));
    }

    @PutMapping("/{cardId}")
    public HttpEntity<?> updateCardById(@PathVariable Long cardId,
                                        @Valid @RequestBody Card card) {
        ApiResponse apiResponse = service.updateCardById(cardId, card);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.ACCEPTED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }

    @DeleteMapping("/{cardId}")
    public HttpEntity<?> deleteCardById(@PathVariable Long cardId) {
        ApiResponse apiResponse = service.deleteCard(cardId);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.ACCEPTED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }
}