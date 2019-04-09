package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.EnergyGrid;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.repository.EnergyGridRepository;
import pt.ipp.isep.dei.project.repository.HouseRepository;
import pt.ipp.isep.dei.project.repository.RoomRepository;

import java.util.Optional;

@Service
public class HouseService {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private EnergyGridRepository energyGridRepository;

    @Autowired
    private RoomRepository roomRepository;

    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }


    public boolean saveHouse(House house) {
        House house1 = houseRepository.findByAddress(house.getAddress());
        if (house1 != null) {
            houseRepository.delete(house1);
        }
        houseRepository.save(house);
        return true;
    }

    public boolean saveEnergyGrid(EnergyGrid energyGrid) {
        EnergyGrid energyGrid1 = energyGridRepository.findByName(energyGrid.getName());
        if (energyGrid1 != null) {
            return false;
        }
        energyGridRepository.save(energyGrid);
        return true;
    }

    public boolean saveRoom(Room room) {
        Optional<Room> room1 = roomRepository.findById(room.getName());
        if (room1.isPresent()) {
            return false;
        }
        roomRepository.save(room);
        return true;
    }

}

