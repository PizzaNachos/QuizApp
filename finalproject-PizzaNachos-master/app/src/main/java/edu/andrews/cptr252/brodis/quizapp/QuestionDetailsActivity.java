package edu.andrews.cptr252.brodis.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import java.util.UUID;

public class QuestionDetailsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        UUID questionId = (UUID)getIntent().getSerializableExtra(QuestionAdapter.EXTRA_QUESTION_ID);
        return QuestionDetailsFragment.newInstance(questionId);
    }

}
