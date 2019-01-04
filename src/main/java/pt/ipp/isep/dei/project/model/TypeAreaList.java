package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;

public class TypeAreaList {
    private List<TypeArea> mTypeAreaList;

    public TypeAreaList() {
        mTypeAreaList = new ArrayList<>();
    }

    /**
     * This method prints all the Geographic Area Types in a TypeAreaList.
     * @return
     */
    public String printTypeAreaList() {
        StringBuilder finalString = new StringBuilder("\nArea Types List:\n");
        if(mTypeAreaList.isEmpty()) {
            finalString.append("\n|||| List is Empty ||||\nAdd types to list first");
        }
        else { for (TypeArea tipo : mTypeAreaList)
            finalString.append("\n").append("-").append(tipo.getTypeOfGeographicArea()).append(";");}
        return finalString.toString();
    }

    /**
     * This method creates a new Type of Geographic Area and adds it to a list.
     * @param name String of the new Area Type that one wishes to create and add to a list.
     * @return
     */
    public boolean newTAG(String name) {
        if (name == null || name.isEmpty() || name.matches(".*\\d+.*")) {
            return false;
        }
        TypeArea tipo = new TypeArea(name);
        return addTypeArea(tipo);
    }

    /**
     * This method adds a previously stated Area Type to a List of Area Types.
     * @param type Type of Geographic Area one wishes to add to a list.
     * @return
     */
    public boolean addTypeArea(TypeArea type) {
        if (!mTypeAreaList.contains(type)){
            mTypeAreaList.add(type);
            return true;
        }
        else{
            return false;
        }
    }

    public List<Integer>  matchGeographicAreaTypeIndexByString(String input){
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < mTypeAreaList.size(); i++){
            if (mTypeAreaList.get(i).getTypeOfGeographicArea().equals(input)){
                result.add(i);
            }
        }
        return result;
    }

    public String printGATypeElementsByIndex (List<Integer> indexes){
        StringBuilder result = new StringBuilder("---------------\n");
        for (int i = 0 ; i<indexes.size() ; i++ ) {
            int pos = indexes.get(i);
            result.append(indexes.get(i)).append(") ").append(mTypeAreaList.get(pos).printTypeGeographicArea());
        }
        result.append("---------------\n");
        return result.toString();
    }

    public String printGATypeWholeList(TypeAreaList typeAreaList) {
        StringBuilder result = new StringBuilder("---------------\n");

        if (typeAreaList.getTypeAreaList().isEmpty()){
            return "Invalid List - List is Empty\n";
        }

        for (int i = 0; i < typeAreaList.getTypeAreaList().size(); i++) {
            TypeArea aux = typeAreaList.getTypeAreaList().get(i);
            result.append(i).append(") Name: ").append(aux.getTypeOfGeographicArea()).append(" | \n");
        }
        result.append("---------------\n");
        System.out.print(result); //TODO remove this and print on UIS, this allows us to test method
        return result.toString();
    }

    /**
     * Getter of the Type Area List.
     * @return
     */
    public List<TypeArea> getTypeAreaList() {
        return this.mTypeAreaList;
    }
}
