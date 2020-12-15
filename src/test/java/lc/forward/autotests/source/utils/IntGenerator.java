package lc.forward.autotests.source.utils;

import java.util.Random;

public interface IntGenerator {
    default Integer generateRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    default Integer generateInt(int max) {
        return new Random().nextInt(max);
    }
}
