package dev.pablolec.topquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dev.pablolec.topquiz.dao.QuestionDao;
import dev.pablolec.topquiz.model.Question;
import dev.pablolec.topquiz.model.QuestionBank;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mQuestionTextView;
    private QuestionBank mQuestionBank;
    private final List<Button> mAnswerButtons = new ArrayList<>();
    private int mAnswerIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mQuestionTextView = findViewById(R.id.game_activity_textview_question);
        mAnswerButtons.add(findViewById(R.id.game_activity_button_1));
        mAnswerButtons.add(findViewById(R.id.game_activity_button_2));
        mAnswerButtons.add(findViewById(R.id.game_activity_button_3));
        mAnswerButtons.add(findViewById(R.id.game_activity_button_4));

        for (Button b : mAnswerButtons) {
            b.setOnClickListener(this);
        }

        try {
            this.mQuestionBank = new QuestionBank(new QuestionDao().loadAll());
        } catch (Exception e) {
            e.printStackTrace();
        }

        displayQuestion(mQuestionBank.getNextQuestion());
    }

    @Override
    public void onClick(View view) {
        int providedAnswerIndex = mAnswerButtons.indexOf((Button) view);
        if (isCorrect(providedAnswerIndex)) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isCorrect(int providedAnswerIndex) {
        return providedAnswerIndex == mAnswerIndex;
    }

    private void displayQuestion(Question question) {
        mQuestionTextView.setText(question.getQuestion());
        List<String> answers = question.getChoiceList();
        mAnswerButtons.get(0).setText(answers.get(0));
        mAnswerButtons.get(1).setText(answers.get(1));
        mAnswerButtons.get(2).setText(answers.get(2));
        mAnswerButtons.get(3).setText(answers.get(3));
        mAnswerIndex = question.getAnswerIndex();
    }
}