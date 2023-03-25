package com.eis.util;

import com.eis.logic.Card;

import java.util.ArrayList;

public class Util {
    public static ArrayList<Card> deckContainsColor(ArrayList<Card> deck, String color){
        ArrayList<Card> cards = new ArrayList<>();
        for (Card card:deck){
            if (card.getColor().equals(color)){
                cards.add(card);
            }
        }
        return cards;
    }

    public static ArrayList<Card> deckContainsNumber(ArrayList<Card> deck, int number){
        ArrayList<Card> cards = new ArrayList<>();
        for (Card card:deck){
            if (card.getNumber() == number){
                cards.add(card);
            }
        }
        return cards;
    }

}
