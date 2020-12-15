package lc.forward.autotests.test;

import com.google.common.io.ByteStreams;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import lc.forward.autotests.source.utils.DataGenerator;
import lc.forward.autotests.source.utils.api.UserRegistration;
import lc.forward.autotests.source.utils.api.UploadDocuments;

import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

public class Test_CreateDocumentDelivery extends BaseTestClass implements DataGenerator,
        UserRegistration, UploadDocuments {

    public static String PHONE;
    public static String MAIL;
    public static String PIN = "1111";
    public static String TOKEN;
    public static String APPLICATION_ID;
    public static String SELFIE_IMG;
    public static String PASSPORT_IMG;
    public static Integer SALARY;
    public static String DELIVERY_STATUS = "Оформляется доставка";

    @BeforeTest
    public void setData() {
        PHONE = generateMobilePhone();
        MAIL = generateUniqEmail();
        SALARY = generateRandomIntBetween(30000, 300000);
        TOKEN = registerUser(PHONE, MAIL);
        try {
            PASSPORT_IMG = new String(Base64.getEncoder().encode(ByteStreams.toByteArray(Objects.requireNonNull(Test_CreateDocumentDelivery.class.getClassLoader().getResourceAsStream("passport.png")))));
        } catch (IOException e) {
            System.out.println("Can't load passport image!");
            e.printStackTrace();
        }
        try {
            SELFIE_IMG = new String(Base64.getEncoder().encode(ByteStreams.toByteArray(Objects.requireNonNull(Test_CreateDocumentDelivery.class.getClassLoader().getResourceAsStream("self.png")))));
        } catch (IOException e) {
            System.out.println("Can't load selfie image!");
            e.printStackTrace();
        }
    }

    @Test
    public void basicTest() {

        mainPage
                .clickProfileButton();
        profilePage
                .fillLoginFields(PHONE, PIN)
                .signInButtonClick();
        mainPage
                .clickCatalogButton()
                .clickCatalogImage();
        detailInfoPage
                .clickBtnBuy();
        onboardingPage
                .clickFirstContinueButton()
                .clickSecondContinueButton()
                .clickThirdContinueButton();
        clientDetailPage
                .fillAndCheckForm(PHONE, MAIL)
                .clickContinueButton();
        enterSMSCode
                .enterSMS("1", "1", "1", "1");
        passportPage
                .checkPassportPage();

        //upload passport and selfie
        APPLICATION_ID = getAppId(TOKEN);
        uploadPassport(PASSPORT_IMG, TOKEN, APPLICATION_ID);
        uploadSelfie(SELFIE_IMG, TOKEN, APPLICATION_ID);

        passportPage
                .clickBackButton();
        detailInfoPage
                .clickBackButton();
        mainPage
                .clickProfileButton();
        profilePage
                .clickConfirmButton();
        reviewPage
                .enterFields(SALARY)
                .clickButtonDone();
        orderEndPage
                .checkFrameText()
                .clickContinueButton();
        orderSignPage
                .checkOrderInfo()
                .clickSignButtin();
        enterSMSCode
                .enterSMS("1", "1", "1", "1");
        gettingOrder
                .checkDeliveryAdress()
                .clickContinueButton();
        confirmOrderPage
                .confirmDelivery()
                .clickDoneButton();
        enterSMSCode
                .enterSMS("1", "1", "1", "1");
        donePage
                .confirmOrder()
                .clickDoneButton();
        profilePage
                .checkDeliveryOrder(DELIVERY_STATUS);
    }
}
