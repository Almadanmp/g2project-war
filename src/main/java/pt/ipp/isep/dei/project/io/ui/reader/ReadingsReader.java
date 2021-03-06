package pt.ipp.isep.dei.project.io.ui.reader;


import pt.ipp.isep.dei.project.dto.ReadingDTO;

import java.util.List;

public interface ReadingsReader {
    /**
     * This method receives a file path that contains information to create readings
     * and returns a mapper with String containing the sensor Id and the corresponding Reading DTO.
     *
     * @param filePath the file location
     * @return mapper with ReadingDTO and corresponding sensor Id
     **/
    List<ReadingDTO> readFile(String filePath);
}
