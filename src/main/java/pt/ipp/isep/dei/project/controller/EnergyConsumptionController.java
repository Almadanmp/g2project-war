package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.devicetypes.DeviceType;

import java.util.List;

public class EnergyConsumptionController {


    /*
     * US705
     As a Power User, I want to know the total nominal power of a subset of rooms and/or devices of my choosing
     connected to a grid.
     */

    /**
     * Calls for the grid's method in the model to print its own rooms and devices.
     *
     * @param grid is the grid the user has already chosen.
     * @return builds a string from the Rooms and the Devices in that grid.
     */

    String printRoomsAndDevices(EnergyGrid grid) {
        return grid.printRoomsAndDevices();
    }

    /**
     * Calls for the room's method in the model to add all of its devices to a given deviceList.
     *
     * @param room       is the room where the devices we want to add are.
     * @param deviceList is the deviceList we want to add devices to.
     * @return true if devices were added.
     */

    public boolean addRoomDevicesToDeviceList(Room room, DeviceList deviceList) {
        return room.addRoomDevicesToDeviceList(deviceList);
    }

    /**
     * Calls for the roomList's method in the model to add a room to itself.
     *
     * @param room the room we want to add.
     * @param list the list we want to add the room to.
     * @return true if the room was added, false if it was already in the list.
     */

    public boolean addRoomToList(Room room, RoomList list) {
        return list.addRoom(room);
    }

    /**
     * Calls for the list's method in the model to add a device to itself.
     *
     * @param device the device we want to add to a list.
     * @param list   the list we want to add the device to.
     * @return true if the device was added, false if it was already in the list.
     */

    public boolean addDeviceToList(Device device, DeviceList list) {
        return list.addDevice(device);
    }

    /**
     * Calls for the grid's method in the model to calculate a total nominal power from a given list of devices.
     *
     * @param grid            is the grid we want to calculate power from.
     * @param selectedDevices is the subset of devices we want to include in the calculation.
     * @return is the total nominal power of given devices.
     */

    public double getSelectionNominalPower(EnergyGrid grid, DeviceList selectedDevices) {
        return grid.getSelectionNominalPower(selectedDevices);
    }

    /**
     * Calls for the room's method in the model to remove all of its devices from a given list.
     *
     * @param room       is the room that contains the devices we want to remove.
     * @param deviceList is the list we want to remove the devices from.
     * @return true if the devices were successfully removed, false if they were not on the list.
     */

    public boolean removeRoomDevicesFromDeviceList(Room room, DeviceList deviceList) {
        return room.removeRoomDevicesFromDeviceList(deviceList);
    }

    /**
     * Calls for the roomList's method in the model to remove a given room from itself.
     *
     * @param room     is the room we want to remove.
     * @param roomList is the list we want to remove it from.
     * @return true if the room was removed, false if it wasn't on the list.
     */

    public boolean removeRoomFromList(Room room, RoomList roomList) {
        return roomList.removeRoom(room);
    }

    /**
     * Calls for the deviceList's method in the model to remove a given device from itself.
     *
     * @param d          is the device we want to remove.
     * @param deviceList is the list we want to remove the device from.
     * @return true if the device was removed, false if it wasn't on the list.
     */

    public boolean removeDeviceFromList(Device d, DeviceList deviceList) {
        return deviceList.removeDevice(d);
    }

    /* US752
     * As a Regular User [or Power User], I want to estimate the total energy used in heating water in a given day,
     * given the cold-water temperature and the volume of water produced in each water heater.
     */

    public List <Device> getWaterHeaterDeviceList(House house){
        return house.getHouseDevicesOfGivenType(DeviceType.WATER_HEATER);
    }

    /**
     * Gets a list of all water heaters in the house and configures them with the user input values
     *
     * @param house                user house
     * @param coldWaterTemperature cold water temperature
     * @param volumeOfWaterToHeat  the volume of water to heat
     */
    public void configureHeaters(House house, double coldWaterTemperature, double volumeOfWaterToHeat) {
        List<Device> allWaterHeaters = house.getHouseDevicesOfGivenType(DeviceType.WATER_HEATER);
        for (Device d : allWaterHeaters) {
            d.setAttributeValue("coldWaterTemperature", coldWaterTemperature);
            d.setAttributeValue("volumeOfWater", volumeOfWaterToHeat);
    //TODO choose implementation (above or bellow) after discussing with teachers.
            //    WaterHeater dSpecs = (WaterHeater) d.getDeviceSpecs();
            //    dSpecs.setVolumeOfWater(volumeOfWaterToHeat);
            //    dSpecs.setColdWaterTemperature(coldWaterTemperature);
        }
    }

    //TODO choose implementation after discussing with teachers.
  /*  public void configureOneHeater(Device device, double coldWaterTemperature, double volumeOfWaterToHeat){
        device.setAttributeValue("coldWaterTemperature", coldWaterTemperature);
        device.setAttributeValue("volumeOfWater", volumeOfWaterToHeat);
    }*/

    /**
     * get the estimate consumption on all water heater available in the users house
     *
     * @param house user house
     * @return estimate energy consumption on the water heaters
     */
    public double getDailyHouseConsumptionWaterHeater(House house) {
        return house.getDailyHouseConsumptionPerType(DeviceType.WATER_HEATER);
    }

    public double getTotalPowerFromGrid(EnergyGrid grid) {
        return grid.getNominalPower();
    }
}
