package org.expamle.tests.base;

import org.example.framework.managers.DriverManager;
import org.example.framework.managers.InitManager;
import org.example.framework.managers.PageManager;
import org.example.framework.managers.TestPropManager;

import org.example.framework.utils.PropConst;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;



public class BaseClasses {

    /**
     * Менеджер страничек
     * @see PageManager#getPageManager()
     */
    protected PageManager app = PageManager.getPageManager();

    /**
     * Менеджер WebDriver
     *
     * @see DriverManager#getDriverManager()
     */
    private final DriverManager driverManager = DriverManager.getDriverManager();

    @BeforeClass
    public static void beforeClass() {
        InitManager.initFramework();
    }

    @Before
    public void Before() {
        driverManager.getDriver().get(TestPropManager.getTestPropManager().getProperty(PropConst.BASE_URL));
    }

    @After
    public void After() {
        driverManager.quitDriver();
    }

    @AfterClass
    public static void afterClass() {
        InitManager.quitFramework();
    }

}

