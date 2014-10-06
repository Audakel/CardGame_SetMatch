package com.example.root.SetCardGamev2;

import java.util.Random;

/**
 * Created by audakel on 10/4/14.
 */
public class SetDeck extends Deck {
    public SetDeck(){
        super();
        Random rand = new Random();
        int randomInteger;

        for (int i = 0; i < 52; i++){
            SetCard setCard = new SetCard();

//          int number
            setCard.number = rand.nextInt(3);

//          String Shape;
            randomInteger = rand.nextInt(6);

            if (randomInteger < 2){
                setCard.shape = 0;
            } else if (randomInteger < 4){
                setCard.shape = 1;
            } else {
                setCard.shape = 2;
            }

//          String color;
            randomInteger = rand.nextInt(6);

            if (randomInteger < 2){
                setCard.color = setCard.colorArray[0];
            } else if (randomInteger < 4){
                setCard.color = setCard.colorArray[1];
            } else {
                setCard.color = setCard.colorArray[2];
            }

//          String shading;
            randomInteger = rand.nextInt(6);

            if (randomInteger < 2){
                setCard.shading = setCard.shadingArray[0];
            } else if (randomInteger < 4){
                setCard.shading = setCard.shadingArray[1];
            } else {
                setCard.shading = setCard.shadingArray[2];
            }


            //Set the actual "displayShape"
            setCard.displayShape = setCard.shapeArray[setCard.shading][setCard.shape];


            addCard(setCard, true);
        }
    }

}
