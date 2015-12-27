package ch.jvmtuning.agent.threadname.filter;

/**
 * @author <A HREF="mailto:ras@panter.ch">Raffael Schmid</A>
 */
public class Option {

    private final Name name;
    private final String value;

    public Option(Name name, String value) {
        this.name = name;
        this.value = value;
    }

    public Name getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public boolean is(Name name) {
        return name.equals(this.name);
    }
}
