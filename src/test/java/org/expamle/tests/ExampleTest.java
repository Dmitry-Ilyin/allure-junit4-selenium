package org.expamle.tests;


import org.expamle.tests.base.BaseClasses;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ExampleTest extends BaseClasses {

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{
                {300000, "3 месяца", 50000},
                {400000, "6 месяцев", 40000},
                {500000, "18 месяцев", 100000},
        });
    }

    @Parameterized.Parameter(0)
    public int sum;

    @Parameterized.Parameter(1)
    public String date;

    @Parameterized.Parameter(2)
    public int replenishment;


    @Test
    public void methodParameterizedTest() {
        app.getHomePage()
                .selectTab("Вклады")
                .checkOpenDepositPage()
                .selectCurrency("Рубли")
                .fillField("Сумма вклада", sum)
                .fillDate(date)
                .fillField("Ежемесячное пополнение", replenishment)
                .selectExtraOptions("Ежемесячная капитализация")
                .checkParameter();

    }
}

