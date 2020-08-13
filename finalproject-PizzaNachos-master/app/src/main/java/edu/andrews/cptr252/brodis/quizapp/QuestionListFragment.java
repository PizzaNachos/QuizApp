package edu.andrews.cptr252.brodis.quizapp;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionListFragment extends Fragment {
    /**
     * Tag for message log
     */
    private static final String TAG = "QuestionListFragment";

    private ArrayList<question> mQuestions;

    /**
     * Recyler view that displays list of quesitons
     */
    private RecyclerView mRecyclerView;

    private  QuestionAdapter mQuestionAdapter;

    public QuestionListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle("Question List");
        mQuestions = QuestionList.getInstance(getActivity()).getQuestions();

        mQuestionAdapter = new QuestionAdapter(mQuestions, getActivity());

        for(question q: mQuestions){
            Log.d(TAG, q.getTitle());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_question_list, container, false);

        mRecyclerView = v.findViewById(R.id.question_list_recyclerView);
        mRecyclerView.setAdapter(mQuestionAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        QuestionSwiper bugSwiper = new QuestionSwiper(mQuestionAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(bugSwiper);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        return v;
    }

    @Override
    public void onResume(){
        super.onResume();
        mQuestionAdapter.notifyDataSetChanged();
    }

    private void addQuestion(){
        question q = new question();
        QuestionList.getInstance(getActivity()).addQuestion(q);

        Intent i = new Intent(getActivity(), QuestionDetailsActivity.class);
        i.putExtra(QuestionAdapter.EXTRA_QUESTION_ID, q.getId());

        startActivityForResult(i, 0);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_question_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.question_list_menu_add_question_menu:
                addQuestion();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
