package io.crowdcode.speedup.scrumr.controller;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LameWaitingControllerTest {


    @ParameterizedTest
    @MethodSource("dataProducer")
    void catchMeIfYouCan(Integer wait) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(wait);
    }

    public static Stream<Arguments> dataProducer() {
        return IntStream.range(1, 100).mapToObj(wait -> Arguments.of(wait));
    }
}
