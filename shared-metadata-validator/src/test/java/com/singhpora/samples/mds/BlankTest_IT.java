package com.singhpora.samples.mds;

import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusXmlTest;
import com.consol.citrus.testng.AbstractTestNGCitrusTest;

/**
 * This is a sample Citrus integration test using SOAP client and server.
 * @author Citrus
 */
@Test
public class BlankTest_IT extends AbstractTestNGCitrusTest {

    @CitrusXmlTest(name = "BlankTest_IT")
    public void blankTestOk() {
    }
}
