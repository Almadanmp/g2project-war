package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.TestUtils;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingList;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.RoomList;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.WashingMachineSpec;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * DeviceList tests class.
 */

class DeviceListTest {

    @Test
    void seeIfAddDeviceWorksWithSameDevice() {
        //Arrange
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 9, 23456789, 2, 2);
        roomList1.addRoom(room1);
        //Reading List
        ReadingList rL1 = new ReadingList();
        Date d2 = new GregorianCalendar(2008, 2, 2).getTime();
        Reading r1;
        r1 = new Reading(30, d2);
        rL1.addReading(r1);
        //Total Power
        //device List
        DeviceList deviceL1 = new DeviceList();
        Device d1 = new Device(new FridgeSpec());
        d1.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        d1.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 56D);
        d1.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 345D);
        deviceL1.addDevice(d1);
        //Act
        Boolean actualResult = deviceL1.addDevice(d1);
        //Assert
        assertEquals(false, actualResult);
    }

    @Test
    void seeIfAddDeviceWorksWithDifferentDevice() {
        //Arrange
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 9, 23456789, 2, 2);
        roomList1.addRoom(room1);
        //Reading List
        ReadingList rL1 = new ReadingList();
        Date d2 = new GregorianCalendar(2008, 2, 2).getTime();
        Reading r1;
        r1 = new Reading(30, d2);
        rL1.addReading(r1);
        //device List
        DeviceList deviceL1 = new DeviceList();
        Device d1 = new Device(new FridgeSpec());
        d1.setName("fdhgfhs");
        d1.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        d1.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 56D);
        d1.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 345D);
        Device device2 = new Device(new FridgeSpec());
        device2.setName("fdhggfh");
        device2.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 5D);
        device2.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 6D);
        device2.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 345D);
        deviceL1.addDevice(d1);
        //Act
        Boolean actualResult = deviceL1.addDevice(device2);
        //Assert
        assertEquals(true, actualResult);
    }

    @Test
    void seeIfContainsDeviceWorks() {
        //Arrange --------------------------
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789, 2, 2);
        roomList1.addRoom(room1);
        //Reading List
        ReadingList rL1 = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        r1 = new Reading(30, d2);
        rL1.addReading(r1);
        //device List
        DeviceList deviceL1 = new DeviceList();
        Device d1 = new Device(new FridgeSpec());
        d1.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        d1.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 56D);
        d1.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 345D);
        deviceL1.addDevice(d1);
        //Act ------------------------------
        boolean result = deviceL1.containsDevice(d1);
        //Assert ---------------------------
        assertTrue(result);
    }

    @Test
    void seeIfContainsDeviceWorksForFalse() {
        //Arrange --------------------------
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789, 2, 2);
        roomList1.addRoom(room1);
        //Reading List
        ReadingList rL1 = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        r1 = new Reading(30, d2);
        rL1.addReading(r1);
        //device List
        DeviceList deviceL1 = new DeviceList();
        Device d1 = new Device(new FridgeSpec());
        d1.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        d1.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 56D);
        d1.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 345D);
        //Act ------------------------------
        boolean result = deviceL1.containsDevice(d1);
        //Assert ---------------------------
        assertFalse(result);
    }

    @Test
    void seeIfRemoveDeviceWorks() {
        //Arrange ------------------------
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789, 2, 2);
        roomList1.addRoom(room1);
        //Reading List
        ReadingList rL1 = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        r1 = new Reading(30, d2);
        rL1.addReading(r1);
        //device List
        DeviceList deviceL1 = new DeviceList();
        Device d1 = new Device(new FridgeSpec());
        d1.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        d1.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 56D);
        d1.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 345D);
        deviceL1.addDevice(d1);
        //Act ----------------------------
        deviceL1.removeDevice(d1);
        boolean result = deviceL1.containsDevice(d1);
        //Assert -------------------------
        assertFalse(result);
    }

    @Test
    void seeIfRemoveDeviceWorksForFalse() {
        //Arrange ------------------------
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789, 2, 2);
        roomList1.addRoom(room1);
        //Reading List
        ReadingList rL1 = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        r1 = new Reading(30, d2);
        rL1.addReading(r1);
        //device List
        DeviceList deviceL1 = new DeviceList();
        Device d1 = new Device(new FridgeSpec());
        d1.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        d1.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 56D);
        d1.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 345D);
        deviceL1.addDevice(d1);
        //Act ----------------------------
        boolean result = deviceL1.containsDevice(d1);
        //Assert -------------------------
        assertTrue(result);
    }

    @Test
    void seeIfCheckIfListIsValidWorks() {
        //Arrange ------------------------------
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789, 2, 2);
        roomList1.addRoom(room1);
        //Reading List
        ReadingList rL1 = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        r1 = new Reading(30, d2);
        rL1.addReading(r1);
        //device List
        DeviceList deviceL1 = new DeviceList();
        Device d1 = new Device(new FridgeSpec());
        d1.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        d1.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 56D);
        d1.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 345D);
        deviceL1.addDevice(d1);
        //Act ----------------------------------
        boolean result = deviceL1.checkIfListIsValid();
        //Assert -------------------------------
        assertTrue(result);
    }

    @Test
    void seeIfCheckIfListIsValidWorksForFalse() {
        //Arrange ------------------------------
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789, 2, 2);
        roomList1.addRoom(room1);
        //Reading List
        ReadingList rL1 = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        r1 = new Reading(30, d2);
        rL1.addReading(r1);
        //device List
        DeviceList deviceL1 = new DeviceList();
        //Act ----------------------------------
        boolean result = deviceL1.checkIfListIsValid();
        //Assert -------------------------------
        assertFalse(result);
    }

    @Test
    void SeeIfMatchDeviceIndexByStringWorks() {
        //Arrange
        Device device = new Device(new FridgeSpec());
        device.setName("fridge");
        device.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        device.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 7D);
        device.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 45D);
        DeviceList dlist = new DeviceList();
        dlist.addDevice(device);
        //Act
        List<Integer> result = dlist.matchDeviceIndexByString("fridge");
        List<Integer> expectedResult = Collections.singletonList(dlist.getList().indexOf(device));
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void SeeIfMatchDeviceIndexByStringFails() {
        //Arrange
        Device device = new Device(new FridgeSpec());
        device.setName("fridge");
        device.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        device.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 7D);
        device.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 45D);
        DeviceList dlist = new DeviceList();
        dlist.addDevice(device);
        //Act
        List<Integer> result = dlist.matchDeviceIndexByString("heater");
        List<Integer> expectedResult = new ArrayList<>();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void SeeIfPrintElementsByIndexWorks() {
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        Device d1 = new Device(new FridgeSpec());
        d1.setNominalPower(150.0);
        d1.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        d1.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 7D);
        d1.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 45D);
        Device d2 = new Device(new WashingMachineSpec());
        d2.setName("maquina de lavar");
        d2.setNominalPower(150.0);
        DeviceList dlist = new DeviceList();
        dlist.addDevice(d1);
        dlist.addDevice(d2);

        //Act
        String result = dlist.buildElementByIndexString(list);
        String expectedResult = "1) maquina de lavar, 150.0.\n";

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void hashCodeDummyTest() {
        //Arrange -------------------
        DeviceList deviceL1 = new DeviceList();
        //Act -----------------------
        int expectedResult = 1;
        int actualResult = deviceL1.hashCode();
        //Assert --------------------
        assertEquals(expectedResult, actualResult);
    }

    //This test can only work after Devices is re-worked.

    @Test
    void ensureThatAObjectIsAInstanceOf() {
        //Arrange
        DeviceList dList1 = new DeviceList();
        Device d1 = new Device(new FridgeSpec());
        d1.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        d1.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 56D);
        d1.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 345D);
        dList1.addDevice(d1);
        DeviceList dList2 = new DeviceList();
        Device device2 = new Device(new FridgeSpec());
        device2.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        device2.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 56D);
        device2.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 345D);
        dList2.addDevice(device2);
        //Act
        Boolean actualResult = dList1.equals(dList1);
        //Assert
        assertTrue(actualResult);
    }


    @Test
    void ensureThatAObjectIsAInstanceOf2() {
        //Arrange
        DeviceList dList1 = new DeviceList();
        Device device1 = new Device(new FridgeSpec());
        device1.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        device1.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 56D);
        device1.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 345D);
        dList1.addDevice(device1);
        DeviceList dList2 = new DeviceList();
        dList2.addDevice(device1);
        //Act
        boolean actualResult = dList1.equals(dList2);
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void ensureThatAObjectIsAInstanceOf3() {
        //Arrange
        DeviceList dList1 = new DeviceList();
        Device device1 = new Device(new FridgeSpec());
        device1.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        device1.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 56D);
        device1.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 345D);
        dList1.addDevice(device1);
        //Act
        boolean actualResult = dList1.equals(device1);
        //Assert
        assertFalse(actualResult);
    }

    @Test
    void ensureThatAObjectIsNotAInstanceOf() {
        //Arrange
        DeviceList dList1 = new DeviceList();
        DeviceList dList2 = new DeviceList();
        Device device1 = new Device(new FridgeSpec());
        device1.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        device1.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 56D);
        device1.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 345D);
        Device device2 = new Device(new FridgeSpec());
        device2.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        device2.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 56D);
        device2.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 345D);
        dList1.addDevice(device1);
        dList2.addDevice(device2);
        //Act
        boolean actualResult = dList1.equals(dList2);
        assertTrue(actualResult);
    }

    @Test
    void seeIfPrintDevicesWorks() {
        Device d1 = new Device(new FridgeSpec());
        d1.setName("Fridge");
        d1.setNominalPower(21.0);
        d1.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        d1.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 6D);
        d1.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 35D);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(d1);
        String expectedResult = "0) The device Name is Fridge, and its NominalPower is 21.0 kW.\n";

        String actualResult = deviceList.buildDevicesString();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintDevicesWorks2() {
        Device d1 = new Device(new FridgeSpec());
        d1.setName("Fridge");
        d1.setNominalPower(21.0);
        d1.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 6D);
        d1.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 5D);
        d1.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 345D);
        Device d2 = new Device(new FridgeSpec());
        d2.setName("Frnidge");
        d2.setNominalPower(21.0);
        d2.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        d2.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 7D);
        d2.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 435D);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(d2);
        deviceList.addDevice(d1);
        String expectedResult = "0) The device Name is Frnidge, and its NominalPower is 21.0 kW.\n" +
                "1) The device Name is Fridge, and its NominalPower is 21.0 kW.\n";

        String actualResult = deviceList.buildDevicesString();

        assertEquals(expectedResult, actualResult);
    }
}