package lc.forward.autotests.source.pageObject;

public interface ReviewPage {

    ReviewPage checkFields(String surname, String name, String middleName, String birthday, String birthPlace,
                     String passportDate, String passportIssue, String passportDepCode);
    ReviewPage enterFields(Integer salaryText);
    ReviewPage clickButtonDone();
}
