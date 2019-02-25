package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Class that represents an Energy Grid present in a House.
 */

public class EnergyGrid implements Metered {
    private String name;
    private RoomList roomList;
    private PowerSourceList listPowerSources;
    private double maxContractedPower;

    public EnergyGrid(String name, double maxContractedPower) {
        this.roomList = new RoomList();
        this.listPowerSources = new PowerSourceList();
        this.name = name;
        this.maxContractedPower = maxContractedPower;
    }

    /**
     * Getter for name of the energy grid.
     *
     * @return returns attribute name of the energy grid.
     */

    public String getName() {
        return name;
    }

    /**
     * Getter for list of rooms that are contained in the energy grid.
     *
     * @return returns a list with all the rooms contained in this energy grid.
     */

    public RoomList getRoomList() {
        return roomList;
    }

    public PowerSourceList getListOfPowerSources() {
        return listPowerSources;
    }

    double getMaxContractedPower() {
        return maxContractedPower;
    }

    /**
     * Method accesses the sum of nominal powers of all rooms and devices connected to a grid..
     *
     * @return is the sum of nominal powers of all rooms and devices connected to a grid.
     */

    public double getNominalPower() {
        double result = 0;
        for (Room r : roomList.getList()) {
            result += r.getNominalPower();
        }
        return result;
    }

    /**
     * Getter for the list of power sources connected to the energy grid.
     *
     * @return the list of power sources connected to the energy grid.
     */
    PowerSourceList getListPowerSources() {
        return listPowerSources;
    }

    boolean setMaxContractedPower(double power) {
        if (power < 0) {
            return false;
        }
        this.maxContractedPower = power;
        return true;
    }

    /**
     * Setter for the list of rooms connected the energy grid.
     *
     * @param mListOfRooms List of rooms in the energy grid.
     */
    public void setRoomList(RoomList mListOfRooms) {
        this.roomList = mListOfRooms;
    }

    /**
     * Setter for a power source to the energy grid.
     *
     * @param powerSource power source to be added to the energy grid.
     * @return returns true case the power source to be added is already associated to the grid.
     */
    public boolean addPowerSource(PowerSource powerSource) {
        if (!(listPowerSources.contains(powerSource))) {
            return listPowerSources.add(powerSource);
        }
        return false;
    }

    /**
     * Setter for a new room to be added to the energy grid.
     *
     * @param room Room to be added to the list of rooms in the energy grid.
     * @return returns true if the room is actually added to the energy grid.
     */
    public boolean addRoom(Room room) {
        return this.roomList.addRoom(room);
    }

    /**
     * Creates a string displaying the name of the grid and respective max nominal power.
     *
     * @return returns a string displaying the name of the grid and respective max nominal power.
     */
    public String buildGridString() {
        return "Energy Grid: " + this.name + ", Max Power: " + this.getMaxContractedPower();
    }

    /**
     * Getter for the list of rooms related to the energy grid as an array of rooms.
     *
     * @return a list of rooms related to the energy grid.
     */
    private List<Room> getListOfRooms() {
        return this.roomList.getList();
    }

    /**
     * This method receives an index as parameter and gets a room from energy grid's room list.
     *
     * @return returns room that corresponds to index.
     */
    public Room getRoom(int index) {
        return this.roomList.get(index);
    }


    /**
     * Method gets all devices associated to energy grid
     *
     * @return energy grid's entire DeviceList
     */
    public DeviceList getDeviceList() {
        return this.roomList.getDeviceList();
    }

    /**
     * Method goes through all rooms associated to energy grid and returns
     * the number of devices in each room
     *
     * @return energy grid's associated devices as int
     */
    public int getNumberOfDevices() {
        return this.roomList.getNumberOfDevices();
    }

    /**
     * This method receives an index as parameter and gets a device from energy grid's device list.
     *
     * @return returns device that corresponds to index.
     */
    public Device getDeviceByIndex(int index) {
        DeviceList deviceList = this.getDeviceList();
        return deviceList.get(index);
    }

