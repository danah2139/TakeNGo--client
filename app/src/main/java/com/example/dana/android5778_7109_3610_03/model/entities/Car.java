package com.example.dana.android5778_7109_3610_03.model.entities;

/**
 * Created by DELL on 11 נובמבר 2017.
 */

public class Car {
    private int branch; //branch num
    private int model;
    private int mile;
    private int carNum; ///only 7 characters!!
   // public Colors color;
    private Colors color;

    public Car()
    {
        this.setBranchNum(0);
        this.setCarModel(0);
        this.setMile(0);
        this.setCarNum(10000000);
        this.color =Colors.SILVER;
    }
    public Car(int branch, int model, int mile, int carNum,Colors color) throws Exception {
        this.setBranchNum(branch);
        this.setCarModel(model);
        this.setMile(mile);
        this.setColor(color);
        if(carNum>1000000&&carNum<99999999)
            this.setCarNum(carNum);
        else
            throw new Exception("Wrong number format");
    }

    public int getBranchNum() {
        return branch;
    }

    public void setBranchNum(int branchNum) { this.branch= branchNum; }

    public int getCarModel() {
        return model;
    }

    public void setCarModel(int carModel) {
        this.model = carModel;
    }

    public int getMile() {
        return mile;
    }

    public void setMile(int mile) {
        this.mile = mile;
    }

    public int getCarNum() {
        return carNum;
    }

    public void setCarNum(int carNum) {
        this.carNum = carNum;
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "branch=" + branch +
                ", model=" + model +
                ", mile=" + mile +
                ", carNum=" + carNum +
                ", color=" + color +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (branch != car.branch) return false;
        if (model != car.model) return false;
        if (getMile() != car.getMile()) return false;
        if (getCarNum() != car.getCarNum()) return false;
        return getColor() == car.getColor();
    }

    @Override
    public int hashCode() {
        int result = branch;
        result = 31 * result + model;
        result = 31 * result + getMile();
        result = 31 * result + getCarNum();
        result = 31 * result + (getColor() != null ? getColor().hashCode() : 0);
        return result;
    }
}
