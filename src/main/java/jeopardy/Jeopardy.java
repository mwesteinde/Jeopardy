package jeopardy;

/* import standard Java collections */
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
                    String cat;
                    String ans;
                    String que;
                    try {
                        val = Integer.parseInt(question.get("value").getAsString().substring(1).replaceAll(",", ""));
                    }
                    catch (Exception e) { /* ignore it for now */ }
                    cat = question.get("category").getAsString().replaceAll(",", "");
                    ans = question.get("answer").getAsString().replaceAll(",", "");
                    que = question.get("question").getAsString().replaceAll(",", "");
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

    public static <T> List<T> apply(List<Jeopardy> list, Function<Jeopardy, T> f) {
        return list.stream().map(f).collect(Collectors.toList());



        //List<T> returned = f.apply(list.stream().collect(toList(T)));
    }

    public static <T> List<Jeopardy> apply(List<Jeopardy> list, Predicate<Jeopardy> f) {
        return list.stream().filter(f).collect(Collectors.toList());
        //List<T> returned = f.apply(list.stream().collect(toList(T)));
    }

    /* implement the different apply methods.
        they will all return a reference to a list and will take
        an instance of List<Jeopardy> as the first argument and a function as
        the second argument.
        Depending on the second function, you will apply map or filter.
     */

}
