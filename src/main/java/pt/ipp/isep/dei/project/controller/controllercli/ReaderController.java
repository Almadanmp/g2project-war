package pt.ipp.isep.dei.project.controller.controllercli;

import org.springframework.stereotype.Service;
import org.w3c.dom.NodeList;
import pt.ipp.isep.dei.project.dto.EnergyGridDTO;
import pt.ipp.isep.dei.project.dto.HouseDTO;
import pt.ipp.isep.dei.project.dto.ReadingDTO;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.mappers.AddressMapper;
import pt.ipp.isep.dei.project.dto.mappers.EnergyGridMapper;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.io.ui.reader.ReaderJSONHouse;
import pt.ipp.isep.dei.project.io.ui.reader.ReaderXMLGeoArea;
import pt.ipp.isep.dei.project.model.areatype.AreaTypeRepository;
import pt.ipp.isep.dei.project.model.energy.EnergyGrid;
import pt.ipp.isep.dei.project.model.energy.EnergyGridRepository;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;
import pt.ipp.isep.dei.project.repository.HouseCrudRepo;

import java.util.List;

@Service
public class ReaderController {

    //
    // USER STORY 15v2 - As an Administrator, I want to import geographical areas and sensors from a JSON or XML file.

    /**
     * This method reads a JSON file that represents the class House() and sets House attributes(US100 Attributes)
     * from the file and saves it into the repository.
     *
     * @param filePath is the file path.
     * @param house    is the House that this method receives from the MainUI(), with houseRepository,
     *                 gridMeteringPeriod, deviceMeteringPeriod and deviceTypeConfig.
     * @return true if the House was successfully saved in the repository, false otherwise.
     */
    public boolean readJSONAndDefineHouse(House house, String filePath, EnergyGridRepository energyGridRepository, HouseCrudRepo houseCrudRepo, RoomRepository roomRepository) {
        ReaderJSONHouse readerJSONHouse = new ReaderJSONHouse();
        //House
        HouseDTO houseDTO;
        try {
            houseDTO = readerJSONHouse.readFile(filePath);
            Address address = AddressMapper.dtoToObject(houseDTO.getAddress());
            house.setAddress(address);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException();
        }

        //EnergyGrid

        List<EnergyGridDTO> gridDTOS = readerJSONHouse.readGridsJSON();
        for (EnergyGridDTO eg : gridDTOS) {
            EnergyGrid energyGrid = EnergyGridMapper.dtoToObjectWithNameRoomsAndPowerSources(eg);
            energyGrid.setHouseId(house.getId());
            energyGridRepository.addGrid(energyGrid);
        }

        //ROOMS
        for (EnergyGridDTO eg : gridDTOS) {
            List<RoomDTO> roomDTOS = eg.getRoomDTOS();
            for (RoomDTO rt : roomDTOS) {
                rt.setHouseId(house.getId());
                Room aux = RoomMapper.dtoToObjectWithoutSensorsAndDevices(rt);
                roomRepository.saveRoom(aux);
            }
        }
        houseCrudRepo.save(house);
        return true;
    }

    /**
     * This method receives a list of Geographic Areas to add the given NodeList correspondent to the Geographic Areas
     * imported from the XML File.
     *
     * @param nListGeoArea             - NodeList imported from the XML.
     * @param geographicAreaRepository - list to which we want to add and persist the Geographic areas.
     * @return - the number of geographic areas imported.
     */
    public int addGeoAreaNodeListToList(NodeList nListGeoArea, GeographicAreaRepository geographicAreaRepository, AreaTypeRepository areaTypeRepository) {
        ReaderXMLGeoArea xmlGeoArea = new ReaderXMLGeoArea();
        int result = 0;
        for (int i = 0; i < nListGeoArea.getLength(); i++) {
            if (xmlGeoArea.readGeographicAreasXML(nListGeoArea.item(i), geographicAreaRepository, areaTypeRepository)) {
                result++;
            }
        }
        return result;
    }


    /**
     * This method will receive a list of reading DTOs, a string of a path to a log file,
     * and a geographic area service and will try to add readings to the given sensors
     * in the given geographic area from the repository.
     *
     * @param readingDTOS              a list of reading DTOs
     * @param logPath                  M  string of a log file path
     * @param geographicAreaRepository service
     * @return the number of readings added
     **/
    public int addReadingsToGeographicAreaSensors(List<ReadingDTO> readingDTOS, String logPath, GeographicAreaRepository geographicAreaRepository) {
        return geographicAreaRepository.addReadingsToGeographicAreaSensors(readingDTOS, logPath);
    }

    /**
     * This method will receive a list of reading DTOs, a string of a path to a log file,
     * and a room service and will try to add readings to the given sensors
     * in the given room from the repository.
     *
     * @param readingDTOS    a list of reading DTOs
     * @param logPath        M  string of a log file path
     * @param roomRepository service
     * @return the number of readings added
     **/
    public int addReadingsToRoomSensors(List<ReadingDTO> readingDTOS, String logPath, RoomRepository roomRepository) {
        return roomRepository.addReadingsToRoomSensors(readingDTOS, logPath);
    }
}