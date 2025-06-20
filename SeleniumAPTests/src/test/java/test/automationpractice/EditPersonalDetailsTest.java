package test.automationpractice;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageobjects.TopNavigationBar;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class EditPersonalDetailsTest extends BaseTest {
    public static final String INVALID_PASSWORD_ERROR_MESSAGE = "The password you entered is incorrect.";
    private static final String NEW_SURNAME = "Programistyczny";
    private static final String SUCCESSFULLY_DATA_CHANGE_MESSAGE = "Your personal information has been successfully updated.";
    private boolean shouldRevertSurname;

    @Test
    public void userShouldBeAbleToChangeHisSurname() {
        shouldRevertSurname = true;
        String confirmationMessage = new TopNavigationBar(driver)
                .openUserAccountDetails()
                .openMyPersonalInformation()
                .updatePersonalDataSuccessfully(true, USER_NAME, NEW_SURNAME, VALID_USER_PASSWORD)
                .getConfirmationMessage();
        assertEquals(confirmationMessage, SUCCESSFULLY_DATA_CHANGE_MESSAGE);
        assertEquals(new TopNavigationBar(driver).getUserName(), "%s %s".formatted(USER_NAME, NEW_SURNAME));
    }

    @Test
    public void userShouldNotBeAbleToChangeHisDataWithInvalidPasswordProvided() {
        shouldRevertSurname = false;
        List<String> errorMessages = new TopNavigationBar(driver)
                .openUserAccountDetails()
                .openMyPersonalInformation()
                .updatePersonalDataUnsuccessfully(true, "Jan", USER_SURNAME, "InvalidP@ssw0rd!")
                .getErrorMessages();
        assertEquals(errorMessages.size(), 1);
        assertEquals(errorMessages.get(0), INVALID_PASSWORD_ERROR_MESSAGE);
    }

    @AfterMethod(dependsOnMethods = {"logoutFromShop"})
    public void revertSurnameIfNecessary() {
        if (shouldRevertSurname) {
            super.loginToShop();
            new TopNavigationBar(driver)
                    .openUserAccountDetails()
                    .openMyPersonalInformation()
                    .updatePersonalDataSuccessfully(true, USER_NAME, USER_SURNAME, VALID_USER_PASSWORD);
            super.logoutFromShop();
        }
    }
}
