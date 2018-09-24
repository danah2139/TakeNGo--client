package com.example.dana.android5778_7109_3610_03.model.entities;

import java.util.Date;

/**
 * Created by DELL on 11 נובמבר 2017.
 */

public class Order {
    private int clientNum;
    private boolean isOpen;
    private int carNum;
    private Date rentalStart;
    private Date rentalEnd;
    private int mileStart;
    private int mileEnd;
    private boolean refueling;
    private int refuelingLiterNum;
    private double chargeSum;
    private int orderNum;
    public static int ORDER_NUM=2;

    public Order(int clientNum, boolean isOpen, int carNum, Date rentalStart, int mileStart, int orderNum) {
        this.setClientNum(clientNum);
        this.setOpen(isOpen);
        this.setCarNum(carNum);
        this.setRentalStart(rentalStart);
        this.setRentalEnd(new Date(01/01/2000));
        this.setMileStart(mileStart);
        this.setMileEnd(0);
        this.setRefueling(false);
        this.setRefuelingLiterNum(0);
        this.setChargeSum(0);
        this.setOrderNum(orderNum);
    }

    public Order() {
        this.setClientNum(0);
        this.setOpen(false);
        this.setCarNum(0);
        this.setRentalStart(new Date(01/01/1900));
        this.setRentalEnd(new Date(01/01/1900));
        this.setMileStart(0);
        this.setMileEnd(0);
        this.setRefueling(false);
        this.setRefuelingLiterNum(0);
        this.setChargeSum(0);
        this.setOrderNum(0);
    }
    public int getClientNum() {
        return clientNum;
    }

    public void setClientNum(int clientNum) {
        this.clientNum = clientNum;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public int getCarNum() {
        return carNum;
    }

    public void setCarNum(int carNum) {
        this.carNum = carNum;
    }

    public Date getRentalStart() {
        return rentalStart;
    }

    public void setRentalStart(Date rentalStart) {
        this.rentalStart = rentalStart;
    }

    public Date getRentalEnd() {
        return rentalEnd;
    }

    public void setRentalEnd(Date rentalEnd) {
        this.rentalEnd = rentalEnd;
    }

    public int getMileStart() {
        return mileStart;
    }

    public void setMileStart(int mileStart) {
        this.mileStart = mileStart;
    }

    public int getMileEnd() {
        return mileEnd;
    }

    public void setMileEnd(int mileEnd) {
        this.mileEnd = mileEnd;
    }

    public boolean isRefueling() {
        return refueling;
    }

    public void setRefueling(boolean refueling) {
        this.refueling = refueling;
    }

    public int getRefuelingLiterNum() {
        return refuelingLiterNum;
    }

    public void setRefuelingLiterNum(int refuelingLiterNum) {
        this.refuelingLiterNum = refuelingLiterNum;
    }

    public double getChargeSum() {
        return chargeSum;
    }

    public void setChargeSum(double chargeSum) {
        this.chargeSum = chargeSum;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "Order{" +
                "clientNum=" + clientNum +
                ", isOpen=" + isOpen +
                ", carNum=" + carNum +
                ", rentalStart=" + rentalStart +
                ", rentalEnd=" + rentalEnd +
                ", mileStart=" + mileStart +
                ", mileEnd=" + mileEnd +
                ", refueling=" + refueling +
                ", refuelingLiterNum=" + refuelingLiterNum +
                ", chargeSum=" + chargeSum +
                ", orderNum=" + orderNum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (getClientNum() != order.getClientNum()) return false;
        if (isOpen() != order.isOpen()) return false;
        if (getCarNum() != order.getCarNum()) return false;
        if (getMileStart() != order.getMileStart()) return false;
        if (getMileEnd() != order.getMileEnd()) return false;
        if (isRefueling() != order.isRefueling()) return false;
        if (getRefuelingLiterNum() != order.getRefuelingLiterNum()) return false;
        if (Double.compare(order.getChargeSum(), getChargeSum()) != 0) return false;
        if (getOrderNum() != order.getOrderNum()) return false;
        if (getRentalStart() != null ? !getRentalStart().equals(order.getRentalStart()) : order.getRentalStart() != null)
            return false;
        return getRentalEnd() != null ? getRentalEnd().equals(order.getRentalEnd()) : order.getRentalEnd() == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getClientNum();
        result = 31 * result + (isOpen() ? 1 : 0);
        result = 31 * result + getCarNum();
        result = 31 * result + (getRentalStart() != null ? getRentalStart().hashCode() : 0);
        result = 31 * result + (getRentalEnd() != null ? getRentalEnd().hashCode() : 0);
        result = 31 * result + getMileStart();
        result = 31 * result + getMileEnd();
        result = 31 * result + (isRefueling() ? 1 : 0);
        result = 31 * result + getRefuelingLiterNum();
        temp = Double.doubleToLongBits(getChargeSum());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getOrderNum();
        return result;
    }
}
