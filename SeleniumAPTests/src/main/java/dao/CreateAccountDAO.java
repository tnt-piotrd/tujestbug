package dao;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateAccountDAO {
    private Boolean isMr;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String dateOfBirthDays;
    private String dateOfBirthMonths;
    private String dateOfBirthYears;
    private Boolean isNewsletter;
}
