package pt.ipp.isep.dei.project.model.device.devicespecs;

import java.util.ArrayList;
import java.util.List;

public class ElectricOvenSpec implements DeviceSpecs {

    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        return result;
    }

    public Object getAttributeValue(String attributeName) {
        return 0;
    }

    public Object getAttributeUnit(String attributeName) {
        return false;
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        return false;
    }
}
