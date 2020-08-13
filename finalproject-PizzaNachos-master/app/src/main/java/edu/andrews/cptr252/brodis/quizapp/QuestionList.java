package edu.andrews.cptr252.brodis.quizapp;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

public class QuestionList {
    private static QuestionList sOurInstance;

    private static final String TAG ="Buglist";
    private static final String FILENAME = "questions.json";
    private QuestionJSONSerializer mSerializer;

    public boolean saveQuestions(){
        try{
            mSerializer.saveQuestions(mQuestions);
            Log.d(TAG,"Bugs saved to file");
            return true;
        }catch (Exception e){
            Log.e(TAG, "Error saving bugs: " + e);
            return false;
        }
    }



    public question getQuestion(UUID id){
        for(question q : mQuestions){
            if(q.getId().equals(id))
                return q;
        }
        return null;
    }

    public static QuestionList getInstance(Context c) {
        if(sOurInstance == null) {
            sOurInstance = new QuestionList(c.getApplicationContext());
        }
        return sOurInstance;
    }

    /**array list of questions
     *
     */
    private ArrayList<question> mQuestions;

    private Context mAppContext;

    public void addQuestion(question q){
        mQuestions.add(q);
    }

    private QuestionList(Context appContext) {
        mAppContext = appContext;
        mSerializer = new QuestionJSONSerializer(mAppContext, FILENAME);

        try{
            mQuestions = mSerializer.loadQuestions();
        } catch (Exception e){
            mQuestions = new ArrayList<>();
            Log.e(TAG, "Error loading bugs: " + e);
        }
    }

    public ArrayList<question> getQuestions() {return mQuestions;}

    public void addQuestion(int position, question q){
        mQuestions.add(position, q);
        saveQuestions();
    }

    public void deleteQuestion(int position){
        mQuestions.remove(position);
        saveQuestions();
    }

}
