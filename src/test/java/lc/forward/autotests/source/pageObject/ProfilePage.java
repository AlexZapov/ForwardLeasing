package lc.forward.autotests.source.pageObject;

public interface ProfilePage {

    ProfilePage fillLoginFields(String phone, String PIN);
    ProfilePage signInButtonClick();
    ProfilePage registrationButtonClick();
    ProfilePage clickConfirmButton();
    ProfilePage checkDeliveryOrder(String status);
}
