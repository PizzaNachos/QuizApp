package edu.andrews.cptr252.brodis.quizapp;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TakeQuizFragment extends Fragment {

    private static final String TAG= "TAKEQUIZFRAGMENT";


    private TextView question_title;
    private Button true_button;
    private Button false_button;
    private Context context;
    private ArrayList<question> mQuestions;



    int mCurrentIndex;
    int score;

    public TakeQuizFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mQuestions = QuestionList.getInstance(getActivity()).getQuestions();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        score = 0;
        mCurrentIndex = 0;
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_take_quiz, container, false);
        context = v.getContext();

        question_title = v.findViewById(R.id.take_quiz_question_textView);


        if(mQuestions.size() == 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("No quiz has been created! Please go add some questions!");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(context, QuestionListActivity.class);
                    context.startActivity(i);
                }
            });
            builder.setCancelable(false);
            AlertDialog dialog = builder.create();
            dialog.show();
            Log.d(TAG, "No quiz found, redirecting...");
            return v;
        }
        question_title.setText(mQuestions.get(mCurrentIndex).getTitle());

        true_button = v.findViewById(R.id.take_quiz_true_button);
        false_button = v.findViewById(R.id.take_quiz_false_button);

        final Toast correct_toast = Toast.makeText(context, "Correct",Toast.LENGTH_SHORT);
        final Toast incorrect_toast = Toast.makeText(context, "Incorrect",Toast.LENGTH_SHORT);
        true_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mQuestions.get(mCurrentIndex).getAnswer() == true){
                    score++;
                    if(mCurrentIndex+1 <  mQuestions.size()){
                        mCurrentIndex++;
                    } else {
                        endQuiz();
                        return;
                    }
                    Log.d("TAKEQUIZFRAGMENT", "current index: " + mCurrentIndex + "Quuestions size: " + mQuestions.size());
                    correct_toast.show();
                    question_title.setText(mQuestions.get(mCurrentIndex).getTitle());
                } else {
                    if(mCurrentIndex+1 <  mQuestions.size()){
                        mCurrentIndex++;
                    } else {
                        endQuiz();
                        return;
                    }
                    Log.d("TAKEQUIZFRAGMENT", "current index: " + mCurrentIndex + "Quuestions size: " + mQuestions.size());
                    incorrect_toast.show();
                    question_title.setText(mQuestions.get(mCurrentIndex).getTitle());
                }
            }
        });

        false_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mQuestions.get(mCurrentIndex).getAnswer() == false){
                    score++;
                    if(mCurrentIndex+1 <  mQuestions.size()){
                        mCurrentIndex++;
                    } else {
                        endQuiz();
                        return;
                    }
                    Log.d("TAKEQUIZFRAGMENT", "current index: " + mCurrentIndex + "Quuestions size: " + mQuestions.size());
                    correct_toast.show();
                    question_title.setText(mQuestions.get(mCurrentIndex).getTitle());

                } else {
                    if(mCurrentIndex+1 <  mQuestions.size()){
                        mCurrentIndex++;
                    } else {
                        endQuiz();
                        return;
                    }
                    incorrect_toast.show();
                    question_title.setText(mQuestions.get(mCurrentIndex).getTitle());
                }
            }
        });

        return v;
    }

    public void endQuiz(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("You got a score of " + score + " out of " + mQuestions.size());
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(context, QuizMenuActivity.class);
                context.startActivity(i);
            }
        });
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
        Log.d(TAG, "End of endQuiz()");
    }
}
