package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.ReaderController;
import pt.ipp.isep.dei.project.controller.HouseConfigurationController;
import pt.ipp.isep.dei.project.io.ui.utils.InputUtils;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.GeographicAreaList;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;


import java.util.Scanner;

class HouseConfigurationUI {
    private HouseConfigurationController controller;
    private String roomName;
    private int roomHouseFloor;
    private double roomWidth;
    private double roomLength;
    private double roomHeight;
    private static final String INVALID_OPTION = "Please enter a valid option";
    private static final String VALID_LOG_PATH = "resources/logs/logOut.log";

    HouseConfigurationUI() {
        this.controller = new HouseConfigurationController();
    }

    void run(House house, GeographicAreaList list) {
        boolean activeInput = true;
        int option;
        System.out.println("--------------\n");
        System.out.println("House Configuration\n");
        System.out.println("--------------\n");
        while (activeInput) {
            printHouseConfigMenu();
            option = InputUtils.getInputAsInt();
            switch (option) {
                case 1:
                    runUS15(list);
                    activeInput = false;
                    break;
                case 2:
                    runUS20v2(list);
                    activeInput = false;
                    break;
                case 3:
                    runUS101(house, list);
                    activeInput = false;
                    break;
                case 4:
                    runUS105(house);
                    activeInput = false;
                    break;
                case 5:
                    runUS108(house);
                    activeInput = false;
                    break;
                case 0:
                    return;
                default:
                    System.out.println(INVALID_OPTION);
                    break;
            }
        }
    }
    // USER STORY 15v.2 - As an Administrator, I want to import Geographic Areas and Sensors from a JSON file and a XML file.

    /**
     * As an Administrator, I want to import Geographic Areas and Sensors from a JSON or XML file.
     *
     * @param list is the static, program list of geographic areas that comes from mainUI.
     */

    private void runUS15(GeographicAreaList list) {
        InputUtils input = new InputUtils();
        System.out.println("Please insert the location of the file you want to import:");
        Scanner scanner = new Scanner(System.in);
        String result = scanner.next();
        String filePath = input.getInputPath(result);
        if (!input.readJsonOrXMLFile(result,filePath,list)){
            System.out.println("The file isn't a JSON nor a XML file.");
        }
    }

    /* USER STORY 20v2 - As an Administrator I want to import geographic area sensor readings into the application
     from a either a CSV, JSON or XML file.
     Data outside the valid sensor operation period should not be imported but registered in the
     application log. */

    /**
     * As an Administrator, I want to import geographic area sensor readings into the application
     * from a a CSV, JSON and XML file and display a message to the user of how many readings were
     * successfully imported.
     *
     * @param geographicAreaList is the static, program list of geographic areas that comes from mainUI.
     */
    private void runUS20v2(GeographicAreaList geographicAreaList) {
        InputUtils inputUtils = new InputUtils();
        String path = inputUtils.getInputFileLocation();
        if (path.endsWith(".csv")) {
            readReadingsFromCSV(geographicAreaList, path, VALID_LOG_PATH);
        } else if (path.endsWith(".json")) {
            readReadingsFromJSON(geographicAreaList, path, VALID_LOG_PATH);
        } else if (path.endsWith(".xml")) {
            readReadingsFromXML(geographicAreaList, path, VALID_LOG_PATH);
        }
    }

    private void readReadingsFromCSV(GeographicAreaList geographicAreaList, String filePath, String logFilePath) {
        int result = 0;
        ReaderController ctrl = new ReaderController();
        try {
            result = ctrl.readReadingsFromCSV(geographicAreaList, filePath, logFilePath);
        } catch (IllegalArgumentException illegal) {
            System.out.println("The CSV file is invalid.");
        }
        System.out.println(result + " reading(s) successfully imported.");
    }

    private void readReadingsFromJSON(GeographicAreaList geographicAreaList, String filePath, String logFilePath) {
        int result = 0;
        ReaderController ctrl = new ReaderController();
        try {
            result = ctrl.readReadingsFromJSON(geographicAreaList, filePath, logFilePath);
        } catch (IllegalArgumentException illegal) {
            System.out.println("The JSON file is invalid.");
        }
        System.out.println(result + " reading(s) successfully imported.");
    }

    private void readReadingsFromXML(GeographicAreaList geographicAreaList, String filePath, String logFilePath) {
        int result = 0;
        ReaderController ctrl = new ReaderController();
        try {
            result = ctrl.readReadingsFromXML(geographicAreaList, filePath, logFilePath);
        } catch (IllegalArgumentException illegal) {
            System.out.println("The XML file is invalid.");
        }
        System.out.println(result + " reading(s) successfully imported.");
    }

    /* USER STORY 101 - As an Administrator, I want to configure the location of the house - MARIA MEIRELES */

    private void runUS101(House house, GeographicAreaList geographicAreaList) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("First select the geographic area where this house is located.");
        GeographicArea motherArea = InputUtils.getGeographicAreaByList(geographicAreaList);

        // get house address
        System.out.print("Please, type the street where the house is located: ");
        String street = scanner.nextLine();

