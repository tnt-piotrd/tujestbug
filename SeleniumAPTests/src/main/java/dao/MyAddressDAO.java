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

    public static class Builder {
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

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setCompany(String company) {
            this.company = company;
            return this;
        }

        public Builder setAddressLine(String addressLine) {
            this.addressLine = addressLine;
            return this;
        }

        public Builder setAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setState(String state) {
            this.state = state;
            return this;
        }

        public Builder setZipPostalCode(String zipPostalCode) {
            this.zipPostalCode = zipPostalCode;
            return this;
        }

        public Builder setHomePhone(String homePhone) {
            this.homePhone = homePhone;
            return this;
        }

        public Builder setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
            return this;
        }

        public Builder setAdditionalInfo(String additionalInfo) {
            this.additionalInfo = additionalInfo;
            return this;
        }

        public Builder setAddressAliasName(String addressAliasName) {
            this.addressAliasName = addressAliasName;
            return this;
        }

        public MyAddressDAO build() {
            return new MyAddressDAO(firstName, lastName, company, addressLine, addressLine2, city, state, zipPostalCode,
                    homePhone, mobilePhone, additionalInfo, addressAliasName);
        }
    }
}
