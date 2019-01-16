package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.RoomConfigurationController;
import pt.ipp.isep.dei.project.controller.SensorSettingsController;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.devicetypes.*;
import pt.ipp.isep.dei.project.model.devicetypes.DeviceType;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

class RoomConfigurationUI {

    private House mHouse;
    private RoomConfigurationController mRoomConfigurationController;
    private Room mRoom;
    private Device mDevice;
    private DeviceType mDeviceType;
    private double mNominalPower;
    private double mVolumeOfWater;
    private double mHotWaterTemperature;
    private double mPerformanceRatio;
    private double mFreezerCapacity;
    private double mRefrigeratorCapacity;
    private double mCapacity;
    private String mDeviceName;
    private String mRoomName;
    private String mSensorName;
    private TypeSensor mTypeSensor;
    private int mDataYear;
    private int mDataMonth;
    private int mDataDay;

    RoomConfigurationUI() {
        this.mRoomConfigurationController = new RoomConfigurationController();
    }

    void run(House house, List<DeviceType> deviceTypeList, List<TypeSensor> typeSensorList) {
        if (house == null) {
            System.out.println("Please create a House before you continue.");
            return;
        }
        this.mHouse = house;
        List<Room> roomList = mHouse.getRoomList();
        if (roomList == null || roomList.isEmpty()) {
            System.out.println("There are no available rooms in the house. Please add a room to continue.");
            return;
        }
        UtilsUI utils = new UtilsUI();
        InputUtils inputUtils = new InputUtils();
        boolean activeInput = true;
        int option;

        System.out.println("--------------\n");
        System.out.println("Room Configuration\n");
        System.out.println("--------------\n");
        while (activeInput) {
            printRoomConfigMenu();
            option = inputUtils.readInputNumberAsInt();
            switch (option) {
                case 1: //US201
                    this.mRoom = inputUtils.getHouseRoomByList(this.mHouse);
                    printRoomDeviceList();
                    activeInput = false;
                    break;
                case 2: //US210
                    this.mRoom = inputUtils.getHouseRoomByList(this.mHouse);
                    if (getInputDeviceTypeByList(deviceTypeList)) {
                        return;
                    }
                    createDevice();
                    displayDeviceUS210();
                    activeInput = false;
                    break;
                case 3: //215
                    this.mRoom = inputUtils.getHouseRoomByList(this.mHouse);
                    this.mDevice = inputUtils.getInputDeviceByList(this.mRoom);
                    getInputDeviceCharacteristicsUS215();
                    updateDeviceUS215();
                    displayDeviceUS215();
                    activeInput = false;
                    break;
                case 4: //US230
                    this.mRoom = inputUtils.getHouseRoomByList(this.mHouse);
                    getRoomNominalPower();
                    activeInput = false;
                    break;
                case 5: //US250
                    this.mRoom = inputUtils.getHouseRoomByList(this.mHouse);
                    displaySensorListUS250();
                    activeInput = false;
                    break;
                case 6: //US253
                    if (typeSensorList.isEmpty()) {
                        System.out.println("There's no defined types of sensor available yet. Please define one first.");
                        return;
                    }
                    this.mRoom = inputUtils.getHouseRoomByList(this.mHouse);
                    if (getInputTypeFromTypeListByList(typeSensorList)) {
                        return;
                    }
                    getInput253();
                    updateAndDisplay253();
                    activeInput = false;
                    break;
                case 0:
                    return;
                default:
                    System.out.println(utils.invalidOption);
                    break;
            }
        }
    }


    /**
     * US201 As an administrator, I want to get a list of all devices in a room, so that I can configure them.
     * <p>
     * Prints Device List in that room.
     */
    private void printRoomDeviceList() {
        System.out.println("Available Devices in Room " + mRoomName);
        System.out.println(mRoomConfigurationController.printDeviceList(mRoom));
    }

     /* USER STORY 210 - As an Administrator, I want to add a new device to a room from the list of available
    device types, so that I can configure it. - MARIA MEIRELES */

