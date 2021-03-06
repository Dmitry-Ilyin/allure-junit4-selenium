package org.example.framework.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Стартовая страница приложения
 */
public class HomePage extends BasePage {

    @FindBy(xpath = "//div[@class='service__title-text']")
    private List<WebElement> tabs;


    /**
     * Выбор пункт
     * @return DepositPage - т.е. переходим на другую страницу
     */
    @Step("Выбираем '{value}'")
    public DepositPage selectTab(String value) {
        for (WebElement tab : tabs) {
            waitUtilElementToBeVisible(tab);
            if (tab.getAttribute("outerText").equalsIgnoreCase(value)) {
                waitUtilElementToBeClickable(tab.findElement(By.xpath("./../a[1]"))).click();
                return pageManager.getDepositPage();
            }
        }
        Assert.fail("Пункта "+ value +" на стартовой странице не существует");
        return pageManager.getDepositPage();
    }
}
