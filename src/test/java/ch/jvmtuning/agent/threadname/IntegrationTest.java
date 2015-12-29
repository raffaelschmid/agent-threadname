package ch.jvmtuning.agent.threadname;

/**
 * @author <A HREF="mailto:ras@panter.ch">Raffael Schmid</A>
 */
public class IntegrationTest {

    public static void main(String... args) {

        IntegrationTest.test("one", "two", 3);
    }

    public static void test(String firstParameter, String secondParameter, int thirdParameter) {
        final int max = Integer.MAX_VALUE;

        for (int i = 0; i < max; i++) {
            if (i % 1000 == 0) {
                System.out.println("[" + Thread.currentThread().getName() + "] processing " + i + " of " + max);
                sleep(1000l);
            }

        }
        System.out.println("finished");
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
