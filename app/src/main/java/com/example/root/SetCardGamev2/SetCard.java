package com.example.root.SetCardGamev2;

import android.graphics.Color;

/**
 * Created by audakel on 10/2/14.
 */
public class SetCard extends Card {

    //Constants

    //No shading, partial, full
    //Square, triangle, circle
    String[][] shapeArray ={{"□", "▽", "○"}, {"▥","◬","◍"}, {"■","▼","●"}} ;

//  String[][] shapeArray ={{"\u25A1", "\u25BD", "\u25CB"}, {"\u25A5","\u25EC","\u25CD"}, {"\u25A0","\u25BC","\u25CF"}} ;
    //String[] shapeArray = {"\u25A1", "\u25A0", "\u25B5", "\u25B4", "\u25CB", "\u25CF"};
    int[] colorArray = {Color.RED, Color.rgb(0, 153, 0), Color.BLUE};
    int[] shadingArray = {0,1,2};

    //Properties



    String displayShape;
    int shape;
    int shading;
    int color;
    int number;

    //Initializations

    //Helpers

    public String getDisplayShape() {
        return displayShape;
    }

    public int getColor() {
        return color;
    }

    public int getShading() {
        return shading;
    }

    public int getNumber() {
        return number;
    }

    public int getShape() {
        return shape;
    }



    //Class Methods

    public int compareCardsReturnScore(SetCard cardToCompare1, SetCard cardToCompare2) {
        int countOfMatchConditions = 0;

        int number1 = this.getNumber();
        int shading1 = this.getShading();
        int color1 = this.getColor();
        int shape1 = this.getShape();

        int number2 = cardToCompare1.getNumber();
        int shading2 = cardToCompare1.getShading();
        int color2 = cardToCompare1.getColor();
        int shape2 = cardToCompare1.getShape();

        int number3 = cardToCompare2.getNumber();
        int shading3 = cardToCompare2.getShading();
        int color3 = cardToCompare2.getColor();
        int shape3 = cardToCompare2.getShape();

        if ((number1 == number2 && number2 == number3) || (number1 != number2 && number2 != number3)){
            countOfMatchConditions++;
        }

        if ((shape1 == (shape2) && shape2 == (shape3)) || (shape1 != shape2 && shape2 != (shape3))){
            countOfMatchConditions++;
        }

        if ((shading1 == shading2 && shading2 == shading3) || (shading1 != shading2 && shading2 != shading3)){
            countOfMatchConditions++;
        }

        if ((color1 == color2 && color2 == color3) || (color1 != color2 && color2 != color3)){
            countOfMatchConditions++;
        }

        return (countOfMatchConditions == 4) ? 4 : -1;


    }


    }
