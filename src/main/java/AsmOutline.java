/**
 * @author <A HREF="mailto:ras@panter.ch">Raffael Schmid</A>
 */
public class AsmOutline {

    public static void main(String[] args) {
        setThreadName("foobar");
    }

    private static void setThreadName(String foobar) {
        final String currentName = Thread.currentThread().getName();
        final String newThreadName = Thread.currentThread() + " - test";

        Thread.currentThread().setName(newThreadName);
    }
}
