package ch.jvmtuning.agent.threadname;

import ch.jvmtuning.agent.threadname.filter.InstrumentationFilter;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author <A HREF="mailto:ras@panter.ch">Raffael Schmid</A>
 */
public class InstrumentationFilterTest {

    private final InstrumentationFilter instance = new InstrumentationFilter("java\\.lang\\.String", "toString");

    @Test
    public void testShouldInstrumentClassReturnsTrueOnMatch() throws Exception {

        //when
        final boolean retVal = instance.shouldInstrumentClass("java/lang/String");

        //then
        assertThat(retVal, is(true));
    }

    @Test
    public void testShouldInstrumentClassReturnsFalseIfNotMatches() throws Exception {

        //when
        final boolean retVal = instance.shouldInstrumentClass("java/lang/Long");

        //then
        assertThat(retVal, is(false));
    }
}