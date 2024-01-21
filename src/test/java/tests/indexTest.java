package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utils.*;
import pages.index;

public class indexTest extends SeleniumUtils {
    @BeforeClass
    public void setUpTest() {
        new index(getDriver());
        setUp();
    }

    @Test
    public void loadPage() {
        getDriver().get(getBaseUrl());
        // Perform login using page objects and generic functions
    }
}