    /**
     * Method to remove a room from the energy grid.
     *
     * @param room the room we want to remove from the energy grid.
     * @return returns true if the room is successfully removed from the energy grid.
     */
    public boolean removeRoom(Room room) {
        if (this.roomList.contains(room)) {
            this.roomList.removeRoom(room);
            return true;
        }
        return false;
    }

    /**
     * Creates a string displaying the name of the devices in the energy grid.
     *
     * @return returns a string displaying the name of the devices in the energy grid.
     */
    public String buildDeviceListString() {
        return this.getDeviceList().buildDevicesString();
    }

    /**
     * Creates a string displaying the rooms in the energy grid and the room's attributes.
     *
     * @return returns a string displaying the rooms in the energy grid and the room's attributes.
     */
    public String buildRoomListString() {
        return this.roomList.buildRoomsString();
    }


    /**
     * Creates a String with the device index, device type, device name and the room in which the device is contained.
     * @param house the house that contains the devices.
     * @param energyGrid energy grid that we want to see which devices will be printed.
     * @return a String with the device index, device type, device name and the room in which the device is contained.
     */
    public String buildDeviceListWithTypeString(EnergyGrid energyGrid, House house) {
        String stringSpacer = "---------------\n";
        StringBuilder result = new StringBuilder(stringSpacer);
        for (DeviceType d : house.getDeviceTypeList()) {
            for (int i = 0; i < energyGrid.getRoomList().size(); i++) {
                Room r = energyGrid.getRoomList().get(i);
                if (r != null) {
                    result.append(buildDevicesStringByType(r, d.getDeviceType()));
                }
            }
        }
        result.append(stringSpacer);
        return result.toString();
    }

    /**
     * Creates a string that displays all devices of a given type in a given room.
     *
     * @param room room where we want to see the devices.
     * @param type type of the devices.
     * @return a string that displays the device type and in which room it is contained.
     */
    String buildDevicesStringByType(Room room, String type) {
        StringBuilder result = new StringBuilder();
        for (int x = 0; x < room.getDeviceListSize(); x++) {
            if (type.equals(room.getDeviceList().get(x).getType())) {
                Device device = room.getDeviceList().get(x);
                result.append("Device type: ").append(type).append(" | ");
                result.append("Device name: ").append(device.getName()).append(" | ");
                result.append("Nominal power: ").append(device.getNominalPower()).append(" | ");
                result.append("Room: ").append(room.getRoomName()).append(" | \n");
            }
        }
        return result.toString();
    }

    /**
     * US722 As a Power User [or Administrator], I want the sum of the energy consumption of all energy-metered rooms
     * in the grid in the interval.
     *
     * @param initialDate for metering period.
     * @param finalDate   for metering period.
     * @return total metered energy consumption of a grid in a given time interval.
     */
    public double getGridConsumptionInInterval(Date initialDate, Date finalDate) {
        double gridConsumption = 0;
        for (Room r : this.getListOfRooms()) {
            gridConsumption += r.getConsumptionInInterval(initialDate, finalDate);
        }
        return gridConsumption;
    }

    public LogList getLogsInInterval(Date startDate, Date endDate) {
        LogList result = new LogList();
        for (Room r : this.getListOfRooms()) {
            LogList tempList = r.getLogsInInterval(startDate, endDate);
            result.addLogList(tempList);
        }
        return result;
    }

    /**
     * This method checks if energy grid's Device List is empty.
     *
     * @return true if energy grid's DeviceList is empty, false otherwise.
     **/
    public boolean isDeviceListEmpty() {
        return roomList.isDeviceListEmpty();
    }

    /**
     * This method checks if energy grid's Room List is empty.
     *
     * @return true if energy grid's RoomList is empty, false otherwise.
     **/
    public boolean isRoomListEmpty() {
        return roomList.isEmpty();
    }

    /**
     * This method checks the Room List's size.
     *
     * @return energy grid's RoomList size as int
     **/
    public int roomListSize() {
        return roomList.size();
    }

    @Override
    public double getEnergyConsumption(float time) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EnergyGrid eg = (EnergyGrid) o;
        return Objects.equals(name, eg.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
