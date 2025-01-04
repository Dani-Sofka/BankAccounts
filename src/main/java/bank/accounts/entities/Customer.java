package bank.accounts.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private long id;
    private String name;
    private String lastName;
    private String email;
}
