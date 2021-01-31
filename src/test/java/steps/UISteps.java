package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;
import pages.SearchResultPage;
import utilities.Config;
import utilities.DataStorage;
import utilities.Driver;

import java.io.IOException;

public class UISteps {

    WebDriver driver = Driver.getDriver();
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    DataStorage dataStorage = DataStorage.getInstance();
    SearchResultPage searchResultPage = new SearchResultPage();

    @When("user navigates to HrApp login page")
    public void user_navigates_to_HrApp_login_page() throws IOException {
        driver.get(Config.getProperty("uiconfigs","URL"));
    }
    @When("user logs in to application")
    public void user_logs_in_to_application() throws IOException {
        loginPage.username.sendKeys(Config.getProperty("uiconfigs","username"));
        loginPage.password.sendKeys(Config.getProperty("uiconfigs","password"));
        loginPage.loginButton.click();
    }
    @When("user searches for employeeId {string}")
    public void user_searches_for_employeeId(String emploeeId) throws InterruptedException {
//        WebDriverWait wait = new WebDriverWait(driver,10);
//        wait.until(ExpectedConditions.elementToBeClickable(homePage.searchButton));
        Thread.sleep(3000);
        homePage.serschBox.sendKeys(emploeeId);
        homePage.searchButton.click();
    }
    @Then("user verifies employee info matching with ape response")
    public void user_verifies_employee_info_matching_with_ape_response() {
//        String departmantName = dataStorage.getValue("getEmployee","department.departmentName").toString();
//        System.out.println(departmantName);

        Assert.assertEquals(dataStorage.getValue("getEmployee","firstName").toString(),
                searchResultPage.employeeInfo.get(1).getText());

        Assert.assertEquals(dataStorage.getValue("getEmployee","lastName").toString(),
                searchResultPage.employeeInfo.get(2).getText());

        Assert.assertEquals(dataStorage.getValue("getEmployee","employeeId").toString(),
                searchResultPage.employeeInfo.get(0).getText());

        Assert.assertEquals(dataStorage.getValue("getEmployee","department.departmentName").toString(),
                searchResultPage.employeeInfo.get(3).getText());

//        Assert.;

    }

}
