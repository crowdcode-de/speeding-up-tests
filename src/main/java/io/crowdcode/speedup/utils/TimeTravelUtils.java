package io.crowdcode.speedup.utils;

public class TimeTravelUtils {
    public static void sleepAWhile() {
        if (System.getProperty("slowDown") != null) {
            try {
                Thread.sleep(1);
                Thread.yield();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
