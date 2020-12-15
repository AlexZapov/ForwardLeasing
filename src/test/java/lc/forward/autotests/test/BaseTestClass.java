package lc.forward.autotests.test;

import lc.forward.autotests.appium.AppiumBaseClass;
import lc.forward.autotests.appium.AppiumController;
import lc.forward.autotests.source.pageObject.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTestClass extends AppiumBaseClass {

    MainPage mainPage;
    ProfilePage profilePage;
    RegistrationPage registrationPage;
    EnterSMSCode enterSMSCode;
    DetailInfoPage detailInfoPage;
    OnboardingPage onboardingPage;
    ClientDetailPage clientDetailPage;
    CurrentContract currentContract;
    PassportPage passportPage;
    ReviewPage reviewPage;
    OrderSignPage orderSignPage;
    GettingOrder gettingOrder;
    ConfirmOrderPage confirmOrderPage;
    DonePage donePage;
    OrderEndPage orderEndPage;

    @BeforeMethod
    public void setUp() throws Exception {
        AppiumController.instance.start();
        switch (AppiumController.executionOS) {
            case ANDROID:
                mainPage = new MainPageAndroid(driver());
                profilePage = new ProfilePageAndroid(driver());
                registrationPage = new RegistrationPageAndroid(driver());
                enterSMSCode = new EnterSMSCodeAndroid(driver());
                detailInfoPage = new DetailInfoPageAndroid(driver());
                onboardingPage = new OnboardingPageAndroid(driver());
                clientDetailPage = new ClientDetailPageAndroid(driver());
                currentContract = new CurrentContractAndroid(driver());
                passportPage = new PassportPageAndroid(driver());
                reviewPage = new ReviewPageAndroid(driver());
                orderSignPage = new OrderSignPageAndroid(driver());
                gettingOrder = new GettingOrderAndroid(driver());
                confirmOrderPage = new ConfirmOrderPageAndroid(driver());
                donePage = new DonePageAndroid(driver());
                orderEndPage = new OrderEndPageAndroid(driver());
                break;
            case IOS:
        }
    }

    @AfterMethod
    public void tearDown() {
        AppiumController.instance.stop();
    }
}
