package com.eis.game;

import com.eis.logic.Card;
import com.eis.logic.Player;
import com.eis.util.Util;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Controller {

    private List<String> names;
    private List<Card> deck;
    private Game currentGame;
    private static final String DEFAULT_DECK_PATH = "resources/defaultCards.xml";
    private static final int DEFAULT_PLAYER_COUNT = 4;
    private static final int DEFAULT_CARDS_PER_PLAYER = 7;

    public Controller() {
        try {
            names = Files.readAllLines(Paths.get("resources/playerNames.txt"), StandardCharsets.UTF_8);
            deck = getCards(DEFAULT_DECK_PATH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        startNewGame(DEFAULT_PLAYER_COUNT,DEFAULT_CARDS_PER_PLAYER);
    }

    public Controller(String altPath){
        try {
            names = Files.readAllLines(Paths.get("resources/playerNames.txt"), StandardCharsets.UTF_8);
            deck = getCards(altPath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        startNewGame(DEFAULT_PLAYER_COUNT,DEFAULT_CARDS_PER_PLAYER);
    }

    public void startNewGame(int playerCount, int startingCardsPerPlayer){
        ArrayList<Card> stack = createStack();
        if (stack.size() > playerCount*startingCardsPerPlayer+1){
            ArrayList<Player> players = new ArrayList<>();
            players.add(new Player(0, "Player"));
            for (int i = 1; i < playerCount; i++) {
                players.add(new Player(i, randomName()));
            }
            for (Player p : players) {
                ArrayList<Card> playerCards = new ArrayList<>(stack.subList(0, 7));
                stack.removeAll(playerCards);
                p.setCards(playerCards);
            }
            currentGame = new Game(players, stack);
            currentGame.setCurrentPlayer(players.get((int) (Math.random()*playerCount)));

            if (currentGame.getCurrentPlayer().getId() != 0){
                npcMove();
            }
        } else {
            System.err.println("Not enough cards to start game");
        }
    }

    public ArrayList<Card> createStack(){
        ArrayList<Card> stack = new ArrayList<>(deck);
        Collections.shuffle(stack);
        return stack;
    }
    public String randomName(){
        int size = names.size();
        return names.get((int)(Math.random()*size));
    }
    private ArrayList<Card> getCards(String path) throws ParserConfigurationException, IOException, SAXException {
        ArrayList<Card> deck = new ArrayList<>();
        File xml = new File(path);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xml);
        Element element = doc.getDocumentElement();
        NodeList cards = element.getChildNodes();
        for (int i = 0; i < cards.getLength(); i++){
            if (cards.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element card = (Element) cards.item(i);
                Card newCard = new Card(Integer.parseInt(card.getElementsByTagName("id").item(0).getTextContent()),
                        card.getElementsByTagName("name").item(0).getTextContent(),
                        card.getElementsByTagName("color").item(0).getTextContent(),
                        Integer.parseInt(card.getElementsByTagName("number").item(0).getTextContent()),
                        card.getElementsByTagName("isSpecial").item(0).getTextContent().equals("1"));
                for (int e = 0; e < Integer.parseInt(card.getElementsByTagName("amount").item(0).getTextContent()); e++) {
                    deck.add(new Card(newCard));
                }
            }
        }
        return deck;
    }

    private void move(Card card){
        currentGame.getCurrentPlayer().getCards().remove(card);
        currentGame.getDiscardStack().add(currentGame.getCurrentCard());
        currentGame.setCurrentCard(card);
    }

    private void drawCard(ArrayList<Card> hand){
        if (deck.size()==0){
            Collections.shuffle(currentGame.getDiscardStack());
            deck.addAll(currentGame.getDiscardStack());
            currentGame.setDiscardStack(new ArrayList<>());
        }
        hand.add(deck.get(0));
        deck.remove(0);
    }

    public void npcMove(){
        Card currentCard = currentGame.getCurrentCard();
        Player currentPlayer = currentGame.getCurrentPlayer();
        ArrayList<Card> possibleCards = new ArrayList<>();
        ArrayList<Card> drawCards = Util.deckContainsNumber(currentPlayer.getCards(),-3);
        drawCards.addAll(Util.deckContainsNumber(currentPlayer.getCards(),-4));
        if (drawCards.size()<1) {
            for (Card card : currentGame.getCurrentPlayer().getCards()) {
                if (card.getColor().equals(currentCard.getColor()) || card.getNumber() == currentCard.getNumber() || card.getColor().equals("Black")) {
                    possibleCards.add(card);
                }
            }
        } else {
            possibleCards = drawCards;
        }
        if (possibleCards.size()==0){
            drawCard(currentPlayer.getCards());
        } else {
            move(possibleCards.get((int) (Math.random()*possibleCards.size())));
        }
    }
}
