package com.example.webservice.repositories;

import com.example.webservice.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Card getCardByCardNumber(String cardNumber);

    Card findByCardNumber(String cardNumber);

    Card getCardByUserForCard(String userForCard);

    Card findByUserForCard(String userForCard);

    boolean existsByCardNumber(String cardNumber);

}