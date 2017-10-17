package com.singhpora.samples.citrus;

import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusXmlTest;
import com.consol.citrus.testng.AbstractTestNGCitrusTest;

/**
 * This is a sample Citrus integration test using SOAP client and server.
 * @author Citrus
 */
@Test
public class SOACompositeTest_IT extends AbstractTestNGCitrusTest {
    
    

    @CitrusXmlTest(name = "SOACompositeTest_IT")
    public void helloServiceOk() {}
}
