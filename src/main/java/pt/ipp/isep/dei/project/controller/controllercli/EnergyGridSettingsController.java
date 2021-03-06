package pt.ipp.isep.dei.project.controller.controllercli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.model.bridgeservices.EnergyGridRoomService;
import pt.ipp.isep.dei.project.model.energy.EnergyGrid;
import pt.ipp.isep.dei.project.model.energy.EnergyGridRepository;
import pt.ipp.isep.dei.project.model.energy.PowerSource;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;

import java.util.List;

/**
 * Controller class for Energy Grid Settings UI
 */

@Service
public class EnergyGridSettingsController {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    EnergyGridRoomService energyGridRoomService;
    @Autowired
    EnergyGridRepository energyGridRepository;
    //SHARED METHODS THROUGH DIFFERENT UIS


    /**
     * @return builds a string of all the individual rooms contained in the list.
     */

    public String buildRoomsString(List<Room> roomsOnGrid) {
        return roomRepository.buildRoomsAsString(roomsOnGrid);
    }

    /**
     * @return builds a string of all the individual EnergyGrids contained in the house's EnergyGridList.
     */
    public static String buildGridListString(EnergyGridRepository energyGridRepository) {
        return energyGridRepository.buildString();
    }

    /*
     * USER STORY 130 - As an Administrator, I want to create a energy grid, so that I can define the rooms that are
     * attached to it and the contracted maximum power for that grid.
     */

    /**
     * This method directly adds the desired energy grid to the energy grid list from a selected house;
     *
     * @param energyGrid the grid to addWithoutPersisting to the House.
     * @return true if the grid was added to the house.
     */
    public EnergyGrid addEnergyGridToHouse(EnergyGrid energyGrid) {
        EnergyGrid result = energyGridRepository.addGrid(energyGrid);
        if (result == null) {
            throw new RuntimeException();
        } else return result;
    }

    /**
     * @param designation is the name we're going to give to the new EnergyGrid.
     * @param maxPower    is the new grid's maxPower.
     * @return a new EnergyGrid.
     */
    public EnergyGrid createEnergyGrid(String designation, double maxPower, String houseID) {
        return energyGridRepository.createEnergyGrid(designation, maxPower, houseID);
    }

    /* USER STORY 135 - As an Administrator, I want to addWithoutPersisting a power source to an energy grid, so that the produced
    energy may be used by all devices on that grid. */

    /**
     * @param name             is the name we're going to give to the new power source.
     * @param maxPowerOutput   is the new power source's maximum power output.
     * @param maxEnergyStorage is the new power source's maximum capacity.
     * @return a new power source.
     */

    public PowerSource createPowerSource(String name, double maxPowerOutput, double maxEnergyStorage) {
        return energyGridRepository.createPowerSource(name, maxPowerOutput, maxEnergyStorage);
    }

    /**
     * @param grid        is the grid we're going to addWithoutPersisting the new powerSource to.
     * @param powerSource the power source we are going to addWithoutPersisting.
     * @return is true if the power source is added successfully, false if it isn't.
     */

    public boolean addPowerSourceToGrid(EnergyGrid grid, PowerSource powerSource) {
        return grid.addPowerSource(powerSource);
    }
    /* USER STORY 147 -  As an Administrator, I want to attach a room to a house grid, so that the room’s power and
    energy consumption is included in that grid. MIGUEL ORTIGAO*/

    /**
     * Adds a room to an Energy grid
     *
     * @param grid    is the grid we're going to addWithoutPersisting a room to.
     * @param roomDTO is the room we're going to addWithoutPersisting to a grid.
     * @return is true if the room is added to the grid successfully, false if it isn't.
     */

    public boolean addRoomDTOToGrid(EnergyGrid grid, RoomDTO roomDTO) {
        try {
            Room room = roomRepository.updateHouseRoom(roomDTO);
            return energyGridRoomService.addRoom(grid,room);
        } catch (RuntimeException ok) {
            throw new RuntimeException();
        }
    }

    /*USER STORY 149 -  an Administrator, I want to detach a room from a house grid, so that the room’s power  and
    energy  consumption  is  not  included  in  that  grid.  The  room’s characteristics are not changed. */

    /**
     * @param grid is the grid we're going to removeGeographicArea a room from.
     * @param room is the room we're going to removeGeographicArea from the grid.
     * @return is true if the room is removed from the grid successfully, false if it isn't.
     */

    public boolean removeRoomFromGrid(EnergyGrid grid, Room room) {
        return energyGridRoomService.removeRoom(grid, room);
    }

    /*USER STORY 160 - As a Power User (or Administrator),
    I want to get a list of all devices in a grid, grouped by device type.  It must include device location
    DANIEL OLIVEIRA*/

    /**
     * This method validates the list of rooms and the list of devices in all rooms.
     * If all the attributes are valid, this method will print the devices, according to their type
     *
     * @param energyGrid - This will be the parameter in which we want to search all the devices
     * @return a string with the devices ordered by their type.
     */

    public String buildListOfDevicesOrderedByTypeString(EnergyGrid energyGrid) {
        return energyGridRoomService.buildDeviceListWithTypeString(energyGrid);
    }
}