    /**
     * @param deviceTypeList is a list of device types
     * @return prints a list of available device types by index if the list of device types is not empty
     */
    private boolean getInputDeviceTypeByList(List<DeviceType> deviceTypeList) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        if (deviceTypeList.isEmpty()) {
            System.out.println("Device types list is empty.");
            return true;
        }
        System.out.println("Please select one of the Device Types: ");
        System.out.println(mRoomConfigurationController.printDeviceTypeList(deviceTypeList));
        int aux = inputUtils.readInputNumberAsInt();
        if (aux >= 0 && aux < DeviceType.values().length) {
            this.mDeviceType = DeviceType.values()[aux];
            return false;
        } else {
            System.out.println(utils.invalidOption);
            return true;
        }
    }

    /**
     * Asks for input from the user in order to construct a new device with its parameters
     * (name, nominal power and device specs (according to the selected device Type)
     */

    private void createDevice() {
        this.mDevice = new Device();
        Scanner scanner = new Scanner(System.in);
        String onlyNumbers = "Please,try again. Only numbers this time:";

        // get device name
        System.out.print("Please, type the new name of the Device: ");
        mDeviceName = scanner.nextLine();

        //get nominal power
        System.out.print("Please, type the new Nominal Power: ");
        while (!scanner.hasNextDouble()) {
            System.out.println(onlyNumbers);
            scanner.next();
        }
        this.mNominalPower = scanner.nextDouble();

        //Device Type
        if (this.mDeviceType == DeviceType.WATER_HEATER) {
            System.out.print("Please, type the new Water Volume that the Water Heater will heat: ");
            while (!scanner.hasNextDouble()) {
                System.out.println(onlyNumbers);
                scanner.next();
            }
            this.mVolumeOfWater = scanner.nextDouble();

            System.out.print("Please, type the Maximum Temperature of the water in the Water Heater: ");
            while (!scanner.hasNextDouble()) {
                System.out.println(onlyNumbers);
                scanner.next();
            }
            this.mHotWaterTemperature = scanner.nextDouble();
            System.out.print("Please, type the Performance Ration of the Water Heater: ");
            while (!scanner.hasNextDouble()) {
                System.out.println(onlyNumbers);
                scanner.next();
            }
            this.mPerformanceRatio = scanner.nextDouble();
            mDevice = new Device(mDeviceName, mNominalPower, new WaterHeater(mVolumeOfWater, mHotWaterTemperature, mPerformanceRatio));
        }
        if (this.mDeviceType == DeviceType.FRIDGE) {
            System.out.print("Please, type the Freezer Capacity in L for the Fridge:");
            while (!scanner.hasNextDouble()) {
                System.out.println(onlyNumbers);
                scanner.next();
            }
            this.mFreezerCapacity = scanner.nextDouble();
            System.out.print("Please, type the Refrigerator Capacity in L for the Fridge:");
            while (!scanner.hasNextDouble()) {
                System.out.println(onlyNumbers);
                scanner.next();
            }
            this.mRefrigeratorCapacity = scanner.nextDouble();
            mDevice = new Device(mDeviceName, mNominalPower, new Fridge(mFreezerCapacity, mRefrigeratorCapacity));
        }
        if (this.mDeviceType == DeviceType.WASHING_MACHINE) {
            System.out.print("Please, type the Capacity in Kg for the Washing Machine: ");
            while (!scanner.hasNextDouble()) {
                System.out.println(onlyNumbers);
                scanner.next();
            }
            this.mCapacity = scanner.nextDouble();
            mDevice = new Device(mDeviceName, mNominalPower, new WashingMachine(mCapacity));
        }
        if (this.mDeviceType == DeviceType.DISHWASHER) {
            System.out.print("Please, type the Capacity in Kg for the Dishwasher:");
            while (!scanner.hasNextDouble()) {
                System.out.println(onlyNumbers);
                scanner.next();
            }
            this.mCapacity = scanner.nextDouble();
            mDevice = new Device(mDeviceName, mNominalPower, new Dishwasher(mCapacity));
        }
    }


    /**
     * Displays a string with the new created device and its parameters.
     * Adds the new created device to the selected room
     */

    private void displayDeviceUS210() {
        System.out.println("You have successfully created a " + mDeviceType.printDeviceType(mDeviceType) + " with the name " + mDeviceName + ". \n"
                + "The Nominal Power is: " + mNominalPower + " kW. \n" + "And the room is " + mRoom.getRoomName() + ".");
        if (mDevice.getDeviceType() == DeviceType.WATER_HEATER) {
            System.out.println("The volume of water is " + mVolumeOfWater + " L, the Max Water Temperature " +
                    mHotWaterTemperature + " ºC, and the Performance Ratio is: "
                    + mPerformanceRatio + ".");
        }
        if (mDevice.getDeviceType() == DeviceType.WASHING_MACHINE || mDevice.getDeviceType() == DeviceType.DISHWASHER) {
            System.out.println("The capacity is " + mCapacity + " Kg.");
        }
        if (mDevice.getDeviceType() == DeviceType.FRIDGE) {
            System.out.println("The freezer Capacity is  " + mFreezerCapacity + " L and the Refrigerator Capacity is " + mRefrigeratorCapacity + " L.");
        }
        mDevice.setmParentRoom(mRoom);
        mRoom.addDevice(mDevice);
    }

    /* USER STORY 215 - As an Administrator, I want to edit the configuration of an existing device,
    so that I can reconfigure it.. - CARINA ALAS */

    public void getInputDeviceCharacteristicsUS215() {

        Scanner scanner = new Scanner(System.in);

        // get device name
        System.out.print("Please, type the new name of the Device: ");
        this.mDeviceName = scanner.nextLine();

        //get room
        mRoomConfigurationController.removeDeviceFromRoom(mRoom, mDevice);
        InputUtils inputUtils = new InputUtils();
        this.mRoom = inputUtils.getHouseRoomByList(this.mHouse);
        mDevice.setmParentRoom(mRoom);
        this.mRoom = inputUtils.getHouseRoomByList(mHouse);
        mRoomConfigurationController.addDeviceToRoom(mRoom, mDevice);
        //get nominal power
        String onlyNumbers = "Please,try again. Only numbers this time:";
        System.out.print("Please, type the new Nominal Power: ");
        while (!scanner.hasNextDouble()) {
            System.out.println(onlyNumbers);
            scanner.next();
        }
        this.mNominalPower = scanner.nextDouble();

        if (mDevice.getDeviceType() == DeviceType.WATER_HEATER) {
            System.out.print("Please, type the new Water Volume that the Water Heater will heat: ");
            while (!scanner.hasNextDouble()) {
                System.out.println(onlyNumbers);
                scanner.next();
            }
            this.mVolumeOfWater = scanner.nextDouble();

            System.out.print("Please, type the Maximum Temperature of the water in the Water Heater: ");
            while (!scanner.hasNextDouble()) {
                System.out.println(onlyNumbers);
                scanner.next();
            }
            this.mHotWaterTemperature = scanner.nextDouble();

            System.out.println(
                    "Do you wish to alter the performance ratio?\n" +
                            "1) Yes;\n" +
                            "2) No;\n");
            int option = inputUtils.readInputNumberAsInt();

            switch (option) {
                case 1:
                    System.out.print("Please, type the Performance Ration of the Water Heater: ");
                    while (!scanner.hasNextDouble()) {
                        System.out.println(onlyNumbers);
                        scanner.next();
                    }
                    this.mPerformanceRatio = scanner.nextDouble();
                    break;
                case 2:
                    this.mPerformanceRatio = 0.9;
                    break;
            }

        }
        if (mDevice.getDeviceType() == DeviceType.WASHING_MACHINE) {
            System.out.print("Please, type the new Capacity in Kg for the Washing Machine: ");
            while (!scanner.hasNextDouble()) {
                System.out.println(onlyNumbers);
                scanner.next();
            }
            this.mCapacity = scanner.nextDouble();
        }
        if (mDevice.getDeviceType() == DeviceType.DISHWASHER) {
            System.out.print("Please, type the new Capacity in Kg for the Dishwasher:");
            while (!scanner.hasNextDouble()) {
                System.out.println(onlyNumbers);
                scanner.next();
            }
            this.mCapacity = scanner.nextDouble();
        }

        if (mDevice.getDeviceType() == DeviceType.FRIDGE) {
            System.out.print("Please, type the new Freezer Capacity in L for the Fridge:");
            while (!scanner.hasNextDouble()) {
                System.out.println(onlyNumbers);
                scanner.next();
            }
            this.mFreezerCapacity = scanner.nextDouble();

            System.out.print("Please, type the new Refrigerator Capacity in L for the Fridge:");
            while (!scanner.hasNextDouble()) {
                System.out.println(onlyNumbers);
                scanner.next();
            }
            this.mRefrigeratorCapacity = scanner.nextDouble();
        }

    }

    /*
    US215 As an Administrator, I want to edit the configuration of an existing device, so that I
    can reconfigure it.*/
    private void updateDeviceUS215() {
        mRoomConfigurationController.setDeviceName(mDeviceName, mDevice);
        mRoomConfigurationController.setNominalPower(mNominalPower, mDevice);
        if (mDevice.getDeviceType() == DeviceType.WATER_HEATER) {

            //mRoomConfigurationController.configureOneHeater(mDevice, mVolumeOfWater, mHotWaterTemperature, mPerformanceRatio);
            //System.out.println("Device Configured.\n");//

            WaterHeater waterHeater = new WaterHeater(mVolumeOfWater, mHotWaterTemperature, mPerformanceRatio);
            mDevice = new Device(mDeviceName, mNominalPower, waterHeater);
        }
        if (mDevice.getDeviceType() == DeviceType.WASHING_MACHINE) {
            //mRoomConfigurationController.configureOneWashingMachine(mDevice, mCapacity);
            //System.out.println("Device Configured.\n");//

            WashingMachine washingMachine = new WashingMachine(mCapacity);
            mDevice = new Device(mDeviceName, mNominalPower, washingMachine);
        }
        if (mDevice.getDeviceType() == DeviceType.DISHWASHER) {
            //mRoomConfigurationController.configureOneDishWasher(mDevice, mCapacity);
            //System.out.println("Device Configured.\n");//

            Dishwasher dishwasher = new Dishwasher(mCapacity);
            mDevice = new Device(mDeviceName, mNominalPower, dishwasher);
        }
        if (mDevice.getDeviceType() == DeviceType.FRIDGE) {
            //mRoomConfigurationController.configureOneFridge(mDevice, mFreezerCapacity, mRefrigeratorCapacity);
            //System.out.println("Device Configured.\n");//

            Fridge fridge = new Fridge(mFreezerCapacity, mRefrigeratorCapacity);
            mDevice = new Device(mDeviceName, mNominalPower, fridge);
        }
    }

    /* US215 As an Administrator, I want to edit the configuration of an existing device, so that I
    can reconfigure it. - CARINA ALAS*/

    private void displayDeviceUS215() {
        System.out.println("\nYou have successfully changed the Device name to " + mDeviceName + ". \n"
                + "The Nominal Power is: " + mNominalPower + " kW. \n" + "And the room is " + mRoom.getRoomName() + "\n");
        if (mDevice.getDeviceType() == DeviceType.WATER_HEATER) {
            System.out.println("The volume of water is " + mVolumeOfWater + " L, the Max Water Temperature " +
                    mHotWaterTemperature + " ºC, and the Performance Ratio is: "
                    + mPerformanceRatio + ".");
        }
        if (mDevice.getDeviceType() == DeviceType.WASHING_MACHINE || mDevice.getDeviceType() == DeviceType.DISHWASHER) {
            System.out.println("The capacity is " + mCapacity + " Kg.");
        }
        if (mDevice.getDeviceType() == DeviceType.FRIDGE) {
            System.out.println("The freezer Capacity is  " + mFreezerCapacity + " L and the Refrigerator Capacity is " + mRefrigeratorCapacity + " L.");
        }
    }

    /*USER STORY 230 - As a Room Owner [or Power User, or Administrator], I want to know the total
    nominal power of a room, i.e. the sum of the nominal power of all devices in the
    room. - TERESA VARELA*/

    private void getRoomNominalPower() {
        double roomNominalPower = mRoomConfigurationController.getRoomNominalPower(this.mRoom);
        System.out.println("This room has a total nominal power of " + roomNominalPower + " kW.\nThis results " +
                "from the sum of the nominal power of all devices in the room.");
    }

    /*US250 - As an Administrator, I want to get a list of all sensors in a room, so that I can configure them.
    MIGUEL ORTIGAO*/

    private void displaySensorListUS250() {
        SensorList sensorList = mRoom.getmRoomSensorList();
        System.out.println(mRoomConfigurationController.printSensorList(sensorList));
    }

    /* USER STORY 253 - As an Administrator, I want to add a new sensor to a room from the list of available
    sensor types, in order to configure it. - ANDRÉ RUA */

    private boolean getInputTypeFromTypeListByList(List<TypeSensor> typeSensorList) {
        UtilsUI utils = new UtilsUI();
        InputUtils inputUtils = new InputUtils();
        RoomConfigurationController ctrl = new RoomConfigurationController();
        System.out.println("Please select a Type of Sensor from the list: ");
        System.out.println(ctrl.printTypeList(typeSensorList));
        int aux = inputUtils.readInputNumberAsInt();
        if (aux >= 0 && aux < typeSensorList.size()) {
            this.mTypeSensor = typeSensorList.get(aux);
            String typeSensorName = mTypeSensor.getName();
            System.out.println("You have chosen the following Type: " + typeSensorName);
            return false;
        } else {
            System.out.println(utils.invalidOption);
            return true;
        }
    }

    private void getInput253() {
        Scanner input = new Scanner(System.in);
        // Name Getter
        System.out.println("\nEnter Sensor Name:\t");
        this.mSensorName = input.nextLine();
        System.out.println("You entered sensor " + mSensorName);
        // Date Getter
        System.out.println("\nEnter Sensor starting date:\t");
        System.out.println("\nEnter the year:\t");
        while (!input.hasNextInt()) {
            input.nextLine();
            System.out.println("Not a valid year. Try again");
        }
        this.mDataYear = input.nextInt();
        input.nextLine();
        System.out.println("\nEnter the Month:\t");
        while (!input.hasNextInt()) {
            input.nextLine();
            System.out.println("Not a valid month. Try again");
        }
        this.mDataMonth = input.nextInt();
        input.nextLine();
        System.out.println("\nEnter the Day:\t");
        while (!input.hasNextInt()) {
            input.nextLine();
            System.out.println("Not a valid day. Try again");
        }
        this.mDataDay = input.nextInt();
        System.out.println("You entered the date successfully!");
    }

    private void updateAndDisplay253() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        SensorSettingsController mController = new SensorSettingsController();
        Date mDate = mController.createDate(this.mDataYear, this.mDataMonth, this.mDataDay);
        Sensor mSensor = mController.createRoomSensor(mSensorName, mTypeSensor, mDate);
        if (ctrl.addSensorToRoom(mRoom, mSensor)) {
            System.out.println("\nSensor successfully added to the Room " + mRoom.getRoomName());
        } else System.out.println("\nSensor already exists in the room.");
    }


    /* UI SPECIFIC METHODS - NOT USED ON USER STORIES */

    private void printRoomConfigMenu() {
        System.out.println("Room Configuration Options:\n");
        System.out.println("1) Get a list of all devices in a room. (US201)");
        System.out.println("2) Add a new device to the room from the list of device types (US210)");
        System.out.println("3) Edit the configuration of an existing device (US215)");
        System.out.println("4) Get the total nominal power of a room (US230)");
        System.out.println("5) Get a list of all sensors in a room (US250)");
        System.out.println("6) Add a sensor to a room from the list of sensor types (US253)");
        System.out.println("0) (Return to main menu)\n");
    }
}




