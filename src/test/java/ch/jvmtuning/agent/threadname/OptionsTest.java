package ch.jvmtuning.agent.threadname;


import ch.jvmtuning.agent.threadname.filter.Options;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <A HREF="mailto:ras@panter.ch">Raffael Schmid</A>
 */
public class OptionsTest {

    @Test
    public void parseReturnsClassPatternIfDefined() throws Exception {

        //when
        final Options retVal = Options.parse("classPattern=main.Main,methodPattern=doValidationTests");

        //then
        assertThat(retVal.getClassPattern(), equalTo("main.Main"));
    }

    @Test
    public void parseReturnsMethodPatternIfDefined() throws Exception {

        //when
        final Options retVal = Options.parse("classPattern=main.Main,methodPattern=doValidationTests");

        //then
        assertThat(retVal.getMethodPattern(), equalTo("doValidationTests"));
    }

    @Test
    public void testParseReturnsDefaultClassPatternIfNotDefined() throws Exception {

        //when
        final Options retVal = Options.parse("");

        //then
        assertThat(retVal.getClassPattern(), equalTo(".*"));
    }

    @Test
    public void testParseReturnsDefaultMethodPatternIfNotDefined() throws Exception {

        //when
        final Options retVal = Options.parse("");

        //then
        assertThat(retVal.getMethodPattern(), equalTo(".*"));
    }
}