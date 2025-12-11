# Teacher Notes – ArrayList Coding Activity: Multiples of 3

These are **teacher-facing** notes for the GitHub Classroom assignment:

**ArrayList Coding Activity – multiples of 3**

This doc includes:

- The full **JUnit test file** (`NumberListTest.java`) used for autograding.  
- The **point breakdown** for each test.  
- A mapping between **JUnit tests** and **GitHub Classroom tests**.  
- The exact **Maven commands** used in Classroom autograding.  
- A reusable **10-step cheatsheet** for building new autograded Java labs.

Students do **not** need this file.

---

## 1. Lab summary (NumberList)

Students implement the `NumberList` class with three methods:

- `multiplesOf3()` – returns an `ArrayList<Integer>` with the first 100 multiples of 3 starting at 0 (`0, 3, 6, …, 297`).  
- `toString(ArrayList<Integer> list)` – returns a `String` that prints the list values with **10 numbers per line**.  
- `removeEvens(ArrayList<Integer> list)` – removes all **even** numbers from the list (in place), leaving only the odd multiples of 3.

Autograding uses **JUnit 5** and **Maven**.

---

## 2. JUnit tests (NumberListTest.java)

This is the exact content of `src/test/java/NumberListTest.java` used for partial credit.

    import org.junit.jupiter.api.Test;
    
    import java.util.ArrayList;
    import java.util.List;
    
    import static org.junit.jupiter.api.Assertions.*;
    
    /**
     * JUnit tests for NumberList.
     *
     * Suggested point breakdown (total 20 pts):
     *
     *  - multiplesOf3_notNullAndSize                 [3 pts]
     *  - multiplesOf3_firstAndLastValues             [3 pts]
     *  - multiplesOf3_fullSequenceCorrect            [4 pts]
     *  - toString_notNullAndHasNewlines              [3 pts]
     *  - toString_containsAllNumbersInOrder          [3 pts]
     *  - removeEvens_removesAllEvenNumbers           [2 pts]
     *  - removeEvens_resultsInCorrectOddSequence     [2 pts]
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

---

## 3. Point breakdown, mapping, and autograding commands

### 3.1 Point breakdown

- `multiplesOf3_notNullAndSize` – 3 pts  
- `multiplesOf3_firstAndLastValues` – 3 pts  
- `multiplesOf3_fullSequenceCorrect` – 4 pts  
- `toString_notNullAndHasNewlines` – 3 pts  
- `toString_containsAllNumbersInOrder` – 3 pts  
- `removeEvens_removesAllEvenNumbers` – 2 pts  
- `removeEvens_resultsInCorrectOddSequence` – 2 pts  

Total = **20 points**.

### 3.2 Mapping table: JUnit vs Classroom

In **GitHub Classroom**, you configured 7 autograding tests (one per JUnit method) with these names:

- `Test1_multiplesOf3_notNullAndSize`  
- `Test2_multiplesOf3_firstAndLastValues`  
- `Test3_multiplesOf3_fullSequenceCorrect`  
- `Test4_toString_notNullAndHasNewlines`  
- `Test5_removeEvens_removesAllEvenNumbers`  
- `Test6_removeEvens_resultsInCorrectOddSequence`  
- `Test7_toString_containsAllNumbersInOrder`  

| Concept being tested                      | JUnit test method name                    | Classroom test name                                   | Points |
|-------------------------------------------|-------------------------------------------|-------------------------------------------------------|--------|
| `multiplesOf3` returns a 100-element list | `multiplesOf3_notNullAndSize`             | `Test1_multiplesOf3_notNullAndSize`                   | 3      |
| First value is 0, last is 297             | `multiplesOf3_firstAndLastValues`         | `Test2_multiplesOf3_firstAndLastValues`               | 3      |
| Full sequence `0, 3, 6, ..., 297`         | `multiplesOf3_fullSequenceCorrect`        | `Test3_multiplesOf3_fullSequenceCorrect`              | 4      |
| `toString` returns non-null & enough rows | `toString_notNullAndHasNewlines`          | `Test4_toString_notNullAndHasNewlines`                | 3      |
| `removeEvens` removes all even numbers    | `removeEvens_removesAllEvenNumbers`       | `Test5_removeEvens_removesAllEvenNumbers`             | 2      |
| Result after `removeEvens` is correct     | `removeEvens_resultsInCorrectOddSequence` | `Test6_removeEvens_resultsInCorrectOddSequence`       | 2      |
| `toString` prints all numbers in order    | `toString_containsAllNumbersInOrder`      | `Test7_toString_containsAllNumbersInOrder`            | 3      |

### 3.3 Autograding run commands (for Classroom)

These are the exact **Run command** lines you use in each Classroom autograding test (type: “Run command”):

- Test 1 – `Test1_multiplesOf3_notNullAndSize`  3 points

  Run command:

    mvn -B -q -Dtest=NumberListTest#multiplesOf3_notNullAndSize test

- Test 2 – `Test2_multiplesOf3_firstAndLastValues`  3 points

   Run command:

    mvn -B -q -Dtest=NumberListTest#multiplesOf3_firstAndLastValues test

- Test 3 – `Test3_multiplesOf3_fullSequenceCorrect`  4 points

   Run command:

    mvn -B -q -Dtest=NumberListTest#multiplesOf3_fullSequenceCorrect test

- Test 4 – `Test4_toString_notNullAndHasNewlines`  3 points

  Run command:

    mvn -B -q -Dtest=NumberListTest#toString_notNullAndHasNewlines test

- Test 5 – `Test5_removeEvens_removesAllEvenNumbers`  2 points

  Run command:

    mvn -B -q -Dtest=NumberListTest#removeEvens_removesAllEvenNumbers test

- Test 6 – `Test6_removeEvens_resultsInCorrectOddSequence`  2 points

  Run command:

    mvn -B -q -Dtest=NumberListTest#removeEvens_resultsInCorrectOddSequence test

- Test 7 – `Test7_toString_containsAllNumbersInOrder`  3 points

   Run command:

    mvn -B -q -Dtest=NumberListTest#toString_containsAllNumbersInOrder test

These are the commands you can copy–paste into new assignments and adapt for future labs by changing `NumberListTest` and the method names.

---

## 4. Teacher cheat note – “If X is broken, expect these tests to fail”

Helps you quickly interpret failures.

### 4.1 If `multiplesOf3` is wrong

(Incorrect size, values, or order.)

Expect these to fail:

- `Test1_multiplesOf3_notNullAndSize`  
- `Test2_multiplesOf3_firstAndLastValues`  
- `Test3_multiplesOf3_fullSequenceCorrect`  

And any downstream tests that rely on its output:

- `Test4_toString_notNullAndHasNewlines`  
- `Test7_toString_containsAllNumbersInOrder`  
- `Test5_removeEvens_removesAllEvenNumbers`  
- `Test6_removeEvens_resultsInCorrectOddSequence`  

### 4.2 If `toString` is wrong

(Missing numbers, wrong order, not enough newlines.)

Expect these to fail:

- `Test4_toString_notNullAndHasNewlines`  
- `Test7_toString_containsAllNumbersInOrder`  

### 4.3 If `removeEvens` is wrong

(Removing odds instead of evens, not removing all evens, etc.)

Expect these to fail:

- `Test5_removeEvens_removesAllEvenNumbers`  
- `Test6_removeEvens_resultsInCorrectOddSequence`  

---

## 5. Cheatsheet – How to make a new autograding Java lab (10 steps)

Use this pattern for future labs.

### 0. Pre-reqs (once)

- You already have at least one working Maven/JUnit lab (like this one).  
- You’re comfortable with:
  - `pom.xml`  
  - `src/main/java` for student code  
  - `src/test/java` for JUnit tests  

---

### 1. Create a new template repo (per lab)

In your Classroom org:

1. On GitHub, click **New repository**.  
2. Name it something like:  
   `arraylist-coding-activity-MOVIES`  
3. Click **Create repository**.

---

### 2. Add Maven + folders

In the new repo:

1. Add `pom.xml` (copy from this lab; change `<artifactId>` if desired).  
2. Create:
   - `src/main/java/NewClass.java`  
   - `src/test/java/NewClassTest.java`  
   - (optional) `src/main/java/NewClassDriver.java`  

---

### 3. Write starter code (student skeleton)

In `NewClass.java`:

- Add the methods students must implement.  
- Put clear comments and placeholder returns so it compiles, for example:

    public int something(...) {
        // Your code goes here
        return 0; // temporary
    }

---

### 4. Write JUnit tests (teacher logic)

In `NewClassTest.java`:

- Create small, focused tests, for example:
  - `methodA_notNullAndSize`  
  - `methodA_fullSequenceCorrect`  
  - `methodB_handlesEdgeCases`  

- Use good assertion messages with point hints, for example:

    assertNotNull(result, "[4 pts] methodA should not return null");

These method names will be targeted by GitHub Classroom using `-Dtest=NewClassTest#methodName`.

---

### 5. Add a student README

Create `README.md` with:

- Lab description & goal.  
- File(s) they edit (`src/main/java/...`).  
- Requirements per method.  
- How to:
  - Edit via GitHub web editor.  
  - Commit and trigger autograding.  
  - Check Classroom/Actions for results.  

(Optional) Add a per-test point breakdown and mapping table, similar to this lab.

---

### 6. Mark repo as Template

In the new repo:

1. Go to **Settings → General**.  
2. Check **Template repository**.  
3. Save.

This is required so GitHub Classroom can use it as a starter.

---

### 7. Create the Classroom assignment

In **GitHub Classroom**:

1. Create a **New assignment → Individual**.  
2. Assignment name (student-facing), for example:
   - `ArrayList Coding Activity - Movie Ratings`
3. For **Repository template**, choose this new repo.  
4. Turn on **Autograding**.

---

### 8. Add one Classroom test per JUnit method

For each test method in `NewClassTest`:

1. Click **Add test → Run command**.  
2. Fill in:
   - **Test name:** e.g. `Test1_methodName` (must be unique).  
   - **Setup command:** *(leave blank)*  
   - **Run command:**

        mvn -B -q -Dtest=NewClassTest#methodName test

   - **Timeout:** `300`  
   - **Points:** the point value for this piece (sum all tests to your desired total, e.g. 20).

Repeat for each JUnit test method.

---

### 9. Fake-student dry run

1. Copy the assignment invite link.  
2. Open it in a private/incognito window → **Accept assignment**.  
3. Open the new student repo.  
4. Implement a correct solution in `NewClass.java`:
   - Commit → confirm full points in Classroom.  
5. Break one method on purpose:
   - Commit → confirm only the relevant Classroom test(s) fail and the score drops as expected.

---

### 10. Assign to students

Once the dry run looks good:

- Post the **Classroom invite link** in your LMS.  
- Short student instructions:
  1. Accept the assignment.  
  2. Open your repo on GitHub.  
  3. Read README.md carefully.  
  4. Edit the specified Java file(s).  
  5. Commit your changes.  
  6. Check GitHub Classroom for your autograding score.

---

_End of Teacher Notes_
