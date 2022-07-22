package dev.pablolec.topquiz.model;

import java.util.Collections;
import java.util.List;

public class QuestionBank {
    private final List<Question> mQuestionList;
    private int mNextQuestionIndex;

    public QuestionBank(List<Question> mQuestionList) {
        this.mQuestionList = mQuestionList;
        this.mNextQuestionIndex = 0;
        Collections.shuffle(mQuestionList);
    }

    public Question getNextQuestion() {
        Question nextQuestion = this.mQuestionList.get(this.mNextQuestionIndex);
        this.mNextQuestionIndex++;
        return nextQuestion;
    }

}
