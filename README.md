# ArrayList Coding Activity – Multiples of 3

This repository is your starter code for the GitHub Classroom assignment:

**ArrayList Coding Activity - multiples of 3**

You will complete the `NumberList` class so that it:

1. Creates an `ArrayList<Integer>` of the **first 100 multiples of 3** starting at 0 (`0, 3, 6, ..., 297`).
2. Converts that list to a formatted `String` with **10 numbers per line**.
3. Removes all **even numbers** from the list.

Your code will be **automatically graded** using JUnit tests that run on GitHub.

---

## 1. Files in this repository

You will mainly work with:

- `src/main/java/NumberList.java` – **you write the code here**
- `src/main/java/NumberListDriver.java` – optional driver to test your code locally
- `src/test/java/NumberListTest.java` – **do not edit**; used by the autograder

---

## 2. Your task: complete `NumberList.java`

Open:

`src/main/java/NumberList.java`

You will see the class `NumberList` with three methods to complete:

    import java.util.ArrayList;

    public class NumberList {

        public ArrayList<Integer> multiplesOf3() {
            //Your code goes here

            return null; // temporary so it compiles; replace this
        }

        public String toString(ArrayList<Integer> list) {
            //Your code goes here

            return ""; // temporary; replace this
        }

        public void removeEvens(ArrayList<Integer> list) {
            //Your code goes here

        }
    }

You must replace the placeholder code in these three methods with your own working Java code.

---

## 3. Detailed requirements for each method

### 3.1 `multiplesOf3`

**Method header:**

    public ArrayList<Integer> multiplesOf3()

**Requirements:**

- Returns an `ArrayList<Integer>`.
- The returned list must:
  - Contain **exactly 100 elements**.
  - Be the first 100 multiples of 3, starting at 0:
    - `0, 3, 6, 9, ..., 297` in order.

**Hints:**

- Create a new `ArrayList<Integer>`.
- Use a `for` loop that runs 100 times.
- Either:
  - Start at `0` and keep adding `3`, or
  - Use `3 * i` inside the loop.

---

### 3.2 `toString(ArrayList<Integer> list)`

**Method header:**

    public String toString(ArrayList<Integer> list)

**Requirements:**

- Parameter: an `ArrayList<Integer>` called `list`.
- Return: a `String` that, when printed, shows all numbers:
  - In the same order as in `list`.
  - With **10 numbers per line**.

You may use tabs (`"\t"`) between numbers and `"\n"` between rows.

Example layout (first few lines):

    0   3   6   9   12  15  18  21  24  27
    30  33  36  39  42  45  48  51  54  57
    ...

The autograder will:

- Extract the integers from your returned `String`.
- Check that they match the expected sequence `0, 3, 6, ..., 297` in order.
- Check that there are at least **9 newline characters** (for 10 rows).

**Hints:**

- Use a loop over `list`.
- Keep a counter and add a newline every time you’ve added 10 numbers.
- You can build the string with `String` concatenation or `StringBuilder`.

---

### 3.3 `removeEvens(ArrayList<Integer> list)`

**Method header:**

    public void removeEvens(ArrayList<Integer> list)

**Requirements:**

- This method **modifies the given list**.
- After the method finishes:
  - The list should contain **no even numbers**.
  - For this assignment (when called on the result of `multiplesOf3`), it should contain only the **odd multiples of 3** from the original list.

**Hints:**

- You can loop over the list and remove elements that are even (`value % 2 == 0`).
- Be careful: removing while looping **forward** can skip elements.
  - One common pattern is to decrement the index `i` after a removal:
    
        list.remove(i);
        i--;

---

## 4. Optional: use the driver to see your output

Open:

`src/main/java/NumberListDriver.java`

It contains:

    import java.util.ArrayList;

    public class NumberListDriver {

        public static void main(String[] args) {
            NumberList nl = new NumberList();
            ArrayList<Integer> myList = nl.multiplesOf3();
            System.out.println(nl.toString(myList));
            nl.removeEvens(myList);
            System.out.println(nl.toString(myList));
        }
    }

If you clone this repo to your computer and open it in an IDE (Eclipse, PickCode, IntelliJ, VS Code, BlueJ, etc.), you can run `NumberListDriver` to see your output.

This is **optional**. The official grading happens via JUnit tests on GitHub.

---

## 5. How to edit and commit your code on GitHub (in the browser)

You can do this entire assignment in the browser:

1. In **your** assignment repo, open  
   `src/main/java/NumberList.java`.
