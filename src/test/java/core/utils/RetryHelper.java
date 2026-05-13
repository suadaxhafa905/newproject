package core.utils;

public class RetryHelper {

    public static void retryAction(
            Runnable action,
            int maxAttempts
    ) {

        int attempts = 0;

        while (attempts < maxAttempts) {

            try {

                action.run();

                return;

            } catch (Exception e) {

                attempts++;

                System.out.println(
                        "Retry attempt: " + attempts
                );

                if (attempts == maxAttempts) {

                    throw e;
                }

                try {

                    Thread.sleep(1000);

                } catch (InterruptedException ex) {

                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}