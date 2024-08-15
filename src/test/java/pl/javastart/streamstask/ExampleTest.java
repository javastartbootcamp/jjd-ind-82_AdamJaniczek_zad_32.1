package pl.javastart.streamstask;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ExampleTest {
    private final StreamsTask streamsTask = new StreamsTask();

    User alicja = new User(1L, "Alicja", 20);
    User beata = new User(2L, "Beata", 17);
    User tom = new User(3L, "Tom", 20);
    User michal = new User(4L, "Michal", 30);
    User bogdan = new User(5L, "Bogdan", 40);

    List<User> users = List.of(alicja, beata, tom, michal, bogdan);
    @Test
    public void shouldReturnOnlyWomen() {
        // given

        // when
        Collection<User> women = streamsTask.findWomen(users);

        // then
        assertEquals(2, women.size());
        assertTrue(women.contains(alicja));
        assertTrue(women.contains(beata));
    }

    @Test
    public void shouldReturnOnlyWomenWithNameWriteOnlyCapital() {
        alicja.setName("ALICJA");
        // given

        // when
        Collection<User> women = streamsTask.findWomen(users);

        // then
        assertEquals(2, women.size());
        assertTrue(women.contains(alicja));
        assertTrue(women.contains(beata));
    }

    @Test
    public void shouldReturnOnlyMen() {
        // given

        // when
        Collection<User> men = streamsTask.findMen(users);

        // then
        assertEquals(3, men.size());
        assertTrue(men.contains(tom));
        assertTrue(men.contains(michal));
        assertTrue(men.contains(bogdan));
    }

    @Test
    public void shouldReturnAverageMenAgeEquals30() {
        double averageMenAge = streamsTask.averageMenAge(users);

        assertEquals(30, averageMenAge);
    }

    @Test
    public void shouldReturnAverageMenAgeEquals35() {
        tom.setAge(30);
        michal.setAge(35);
        bogdan.setAge(40);

        double averageMenAge = streamsTask.averageMenAge(users);

        assertEquals(35, averageMenAge);

    }

    @Test
    void shouldReturnExpensesByUser() {
        User user1 = new User(1L, "Alice", 35);
        User user2 = new User(2L, "Bob", 43);
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense(1L, "Buty", new BigDecimal("149.99"), ExpenseType.WEAR));
        expenses.add(new Expense(1L, "Sałatka", new BigDecimal("14.99"), ExpenseType.FOOD));
        expenses.add(new Expense(2L, "Bluza", new BigDecimal("100"), ExpenseType.WEAR));
        expenses.add(new Expense(2L, "Skarpetki", new BigDecimal("39"), ExpenseType.WEAR));
        expenses.add(new Expense(2L, "Pizza", new BigDecimal("25"), ExpenseType.FOOD));


        Map<User, List<Expense>> result = new StreamsTask().groupExpensesByUser(Arrays.asList(user1, user2), expenses);

        assertEquals(2, result.size());
        assertTrue(result.containsKey(user1));
        assertTrue(result.containsKey(user2));
        assertEquals(2, result.get(user1).size());
        assertEquals(3, result.get(user2).size());
        assertEquals("Buty", result.get(user1).get(0).getName());
        assertEquals("Sałatka", result.get(user1).get(1).getName());
        assertEquals("Bluza", result.get(user2).get(0).getName());
        assertEquals("Skarpetki", result.get(user2).get(1).getName());
        assertEquals("Pizza", result.get(user2).get(2).getName());
    }

    @Test
    void shouldReturnExpensesByUserId() {
        User user1 = new User(1L, "Alice", 32);
        User user2 = new User(2L, "Bob", 63);
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense(1L, "Buty", new BigDecimal("149.99"), ExpenseType.WEAR));
        expenses.add(new Expense(1L, "Sałatka", new BigDecimal("14.99"), ExpenseType.FOOD));
        expenses.add(new Expense(2L, "Bluza", new BigDecimal("100"), ExpenseType.WEAR));
        expenses.add(new Expense(2L, "Skarpetki", new BigDecimal("39"), ExpenseType.WEAR));
        expenses.add(new Expense(2L, "Pizza", new BigDecimal("25"), ExpenseType.FOOD));

        Map<Long, List<Expense>> result = new StreamsTask().groupExpensesByUserId(Arrays.asList(user1, user2), expenses);

        assertEquals(2, result.size());
        assertTrue(result.containsKey(1L));
        assertTrue(result.containsKey(2L));
        assertEquals(2, result.get(1L).size());
        assertEquals(3, result.get(2L).size());
        assertEquals("Buty", result.get(1L).get(0).getName());
        assertEquals("Sałatka", result.get(1L).get(1).getName());
        assertEquals("Bluza", result.get(2L).get(0).getName());
        assertEquals("Skarpetki", result.get(2L).get(1).getName());
        assertEquals("Pizza", result.get(2L).get(2).getName());
    }

    @Test
    void shouldTestGroupExpensesByUserWithEmptyLists() {
        List<User> users = Collections.emptyList();
        List<Expense> expenses = Collections.emptyList();

        Map<User, List<Expense>> result = new StreamsTask().groupExpensesByUser(users, expenses);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldTestGroupExpensesByUserIdWithEmptyLists() {
        List<User> users = Collections.emptyList();
        List<Expense> expenses = Collections.emptyList();

        Map<Long, List<Expense>> result = new StreamsTask().groupExpensesByUserId(users, expenses);

        assertTrue(result.isEmpty());
    }
}
