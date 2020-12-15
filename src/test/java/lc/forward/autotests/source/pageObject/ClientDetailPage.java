package lc.forward.autotests.source.pageObject;

public interface ClientDetailPage {

    ClientDetailPage fillAndCheckForm(String phone, String mail);
    ClientDetailPage clickContinueButton();
}
