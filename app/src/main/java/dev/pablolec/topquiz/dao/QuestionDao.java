package dev.pablolec.topquiz.dao;

import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import dev.pablolec.topquiz.App;
import dev.pablolec.topquiz.model.Question;

public class QuestionDao {
    private final String dataBaseFile = "questions.json";

    public List<Question> loadAll() throws Exception {
        InputStream is = App.getContext().getAssets().open(dataBaseFile);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        String jsonString = new String(buffer, StandardCharsets.UTF_8);

        Question[] questionsArray = new GsonBuilder().create().fromJson(jsonString, Question[].class);
        return Arrays.asList(questionsArray);
    }
}