2. Click the **pencil icon (✏️)** in the top-right to edit.
3. Write your code for the three methods:
   - `multiplesOf3`
   - `toString`
   - `removeEvens`
4. Scroll to the bottom of the page.
5. In **Commit changes**:
   - Write a message (for example): `Implement NumberList methods`.
   - Leave **“Commit directly to the main branch”** selected.
6. Click **Commit changes**.

Every time you commit, the autograder will run your code and update your score in GitHub Classroom.

---

## 6. Seeing your autograding results

There are two ways to see your results:

### 6.1 In GitHub Classroom

1. Go to **GitHub Classroom**.
2. Open the assignment: **ArrayList Coding Activity - multiples of 3**.
3. You should see your current **score** based on the tests.
4. If not all tests passed, you can fix your code and commit again (as allowed by your teacher).

### 6.2 In your GitHub repo (detailed logs)

1. In your assignment repo, click the **Actions** tab.
2. Click the latest workflow run (often named something like “Autograding”).
3. Expand the steps to see:
   - Which JUnit tests passed or failed.
   - Any error messages (for example: wrong list size, wrong values, etc.).

---

## 7. What the autograder checks

The JUnit tests in `src/test/java/NumberListTest.java` will:

- Call `multiplesOf3()` and check:
  - The returned list is **not null**.
  - The size is **100**.
  - The values are exactly `0, 3, 6, ..., 297` in order.

- Call `toString(list)` and:
  - Extract all integers from the returned `String`.
  - Check that they match the same `0, 3, 6, ..., 297` sequence in order.
  - Check that there are at least **9 newline characters** (for 10 rows).

- Call `removeEvens(list)` and:
  - Check that **no even numbers** remain in the list.
  - Check that the remaining numbers match the expected **odd multiples of 3** in order.

If your code satisfies all of these conditions, you will pass all the tests and receive full credit from the autograder.
---

## 8. Points and partial credit (how tests map to score)

The autograder breaks this assignment into several smaller tests.  
Each test checks one specific part of your code and is worth a few points.

Rough point breakdown (total ≈ 20 pts):

- `multiplesOf3_notNullAndSize` – about **3 pts**  
- `multiplesOf3_firstAndLastValues` – about **3 pts**  
- `multiplesOf3_fullSequenceCorrect` – about **4 pts**  
- `toString_notNullAndHasNewlines` – about **3 pts**  
- `toString_containsAllNumbersInOrder` – about **3 pts**  
- `removeEvens_removesAllEvenNumbers` – about **2 pts**  
- `removeEvens_resultsInCorrectOddSequence` – about **2 pts**

If a test passes ✅, you earn the points for that part.  
If a test fails ❌, read the error message, fix your code in `NumberList.java`, and commit again to re-run the autograder (as allowed by your teacher).
---

---

## 9. Mapping Classroom tests to JUnit methods

Each autograding test in GitHub Classroom runs **one** JUnit test method from `NumberListTest.java`.

Here is how they line up for this assignment:

| Concept being tested                      | JUnit test method name                          | Classroom test name                                       | Points |
|-------------------------------------------|-------------------------------------------------|-----------------------------------------------------------|--------|
| `multiplesOf3` returns a 100-element list | `multiplesOf3_notNullAndSize`                   | `Test1_multiplesOf3_notNullAndSize`                       | 3      |
| First value is 0, last is 297             | `multiplesOf3_firstAndLastValues`               | `Test2_multiplesOf3_firstAndLastValues`                   | 3      |
| Full sequence `0, 3, 6, ..., 297`         | `multiplesOf3_fullSequenceCorrect`              | `Test3_multiplesOf3_fullSequenceCorrect`                  | 4      |
| `toString` returns non-null & enough rows | `toString_notNullAndHasNewlines`                | `Test4_toString_notNullAndHasNewlines`                    | 3      |
| `removeEvens` removes all even numbers    | `removeEvens_removesAllEvenNumbers`             | `Test5_removeEvens_removesAllEvenNumbers`                 | 2      |
| Result after `removeEvens` is correct     | `removeEvens_resultsInCorrectOddSequence`       | `Test6_removeEvens_resultsInCorrectOddSequence`           | 2      |
| `toString` prints all numbers in order    | `toString_containsAllNumbersInOrder`            | `Test7_toString_containsAllNumbersInOrder`                | 3      |

- In **GitHub Classroom**, you will see the **Classroom test name** and how many points it is worth.  
- In the **Actions** log (GitHub → Actions tab), you will see the **JUnit method name** in the test output.  

Use this table to understand exactly **which part** of your solution needs work when a test fails and how many points that specific test affects.



