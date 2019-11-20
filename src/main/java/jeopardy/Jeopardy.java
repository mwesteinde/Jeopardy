package jeopardy;

/* import standard Java collections */
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* import json library */
import com.google.gson.*;

public class Jeopardy {

    private String category;
    private int value;
    private String question;
    private String answer;

    public Jeopardy(String category, String question, int value, String answer) {
        this.category = category;
        this.question = question;
        this.value = value;
        this.answer = answer;
    }

    public static List<Jeopardy> toList(String jsonFile) {
        JsonParser parser = new JsonParser();
        Gson gson = new Gson();
        List<Jeopardy> qList = new ArrayList<>();
        try (FileReader fr = new FileReader(jsonFile)) {
            JsonElement json = parser.parse(fr);
            if (json.isJsonArray()) {
                JsonArray questionArray = json.getAsJsonArray();
                for (int i = 0; i < questionArray.size(); i++) {
                    JsonObject question = questionArray.get(i).getAsJsonObject();
                    int val = 0;
                    try {
                        val = Integer.parseInt(question.get("value").getAsString().substring(1).replaceAll(",", ""));
                    }
                    catch (Exception e) { /* ignore it for now */ }
                    /* what else? */

                    qList.add(new Jeopardy(cat, que, val, ans)); /* change this */
                }
            }
        }
        catch (IOException ioe) {
            System.out.println("Unexpected");
        }
        System.out.println(qList.size());
        return qList;
    }

    public String question() {
        return this.question;
    }

    public String answer() {
        return this.answer;
    }

    public String category() {
        return this.category;
    }

    public int value() {
        return this.value;
    }

    /* implement the different apply methods.
        they will all return a reference to a list and will take
        an instance of List<Jeopardy> as the first argument and a function as
        the second argument.
        Depending on the second function, you will apply map or filter.
     */

}
