package edu.andrews.cptr252.brodis.quizapp;

import androidx.fragment.app.Fragment;

public class QuestionListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {return new QuestionListFragment();}

}
