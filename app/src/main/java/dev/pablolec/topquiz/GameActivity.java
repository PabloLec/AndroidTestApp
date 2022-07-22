package dev.pablolec.topquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import dev.pablolec.topquiz.dao.QuestionDao;
import dev.pablolec.topquiz.model.Question;
import dev.pablolec.topquiz.model.QuestionBank;

public class GameActivity extends AppCompatActivity {
    private TextView mQuestionTextView;
    private Button mAnswer1Button;
    private Button mAnswer2Button;
    private Button mAnswer3Button;
    private Button mAnswer4Button;
    private QuestionBank qb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mQuestionTextView = findViewById(R.id.game_activity_textview_question);
        mAnswer1Button = findViewById(R.id.game_activity_button_1);
        mAnswer2Button = findViewById(R.id.game_activity_button_2);
        mAnswer3Button = findViewById(R.id.game_activity_button_3);
        mAnswer4Button = findViewById(R.id.game_activity_button_4);

        try {
            this.qb = new QuestionBank(new QuestionDao().loadAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}