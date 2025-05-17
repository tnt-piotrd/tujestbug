package dao;

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

    public MyAddressDAO(String firstName, String lastName, String company, String addressLine, String addressLine2, String city, String state, String zipPostalCode, String homePhone, String mobilePhone, String additionalInfo, String addressAliasName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.addressLine = addressLine;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.zipPostalCode = zipPostalCode;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.additionalInfo = additionalInfo;
        this.addressAliasName = addressAliasName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompany() {
        return company;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipPostalCode() {
        return zipPostalCode;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public String getAddressAliasName() {
        return addressAliasName;
    }
}
