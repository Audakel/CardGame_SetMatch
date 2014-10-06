package com.example.root.SetCardGamev2;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TabMatchismo.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TabMatchismo#newInstance} factory method to
 * create an instance of this fragment.
 *
 */

public class TabMatchismo extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabMatchismo.
     */
    // TODO: Rename and change types and number of parameters
    public static TabMatchismo newInstance(String param1, String param2) {
        TabMatchismo fragment = new TabMatchismo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public TabMatchismo() {
        // Required empty public constructor

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    private List<Button> cardButtons = new ArrayList<Button>();
    private TextView flipCountView;
    private TextView scoreCountView;
    private TextView activityView;
    private TextView activityView2;
    private Button reDealButton;
    int count;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View matchismoView = inflater.inflate(R.layout.fragment_tab_matchismo, container, false);

        ViewGroup buttonGrid = (ViewGroup) matchismoView.findViewById(R.id.gridLayout);
        flipCountView = (TextView) matchismoView.findViewById(R.id.flipCount);
        scoreCountView = (TextView) matchismoView.findViewById(R.id.scoreView);
        activityView = (TextView) matchismoView.findViewById(R.id.activityView);
        countOfButtons = buttonGrid.getChildCount();
        reDealButton = (Button) matchismoView.findViewById(R.id.redrawButton);

        reDealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawDeckButton(v);
            }
        });

        for (int i = 0; i < countOfButtons; i ++) {
            Button buttonBeingCreated = (Button) buttonGrid.getChildAt(i);
            cardButtons.add(buttonBeingCreated);
                buttonBeingCreated.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        flipOneCard(v);
                    }
                });

        }
        drawDeck();
        return matchismoView;
    }


//********************************************************************************************************

    // Constants

    private static final int THREE_CARD_GAME_MATCH_COUNT = 3;
    private static final int TWO_CARD_GAME_MATCH_COUNT = 2;

    // Instance variables

    private Map<Button, PlayingCard> cardMap;
    private int countCardsUp = 0;//
    private int flipCount = 0;
    private int mNumberOfCardsToCompare = TWO_CARD_GAME_MATCH_COUNT;
    private int score = 0;
    private PlayingCard firstDraw = null;
    private PlayingCard secondDraw = null;
    private PlayingCard activityLogCard;
    private PlayingCard activityLogCard2;
    private int countOfButtons;
    StoreGameResult gameResult;

    // "Outlets"




    public void drawDeck() {
        int numberOfCards = countOfButtons;
        PlayingCardDeck playingCardDeck = new PlayingCardDeck();

        cardMap = new HashMap<Button, PlayingCard>();

        for (int i = 0; i < numberOfCards; i++) {
            cardMap.put(cardButtons.get(i), (PlayingCard) playingCardDeck.drawRandomCard());
        }
        gameResult = new StoreGameResult();
    }



    public void drawDeckButton(View v) {
        int numberOfCards = countOfButtons;
        PlayingCardDeck playingCardDeck = new PlayingCardDeck();

        cardMap = new HashMap<Button, PlayingCard>();

        for (int i = 0; i < numberOfCards; i++) {
            cardMap.put(cardButtons.get(i), (PlayingCard) playingCardDeck.drawRandomCard());
        }

        score = 0;
        flipCount = 0;
        firstDraw = null;
        secondDraw = null;
        gameResult = new StoreGameResult();
        updateUI();
    }


    private void updateUI() {
        // Loop over all cards, configure each
        PlayingCard currentCard;
        Button cardImageButton;

        for (int i = 0; i < 16; i++){
            currentCard = cardMap.get(cardButtons.get(i));
            cardImageButton = (Button) cardButtons.get(i);

            //if faceup
            if (currentCard.faceUp){
                cardImageButton.setBackgroundResource(R.drawable.blankplayingcard);
                cardImageButton.setText(currentCard.getContents());
                //cardImageButton.getBackground().setAlpha(255);
                if (currentCard.redBlackColor == 0){cardImageButton.setTextColor(Color.BLACK);}
                else {cardImageButton.setTextColor(Color.RED);}
            }

            //if facedown
            if (!currentCard.faceUp) {
                cardImageButton.setBackgroundResource(R.drawable.bluecard);
                cardImageButton.setText("");
            }

            //if unplayable
            if (currentCard.unplayable) {
                cardImageButton.setBackgroundResource(R.drawable.blankplayingcard);
                cardImageButton.setText(currentCard.getContents());
                cardImageButton.setAlpha(100);

                if (currentCard.redBlackColor == 0){cardImageButton.setTextColor(Color.GRAY);}
                    else {cardImageButton.setTextColor(Color.rgb(255,153,153));}

            }

            // Configure activity label (report of last action)

            if (activityLogCard != null && activityView != null){

                if (activityLogCard.redBlackColor == 0){activityView.setTextColor(Color.BLACK);}
                    else {activityView.setTextColor(Color.RED);}

                activityView.setText("You chose: " + activityLogCard.getContents());
                {
                    final Pattern p = Pattern.compile("You chose:");
                    final Matcher matcher = p.matcher(activityView.getText());

                    final SpannableStringBuilder spannable = new SpannableStringBuilder(activityView.getText());
                    final ForegroundColorSpan span = new ForegroundColorSpan(Color.WHITE);
                    while (matcher.find()) {
                        spannable.setSpan(
                                span, matcher.start(), matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                        );
                    }
                    activityView.setText(spannable);
                }
            }
        }

        // Configure flip-count label
            flipCountView.setText("Flips: " + flipCount);

        // Configure score label
            scoreCountView.setText("Score: " + score);

//        gameResult.save(score, "MATCH");

    }


    public void flipOneCard(View v) {
        Button clickedButton = (Button) v;
        // use clickedButton to get the Model card object for this button
        PlayingCard card = cardMap.get(clickedButton);

        activityLogCard = card;
        activityLogCard2 = firstDraw;
        firstDraw = secondDraw;
        secondDraw = card;
        card.faceUp = (!card.isFaceUp());
        if (!card.faceUp){
            activityLogCard = null;
            secondDraw = null;
            ++score;
        }
        ++flipCount;
        --score;
        checkMatchReturnScore();
        updateUI();

        // Modify model, then call updateUI()
    }


    public void checkMatchReturnScore(){
        if (firstDraw != null && secondDraw != null){
            int updatedScore = firstDraw.compareCardsReturnScore(secondDraw);
            score += updatedScore;
            firstDraw = null;
            secondDraw = null;
        }
    }
//*************************************************************************************************







    //************************************************************************************************************
        // TODO: Rename method, update argument and hook method into UI event
        public void onButtonPressed(Uri uri) {
            if (mListener != null) {
                mListener.onFragmentInteraction(uri);
            }
        }


//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
