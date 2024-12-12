
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor


public class Order {
    private int id;
    private LocalDate date;
    private double amount;
    private int customerId;
    private String status;

    public Order( LocalDate date, double amount, int customerId) {
        this.date = date;
        this.amount = amount;
        this.customerId = customerId;
        this.status = "pending";
    }
    @Override
    public String toString() {
        return "Order{id=" + id + ", date=" + date + ", amount=" + amount + ", customerId=" + customerId + ", status='" + status + "'}";
    }
}