package com.example.dana.android5778_7109_3610_03.model.backend;

import android.content.ContentValues;

//import com.example.dana.android5778_7109_3610_03.controller.User;
import com.example.dana.android5778_7109_3610_03.model.entities.Branch;
import com.example.dana.android5778_7109_3610_03.model.entities.Car;
import com.example.dana.android5778_7109_3610_03.model.entities.CarModel;
import com.example.dana.android5778_7109_3610_03.model.entities.Client;
import com.example.dana.android5778_7109_3610_03.model.entities.Order;

import java.util.Date;
import java.util.List;

/**
 * Created by DELL on 17 דצמבר 2017.
 */

public interface DB_manager {
    public boolean isExist(int id) throws Exception;

    public boolean addClient(ContentValues client) throws Exception;

    public void updateCar(int mile, ContentValues car) throws Exception;

    public List<Client> getClientsList() throws Exception;

    public List<Branch> getBranchesList() throws Exception;

    public List<Car> getAllAvailableCarsList() throws Exception;

    public List<Car> getAvailableCarsBranchList(ContentValues branch) throws Exception;

    public List<CarModel> getCarModelsList() throws Exception;

    public List<Branch> getAvailableModelsBranchList(ContentValues carModel) throws Exception;

    public List<Order> getOrdersList() throws Exception;

    public List<Order> getOpenOrdersList() throws Exception;

    public void addOrder(ContentValues order) throws Exception;

    public double closeOrder(int mileEnd, Boolean isReful, int refuelingLiterNum, ContentValues order) throws Exception;

    public boolean isCloseInTime(Date ClosedOrderTime) throws Exception;

    //public void addModel(ContentValues model) throws Exception;
    //public void addCar(ContentValues car) throws Exception;
    public List<Car> GetCarsList() throws Exception;

    public boolean checkClosedOrder() throws Exception;

}

