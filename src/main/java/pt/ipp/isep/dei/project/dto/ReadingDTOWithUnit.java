package pt.ipp.isep.dei.project.dto;

import pt.ipp.isep.dei.project.services.units.Unit;

import java.util.Date;

public class ReadingDTOWithUnit {

    private Date date;
    private double value;
    private Unit unit; // This needs to be UnitDto

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Unit getUnit() {
        return this.unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReadingDTOWithUnit)) {
            return false;
        } else {
            ReadingDTOWithUnit reading = (ReadingDTOWithUnit) o;
            return (this.date.equals(reading.getDate()));
        }
    }

    @Override
    public int hashCode() {
        return date.hashCode() * unit.hashCode() * (int) value;
    }
}
