package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.TestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * FridgeSpec tests class.
 */

class FridgeSpecTest {

    @Test
    void getTypeTest() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        String expectedResult = "Fridge";
        String result = fridgeSpec.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    void getConsumptionTest() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        double expectedResult = 0;
        double result = fridgeSpec.getConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeValuesTest() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        fridgeSpec.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        double expectedResult = 4.0;
        Object result = fridgeSpec.getAttributeValue("Freezer Capacity");
        assertEquals(expectedResult, result);
    }

    @Test
    void setAttributeValueTest() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        boolean result = fridgeSpec.setAttributeValue("lisboa", 12D);
        assertEquals(false, result);
    }

    @Test
    void seeIfGetAttributeNamesFreezer() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        fridgeSpec.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        double expectedResult = 4;
        assertEquals(expectedResult, fridgeSpec.getAttributeValue("Freezer Capacity"));
    }

    @Test
    void seeIfGetAttributeNamesRefrigerator() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        fridgeSpec.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 5D);
        double expectedResult = 5;
        assertEquals(expectedResult, fridgeSpec.getAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY));
    }

    @Test
    void seeIfGetAttributeNamesAnnual() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        fridgeSpec.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 6D);
        double expectedResult = 6;
        assertEquals(expectedResult, fridgeSpec.getAttributeValue("Annual Energy Consumption"));
    }

    @Test
    void seeIfSetAttributeValuesTest() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        Object result = fridgeSpec.setAttributeValue("Freezer Capacity", fridgeSpec);
        assertEquals(false, result);
    }

    @Test
    void seeIfGetAttributeNamesTest() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(FridgeSpec.FREEZER_CAPACITY);
        expectedResult.add(FridgeSpec.REFRIGERATOR_CAPACITY);
        expectedResult.add(FridgeSpec.ANNUAL_CONSUMPTION);
        expectedResult.add(FridgeSpec.NOMINAL_POWER);
        List<String> result = fridgeSpec.getAttributeNames();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesTest1() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        double expectedResult = 4;
        fridgeSpec.setAttributeValue(FridgeSpec.NOMINAL_POWER, 4D);
        Object result = fridgeSpec.getAttributeValue("nominal power");
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesTest() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        double expectedResult = 4;
        fridgeSpec.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        Object result = fridgeSpec.getAttributeValue("Freezer Capacity");
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeUnitTest() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        String expectedResult = "Kg";
        Object result = fridgeSpec.getAttributeUnit("Freezer Capacity");
        assertEquals(expectedResult, result);
        assertEquals(0, fridgeSpec.getAttributeUnit(""));
    }
    @Test
    void seeIfGetAttributeUnitTest2() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        String expectedResult = "Kg";
        Object result = fridgeSpec.getAttributeUnit("Refrigerator Capacity");
        assertEquals(expectedResult, result);
        assertEquals(0, fridgeSpec.getAttributeUnit(""));

    }

    @Test
    void seeIfGetAttributeUnitTest3() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        String expectedResult = "kWh";
        Object result = fridgeSpec.getAttributeUnit("Annual Energy Consumption");
        assertEquals(expectedResult, result);
        assertEquals(0, fridgeSpec.getAttributeUnit(""));

    } @Test
    void seeIfGetAttributeUnitTest4() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        String expectedResult = "kW";
        Object result = fridgeSpec.getAttributeUnit("nominal power");
        assertEquals(expectedResult, result);
        assertEquals(0, fridgeSpec.getAttributeUnit(""));

    }

    @Test
    void seeIfGetAttributeValuesTest2() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        int expectedResult = 0;
        Object result = fridgeSpec.getAttributeValue("no");
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAndSetAttributeValue() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        String attribute = "Freezer Capacity";
        fridgeSpec.setAttributeValue(attribute, 6);
        Double expectedResult = 6.0;
        boolean setResult = fridgeSpec.setAttributeValue(attribute, 6.0);
        Object getResult = fridgeSpec.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);
    }

    @Test
    void seeIfGetAndSetAttributeValue2() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        String attribute = "Refrigerator Capacity";
        Double expectedResult = 6.0;
        fridgeSpec.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 6);
        boolean setResult = fridgeSpec.setAttributeValue(attribute, 6.0);
        Object getResult = fridgeSpec.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);
    }

    @Test
    void seeIfGetAndSetAttributeValue3() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        String attribute = "Annual Energy Consumption";
        fridgeSpec.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 6);
        Double expectedResult = 6.0;
        boolean setResult = fridgeSpec.setAttributeValue(attribute, 6.0);
        Object getResult = fridgeSpec.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);
    }

    @Test
    void seeIfGetAndSetAttributeValue4() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        String attribute = "nominal power";
        fridgeSpec.setAttributeValue(attribute, 6.0);
        Double expectedResult = 6.0;
        boolean setResult = fridgeSpec.setAttributeValue(attribute, 6.0);
        Object getResult = fridgeSpec.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);
    }

    @Test
    void seeIfSetAttributeValueInvalid() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        String attribute = "invalid";
        boolean result = fridgeSpec.setAttributeValue(attribute, 6);
        assertFalse(result);
    }

    @Test
    void seeIfSetAttributeValueInvalid2() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        String attribute = "freezerCapacity";
        boolean result = fridgeSpec.setAttributeValue(attribute, 6);
        assertFalse(result);
    }

    @Test
    void seeIfSetAttributeValueInvalid3() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        String attribute = "refrigeratorCapacity";
        boolean result = fridgeSpec.setAttributeValue(attribute, 6);
        assertFalse(result);
    }

    @Test
    void seeIfSetAttributeValueInvalid4() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        String attribute = "annualEnergyConsumption";
        boolean result = fridgeSpec.setAttributeValue(attribute, 6);
        assertFalse(result);
    }
    @Test
    void seeIfSetAttributeValueInvalid9() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        String attribute = "nominal power";
        boolean result = fridgeSpec.setAttributeValue(attribute, 89);
        assertFalse(result);
    }

    @Test
    void seeIfSetAttributeValueInvalid5() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        String attribute = "freezerCapacity";
        boolean result = fridgeSpec.setAttributeValue(attribute, "ljlkhg");
        assertFalse(result);
    }

    @Test
    void seeIfSetAttributeValueInvalid6() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        String attribute = "refrigeratorCapacity";
        boolean result = fridgeSpec.setAttributeValue(attribute, "ljlkhg");
        assertFalse(result);
    }

    @Test
    void seeIfSetAttributeValueInvalid7() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        String attribute = "annualEnergyConsumption";
        boolean result = fridgeSpec.setAttributeValue(attribute, "ljlkhg");
        assertFalse(result);
    } @Test
    void seeIfSetAttributeValueInvalid8() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        String attribute = "nominal power";
        boolean result = fridgeSpec.setAttributeValue(attribute, "ljlkhg");
        assertFalse(result);
    }

    @Test
    void seeIfGetObjectAttributeValueTestWorks() {
        //Arrange
        FridgeSpec fridgeSpec = new FridgeSpec();
        fridgeSpec.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        fridgeSpec.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 5D);
        fridgeSpec.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 6D);
        fridgeSpec.setAttributeValue(TestUtils.NOMINAL_POWER, 6D);
        //Act
        double expectedResult1 = 4;
        double expectedResult2 = 5;
        double expectedResult3 = 6;
        double expectedResult4 = 6.0;
        Object actualResult1 = fridgeSpec.getAttributeValue("Freezer Capacity");
        Object actualResult2 = fridgeSpec.getAttributeValue("Refrigerator Capacity");
        Object actualResult3 = fridgeSpec.getAttributeValue("Annual Energy Consumption");
        Object actualResult4 = fridgeSpec.getAttributeValue("nominal power");
        //Assert
        assertEquals(expectedResult1, actualResult1);
        assertEquals(expectedResult2, actualResult2);
        assertEquals(expectedResult3, actualResult3);
        assertEquals(expectedResult4, actualResult4);
    }

    @Test
    void testGetAttributeCoveringAllCases() {
        //Arrange
        FridgeSpec fridgeSpec = new FridgeSpec();
        fridgeSpec.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, null);
        fridgeSpec.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, null);
        fridgeSpec.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, null);
        fridgeSpec.setAttributeValue(TestUtils.NOMINAL_POWER, null);


        // original strings:
        assertEquals(0.0, fridgeSpec.getAttributeValue("Freezer Capacity"));
        assertEquals(0.0, fridgeSpec.getAttributeValue("Refrigerator Capacity"));
        assertEquals(0.0, fridgeSpec.getAttributeValue("Annual Energy Consumption"));
        assertEquals(0.0, fridgeSpec.getAttributeValue("nominal power"));

        // same hash codes, but different strings:
        assertEquals(0, fridgeSpec.getAttributeValue("\0Freezer Capacity"));
        assertEquals(0, fridgeSpec.getAttributeValue("\0Refrigerator Capacity"));
        assertEquals(0, fridgeSpec.getAttributeValue("\0Annual Energy Consumption"));
        assertEquals(0, fridgeSpec.getAttributeValue("\0nominal power"));

        // distinct hash code to cover default cases of switches
        assertEquals(0, fridgeSpec.getAttributeValue(""));
    }

    @Test
    void testSetAttributeCoveringAllCases() {
        //Arrange
        FridgeSpec fridgeSpec = new FridgeSpec();
        Double attribute = 6.0;
        fridgeSpec.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, null);
        fridgeSpec.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, null);
        fridgeSpec.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, null);
        fridgeSpec.setAttributeValue(TestUtils.NOMINAL_POWER, null);

        // original strings:
        assertTrue(fridgeSpec.setAttributeValue("Freezer Capacity", attribute));
        assertTrue(fridgeSpec.setAttributeValue("Refrigerator Capacity", attribute));
        assertTrue(fridgeSpec.setAttributeValue("Annual Energy Consumption", attribute));
        assertTrue(fridgeSpec.setAttributeValue("nominal power", attribute));

        // same hash codes, but different strings:
        assertFalse(fridgeSpec.setAttributeValue("\0Freezer Capacity", attribute));
        assertFalse(fridgeSpec.setAttributeValue("\0Refrigerator Capacity", attribute));
        assertFalse(fridgeSpec.setAttributeValue("\0Annual Energy Consumption", attribute));
        assertFalse(fridgeSpec.setAttributeValue("\0nominal power", attribute));

        // distinct hash code to cover default cases of switches
        assertFalse(fridgeSpec.setAttributeValue("", attribute));
    }

}