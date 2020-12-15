package lc.forward.autotests.source.utils;

import lc.forward.autotests.source.utils.IntGenerator;

public interface DataGenerator extends IntGenerator {
    default String generateMobilePhone() {
        String mobile = String.valueOf(100 + generateRandomIntBetween(0, 599)) +
                (100 + generateInt(899)) +
                (10 + generateInt(89)) +
                (10 + generateInt(89));
        System.out.println("Mobile phone: " + mobile);
        return mobile;
    }

    default String generateUniqEmail() {
        String email = "test".concat(generateInt(999999999).toString()).concat("@forward.lc");
        System.out.println("email: " + email);
        return email;
    }
}
