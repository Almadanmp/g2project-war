//package pt.ipp.isep.dei.project.controller;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.support.AnnotationConfigContextLoader;
//import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
//import pt.ipp.isep.dei.project.dto.LocalDTO;
//import pt.ipp.isep.dei.project.dto.Mapper;
//import pt.ipp.isep.dei.project.dto.SensorDTO;
//import pt.ipp.isep.dei.project.io.ui.MainUI;
//import pt.ipp.isep.dei.project.model.*;
//
//import java.io.*;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import static org.testng.Assert.*;
//
///**
// * GASettingsController tests class.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@DataJpaTest
//@ContextConfiguration(classes = {MainUI.class },
//        loader = AnnotationConfigContextLoader.class)
//class GASettingsControllerTest {
//    private GASettingsController controller = new GASettingsController();
//    private GeographicArea firstValidArea;
//    private GeographicArea secondValidArea;
//    private TypeArea typeCountry;
//    private TypeArea typeCity;
//    private GeographicAreaDTO validGeographicAreaDTO;
//    private SensorDTO validSensorDTO;
//    private Sensor validSensor;
//    private Mapper mapper;
//    private GeographicAreaList validGeographicAreaList;
//    private Date date; // Wed Nov 21 05:12:00 WET 2018
//
//
//    @BeforeEach
//    void arrangeArtifacts() {
//        SimpleDateFormat day = new SimpleDateFormat("dd-MM-yyyy");
//        try {
//            date = day.parse("12-12-2018");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        typeCountry = new TypeArea("Country");
//        typeCity = new TypeArea("City");
//        firstValidArea = new GeographicArea("Portugal", typeCountry,
//                2, 5, new Local(21, 33, 5));
//        secondValidArea = new GeographicArea("Portugal", typeCity,
//                2, 5, new Local(21, 33, 5));
//        validSensor = new Sensor("RF12345", "SensOne", new TypeSensor("Temperature", "Celsius"),
//                new Local(31, 15, 3), date);
//        firstValidArea.addSensor(validSensor);
//        mapper = new Mapper();
//        validGeographicAreaDTO = mapper.geographicAreaToDTO(firstValidArea);
//        validSensorDTO = mapper.sensorToDTO(validSensor);
//        validGeographicAreaList = new GeographicAreaList();
//        validGeographicAreaList.addGeographicArea(firstValidArea);
//    }
//
//
//    //SHARED METHODS
//
////    @Test
////    void seeIfPrintGATypeListWorks() {
////        // Arrange
////
////        TypeAreaList list = new TypeAreaList();
////        list.addTypeArea(typeCountry);
////        list.addTypeArea(typeCity);
////        String expectedResult = "---------------\n" +
////                "0) Name: Country \n" +
////                "1) Name: City \n" +
////                "---------------\n";
////
////        // Act
////
////        String actualResult = controller.buildGATypeListString(list);
////
////
////        // Assert
////
////        assertEquals(expectedResult, actualResult);
////    }
//
//    @Test
//    void seeIfMatchGAByTypeAreaWorks() {
//        // Arrange
//
//        GeographicAreaList geographicAreaList = new GeographicAreaList();
//        geographicAreaList.addGeographicArea(firstValidArea);
//        geographicAreaList.addGeographicArea(secondValidArea);
//        GeographicAreaList expectedResult = new GeographicAreaList();
//        expectedResult.addGeographicArea(secondValidArea);
//
//        // Act
//
//        GeographicAreaList actualResult = controller.matchGAByTypeArea(geographicAreaList, mapper.typeAreaToDTO(typeCity));
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    void seeIfGetTypeAreaName() {
//
//        // Arrange
//
//        String expectedResult = "City";
//
//        // Act
//        String actualResult = controller.getTypeAreaName(mapper.typeAreaToDTO(typeCity));
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//    }
//
//    //USER STORY 001 TESTS
//
////    @Test
////    void seeIfCreateTypeAreaWorksEmptyList() {
////
////        // Arrange
////
////        TypeAreaList newList = new TypeAreaList();
////
////        // Act
////
////        boolean result = controller.createAndAddTypeAreaToList("City", newList);
////
////        // Assert
////
////        assertTrue(result);
////    }
//
////    @Test
////    void seeIfCreateTypeAreaWorksListWithElements() {
////
////        // Arrange
////
////        TypeAreaList typeAreaList = new TypeAreaList();
////        typeAreaList.addTypeArea(typeCountry);
////
////        // Act
////
////        boolean result = controller.createAndAddTypeAreaToList("City", typeAreaList);
////
////        // Assert
////
////        assertTrue(result);
////    }
//
////    @Test
////    void seeIfNewTAGDoesntWorkWhenDuplicatedISAdded() {
////
////        // Arrange
////
////        TypeAreaList expectedResult = new TypeAreaList();
////        expectedResult.addTypeArea(typeCountry);
////
////        // Act
////
////        boolean result = controller.createAndAddTypeAreaToList("Country", expectedResult);
////
////        // Assert
////
////        assertFalse(result);
////    }
//
////    @Test
////    void seeIfCreateTypeAreaDoesntWorkWhenNullIsAdded() {
////
////        // Arrange
////
////        TypeAreaList list = new TypeAreaList();
////        list.addTypeArea(typeCountry);
////
////        // Act
////
////        boolean result = controller.createAndAddTypeAreaToList(null, list);
////
////        // Assert
////
////        assertFalse(result);
////    }
//
////    @Test
////    void seeIfCreateTypeAreaDoesntWorkWhenNameIsEmpty() {
////
////        // Arrange
////
////
////        TypeAreaList list = new TypeAreaList();
////        list.addTypeArea(typeCountry);
////
////        // Act
////
////        boolean result = controller.createAndAddTypeAreaToList("", list);
////
////        // Assert
////
////        assertFalse(result);
////    }
//
////    @Test
////    void seeIfCreateTypeAreaDoesntWorkWhenNumbersAreAdded() {
////
////        // Arrange
////
////        TypeAreaList list = new TypeAreaList();
////        list.addTypeArea(typeCountry);
////
////        // Act
////
////        boolean result = controller.createAndAddTypeAreaToList("Country21", list);
////
////        // Assert
////
////        assertFalse(result);
////    }
//
//
//    //USER STORY 002 TESTS
//
////    @Test
////    void seeIfPrintTypeAreaListWorks() {
////
////        // Arrange
////
////        TypeAreaList list = new TypeAreaList();
////        list.addTypeArea(typeCountry);
////        String expectedResult = "---------------\n" +
////                "0) Name: Country \n" +
////                "---------------\n";
////
////        // Act
////
////        String actualResult = controller.getTypeAreaList(list);
////
////        // Assert
////
////        assertEquals(expectedResult, actualResult);
////    }
//
////    @Test
////    void seeIfPrintTypeAreaListWorksWithTwoTypes() {
////
////        // Arrange
////
////        TypeAreaList list = new TypeAreaList();
////        list.addTypeArea(typeCountry);
////        list.addTypeArea(typeCity);
////        String expectedResult = "---------------\n" +
////                "0) Name: Country \n" +
////                "1) Name: City \n" +
////                "---------------\n";
////
////        // Act
////
////        String actualResult = controller.getTypeAreaList(list);
////
////        // Assert
////
////        assertEquals(expectedResult, actualResult);
////    }
//
////    @Test
////    void seeIfPrintTypeAreaListWorksWithThreeTypes() {
////
////        // Arrange
////
////        TypeAreaList list = new TypeAreaList();
////        list.addTypeArea(typeCity);
////        list.addTypeArea(typeCountry);
////        String expectedResult = "---------------\n" +
////                "0) Name: City \n" +
////                "1) Name: Country \n" +
////                "---------------\n";
////
////        // Act
////
////        String actualResult = controller.getTypeAreaList(list);
////
////        // Assert
////
////        assertEquals(expectedResult, actualResult);
////    }
//
//    //USER STORY 003 TESTS
//
//    @Test
//    void seeIfCreatesGeographicAreaAndAddsItToList() {
//
//        // Arrange
//
//        GeographicAreaList geoList = new GeographicAreaList();
//
//        // Act
//
//        boolean result = controller.addNewGeoAreaToList(geoList, validGeographicAreaDTO, mapper.localToDTO(firstValidArea.getLocal()));
//
//        // Assert
//
//        assertTrue(result);
//        assertEquals(1, geoList.size());
//    }
//
//    //USER STORY 004 TESTS
//
//    @Test
//    void seeIfMatchGAByTypeCountry() {
//
//        //Arrange
//
//        GeographicAreaList gaL1 = new GeographicAreaList();
//        gaL1.addGeographicArea(firstValidArea);
//        gaL1.addGeographicArea(secondValidArea);
//        GeographicAreaList expectedResult = new GeographicAreaList();
//        expectedResult.addGeographicArea(firstValidArea);
//
//        //Act
//
//        GeographicAreaList actualResult = controller.matchGAByTypeArea(gaL1, mapper.typeAreaToDTO(typeCountry));
//
//        //Assert
//
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    void seeIfMatchGAByTypeCity() {
//
//        //Arrange
//
//        GeographicAreaList gaL1 = new GeographicAreaList();
//        gaL1.addGeographicArea(firstValidArea);
//        gaL1.addGeographicArea(secondValidArea);
//        GeographicAreaList expectedResult = new GeographicAreaList();
//        expectedResult.addGeographicArea(secondValidArea);
//
//        //Act
//
//        GeographicAreaList actualResult = controller.matchGAByTypeArea(gaL1, mapper.typeAreaToDTO(typeCity));
//
//        //Assert
//
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    void seeMatchGAByTypeNotInList() {
//
//        //Arrange
//
//        GeographicAreaList gaL1 = new GeographicAreaList();
//        gaL1.addGeographicArea(firstValidArea);
//        GeographicAreaList expectedResult = new GeographicAreaList();
//
//        //Act
//
//        GeographicAreaList actualResult = controller.matchGAByTypeArea(gaL1, mapper.typeAreaToDTO(typeCity));
//
//        //Assert
//
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    void getTypeAreaName() {
//
//        //Arrange
//
//        String expectedResult = "City";
//        String actualResult;
//
//        //Act
//        actualResult = controller.getTypeAreaName(mapper.typeAreaToDTO(typeCity));
//
//        //Assert
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    void seeIfDeactivateSensor() {
//
//        //Act
//        boolean actualResult = controller.deactivateSensor(validGeographicAreaList, validSensorDTO, validGeographicAreaDTO);
//
//        //Assert
//        assertTrue(actualResult);
//    }
//
//    @Test
//    void seeIfDeactivatedSensorDoenstChange() {
//
//        //Arrange
//        validSensor.deactivateSensor();
//        SensorDTO sensorDTO =  mapper.sensorToDTO(validSensor);
//
//        //Act
//        boolean actualResult = controller.deactivateSensor(validGeographicAreaList, sensorDTO, validGeographicAreaDTO);
//
//        //Assert
//        assertFalse(actualResult);
//    }
//
//
//    //
//    @Test
//    void seeIfPrintGAList() {
//
//        //Arrange
//
//        GeographicAreaList gAL1 = new GeographicAreaList();
//        gAL1.addGeographicArea(firstValidArea);
//        gAL1.addGeographicArea(secondValidArea);
//        String expectedResult = "---------------\n" +
//                "0) Name: Portugal | Type: Country | Latitude: 21.0 | Longitude: 33.0\n" +
//                "1) Name: Portugal | Type: City | Latitude: 21.0 | Longitude: 33.0\n" +
//                "---------------\n";
//
//        //Act
//
//        String result = controller.buildGAListString(gAL1);
//
//        //Assert
//
//        assertEquals(expectedResult, result);
//    }
//
//    @Test
//    void seeIfSetMotherAreaWorks() {
//
//        // Arrange
//
//        GeographicAreaList geographicAreaList = new GeographicAreaList();
//        geographicAreaList.addGeographicArea(firstValidArea);
//        geographicAreaList.addGeographicArea(secondValidArea);
//
//        // Act
//
//        boolean actualResult = controller.setMotherArea(firstValidArea, secondValidArea);
//
//        // Assert
//
//        Assertions.assertTrue(actualResult);
//    }
//
//    @Test
//    void seeIfSetMotherAreaBreaks() {
//        // Arrange
//
//        GeographicAreaList geographicAreaList = new GeographicAreaList();
//
//        // Act
//
//        geographicAreaList.addGeographicArea(firstValidArea);
//        boolean actualResult = controller.setMotherArea(firstValidArea, null);
//
//        // Assert
//
//        Assertions.assertFalse(actualResult);
//    }
//
////USER STORY 008 Tests
//
//    @Test
//    void seeIfItsContained() {
//
//        //Arrange
//
//        firstValidArea.setMotherArea(secondValidArea);
//
//        //Act
//
//        boolean actualResult = controller.isAreaContained(secondValidArea, firstValidArea);
//
//        //Assert
//
//        assertTrue(actualResult);
//    }
//
//    @Test
//    void seeIfIndirectlyContained() {
//
//        // Arrange
//
//        GeographicArea grandDaughterGA = new GeographicArea("Porto", new TypeArea("Country"),
//                2, 4, new Local(21, 33, 5));
//        grandDaughterGA.setMotherArea(secondValidArea);
//        secondValidArea.setMotherArea(firstValidArea);
//
//        // Act
//
//        boolean actualResult = controller.isAreaContained(firstValidArea, grandDaughterGA);
//
//        // Assert
//
//        assertTrue(actualResult);
//    }
//
//    @Test
//    void seeIfNotContained() {
//
//        // Arrange
//
//        GeographicArea grandDaughterGA = new GeographicArea("Oporto", new TypeArea("Country"), 2, 4, new Local(21, 33, 5));
//        grandDaughterGA.setMotherArea(secondValidArea);
//        secondValidArea.setMotherArea(firstValidArea);
//
//        // Act
//
//        boolean actualResult = controller.isAreaContained(grandDaughterGA, firstValidArea);
//
//        // Assert
//
//        assertFalse(actualResult);
//    }
//
//    @Test
//    void seeGAId() {
//
//        //Act
//
//        String actualResult = controller.getGeographicAreaId(firstValidArea);
//
//        //Assert
//
//        assertEquals(actualResult, "Portugal");
//    }
//
////    @Test
////    void seeIfActivate() {
////        // Arrange
////        Mapper mapper = new Mapper();
////        Date startDate = new Date();
////        Sensor sensor = new Sensor("SensOne", new TypeSensor("Temperature", "Celsius"),startDate );
////        sensor.setLocal(new Local(12,23,23));
////        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
////        try {
////            startDate = validSdf.parse("11/01/2018 10:00:00");
////
////        } catch (ParseException e) {
////            e.printStackTrace();
////        }
////        SensorDTO sensorDTO = mapper.sensorToDTO(sensor);
////
////        // Act
////
////        boolean actualResult = controller.activateOrDeactivateSensor(sensorDTO);
////
////        // Assert
////
////        Assertions.assertTrue(actualResult);
////    }
////
////    @Test
////    void seeIfDeactivate() {
////        // Arrange
////        Mapper mapper = new Mapper();
////        Date startDate = new Date();
////        Sensor sensor = new Sensor("SensOne", new TypeSensor("Temperature", "Celsius"),startDate );
////        sensor.setActive();
////        sensor.setLocal(new Local(12,23,23));
////        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
////        try {
////            startDate = validSdf.parse("11/01/2018 10:00:00");
////
////        } catch (ParseException e) {
////            e.printStackTrace();
////        }
////        SensorDTO sensorDTO = mapper.sensorToDTO(sensor);
////
////
////        // Act
////
////        boolean actualResult = controller.activateOrDeactivateSensor(sensorDTO);
////
////        // Assert
////
////        Assertions.assertFalse(actualResult);
////    }
//
//    @Test
//    void seeIfCreateGeoAreaDTO() {
//        GeographicAreaDTO expectedResult = new GeographicAreaDTO();
//        expectedResult.setName("Joana");
//        expectedResult.setLatitude(12);
//        expectedResult.setLongitude(13);
//        expectedResult.setAltitude(13);
//        expectedResult.setTypeArea(typeCity.getName());
//
//        GeographicAreaDTO result = controller.createGeoAreaDTO("Joana", mapper.typeAreaToDTO(typeCity), controller.createLocalDTO(12, 13, 13), 12, 13);
//
//        assertEquals(expectedResult, result);
//    }
//
//    @Test
//    void seeIfCreateLocalDTO() {
//
//        LocalDTO result = controller.createLocalDTO(12, 13, 14);
//
//        assertTrue(result instanceof LocalDTO);
//    }
//
//    //USER STORY 011 Tests
//
//    @Test
//    void seeIfInputAreaWorks() {
//
//        //Arrange
//
//        InputStream in = new ByteArrayInputStream("0".getBytes());
//        System.setIn(in);
//        GeographicAreaDTO expectedResult = validGeographicAreaDTO;
//
//        //Act
//
//        GeographicAreaDTO actualResult = controller.inputArea(validGeographicAreaList);
//
//        //Assert
//
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    void seeIfInputSensorWorks() {
//
//        //Arrange
//
//        InputStream in = new ByteArrayInputStream("0".getBytes());
//        System.setIn(in);
//        SensorDTO expectedResult = validSensorDTO;
//
//        //Act
//
//        SensorDTO actualResult = controller.inputSensor(validGeographicAreaDTO);
//
//        //Assert
//
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    void seeIfRemoveSensorWorks() {
//
//        //Arrange
//
//        GeographicAreaList expectedResult = validGeographicAreaList;
//        expectedResult.get(0).removeSensor(validSensor);
//
//        //Act
//
//        controller.removeSensor(validGeographicAreaList, validSensorDTO, validGeographicAreaDTO);
//        GeographicAreaList actualResult = validGeographicAreaList;
//
//        //Assert
//
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    void seeIfRemoveSensorFails() {
//
//        //Arrange
//
//        GeographicAreaList expectedResult = new GeographicAreaList();
//
//        //Act
//
//        controller.removeSensor(validGeographicAreaList, validSensorDTO, validGeographicAreaDTO);
//        GeographicAreaList actualResult = validGeographicAreaList;
//
//        //Assert
//
//        assertNotEquals(expectedResult, actualResult);
//    }
//
//}