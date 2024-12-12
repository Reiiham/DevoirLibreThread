// Customer.java
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Customer {
    private int id;
    private String name;
    private String email;
    private String phone;

    // id is added by db so didn't add @AllArgsConstructor
    public Customer (String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }


}
