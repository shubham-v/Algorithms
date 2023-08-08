package retry;

import java.util.Random;

public class ExponentialBackoffWithJitter {

    void execute() throws InterruptedException {
        int maxRetries = 5, initialWaitTime = 3000, multiplier = 2, failedCount = 0;
        Random random = new Random();
        boolean isSuccess = false;
        while (!isSuccess) {
            try {
                this.send();
                isSuccess = true;
            } catch (final Exception ex) {
                failedCount++;
                if (failedCount > maxRetries) {
                    System.out.println(String.format("{ FailedCount: %s }", failedCount));
                    break;
                }
                long waitInterval = (initialWaitTime * (long) Math.pow(multiplier, failedCount)) + this.getJitter(random);
                System.out.println(String.format("{ FailedCount: %s, WaitInterval: %s milliseconds }", failedCount, waitInterval));
                Thread.sleep(waitInterval);
            }
        }
    }

    private int getJitter(final Random random) {
        return random.nextInt(1000);
    }

    private void send() throws Exception {
        Random random = new Random();
        int n = random.nextInt(20) + 1;
        if (n % 11 != 0) {
            throw new Exception();
        }
        System.out.println(n);
    }

    public static void main(String[] args) throws InterruptedException {
        new ExponentialBackoffWithJitter().execute();
    }
}
