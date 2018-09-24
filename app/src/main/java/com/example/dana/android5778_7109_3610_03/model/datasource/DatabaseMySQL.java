package com.example.dana.android5778_7109_3610_03.model.datasource;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

import com.example.dana.android5778_7109_3610_03.model.backend.DB_manager;
import com.example.dana.android5778_7109_3610_03.model.entities.Branch;
import com.example.dana.android5778_7109_3610_03.model.entities.Car;
import com.example.dana.android5778_7109_3610_03.model.entities.CarModel;
import com.example.dana.android5778_7109_3610_03.model.entities.Client;
import com.example.dana.android5778_7109_3610_03.model.entities.Colors;
import com.example.dana.android5778_7109_3610_03.model.entities.Order;
//import com.example.dana.android5778_7109_3610_03.model.entities.Worker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.example.dana.android5778_7109_3610_03.model.datasource.PHPtools.POST;
import static com.example.dana.android5778_7109_3610_03.model.datasource.Tools.BranchToContentValues;
import static com.example.dana.android5778_7109_3610_03.model.datasource.Tools.CarToContentValues;
import static com.example.dana.android5778_7109_3610_03.model.datasource.Tools.ContentValuesToBranch;
import static com.example.dana.android5778_7109_3610_03.model.datasource.Tools.ContentValuesToCar;
import static com.example.dana.android5778_7109_3610_03.model.datasource.Tools.ContentValuesToCarModel;
import static com.example.dana.android5778_7109_3610_03.model.datasource.Tools.ContentValuesToOrder;



/**
 * Created by DELL on 17 דצמבר 2017.
 */
//Log can do problems!
public class DatabaseMySQL implements DB_manager {
    //private final String UserName="goldfarb";
    private final String WEB_URL = "http://goldfarb.vlab.jct.ac.il/";

