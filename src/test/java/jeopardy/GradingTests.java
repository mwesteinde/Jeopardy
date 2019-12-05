package jeopardy;

import org.junit.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;

import static junit.framework.Assert.assertEquals;

public class GradingTests {

    @Test
    public void test1() {
        List<Jeopardy> list = Jeopardy.toList("data/jeopardy-small.json");
        Function<Jeopardy, String> f =
                (q -> {
                    if (q.value() >= 5000) {
                        return q.question();
                    } else return null;
                });
        HashSet<String> set2 = new HashSet<>(Jeopardy.apply(list, f)); // you have to implement apply
        assertEquals(3, set2.size());
    }

    @Test
    public void test2() {
        List<Jeopardy> list = Jeopardy.toList("data/jeopardy-small.json");
        Function<Jeopardy, String> f = Jeopardy::category;
        List<String> list2 = Jeopardy.apply(list, f); // you have to implement apply
        assertEquals(159, new HashSet<>(list2).size(), 12);
    }

    // @Test
    // public void test3() {
    //     List<Jeopardy> list = Jeopardy.toList("data/jeopardy-small.json");
    //     Predicate<Jeopardy> f = (q -> q.category().equals("THE COMPANY LINE"));
    //     List<Jeopardy> list2 = Jeopardy.apply(list, f); // you have to implement apply
    //     assertEquals(5, list2.size());
    // }

    @Test
    public void test4() {
        List<Jeopardy> list = Jeopardy.toList("data/jeopardy-small.json");
        Function<Jeopardy, Integer> f = (q -> q.value());
        List<Integer> list2 = Jeopardy.apply(list, f); // you have to implement apply
        assertEquals(java.util.Optional.of(5000), list2.stream().max(Comparator.naturalOrder()));
    }

    @Test
    public void test5() {
        List<Jeopardy> list = Jeopardy.toList("data/jeopardy-small.json");
        Function<Jeopardy, String> f =
                (q -> {
                    if (q.category().contains("ESPN")) {
                        return q.question();
                    }
                    else return null;
                });
        HashSet<String> set2 = new HashSet<>(Jeopardy.apply(list, f)); // you have to implement apply
        assertEquals(6, set2.size());
    }
}
