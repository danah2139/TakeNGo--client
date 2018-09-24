package com.example.dana.android5778_7109_3610_03.model.backend;

import android.content.ContentValues;

import com.example.dana.android5778_7109_3610_03.model.entities.Branch;
import com.example.dana.android5778_7109_3610_03.model.entities.Car;
import com.example.dana.android5778_7109_3610_03.model.entities.CarModel;
import com.example.dana.android5778_7109_3610_03.model.entities.Client;
import com.example.dana.android5778_7109_3610_03.model.entities.Order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by DELL on 17 דצמבר 2017.
 */

public class CompanyConst {

    public static class BranchConst {
        public static final String BRANCH_NUM = "_id";
        public static final String CITY = "city";
        public static final String STREET = "street";
        public static final String NUMBER = "number";
        public static final String PARKING_NUM = "parkingNum";
    }

    public static class CarConst {
        public static final String CAR_NUM = "_id";
        public static final String BRANCH = "branch";
        public static final String MODEL = "model";
        public static final String MILE = "mile";
        public static final String COLOR = "color";

    }

    public static class CarModelConst {
        public static final String MODEL_CODE = "_id";
        public static final String COMP_NAME = "compName";
        public static final String MODEL_NAME = "modelName";
        public static final String ENGINE_CAPACITY = "engineCapacity";
        public static final String GEARBOX = "Gearbox";
        public static final String SEATS_NUM = "seatsNum";
        public static final String IS_BLUETOOTH = "isBluetooth";
    }

    public static class ClientConst {
        public static final String ID = "_id";
        public static final String LAST_NAME = "lastName";
        public static final String FIRST_NAME = "firstName";
        public static final String PHONE_NUM = "phoneNum";
        public static final String EMAIL = "eMail";
        public static final String CREDIT_CARD = "creditCard";
    }


    public static class OrderConst {
        public static final String ORDER_NUM = "_id";
        public static final String CLIENT_NUM = "clientNum";
        public static final String IS_OPEN = "isOpen";
        public static final String CAR_NUM = "carNum";
        public static final String RENTAL_START = "rentalStart";
        public static final String RENTAL_END = "rentalEnd";
        public static final String MILE_START = "mileStart";
        public static final String MILE_END = "mileEnd";
        public static final String REFUEING = "refueling";
        public static final String REFUEING_LITER_NUM = "refuelingLiterNum";
        public static final String CHARGE_SUM = "chargeSum";
    }
}
