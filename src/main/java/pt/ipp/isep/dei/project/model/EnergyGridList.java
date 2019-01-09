package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnergyGridList {

    private List<EnergyGrid> mEnergyGridList;

    public EnergyGridList() {
        mEnergyGridList = new ArrayList<>();
    }

    public boolean addEnergyGridToEnergyGridList(EnergyGrid energyGridToAdd) {
        if (!(mEnergyGridList.contains(energyGridToAdd))) {
            mEnergyGridList.add(energyGridToAdd);
            return true;
        }
        return false;
    }

    public String printEnergyGridList() {
        StringBuilder finalString = new StringBuilder();
        String emptyList = "The list is empty.";
        if (mEnergyGridList.isEmpty()) {
            return emptyList;
        }
        finalString.append("Energy grid list:");
        for (EnergyGrid energyGrid : mEnergyGridList) {
            finalString.append(" \n" + "-").append(energyGrid.getName()).append(";");
        }
        return finalString.toString();
    }

    public List<EnergyGrid> getEnergyGridList() {
        return mEnergyGridList;
    }


    public List<Integer> matchGridListElementsByString(String name) {

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < mEnergyGridList.size(); i++) {
            if (mEnergyGridList.get(i).getName().equals(name)) {
                result.add(i);
            }
        }
        return result;
    }

    public String printElementsByIndex(List<Integer> indexes) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < indexes.size(); i++) {
            result.append(indexes.get(i)).append(") ").append(mEnergyGridList.get(i).getName()).append(", ").append(mEnergyGridList.get(i).getMaxPower()).append(", ").append(mEnergyGridList.get(i).getListPowerSources()).append(".\n");
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof EnergyGridList)) {
            return false;
        }
        EnergyGridList list = (EnergyGridList) testObject;
        return Arrays.equals(this.getEnergyGridList().toArray(), list.getEnergyGridList().toArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
