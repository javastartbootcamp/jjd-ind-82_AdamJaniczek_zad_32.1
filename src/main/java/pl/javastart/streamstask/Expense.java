package pl.javastart.streamstask;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Expense {

    private Long userId;
    private String name;
    private BigDecimal value;
    private ExpenseType type;

}