    public void printLog(String message)
    {
        Log.d(this.getClass().getName(),"\n"+message);
    }
    public void printLog(Exception message)
    {
        Log.d(this.getClass().getName(),"Exception-->\n"+message);
    }
    boolean result=false;
    int days=0;
    @Override
    public boolean isExist(int id)throws Exception {
        try {
            //final List<Client> clientsList = new ArrayList<Client>();
            int ID=id;
            new AsyncTask<Integer,Void,Boolean>(){
                @Override
                protected Boolean doInBackground(Integer... integers) {
                    try {
                        List<Client> clientsListTemp=new ArrayList<Client>();
                        clientsListTemp = getClientsList();
                        for (Client c: clientsListTemp ) {
                            //clientsList.add(c);

                            if (integers[0] == c.getId())
                                result= true;
                            //throw new Exception(integers[0].toString());
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                    try{
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    return result;
                }
            }.execute(ID);
            /*for (Client client : clientsList) {
                if (client.getId() == (id))
                    return true;
            }*/
            return result;
        }
        catch (Exception e)
        {
            throw e;
        }
    }
    //critical to call the string in parms.put(String, Object) will be exactly the same as the string in the REQUEST in our PHP
    @Override
    public boolean addClient(ContentValues client) throws Exception {
        try {
            Map<String,Object> params = new LinkedHashMap<>();
            params.put("_id",client.getAsInteger("_id"));
            params.put("lastName",client.getAsString("lastName"));
            params.put("firstName",client.getAsString("firstName"));
            params.put("phoneNum",client.getAsInteger("phoneNum"));
            params.put("eMail",client.getAsString("eMail"));
            params.put("creditCard",client.getAsInteger("creditCard"));

            String results = POST(WEB_URL + "addClient.php", params);
            return true;
            //printLog("add client:\n" + results);

        } catch (IOException e) {
            return false;
            //printLog("add client Exception:\n" + e);

        }
    }
    public void updateCar(int mile, ContentValues car)throws Exception {
        Car carUpdate =ContentValuesToCar(car);
        carUpdate.setMile(mile);
        try {
            Map<String,Object> params = new LinkedHashMap<>();
            params.put("_id",car.getAsInteger("_id"));
            params.put("branch",car.getAsInteger("branch"));
            params.put("model",car.getAsInteger("model"));
            params.put("mile",carUpdate.getMile());
            params.put("color",car.getAsString("color"));
            String result = POST(WEB_URL + "updateCar.php", params);
        } catch (IOException e) {
            System.out.println("upDate Exception:\n" + e);
        }


    }
    public List<Client> getClientsList ()throws Exception {
        List<Client> result = new ArrayList<Client>();
        try {
            String str = PHPtools.GET(WEB_URL + "getAllClients.php");
            JSONArray array = new JSONObject(str).getJSONArray("clients");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                Client client = new Client();
                client.setId(jsonObject.getInt("_id"));
                client.setLastName(jsonObject.getString("lastName"));
                client.setFirstName(jsonObject.getString("firstName"));
                client.setPhoneNum(jsonObject.getInt("phoneNum"));
                client.seteMail(jsonObject.getString("eMail"));
                client.setCreditCard(jsonObject.getInt("creditCard"));
                result.add(client);
            }
            return result;
        }
        catch (Exception e) {
            throw e;
            // e.printStackTrace();
        }
        //return null;
    }
    public List<Branch> getBranchesList ()throws Exception{
        List<Branch> result = new ArrayList<Branch>();
        try {
            String str = PHPtools.GET(WEB_URL + "getAllBranchs.php");
            JSONArray array = new JSONObject(str).getJSONArray("branchs");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                Branch branch= new Branch();
                branch.setBranchNum(jsonObject.getInt("_id"));
                branch.setCity(jsonObject.getString("city"));
                branch.setStreet(jsonObject.getString("street"));
                branch.setNumber(jsonObject.getInt("number"));
                branch.setParkingNum(jsonObject.getInt("parkingNum"));
                result.add(branch);
            }
            return result;
        }
        catch (Exception e) {
            throw e;
        }
    }
    public List<Car> getAllAvailableCarsList()throws Exception{
        List<Car> result = new ArrayList<Car>();
        List<Order> ordersList = getOpenOrdersList();
        List<Car> carsList = GetCarsList();
        boolean flag = false;
        try {
            for(Car car : carsList) {
                for (Order order : ordersList) {
                    if (order.getCarNum() == car.getCarNum() )
                        flag=true;
                }
                if(!flag)
                    result.add(car);
                flag=false;
            }
            return result;
        }
        catch (Exception e) {
            throw e;
        }
    }
    public List<Car> getAvailableCarsBranchList(ContentValues branch)throws Exception{
        List<Car> result = new ArrayList<Car>();
        Branch b=ContentValuesToBranch(branch);
        List<Car> carsList = getAllAvailableCarsList();
        try {
            for (Car car : carsList)
            {
                if (car.getBranchNum()==b.getBranchNum())
                    result.add(car);
            }
            return result;
        }
        catch (Exception e) {
            throw e;
        }

    }

    public List<CarModel> getCarModelsList()throws Exception{
        List<CarModel> result = new ArrayList<CarModel>();
        try {
            String str = PHPtools.GET(WEB_URL + "getAllModels.php");
            JSONArray array = new JSONObject(str).getJSONArray("models");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                CarModel carModel= ContentValuesToCarModel(contentValues);
                result.add(carModel);
            }
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Branch> getAvailableModelsBranchList(ContentValues carModel)throws Exception{ // the function not necessary , we may will change to return List <CarModel,Branch> and forech (CarModel carModel:carModelsList)
        List<Branch> result = new ArrayList<>();
        List<Branch> branchesList = getBranchesList();
        CarModel c=ContentValuesToCarModel(carModel);
        try {
            for (Branch branch : branchesList) {
                ContentValues b=BranchToContentValues(branch);
                List<Car> carsList = getAvailableCarsBranchList(b);
                for (Car car:carsList) {
                    if (car.getCarModel() == c.getModelCode())
                        result.add(branch);
                }
            }
            return result;
        }
        catch (Exception e) {
            throw e;
        }

    }

    public List<Order> getOrdersList()throws Exception{
        List<Order> result = new ArrayList<>();
        try {
            String str = PHPtools.GET(WEB_URL + "getAllOrders.php");
            JSONArray array = new JSONObject(str).getJSONArray("orders");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Order order= ContentValuesToOrder(contentValues);
                result.add(order);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    public List<Order> getOpenOrdersList()throws Exception{
        List<Order> result = new ArrayList<>();
        List<Order> ordersList = getOrdersList();
        try {
            for (Order order:ordersList)
                if(order.isOpen())
                    result.add(order);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public void addOrder (ContentValues order) throws Exception{
        try {
            Map<String,Object> params = new LinkedHashMap<>();
            Order o=ContentValuesToOrder(order);
            //o.getCarNum())
            params.put("_id",Order.ORDER_NUM++);
            params.put("clientNum",order.getAsInteger("clientNum"));
            params.put("isOpen",order.getAsBoolean("isOpen"));
            params.put("carNum",order.getAsInteger("carNum"));
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // like MySQL Format
            //String dateString1 = dateFormat1.format(order.getAsString("rentalStart"));
            //Date date= Calendar.getInstance().getTime();
            params.put("rentalStart",dateFormat.format(Calendar.getInstance().getTime()));

            //DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // like MySQL Format
            //String dateString2 = dateFormat1.format(order.getAsString("rentalEnd"));

            //Date date1=Calendar.getInstance().getTime();
            params.put("rentalEnd",dateFormat.format(Calendar.getInstance().getTime()));
            params.put("mileStart",order.getAsInteger("mileStart"));
            params.put("mileEnd",order.getAsInteger("mileEnd"));
            params.put("refueling" , order.getAsBoolean("refueling"));
            params.put("refuelingLiterNum",order.getAsInteger("refuelingLiterNum"));
            params.put("chargeSum",order.getAsDouble("chargeSum"));


            String results = POST(WEB_URL + "addOrder.php", params);

            //printLog("add client:\n" + results);

        } catch (IOException e) {
            System.out.println("addOrder Exception:\n" + e);

        }
    }
    public double closeOrder( int mileEnd,Boolean isReful,int refuelingLiterNum, ContentValues order) throws Exception{
        Order o=ContentValuesToOrder(order);
        List<Order> openOrdersList=getOpenOrdersList();
        boolean b=false;
        try {
            for (Order order1:openOrdersList) {
                if (order1.getOrderNum() == o.getOrderNum()) {
                    b = true;
                }
            }
            if(!b)
                throw new Exception("Order is not open");

            o.setOpen(false);

            o.setRentalEnd(Calendar.getInstance().getTime());
            o.setMileEnd(mileEnd);
            o.setRefueling(isReful);
            o.setRefuelingLiterNum(refuelingLiterNum);

            Calendar cal1 = Calendar.getInstance();       // get calendar instance of rental start
            cal1.setTime(o.getRentalStart());
            Calendar cal2 = Calendar.getInstance();       // get calendar instance
            cal2.setTime(o.getRentalEnd());

            while(cal1.before(cal2))
            {
                days++;
                cal1.add(Calendar.DAY_OF_MONTH,1);
            }
            o.setChargeSum(days*100);

            Map<String,Object> params = new LinkedHashMap<>();
            params.put("_id",order.getAsInteger("_id"));
            params.put("clientNum",order.getAsInteger("clientNum"));
            params.put("isOpen",o.isOpen());
            params.put("carNum",order.getAsInteger("carNum"));

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // like MySQL Format
            //String dateString1 = dateFormat1.format(order.getAsString("rentalStart"));
            params.put("rentalStart",dateFormat.format(o.getRentalStart()));

            //String dateString2 = dateFormat1.format(o.getRentalEnd());
            params.put("rentalEnd",dateFormat.format(Calendar.getInstance().getTime()));

            params.put("mileStart",order.getAsInteger("mileStart"));
            params.put("mileEnd",o.getMileEnd());
            params.put("refueling" , o.isRefueling());
            params.put("refuelingLiterNum",o.getRefuelingLiterNum());
            params.put("chargeSum",o.getChargeSum());

            String results = POST(WEB_URL + "updateOrder.php", params);

            List<Car> carList=GetCarsList();
            for (Car car: carList) {
                if(car.getCarNum()==o.getCarNum())
                {
                    ContentValues c=CarToContentValues(car) ;
                    int miles=o.getMileStart()+(o.getMileEnd()-o.getMileStart());
                    updateCar(miles,c);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return o.getChargeSum();
    }

    public boolean isCloseInTime(Date ClosedOrderTime) throws Exception{ // if not o.k we will change
        //Order o= ContentValuesToOrder(order);

        Date now=new Date();
        if ((now.getTime()-ClosedOrderTime.getTime()>10000)||(now.getTime()-ClosedOrderTime.getTime()<0))
            return false;
        else
            return true;
    }

    public List<Car> GetCarsList ()throws Exception{
        List<Car> result = new ArrayList<Car>();
        try {
            String str = PHPtools.GET(WEB_URL + "getAllCars.php");
            JSONArray array = new JSONObject(str).getJSONArray("cars");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                Car car = new Car();
                car.setCarNum(jsonObject.getInt("_id"));
                car.setBranchNum(jsonObject.getInt("branch"));
                car.setCarModel(jsonObject.getInt("model"));
                car.setMile(jsonObject.getInt("mile"));
                car.setColor(Colors.valueOf(jsonObject.getString("color")));
                result.add(car);         }
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkClosedOrder() throws Exception {

        //Date now = new Date();
        List<Order> orderList=getOrdersList();
        for (Order order:orderList){
            if (isCloseInTime(order.getRentalEnd()) && !order.isOpen())
                return true;
        }
        return false;
    }

    public Car carOrder(Order order) throws Exception{

        List<Car> carList=GetCarsList();
        for (Car car : carList) {
            if (order.getCarNum() == car.getCarNum())
                return car;
        }
        return null;
    }
}

