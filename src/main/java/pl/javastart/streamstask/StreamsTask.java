package pl.javastart.streamstask;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class StreamsTask {

    public static void main(String[] args) {
        StreamsTask streamsTask = new StreamsTask();
        streamsTask.run();
    }

    private void run() {
        List<User> users = new ArrayList<>();

        users.add(new User(1L, "ALICJA", 20));
        users.add(new User(2L, "Dominik", 15));
        users.add(new User(3L, "Patrycja", 25));
        users.add(new User(4L, "Marcin", 30));
        users.add(new User(5L, "Tomek", 18));
        users.add(new User(6L, "Damian", 26));

        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense(1L, "Buty", new BigDecimal("149.99"), ExpenseType.WEAR));
        expenses.add(new Expense(1L, "Sałatka", new BigDecimal("14.99"), ExpenseType.FOOD));
        expenses.add(new Expense(2L, "Bluza", new BigDecimal("100"), ExpenseType.WEAR));
        expenses.add(new Expense(2L, "Skarpetki", new BigDecimal("39"), ExpenseType.WEAR));
        expenses.add(new Expense(2L, "Pizza", new BigDecimal("25"), ExpenseType.FOOD));

        Collection<User> women = findWomen(users);
        Double averageMenAge = averageMenAge(users);
        Map<Long, List<Expense>> expensesByUserId = groupExpensesByUserId(users, expenses);
        Map<User, List<Expense>> expensesByUser = groupExpensesByUser(users, expenses);
    }

    // metoda powinna zwracać listę kobiet (sprawdzając, czy imię kończy się na "a")
    Collection<User> findWomen(Collection<User> users) {
        return findWomenOrMen(users).get(true)
                .stream()
                .toList();
    }

    Collection<User> findMen(Collection<User> users) {
        return findWomenOrMen(users).get(false)
                .stream()
                .toList();
    }

    private Map<Boolean, List<User>> findWomenOrMen(Collection<User> users) {
        return users
                .stream()
                .collect(Collectors.partitioningBy(u -> (u.getName().toLowerCase()).endsWith("a")));
    }

    // metoda powinna zwracać średni wiek mężczyzn (sprawdzając, czy imię nie kończy się na "a")
    Double averageMenAge(Collection<User> users) {
        Double result = findMen(users)
                .stream()
                .collect(Collectors.averagingDouble(User::getAge));
        return result;
    }

    // metoda powinna zwracać wydatki zgrupowane po ID użytkownika
    Map<Long, List<Expense>> groupExpensesByUserId(Collection<User> users, List<Expense> expenses) {
        return expenses
                .stream()
                .collect(Collectors.groupingBy(Expense::getUserId));
    }

    // metoda powinna zwracać wydatki zgrupowane po użytkowniku
    // podobne do poprzedniego, ale trochę trudniejsze
    Map<User, List<Expense>> groupExpensesByUser(Collection<User> users, List<Expense> expenses) {
        Map<Long, User> userById = users
                .stream()
                .collect(Collectors.toMap(User::getId, user -> user));
        return expenses
                .stream()
                .collect(Collectors.groupingBy(expense -> userById.get(expense.getUserId())));

    }
}
