package com.eis.logic;

public class Card{
    private int id;
    private String name;
    private String color;
    private int number;
    private boolean isSpecial;

    public Card(int id, String name, String color, int number, boolean isSpecial) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.number = number;
        this.isSpecial = isSpecial;
    }

    //Supposed to be used to avoid replica objects
    public Card(Card card){
        this.id =  card.id;
        this.name = card.name;
        this.color = card.color;
        this.number = card.number;
        this.isSpecial = card.isSpecial;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getNumber() {
        return number;
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setSpecial(boolean special) {
        isSpecial = special;
    }
}
