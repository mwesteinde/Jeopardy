package jeopardy;

import org.junit.Test;

import java.util.List;
import java.util.function.Function;

public class JeopardyTest {

    @Test
    public void test1() {
        List<Jeopardy> list = Jeopardy.toList("data/jeopardy-small.json");
        Function<Jeopardy, Boolean> f = (q -> q.value() >= 5000);
        List<Jeopardy> list2 = Jeopardy.apply(list, f); // you have to implement apply
        // complete a valid tast case with assertEquals()
    }

    /* write at least two other test cases */

}
