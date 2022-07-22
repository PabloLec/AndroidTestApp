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
    private Button mAnswer1Button;
    private Button mAnswer2Button;
    private Button mAnswer3Button;
    private Button mAnswer4Button;
    private QuestionBank mQuestionBank;
    private final List<Button> mAnswerButtons = new ArrayList<>();
    private int answerIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mQuestionTextView = findViewById(R.id.game_activity_textview_question);
        mAnswer1Button = findViewById(R.id.game_activity_button_1);
        mAnswer2Button = findViewById(R.id.game_activity_button_2);
        mAnswer3Button = findViewById(R.id.game_activity_button_3);
        mAnswer4Button = findViewById(R.id.game_activity_button_4);
        mAnswerButtons.add(mAnswer1Button);
        mAnswerButtons.add(mAnswer2Button);
        mAnswerButtons.add(mAnswer3Button);
        mAnswerButtons.add(mAnswer4Button);

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
        return providedAnswerIndex == answerIndex;
    }

    private void displayQuestion(Question question) {
        mQuestionTextView.setText(question.getQuestion());
        List<String> answers = question.getChoiceList();
        mAnswer1Button.setText(answers.get(0));
        mAnswer2Button.setText(answers.get(1));
        mAnswer3Button.setText(answers.get(2));
        mAnswer4Button.setText(answers.get(3));
        answerIndex = question.getAnswerIndex();
    }
}