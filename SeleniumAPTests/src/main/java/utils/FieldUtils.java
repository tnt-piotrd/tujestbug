package utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FieldUtils {
    public static void enterTextIfNotEmpty(WebElement element, String text) {
        if (text != null && !text.isEmpty()) {
            element.clear();
            element.sendKeys(text);
        }
    }

    public static void selectStateIfNotEmpty(WebElement selectElement, String value) {
        if (value == null || value.isEmpty()) {
            return;
        }else {
            Select select = new Select(selectElement);
            select.selectByValue(value);
        }
    }

    public static void selectCheckboxIfNotEmpty(WebElement checkbox, Boolean isChecked) {
        if (isChecked==null){
            return;
        }
        if (isChecked && !checkbox.isSelected()) {
            checkbox.click();
        } else if (!isChecked && checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public static void selectRadioButtonIfNotEmpty(WebElement yesButton, WebElement noButton, Boolean isChecked) {
        if (isChecked==null){
            return;
        }
        if (isChecked && !yesButton.isSelected()) {
            yesButton.click();
        } else if (!isChecked && !noButton.isSelected()) {
            noButton.click();
        }
    }

}
