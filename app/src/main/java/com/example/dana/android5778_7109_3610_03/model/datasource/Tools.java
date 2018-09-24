package com.example.dana.android5778_7109_3610_03.model.datasource;

import android.content.ContentValues;

import com.example.dana.android5778_7109_3610_03.model.backend.CompanyConst;
import com.example.dana.android5778_7109_3610_03.model.entities.Branch;
import com.example.dana.android5778_7109_3610_03.model.entities.Car;
import com.example.dana.android5778_7109_3610_03.model.entities.CarModel;
import com.example.dana.android5778_7109_3610_03.model.entities.Client;
import com.example.dana.android5778_7109_3610_03.model.entities.Colors;
import com.example.dana.android5778_7109_3610_03.model.entities.Gearbox;
import com.example.dana.android5778_7109_3610_03.model.entities.Order;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by DELL on 25 דצמבר 2017.
 */

public class Tools {

    public static ContentValues BranchToContentValues(Branch branch) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CompanyConst.BranchConst.BRANCH_NUM, branch.getBranchNum());
        contentValues.put(CompanyConst.BranchConst.CITY, branch.getCity());
        contentValues.put(CompanyConst.BranchConst.STREET, branch.getStreet());
        contentValues.put(CompanyConst.BranchConst.NUMBER, branch.getNumber());
        contentValues.put(CompanyConst.BranchConst.PARKING_NUM, branch.getParkingNum());
        return contentValues;

    }

    public static Branch ContentValuesToBranch(ContentValues contentValues) {
        Branch branch = new Branch();
        branch.setBranchNum(contentValues.getAsInteger(CompanyConst.BranchConst.BRANCH_NUM));
        branch.setCity(contentValues.getAsString(CompanyConst.BranchConst.CITY));
        branch.setStreet(contentValues.getAsString(CompanyConst.BranchConst.STREET));
        branch.setNumber(contentValues.getAsInteger(CompanyConst.BranchConst.NUMBER));
        branch.setParkingNum(contentValues.getAsInteger(CompanyConst.BranchConst.PARKING_NUM));
        return branch;

    }

    public static ContentValues CarToContentValues(Car car) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CompanyConst.CarConst.CAR_NUM, car.getCarNum());
        contentValues.put(CompanyConst.CarConst.BRANCH, car.getBranchNum());
        contentValues.put(CompanyConst.CarConst.MODEL, car.getCarModel());
        contentValues.put(CompanyConst.CarConst.MILE, car.getMile());
        contentValues.put(CompanyConst.CarConst.COLOR, car.getColor().toString());

        return contentValues;

    }

    public static Car ContentValuesToCar(ContentValues contentValues) throws Exception {
        Car car = new Car();
        car.setCarNum(contentValues.getAsInteger(CompanyConst.CarConst.CAR_NUM));
        car.setBranchNum(contentValues.getAsInteger(CompanyConst.CarConst.BRANCH));
        car.setCarModel(contentValues.getAsInteger(CompanyConst.CarConst.MODEL));
        car.setMile(contentValues.getAsInteger(CompanyConst.CarConst.MILE));
        car.setColor(Colors.valueOf(contentValues.getAsString(CompanyConst.CarConst.COLOR)));

        return car;

    }
    public static ContentValues CarModelToContentValues(CarModel carModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CompanyConst.CarModelConst.MODEL_CODE, carModel.getModelCode());
        contentValues.put(CompanyConst.CarModelConst.COMP_NAME, carModel.getCompName());
        contentValues.put(CompanyConst.CarModelConst.MODEL_NAME, carModel.getModelName());
        contentValues.put(CompanyConst.CarModelConst.ENGINE_CAPACITY, carModel.getEngineCapacity());
        contentValues.put(CompanyConst.CarModelConst.GEARBOX, carModel.getGearbox().toString());
        contentValues.put(CompanyConst.CarModelConst.SEATS_NUM, carModel.getSeatsNum());
        contentValues.put(CompanyConst.CarModelConst.IS_BLUETOOTH, carModel.getBluetooth());
        return contentValues;

    }

    public static CarModel ContentValuesToCarModel(ContentValues contentValues) {
        CarModel carModel = new CarModel();
        carModel.setModelCode(contentValues.getAsInteger(CompanyConst.CarModelConst.MODEL_CODE));
        carModel.setCompName(contentValues.getAsString(CompanyConst.CarModelConst.COMP_NAME));
        carModel.setModelName(contentValues.getAsString(CompanyConst.CarModelConst.MODEL_NAME));
        carModel.setEngineCapacity(contentValues.getAsInteger(CompanyConst.CarModelConst.ENGINE_CAPACITY));
        carModel.setGearbox(Gearbox.valueOf(contentValues.getAsString(CompanyConst.CarModelConst.GEARBOX)));
        carModel.setSeatsNum(contentValues.getAsInteger(CompanyConst.CarModelConst.SEATS_NUM));
        carModel.setBluetooth(contentValues.getAsBoolean(CompanyConst.CarModelConst.IS_BLUETOOTH));
        return carModel;

    }
    public static ContentValues ClientToContentValues(Client client) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CompanyConst.ClientConst.LAST_NAME, client.getLastName());
        contentValues.put(CompanyConst.ClientConst.FIRST_NAME, client.getFirstName());
        contentValues.put(CompanyConst.ClientConst.ID, client.getId());
        contentValues.put(CompanyConst.ClientConst.PHONE_NUM, client.getPhoneNum());
        contentValues.put(CompanyConst.ClientConst.EMAIL, client.geteMail());
        contentValues.put(CompanyConst.ClientConst.CREDIT_CARD, client.getCreditCard());
        return contentValues;

    }

    public static Client ContentValuesToClient(ContentValues contentValues) {
        Client client = new Client();
        client.setId(contentValues.getAsInteger(CompanyConst.ClientConst.ID));
        client.setLastName(contentValues.getAsString(CompanyConst.ClientConst.LAST_NAME));
        client.setFirstName(contentValues.getAsString(CompanyConst.ClientConst.FIRST_NAME));
        client.setPhoneNum(contentValues.getAsInteger(CompanyConst.ClientConst.PHONE_NUM));
        client.seteMail(contentValues.getAsString(CompanyConst.ClientConst.EMAIL));
        client.setCreditCard(contentValues.getAsInteger(CompanyConst.ClientConst.CREDIT_CARD));

        return client;

    }

    public static ContentValues OrderToContentValues(Order order) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CompanyConst.OrderConst.ORDER_NUM, order.getOrderNum());
        contentValues.put(CompanyConst.OrderConst.CLIENT_NUM, order.getClientNum());
        contentValues.put(CompanyConst.OrderConst.IS_OPEN, order.isOpen());
        contentValues.put(CompanyConst.OrderConst.CAR_NUM, order.getCarNum());

        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd"); // like MySQL Format
        String dateString1 = dateFormat1.format(order.getRentalStart());
        contentValues.put(CompanyConst.OrderConst.RENTAL_START, dateString1);
        contentValues.put(CompanyConst.OrderConst.MILE_START, order.getMileStart());

        if(!order.isOpen()) {
            DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd"); // like MySQL Format
            String dateString2 = dateFormat2.format(order.getRentalEnd());
            contentValues.put(CompanyConst.OrderConst.RENTAL_END, dateString2);

            contentValues.put(CompanyConst.OrderConst.MILE_END, order.getMileEnd());
            contentValues.put(CompanyConst.OrderConst.REFUEING, order.isRefueling());
            contentValues.put(CompanyConst.OrderConst.REFUEING_LITER_NUM, order.getRefuelingLiterNum());
            contentValues.put(CompanyConst.OrderConst.CHARGE_SUM, order.getChargeSum());
        }
        return contentValues;

    }


    public static Order ContentValuesToOrder(ContentValues contentValues) {

        Order order = new Order();
        order.setOrderNum(contentValues.getAsInteger(CompanyConst.OrderConst.ORDER_NUM));
        order.setClientNum(contentValues.getAsInteger(CompanyConst.OrderConst.CLIENT_NUM));
        order.setOpen(contentValues.getAsBoolean(CompanyConst.OrderConst.IS_OPEN));
        order.setCarNum(contentValues.getAsInteger(CompanyConst.OrderConst.CAR_NUM));

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // like MySQL Format
        String dateString1 = contentValues.getAsString(CompanyConst.OrderConst.RENTAL_START);
        try {
            order.setRentalStart(dateFormat.parse(dateString1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        order.setMileStart(contentValues.getAsInteger(CompanyConst.OrderConst.MILE_START));

        if(!(contentValues.getAsBoolean(CompanyConst.OrderConst.IS_OPEN))) {
            String dateString2 = contentValues.getAsString(CompanyConst.OrderConst.RENTAL_END);
            try {
                order.setRentalEnd(dateFormat.parse(dateString2));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            order.setMileEnd(contentValues.getAsInteger(CompanyConst.OrderConst.MILE_END));
            order.setRefueling(contentValues.getAsBoolean(CompanyConst.OrderConst.REFUEING));
            order.setRefuelingLiterNum(contentValues.getAsInteger(CompanyConst.OrderConst.REFUEING_LITER_NUM));
            order.setChargeSum(contentValues.getAsDouble(CompanyConst.OrderConst.CHARGE_SUM));
        }
        return order;
    }
}

