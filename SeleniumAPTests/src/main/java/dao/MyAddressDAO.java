package dao;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MyAddressDAO {
    private String firstName;
    private String lastName;
    private String company;
    private String addressLine;
    private String addressLine2;
    private String city;
    private String state;
    private String zipPostalCode;
    private String homePhone;
    private String mobilePhone;
    private String additionalInfo;
    private String addressAliasName;
}
