package com.example.root.SetCardGamev2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Path;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by audakel on 10/2/14.
 */
public class StoreGameResult extends Activity{
    //Constants

    //Properties

    private Date endGame;
    private Date startGame = new Date();
    private int score = 0;
    private Set<String> setOfGameStats = new HashSet<String>();
    private String gameType;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, HH:mm a");

    //Initialization

    StoreGameResult(){
        endGame = startGame;
    }


    //Helpers

    public void setEndGame(Date endGame) {
        this.endGame = endGame;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }


    //Class methods

    public void save(int gameScore, String gameTypeSetOrMatch, FragmentActivity fragmentActivity){
        Date date = new Date();
        endGame = date;
        score = gameScore;
        gameType = gameTypeSetOrMatch;

        gameDataToString();

        SharedPreferences sharedPreferences = fragmentActivity.getSharedPreferences("GameData", 0);
        SharedPreferences.Editor editor;
        editor = sharedPreferences.edit();
        editor.putStringSet(simpleDateFormat.format(startGame), setOfGameStats);
        editor.apply();
    }

    private void gameDataToString(){
        setOfGameStats.add(simpleDateFormat.format(endGame));

        setOfGameStats.add(gameType);

        setOfGameStats.add(Integer.toString(score));

        setOfGameStats.add(simpleDateFormat.format(startGame));

    }

}
