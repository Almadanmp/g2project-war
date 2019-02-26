package pt.ipp.isep.dei.project.model.device.program;

import java.util.ArrayList;
import java.util.List;

public class VariableProgram implements DeviceProgram {

    public static final String NOMINAL_POWER = "Nominal Power";

    private String programName;

    private double programNominalPower;

    public VariableProgram(String name, double nominalPower) {
        setProgramName(name);
        setNominalPower(nominalPower);
    }

    public void setProgramName(String name) {
        this.programName = name;
    }

    public String getProgramName() {
        return this.programName;
    }

    public void setNominalPower(double nominalPower) {
        this.programNominalPower = nominalPower;
    }

    public double getNominalPower() {
        return this.programNominalPower;
    }

    public String buildProgramString() {
        String result;
        result = "- The Program Name is " + getProgramName() + ", its Nomimal Power is " +
                getNominalPower() + " kW.\n";
        return result;
    }

    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add(NOMINAL_POWER);
        return result;
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        switch (attributeName) {
            case NOMINAL_POWER:
                if (attributeValue instanceof Double) {
                    this.programNominalPower = (Double) attributeValue;
                    return true;
                }
                return false;


            default:
                return false;
        }
    }

    public Object getAttributeUnit(String attributeName) {
        switch (attributeName) {
            case NOMINAL_POWER:
                return "min";
            default:
                return false;
        }
    }


    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case NOMINAL_POWER:
                return programNominalPower;
            default:
                return 0;
        }
    }
}
