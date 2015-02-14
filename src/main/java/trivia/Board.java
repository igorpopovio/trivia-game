package trivia;

import java.util.LinkedList;

import static trivia.Logger.log;

public class Board {
    LinkedList<Question> popQuestions = new LinkedList<>();
    LinkedList<Question> scienceQuestions = new LinkedList<>();
    LinkedList<Question> sportsQuestions = new LinkedList<>();
    LinkedList<Question> rockQuestions = new LinkedList<>();

    public Board() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast(new Question("Pop", "Pop Question " + i, "Pop Answer " + i));
            scienceQuestions.addLast(new Question("Science", "Science Question " + i, "Science Answer " + i));
            sportsQuestions.addLast(new Question("Sports", "Sports Question " + i, "Sports Answer " + i));
            rockQuestions.addLast(new Question("Rock", "Rock Question " + i, "Rock Answer " + i));
        }
    }

    public Question askQuestion(int place) {
        String category = currentCategory(place);
        log("The category is %s", category);
        if ("Pop".equals(category))
            return popQuestions.removeFirst();
        if ("Science".equals(category))
            return scienceQuestions.removeFirst();
        if ("Sports".equals(category))
            return sportsQuestions.removeFirst();
        return rockQuestions.removeFirst();
    }

    private String currentCategory(int place) {
        if (place % 4 == 0) return "Pop";
        if (place % 4 == 1) return "Science";
        if (place % 4 == 2) return "Sports";
        return "Rock";
    }
}
