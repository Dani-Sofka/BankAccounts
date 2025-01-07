package bank.accounts.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreateRequestDTO {

    private Long accountId;
    private String name;
    private String lastName;
    private String email;
}
