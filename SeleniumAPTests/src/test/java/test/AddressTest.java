package test;

import dao.MyAddressDAO;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageobjects.TopNavigationBar;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddressTest extends BaseTest {
    private static final String ORIGINAL_ADDRESS = "NEW YORK ADDRESS";
    private boolean shouldDeleteAddress = false;

    @AfterMethod
    public void cleanUpAddresses() {
        if (shouldDeleteAddress) {
            new TopNavigationBar(driver).openUserAccountDetails()
                    .openMyAddresses().deleteAddress(ORIGINAL_ADDRESS);
        }
    }

    @Test
    public void byDefaultUserHasNoAddress() {
        assertTrue(new TopNavigationBar(driver).openUserAccountDetails()
                .openMyAddresses().isLabelForNoAddressesDisplayed());
    }

    @Test
    public void userIsAbleToAddAddress() {
        MyAddressDAO myAddressDAO = MyAddressDAO.builder()
                .firstName("John")
                .lastName("Smith")
                .addressLine("WallStreet 1/2")
                .addressLine2("Manhattan")
                .city("New York")
                .state("New York")
                .zipPostalCode("11111")
                .homePhone("12345678")
                .addressAliasName(ORIGINAL_ADDRESS)
                .build();


        List<String> displayedAddresses = new TopNavigationBar(driver).openUserAccountDetails()
                .openMyAddresses().openNewAddressAddition()
                .addNewAddress(myAddressDAO)
                .getAllDisplayedAddresses();
        shouldDeleteAddress = true;
        assertEquals(displayedAddresses, List.of(ORIGINAL_ADDRESS));
    }
}
