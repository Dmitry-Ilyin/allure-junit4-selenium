package org.example.framework.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.math.BigDecimal;
import java.util.List;

public class DepositPage extends BasePage {


    @FindBy(xpath = "//label[@class='calculator__currency-field']")
    private List<WebElement> currencies;

    @FindBy(xpath = "//h1")
    private WebElement currentPage;

    @FindBy(xpath = "//input[@name='amount']")
    private WebElement sumDeposit;

    @FindBy(xpath = "//li[@style]")
    private List<WebElement> sroki;

    @FindBy(xpath = "//div[@class='jq-selectbox__select']")
    private WebElement srok;

    @FindBy(xpath = "//input[@name='replenish']")
    private WebElement payment;

    @FindBy(xpath = "//div[@class='calculator__content']//label[@class='calculator__check-block']")
    private List<WebElement> extraOptions;

    @FindBy(xpath = "//span[@class='js-calc-amount']")
    private WebElement priceNow;

    //начислено
    @FindBy(xpath = "//span[@class='js-calc-earned']")
    private WebElement accrued;

    //пополнение
    @FindBy(xpath = "//span[@class='js-calc-replenish']")
    private WebElement replenishment;

    @FindBy(xpath = "//span[@class='js-calc-result']")
    private WebElement result;


    @FindBy(xpath = "//select")
    private WebElement select;

    @FindBy(xpath = "//div[@class='jq-selectbox__select-text']")
    private WebElement date;

    /**
     * Проверка страницы
     *
     * @return DepositPage - т.е. остаемся на этой странице
     */
    @Step("Выбираем '{nameField}' со значением '{value}'")
    public DepositPage fillField(String nameField, int value) {
        WebElement element = null;
        String val = Integer.toString(value);
        switch (nameField) {
            case "Сумма вклада":
                fillInputField(sumDeposit, val);
                element = sumDeposit;
                break;
            case "Ежемесячное пополнение":
                fillInputField(payment, val);
                element = payment;
                break;
            default:
                Assert.fail("Поле с наименованием '" + nameField + "' отсутствует на странице " +
                        "'Вклады'");
        }
            Assert.assertEquals("Поле '" + nameField + "' было заполнено некорректно",
                    val, element.getAttribute("value").replaceAll("\\s+", ""));
        return this;
    }
    @Step("Устанавливаем срок '{value}' ")
    public DepositPage fillDate(String value) {
        Select sel1 = new Select(select);
        sel1.selectByVisibleText(value);
        Assert.assertEquals("Срок заполнен не корректно", value, date.getAttribute("outerText"));
        return this;
    }

    /**
     * Выбор дополнительных опций
     *
     * @return DepositPage - т.е. остаемся на этой странице
     */
    @Step("выбираем дополнительный параметр '{value}' ")
    public DepositPage selectExtraOptions(String value) {
        for (WebElement element : extraOptions) {
            if (element.getAttribute("outerText").replaceAll("\\t", "").equalsIgnoreCase(value)) {
                waitUtilElementToBeClickable(element).click();
                return this;
            }
        }
        Assert.fail("Параметр " + value + " не существует");
        return this;
    }


    /**
     * Проверка страницы
     *
     * @return DepositPage - т.е. остаемся на этой странице
     */
    @Step("проверка открытия страницы 'Вклады' ")
    public DepositPage checkOpenDepositPage() {
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому", "Вклады", currentPage.getText());
        return this;
    }


    /**
     * Выбор валюты
     *
     * @return DepositPage - т.е. остаемся на этой странице
     */
    @Step("выбрать валюту '{value}'")
    public DepositPage selectCurrency(String value) {
        for (WebElement currency : currencies) {
            if (currency.getAttribute("outerText").contains(value)) {
                waitUtilElementToBeClickable(currency).click();
                return this;
            }
        }
        Assert.fail("Такой валюты нет");
        return this;
    }

    /**
     * Проверка расчетов по вкладу
     *
     * @return DepositPage - т.е. остаемся на этой странице
     */
    @Step("проверить параметры")
    public DepositPage checkParameter() {
        delay(1000);
        Assert.assertEquals(filterNumber(result),
                BigDecimal.valueOf(filterNumber(priceNow) + filterNumber(accrued) + filterNumber(replenishment))
                        .setScale(2, BigDecimal.ROUND_DOWN)
                        .doubleValue(),
                0.0f);
        return this;
    }


}
