package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * LampSpec tests class.
 */

public class LampSpecTest {

    @Test
    public void getTypeTest() {
        LampSpec lampSpec = new LampSpec();
        String expectedResult = "Lamp";
        String result = lampSpec.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getConsumptionTest() {
        LampSpec lampSpec = new LampSpec();
        double expectedResult = 0;
        double result = lampSpec.getConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAttributeNamesTest() {
        LampSpec lampSpec = new LampSpec();
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Luminous Flux");
        List<String> result = lampSpec.getAttributeNames();
        assertEquals(expectedResult, result);
    }

    @Test
    public void setAttributeValueTest() {
        LampSpec lampSpec = new LampSpec();
        boolean result = lampSpec.setAttributeValue("lisboa", 12);
        assertEquals(false, result);
    }

    @Test
    public void setAttributeValueTestTrue2() {
        LampSpec lampSpec = new LampSpec();
        boolean actualResult = lampSpec.setAttributeValue("Luminous Flux", 12.0);
        assertTrue(actualResult);
    }

    @Test
    public void setAttributeValueTestTrue3() {
        LampSpec lampSpec = new LampSpec();
        boolean actualResult = lampSpec.setAttributeValue("nominal power", 12.0);
        assertTrue(actualResult);
    }

    @Test
    public void getObjectAttributeValueTest() {
        LampSpec lampSpec = new LampSpec();
        lampSpec.setAttributeValue(LampSpec.FLUX, 4D);
        Double expectedResult = 4.0;
        Object result = lampSpec.getAttributeValue(LampSpec.FLUX);
        assertEquals(expectedResult, result);
    }
    @Test
    public void getObjectAttributeUnitTest() {
        LampSpec lampSpec = new LampSpec();
        String expectedResult = "lm";
        Object result = lampSpec.getAttributeUnit(LampSpec.FLUX);
        assertEquals(expectedResult, result);
        assertEquals(false, lampSpec.getAttributeUnit(""));

    }

    @Test
    public void setAttributeValueTestFalse() {
        LampSpec lampSpec = new LampSpec();
        Object result = lampSpec.setAttributeValue("luminousFlux", 5);
        assertEquals(false, result);
    }

    @Test
    public void setAttributeValueTestDefault() {
        LampSpec lampSpec = new LampSpec();
        lampSpec.setAttributeValue("luminousFlux", 5.0);
        Object result = lampSpec.getAttributeValue("lisbon");
        assertEquals(false, result);
    }

    @Test
    public void setAttributeValueTestTrue() {
        LampSpec lampSpec = new LampSpec();
        lampSpec.setAttributeValue("Luminous Flux", 5.0);
        Object result = lampSpec.getAttributeValue("Luminous Flux");
        assertEquals(5.0, result);
    }@Test
    public void getObjectAttributeValueTestNominalPower() {
        LampSpec lampSpec = new LampSpec();
        lampSpec.setAttributeValue(LampSpec.NOMINAL_POWER, 4D);
        Double expectedResult = 4.0;
        Object result = lampSpec.getAttributeValue(LampSpec.NOMINAL_POWER);
        assertEquals(expectedResult, result);
    }
    @Test
    public void getObjectAttributeUnitTestNominalPower() {
        LampSpec lampSpec = new LampSpec();
        String expectedResult = "kW";
        Object result = lampSpec.getAttributeUnit(LampSpec.NOMINAL_POWER);
        assertEquals(expectedResult, result);
        assertEquals(false, lampSpec.getAttributeUnit(""));

    }

    @Test
    public void setAttributeValueTestFalseNominalPower() {
        LampSpec lampSpec = new LampSpec();
        Object result = lampSpec.setAttributeValue(LampSpec.NOMINAL_POWER, 5);
        assertEquals(false, result);
    }

    @Test
    public void setAttributeValueTestFalseFlux() {
        LampSpec lampSpec = new LampSpec();
        Object result = lampSpec.setAttributeValue(LampSpec.FLUX, 5);
        assertEquals(false, result);
    }

    @Test
    public void setAttributeValueTestDefaultNominalPower() {
        LampSpec lampSpec = new LampSpec();
        lampSpec.setAttributeValue(LampSpec.NOMINAL_POWER, 5.0);
        Object result = lampSpec.getAttributeValue("lisbon");
        assertEquals(false, result);
    }

    @Test
    public void setAttributeValueTestTrueNominalPower() {
        LampSpec lampSpec = new LampSpec();
        lampSpec.setAttributeValue(LampSpec.NOMINAL_POWER, 5.0);
        Object result = lampSpec.getAttributeValue("nominal power");
        assertEquals(5.0, result);
    }
    @Test
    public void setAttributeValueTestTrueFlux() {
        LampSpec lampSpec = new LampSpec();
        lampSpec.setAttributeValue(LampSpec.FLUX, 5.0);
        Object result = lampSpec.getAttributeValue("Luminous Flux");
        assertEquals(5.0, result);
    }

    @Test
    void testGetAttributeValueForAllCases() {
        //Arrange
        LampSpec lSpec = new LampSpec();
        Double attribute = 6.0;
        lSpec.setAttributeValue(LampSpec.FLUX, attribute);
        lSpec.setAttributeValue(LampSpec.NOMINAL_POWER, attribute);
        // original strings:
        assertEquals(attribute, lSpec.getAttributeValue(LampSpec.FLUX));
        assertEquals(attribute, lSpec.getAttributeValue(LampSpec.NOMINAL_POWER));
        // same hash codes, but different strings:
        assertEquals(false, lSpec.getAttributeValue("notFLUX"));
        assertEquals(false, lSpec.getAttributeValue("notNOMINAL_POWER"));
        // distinct hash code to cover default cases of switches
        assertEquals(false, lSpec.getAttributeValue(""));
    }

    @Test
    void testGetAttributeUnitForAllCases() {
        //Arrange
        LampSpec lSpec = new LampSpec();
        String attributeLm = "lm";
        String attributeKW = "kW";
        // original strings:
        assertEquals(attributeLm, lSpec.getAttributeUnit(LampSpec.FLUX));
        assertEquals(attributeKW, lSpec.getAttributeUnit(LampSpec.NOMINAL_POWER));
        // same hash codes, but different strings:
        assertEquals(false, lSpec.getAttributeUnit("notFLUX"));
        assertEquals(false, lSpec.getAttributeUnit("notNOMINAL_POWER"));
        // distinct hash code to cover default cases of switches
        assertEquals(false, lSpec.getAttributeUnit(""));
    }

    @Test
    void testSetAttributeValueForAllCases() {
        //Arrange
        LampSpec lSpec = new LampSpec();
        Double attribute = 6.0;
        // original strings + double:
        assertTrue(lSpec.setAttributeValue(LampSpec.FLUX, attribute));
        assertTrue(lSpec.setAttributeValue(LampSpec.NOMINAL_POWER, attribute));
        // same hash codes, but different strings + double:
        assertFalse(lSpec.setAttributeValue("notFLUX", attribute));
        assertFalse(lSpec.setAttributeValue("notNOMINAL_POWER", attribute));
        // distinct hash code to cover default cases of switches + double
        assertFalse(lSpec.setAttributeValue("", attribute));
    }

    @Test
    void testSetAttributeValueForNotDouble() {
        //Arrange
        LampSpec lSpec = new LampSpec();
        Double attributeD = 6.0;
        Integer attribute = 6;
        lSpec.setAttributeValue(LampSpec.FLUX, attributeD);
        lSpec.setAttributeValue(LampSpec.NOMINAL_POWER, attributeD);
        // original strings + not double:
        assertFalse(lSpec.setAttributeValue(LampSpec.FLUX, attribute));
        assertFalse(lSpec.setAttributeValue(LampSpec.NOMINAL_POWER, attribute));
        // same hash codes, but different strings + not double:
        assertFalse(lSpec.setAttributeValue("notFLUX", attribute));
        assertFalse(lSpec.setAttributeValue("notNOMINAL_POWER", attribute));
        // distinct hash code to cover default cases of switches + not double
        assertFalse(lSpec.setAttributeValue("", attribute));
    }
}
