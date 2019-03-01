package pt.ipp.isep.dei.project.model.device.program;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ProgramListTest {

    // Common artifacts for testing in this class.

    private FixedTimeProgram validProgram1;
    private ProgramList validProgramList1; //Empty List
    private ProgramList validProgramList2; //One Fixed Time Program

    @BeforeEach
    void arrangeArtifacts() {
        validProgram1 = new FixedTimeProgram("validProgram1", 2, 3);
        validProgramList1 = new ProgramList();
        validProgramList2 = new ProgramList();
        validProgramList2.addProgram(validProgram1);
    }

    @Test
    void testBuildProgramListString() {
        //Arrange

        String expectedResult = "---------------\n" + "\n0) Program Name: validProgram1, Duration: 2.0, Energy Consumption: 3.0"
                + "\n---------------\n";

        //Act

        String result = validProgramList2.buildString();

        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void testBuildEmptyProgramListString() {
        //Arrange

        String expectedResult = "This device has no programs\n";

        //Act

        String result = validProgramList1.buildString();

        //Assert

        assertEquals(expectedResult, result);
    }


    @Test
    void seeIfAddProgramToEmptyList() {
        //Act

        boolean result = validProgramList1.addProgram(validProgram1);

        //Assert

        assertTrue(result);
    }

    @Test
    void seeIfAddsRepeatedProgram() {
        //Act

        boolean result = validProgramList2.addProgram(validProgram1);

        //Assert

        assertFalse(result);
    }

    @Test
    void seeIfRemovesProgramOfEmptyList() {
        //Act

        boolean result = validProgramList1.removeProgram(validProgram1);

        //Assert

        assertFalse(result);
    }

    @Test
    void seeIfRemovesProgram() {
        //Act

        boolean result = validProgramList2.removeProgram(validProgram1);

        //Assert

        assertTrue(result);
    }

    @Test
    void testBuildDifferentProgramListString() {
        //Arrange

        VariableTimeProgram program2 = new VariableTimeProgram("validProgram2",70);
        validProgramList2.addProgram(program2);
        String expectedResult = "---------------\n" + "\n0) Program Name: validProgram1, Duration: 2.0, Energy Consumption: 3.0"
                + "\n1) Program Name: validProgram2, Nominal Power: 70.0"
                + "\n---------------\n";

        //Act

        String result = validProgramList2.buildString();

        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void isEmpty() {
        //Act

        boolean actualResult1 = validProgramList1.isEmpty();
        boolean actualResult2 = validProgramList2.isEmpty();

        //Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
    }

    @Test
    void size() {
        //Act

        int actualResult1 = validProgramList1.size();
        int actualResult2 = validProgramList2.size();

        //Assert

        assertEquals(0, actualResult1);
        assertEquals(1, actualResult2);
    }

    @Test
    void get() {
        //Arrange

        validProgramList1.addProgram(validProgram1);
        Program validProgram2 = new VariableTimeProgram("validProgram2", 200);
        validProgramList2.addProgram(validProgram2);

        //Act

        Program actualResult1 = validProgramList1.get(0);
        Program actualResult2 = validProgramList2.get(0);
        Program actualResult3 = validProgramList2.get(1);

        //Assert

        assertEquals(validProgram1, actualResult1);
        assertEquals(validProgram1, actualResult2);
        assertEquals(validProgram2, actualResult3);
    }

    @Test
    void getElementsAsArray() {
        //Arrange

        Program[] expectedResult1 = new Program[0];
        Program[] expectedResult2 = new Program[1];
        Program[] expectedResult3 = new Program[2];

        ProgramList emptyList = new ProgramList();
        validProgramList1.addProgram(validProgram1);
        Program validProgram2 = new VariableTimeProgram("validProgram2", 200);
        validProgramList2.addProgram(validProgram2);

        expectedResult2[0] = validProgram1;
        expectedResult3[0] = validProgram1;
        expectedResult3[1] = validProgram2;

        //Act

        Program[] actualResult1 = emptyList.getElementsAsArray();
        Program[] actualResult2 = validProgramList1.getElementsAsArray();
        Program[] actualResult3 = validProgramList2.getElementsAsArray();

        //Assert

        assertArrayEquals(expectedResult1, actualResult1);
        assertArrayEquals(expectedResult2, actualResult2);
        assertArrayEquals(expectedResult3, actualResult3);
    }

    @Test
    void equals() {
        //Arrange

        ProgramList programList = new ProgramList();
        programList.addProgram(validProgram1);

        //Act

        boolean actualResult1 = validProgramList1.equals(validProgramList1);
        boolean actualResult2 = validProgramList2.equals(validProgramList1);
        boolean actualResult3 = validProgramList2.equals(programList);
        boolean actualResult4 = validProgramList2.equals(2D);

        //Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
        assertTrue(actualResult3);
        assertFalse(actualResult4);
    }

    @Test
    void hashcode() {
        //Act

        int actualResult1 = validProgramList1.hashCode();

        //Assert

        assertEquals(1, actualResult1);
    }


}
