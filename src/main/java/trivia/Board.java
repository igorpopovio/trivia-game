package trivia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Board {
    private static final int NUMBER_OF_DUMMY_QUESTIONS = 50;

    private HashMap<String, LinkedList<Question>> questions;
    private List<String> categories;

    public Board() {
        this.questions = new HashMap<>();
        this.categories = new ArrayList<>();
        generateDummyQuestions();
    }

    private void generateDummyQuestions() {
        for (int i = 0; i < NUMBER_OF_DUMMY_QUESTIONS; i++) {
            addQuestion(generateDummyQuestion("Pop", i));
            addQuestion(generateDummyQuestion("Science", i));
            addQuestion(generateDummyQuestion("Sports", i));
            addQuestion(generateDummyQuestion("Rock", i));
        }
    }

    private Question generateDummyQuestion(String category, int number) {
        return new Question(category, category + " Question " + number, category + " Answer " + number);
    }

    private void addQuestion(Question question) {
        if (!categories.contains(question.getCategory()))
            categories.add(question.getCategory());

        LinkedList<Question> subQuestions = questions.get(question.getCategory());
        if (subQuestions == null)
            subQuestions = new LinkedList<>();
        subQuestions.add(question);
        questions.put(question.getCategory(), subQuestions);
    }

    private Question removeQuestion(String category) {
        return questions.get(category).removeFirst();
    }

    public Question retrieveQuestionFor(int place) {
        return removeQuestion(getCategoryFor(place));
    }

    private String getCategoryFor(int place) {
        return categories.get(place % categories.size());
    }
}
