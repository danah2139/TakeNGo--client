package com.example.dana.android5778_7109_3610_03.model.entities;

/**
 * Created by DELL on 11 נובמבר 2017.
 */

public class Branch {

    private String city;
    private String street;
    private int number;
    private int parkingNum;
    private int branchNum;

    public Branch(String city, String street, int number, int parkingNum, int branchNum) {
        this.city = city;
        this.street = street;
        this.number = number;
        this.parkingNum = parkingNum;
        this.branchNum = branchNum;
    }

    public Branch() {
        this.city = "";
        this.street = "";
        this.number = 0;
        this.parkingNum = 0;
        this.branchNum = 0;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getParkingNum() {
        return parkingNum;
    }

    public void setParkingNum(int parkingNum) {
        this.parkingNum = parkingNum;
    }

    public int getBranchNum() {
        return branchNum;
    }

    public void setBranchNum(int branchNum) {
        this.branchNum = branchNum;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number=" + number +
                ", parkingNum=" + parkingNum +
                ", branchNum=" + branchNum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Branch branch = (Branch) o;

        if (getNumber() != branch.getNumber()) return false;
        if (getParkingNum() != branch.getParkingNum()) return false;
        if (getBranchNum() != branch.getBranchNum()) return false;
        if (getCity() != null ? !getCity().equals(branch.getCity()) : branch.getCity() != null)
            return false;
        return getStreet() != null ? getStreet().equals(branch.getStreet()) : branch.getStreet() == null;
    }

    @Override
    public int hashCode() {
        int result = getCity() != null ? getCity().hashCode() : 0;
        result = 31 * result + (getStreet() != null ? getStreet().hashCode() : 0);
        result = 31 * result + getNumber();
        result = 31 * result + getParkingNum();
        result = 31 * result + getBranchNum();
        return result;
    }
}
