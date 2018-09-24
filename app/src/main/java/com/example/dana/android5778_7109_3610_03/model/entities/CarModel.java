package com.example.dana.android5778_7109_3610_03.model.entities;

import java.util.List;

import static com.example.dana.android5778_7109_3610_03.model.entities.Gearbox.AUTOMOAT;

/**
 * Created by DELL on 11 נובמבר 2017.
 */

public class CarModel {
    private int modelCode;
    private String compName;
    private String modelName;
    private double engineCapacity;
    public Gearbox gearbox;
    private int seatsNum;
    private Boolean isBluetooth;

    public CarModel() {
        this.modelCode = 0;
        this.compName = "";
        this.modelName = "";
        this.engineCapacity = 0;
        this.gearbox = AUTOMOAT;
        this.seatsNum = 0;
        this.isBluetooth = false;
    }

    public CarModel(int modelCode, String compName, String modelName, double engineCapacity, Gearbox gearbox, int seatsNum, Boolean isBluetooth) {
        this.modelCode = modelCode;
        this.compName = compName;
        this.modelName = modelName;
        this.engineCapacity = engineCapacity;
        this.gearbox = gearbox;
        this.seatsNum = seatsNum;
        this.isBluetooth = isBluetooth;
    }

    public int getModelCode() {
        return modelCode;
    }

    public void setModelCode(int modelCode) {
        this.modelCode = modelCode;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public Gearbox getGearbox() {
        return gearbox;
    }

    public void setGearbox(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    public int getSeatsNum() {
        return seatsNum;
    }

    public void setSeatsNum(int seatsNum) {
        this.seatsNum = seatsNum;
    }

    public Boolean getBluetooth() {
        return isBluetooth;
    }

    public void setBluetooth(Boolean bluetooth) {
        isBluetooth = bluetooth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarModel carModel = (CarModel) o;

        if (getModelCode() != carModel.getModelCode()) return false;
        if (Double.compare(carModel.getEngineCapacity(), getEngineCapacity()) != 0) return false;
        if (getSeatsNum() != carModel.getSeatsNum()) return false;
        if (getCompName() != null ? !getCompName().equals(carModel.getCompName()) : carModel.getCompName() != null)
            return false;
        if (getModelName() != null ? !getModelName().equals(carModel.getModelName()) : carModel.getModelName() != null)
            return false;
        if (getGearbox() != carModel.getGearbox()) return false;
        return isBluetooth != null ? isBluetooth.equals(carModel.isBluetooth) : carModel.isBluetooth == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getModelCode();
        result = 31 * result + (getCompName() != null ? getCompName().hashCode() : 0);
        result = 31 * result + (getModelName() != null ? getModelName().hashCode() : 0);
        temp = Double.doubleToLongBits(getEngineCapacity());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getGearbox() != null ? getGearbox().hashCode() : 0);
        result = 31 * result + getSeatsNum();
        result = 31 * result + (isBluetooth != null ? isBluetooth.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "modelCode=" + modelCode +
                ", compName='" + compName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", engineCapacity=" + engineCapacity +
                ", gearbox=" + gearbox +
                ", seatsNum=" + seatsNum +
                ", isBluetooth=" + isBluetooth +
                '}';
    }
}