        // get zip code
        System.out.print("Please, type the address's zip code: ");
        String zip = scanner.nextLine();

        // get town
        System.out.println("Please, type the town where the house is located: ");
        String town = scanner.nextLine();

        //get latitude
        System.out.print("Please, type the latitude: ");
        double houseLat = InputUtils.getInputAsDouble();

        // get longitude
        System.out.print("Please, type the longitude: ");
        double houseLon = InputUtils.getInputAsDouble();

        // get longitude
        System.out.print("Please, type the altitude: ");
        double houseAlt = InputUtils.getInputAsDouble();

        controller.setHouseLocal(houseLat, houseLon, houseAlt, house);
        controller.setHouseAddress(street, zip, town, house);
        controller.setHouseMotherArea(house, motherArea);

        String houseId = controller.getHouseName(house);
        System.out.println("\nYou have successfully configured the location of the house " + houseId + ". \n" + "Street: " +
                street + ". \n" + "ZipCode: " + zip + ". \n" + "Town: " + town + ". \n" + "Latitude: " + houseLat + ". \n" +
                "Longitude: " + houseLon + ". \n" + "Altitude: " + houseAlt + ". \n");
    }


    // USER STORY 105 - As an Administrator, I want to add a new room to the house, in order to configure it (name,
    // house floor and dimensions) - TERESA VARELA.
    private void runUS105(House house) {
        getInputRoomCharacteristics();
        Room room = createNewRoom(house);
        displayRoom();
        boolean added = addRoomToHouse(house, room);
        displayFinalState(added);
    }


    /**
     * Method gets input from user to save as the characteristics of a room.
     */
    private void getInputRoomCharacteristics() {
        Scanner scanner = new Scanner(System.in);

        //GET ROOM DESIGNATION
        System.out.println("Please insert the room's name: ");
        this.roomName = scanner.nextLine();

        //GET ROOM HOUSE FLOOR
        System.out.println("Please insert your room's house floor: ");
        this.roomHouseFloor = InputUtils.getInputAsInt();

        //GET ROOM DIMENSIONS
        System.out.println("Please insert your room's width in meters: ");
        this.roomWidth = InputUtils.getInputAsDoublePositive();

        System.out.println("Please insert your room's length in meters: ");
        this.roomLength = InputUtils.getInputAsDoublePositive();

        System.out.println("Please insert your room's height in meters: ");
        this.roomHeight = InputUtils.getInputAsDoublePositive();
    }

    private Room createNewRoom(House house) {
        return controller.createNewRoom(house, roomName, roomHouseFloor, roomWidth, roomLength, roomHeight);
    }

    /**
     * Method displays the input room and its characteristics.
     */

    private void displayRoom() {
        String yourNewRoom = "The room is called ";
        String located = ", located on the ";
        String width = " meters of width, ";
        String length = " meters of length and ";
        String height = " meters of height.";
        //SHOW ROOM ENTERED BY USER
        if (roomHouseFloor == 1) {
            System.out.println(yourNewRoom + roomName + located + roomHouseFloor + "st floor and has " + roomWidth + width + roomLength + length + roomHeight + height);
        } else if (roomHouseFloor == 2) {
            System.out.println(yourNewRoom + roomName + located + roomHouseFloor + "nd floor and has " + roomWidth + width + roomLength + length + roomHeight + height);
        } else if (roomHouseFloor == 3) {
            System.out.println(yourNewRoom + roomName + located + roomHouseFloor + "rd floor and has " + roomWidth + width + roomLength + length + roomHeight + height);
        } else {
            System.out.println(yourNewRoom + roomName + located + roomHouseFloor + "th floor and has " + roomWidth + width + roomLength + length + roomHeight + height);
        }
    }

    private boolean addRoomToHouse(House house, Room room) {
        return controller.addRoomToHouse(house, room);
    }

    private void displayFinalState(boolean addedRoom) {
        if (addedRoom) {
            System.out.println("The " + roomName + " was added to the house.\n");
        } else {
            System.out.println("The " + roomName + " wasn't added to the house because it already exists.\n");
        }
    }


    /* USER STORY 108 - As an Administrator, I want to have a list of existing rooms, so that I can choose one to edit it.
     * - MARIA MEIRELES, TERESA VARELA */
    private void runUS108(House house) {
        if (!house.isRoomListEmpty()) {
            printRoomList(house);
        } else {
            System.out.println(UtilsUI.INVALID_ROOM_LIST);
        }
    }

    private void printRoomList(House house) {
        System.out.println(controller.buildRoomsString(house));
    }


    /* UI SPECIFIC METHODS - NOT USED ON USER STORIES */
    private void printHouseConfigMenu() {
        System.out.println("House Controller Options:\n");
        System.out.println("1) Import Geographic Areas and Sensors from a JSON or XML file.");
        System.out.println("2) Import Geographic Area Sensor Readings from a file - json, xml, csv. (US20v2)");
        System.out.println("3) Configure the location of the house. (US101)");
        System.out.println("4) Add a new room to the house. (US105)");
        System.out.println("5) List the existing rooms. (US108)");
        System.out.println("0) (Return to main menu)\n");
    }
}


