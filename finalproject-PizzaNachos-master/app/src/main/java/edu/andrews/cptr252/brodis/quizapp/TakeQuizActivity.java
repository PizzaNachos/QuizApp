package edu.andrews.cptr252.brodis.quizapp;

import androidx.fragment.app.Fragment;

public class TakeQuizActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new TakeQuizFragment();
    }

}