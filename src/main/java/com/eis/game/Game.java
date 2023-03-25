package com.eis.game;

import com.eis.logic.Card;
import com.eis.logic.Player;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private ArrayList<Card> stack;
    private ArrayList<Card> discardStack;
    private Card currentCard;
    private Player currentPlayer;

    public Game(ArrayList<Player> players, ArrayList<Card> stack) {
        this.players = players;
        this.stack = stack;
        this.discardStack = new ArrayList<>();
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setStack(ArrayList<Card> stack) {
        this.stack = stack;
    }

    public void setDiscardStack(ArrayList<Card> discardStack) {
        this.discardStack = discardStack;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Card> getStack() {
        return stack;
    }

    public ArrayList<Card> getDiscardStack() {
        return discardStack;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
