package com.example.root.SetCardGamev2;

import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by audakel on 9/30/14.
 */
public class Deck {
    public Deck() {

    }

    List<Card> cards = new ArrayList<Card>();

    void addCard(Card card, boolean atTop){
        if (atTop){
            cards.add(0, card);
        }
        else {
            cards.add(card);
        }
    }




    public Card drawRandomCard(){
        Card randomCard;

        if (cards.size() > 0){
            Random rand = new Random();
            int index = rand.nextInt(cards.size());
            randomCard = cards.get(index);
            cards.remove(index);
            return randomCard;
        }
        return cards.get(0);
    }
}
