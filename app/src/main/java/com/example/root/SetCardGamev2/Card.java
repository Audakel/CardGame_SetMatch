package com.example.root.SetCardGamev2;

import android.app.Activity;
import android.content.Context;
import android.view.View;

/**
 * Created by audakel on 9/30/14.
 */
public class Card {
    String contents = "";
    boolean faceUp = false;
    boolean unplayable = false;



    public int match(Card otherCards[]){
        int score = 0;

        for (Card card : otherCards) {
            if (card.contents == contents){
                score = 1;
                break;
            }
        }
        return score;

    }
}
