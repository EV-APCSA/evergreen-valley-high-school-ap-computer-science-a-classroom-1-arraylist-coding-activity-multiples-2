import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for NumberList.
 *
 * Suggested point breakdown (total 20 pts):
 *
 *  - multiplesOf3_notNullAndSize           [3 pts]
 *  - multiplesOf3_firstAndLastValues       [3 pts]
 *  - multiplesOf3_fullSequenceCorrect      [4 pts]
 *  - toString_notNullAndHasNewlines        [3 pts]
 *  - toString_containsAllNumbersInOrder    [3 pts]
 *  - removeEvens_removesAllEvenNumbers     [2 pts]
 *  - removeEvens_resultsInCorrectOddSequence [2 pts]
 *
 * You can use this breakdown when grading, or just treat
 * all tests as equal weight if you prefer.
 */
class NumberListTest {

    /** Helper: build expected 0,3,6,...,297 */
    private List<Integer> expectedMultiples() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(3 * i);
        }
        return list;
    }

    /** Helper: only odd multiples of 3 from 0..297 */
    private List<Integer> expectedOddMultiples() {
        List<Integer> all = expectedMultiples();
        List<Integer> odds = new ArrayList<>();
        for (int n : all) {
            if (n % 2 != 0) {
                odds.add(n);
            }
        }
        return odds;
    }

    // ---------- multiplesOf3 tests ----------

    // [3 pts] multiplesOf3: list is not null and has size 100
    @Test
    void multiplesOf3_notNullAndSize() {
        NumberList nl = new NumberList();
        ArrayList<Integer> list = nl.multiplesOf3();

        assertNotNull(list, "[3 pts] multiplesOf3 should not return null");
        assertEquals(100, list.size(),
                "[3 pts] List returned by multiplesOf3 should contain 100 numbers");
    }

    // [3 pts] multiplesOf3: first value is 0, last value is 297
    @Test
    void multiplesOf3_firstAndLastValues() {
        NumberList nl = new NumberList();
        ArrayList<Integer> list = nl.multiplesOf3();

        assertNotNull(list, "[3 pts] multiplesOf3 should not return null");
        assertTrue(list.size() >= 2,
                "[3 pts] List returned by multiplesOf3 should contain at least 2 elements");
        assertEquals(0, list.get(0),
                "[3 pts] First element of list should be 0");
        assertEquals(297, list.get(99),
                "[3 pts] 100th element of list (index 99) should be 297");
    }

    // [4 pts] multiplesOf3: complete 0,3,6,...,297 sequence in order
    @Test
    void multiplesOf3_fullSequenceCorrect() {
        NumberList nl = new NumberList();
        ArrayList<Integer> list = nl.multiplesOf3();

        assertNotNull(list, "[4 pts] multiplesOf3 should not return null");
        assertEquals(expectedMultiples(), list,
                "[4 pts] List should contain 0,3,6,...,297 in order");
    }

    // ---------- toString tests ----------

    // [3 pts] toString: returns non-null string and has enough newlines for 10 rows
    @Test
    void toString_notNullAndHasNewlines() {
        NumberList nl = new NumberList();
        ArrayList<Integer> list = nl.multiplesOf3();
        String s = nl.toString(list);

        assertNotNull(s, "[3 pts] toString should not return null");

        long newlines = s.chars().filter(ch -> ch == '\n').count();
        assertTrue(newlines >= 9,
                "[3 pts] Expected at least 9 newline characters (10 rows of numbers)");
    }

    // [3 pts] toString: contains all multiples in correct order
    @Test
    void toString_containsAllNumbersInOrder() {
        NumberList nl = new NumberList();
        ArrayList<Integer> list = nl.multiplesOf3();
        String s = nl.toString(list);

        assertNotNull(s, "[3 pts] toString should not return null");
        List<Integer> parsed = extractInts(s);

        assertEquals(expectedMultiples(), parsed,
                "[3 pts] toString output should contain all multiples of 3 in order");
    }

    // ---------- removeEvens tests ----------

    // [2 pts] removeEvens: list contains no even numbers afterward
    @Test
    void removeEvens_removesAllEvenNumbers() {
        NumberList nl = new NumberList();
        ArrayList<Integer> list = nl.multiplesOf3();

        nl.removeEvens(list);

        assertNotNull(list, "[2 pts] List should not be null after removeEvens");

        for (int n : list) {
            assertNotEquals(0, n % 2,
                    "[2 pts] List should not contain even numbers after removeEvens");
        }
    }

    // [2 pts] removeEvens: resulting list matches expected odd multiples of 3
    @Test
    void removeEvens_resultsInCorrectOddSequence() {
        NumberList nl = new NumberList();
        ArrayList<Integer> list = nl.multiplesOf3();

        nl.removeEvens(list);

        assertEquals(expectedOddMultiples(), list,
                "[2 pts] List should contain only odd multiples of 3 in order after removeEvens");
    }

    // ---------- helper to parse ints from strings ----------

    /**
     * Helper: pull out all integers from a string, in order.
     * This allows students some flexibility in spacing/tabs.
     */
    private List<Integer> extractInts(String s) {
        List<Integer> result = new ArrayList<>();
        if (s == null) return result;

        String[] tokens = s.split("\\s+");
        for (String tok : tokens) {
            if (tok.isEmpty()) continue;
            try {
                result.add(Integer.parseInt(tok));
            } catch (NumberFormatException ignored) {
                // ignore non-numeric tokens
            }
        }
        return result;
    }
}
