package com.automationpractice.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

@DefaultUrl("http://automationpractice.com/index.php?controller=authentication&back=my-account")
public class LoginPage extends AbstractPage {

    @FindBy(id = "SubmitLogin")
    private WebElementFacade loginButton;

    @FindBy(id = "email")
    private WebElementFacade registeredEmailInput;

    @FindBy(id = "passwd")
    private WebElementFacade passwordInput;

    @FindBy(id = "email_create")
    private WebElementFacade emailForRegistrationInput;

    @FindBy(xpath = "//button[@name='SubmitCreate']")
    private WebElementFacade createAccountButton;

    @FindBy(id = "id_gender1")
    private WebElementFacade maleGenderButton;

    @FindBy(id = "customer_firstname")
    private WebElementFacade firstNameInput;

    @FindBy(id = "customer_lastname")
    private WebElementFacade lastNameInput;

    @FindBy(id = "address1")
    private WebElementFacade addressInput;

    @FindBy(id = "city")
    private WebElementFacade cityInput;

    @FindBy(id = "id_state")
    private WebElementFacade stateDropdown;

    @FindBy(id = "postcode")
    private WebElementFacade postalCodeInput;

    @FindBy(id = "id_country")
    private WebElementFacade countryDropdown;

    @FindBy(id = "phone_mobile")
    private WebElementFacade mobilePhoneInput;

    @FindBy(id = "submitAccount")
    private WebElementFacade registerButton;

    public void loginToProfile(String email, String password) {
        fillRegisteredEmail(email);
        fillPassword(password);
        this.loginButton.click();
    }

    private void fillRegisteredEmail(String email) {
        registeredEmailInput.sendKeys(email);
    }

    //Fill registered Password
    private void fillPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void registerEmail() {
        emailForRegistrationInput.sendKeys(generateRegistrationEmail());
        createAccountButton.waitUntilEnabled();
        createAccountButton.click();
        if (createAccountButton.isDisplayed()) {
            createAccountButton.click();
        }
        createAccountButton.waitUntilNotVisible();
    }

    private String generateRegistrationEmail() {
        String generatedString = RandomStringUtils.randomAlphabetic(8);
        return (generatedString + "@gmail.com");
    }

    public void fillPersonalData(String firstName, String lastName, String password) {
        maleGenderButton.waitUntilVisible();
        maleGenderButton.click();
        firstNameInput.waitUntilEnabled();
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        passwordInput.sendKeys(password);
    }

    public void fillAddressData(String address, String city, String state, String postalCode, String country, String phone) {
        addressInput.sendKeys(address);
        cityInput.sendKeys(city);
        selectStateFromDropdown(state);
        postalCodeInput.sendKeys(postalCode);
        selectCountryFromDropdown(country);
        mobilePhoneInput.sendKeys(phone);
    }

    private void selectStateFromDropdown(String state) {
        stateDropdown.waitUntilPresent();
        Select stateFromDropdown = new Select(stateDropdown);
        stateFromDropdown.selectByVisibleText(state);
    }

    private void selectCountryFromDropdown(String country) {
        Select stateFromDropdown = new Select(countryDropdown);
        stateFromDropdown.selectByVisibleText(country);
    }

    public void registerNewUser() {
        registerButton.click();
    }

}
