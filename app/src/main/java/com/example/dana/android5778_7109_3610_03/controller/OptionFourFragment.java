package com.example.dana.android5778_7109_3610_03.controller;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dana.android5778_7109_3610_03.R;
import com.example.dana.android5778_7109_3610_03.model.backend.DBManagerFactory;
import com.example.dana.android5778_7109_3610_03.model.backend.DB_manager;
import com.example.dana.android5778_7109_3610_03.model.datasource.Tools;
import com.example.dana.android5778_7109_3610_03.model.entities.Car;
import com.example.dana.android5778_7109_3610_03.model.entities.Order;

import java.util.Date;
import java.util.List;

public class OptionFourFragment extends Fragment {

    public OptionFourFragment(){}

    View view;
    TextView carNum;
    TextView branchNum;
    TextView carModel;
    TextView startMiles;
    TextView color;
    TextView rentalStart;
    Button closeOrder;
    EditText insertId;
    EditText endMiles;
    CheckBox refCheck;
    EditText refulNumText;
    List<Order> orderList;
    List<Car> carsList;
    int idNum;
    int intStartMile;
    int intEndMile;
    int intReful;
    double sum=0;
    Car car;
    Order order1 = null;
    boolean flag = false;
    boolean flag1 = false;
    DB_manager manager = DBManagerFactory.getManager();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_option_four, container, false);

        carNum = (TextView) view.findViewById(R.id.carNumBox);
        branchNum = (TextView) view.findViewById(R.id.branchBox);
        carModel = (TextView) view.findViewById(R.id.ModelBox);
        startMiles = (TextView) view.findViewById(R.id.mileStartBox);
        rentalStart = (TextView) view.findViewById(R.id.rentalStartBox);
        color = (TextView) view.findViewById(R.id.colorBox);
        closeOrder = (Button) view.findViewById(R.id.closeOrderButton);
        endMiles = (EditText) view.findViewById(R.id.mileEndBox);
        refCheck = (CheckBox) view.findViewById(R.id.refCheck);
        refulNumText = (EditText) view.findViewById(R.id.refulNumText);
        order1 = dialogDec();

        closeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intEndMile = Integer.valueOf(endMiles.getText().toString());
                intStartMile = Integer.valueOf(startMiles.getText().toString());
                if(refCheck.isChecked())
                   intReful = Integer.valueOf(refulNumText.getText().toString());
                final ContentValues cvOrder = Tools.OrderToContentValues(order1);
                if (intEndMile <= intStartMile)
                    Toast.makeText(getContext(), "Wrong end mile value", Toast.LENGTH_LONG).show();

                else {
                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... params) {
                            try {
                                sum=manager.closeOrder(intEndMile, false, intReful, cvOrder);
                                flag1 = true;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            charSumDialog(sum);
                        }
                    }.execute();
                    if (flag1)
                        Toast.makeText(getContext(), "Order closed succesfuly", Toast.LENGTH_LONG).show();

                }
            }
        });

        return view;
    }

    protected Order dialogDec() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Insert your ID");
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.insert_id_dialog, null);

        insertId = (EditText) view.findViewById(R.id.insert_id);

        AlertDialog.Builder ok = builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            orderList = manager.getOpenOrdersList();
                            carsList = manager.GetCarsList();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        idNum = Integer.valueOf(insertId.getText().toString());

                        for (final Order order : orderList) {
                            if (order.getClientNum() == idNum) {
                                order1 = order;
                            }
                        }
                        if (order1 != null) {
                            for (Car car : carsList) {
                                if (order1.getCarNum() == car.getCarNum()) {
                                    carNum.setText(String.valueOf(car.getCarNum()));
                                    branchNum.setText(String.valueOf(car.getBranchNum()));
                                    carModel.setText(String.valueOf(car.getCarModel()));
                                    startMiles.setText(Integer.valueOf(order1.getMileStart()).toString());
                                    color.setText(car.getColor().toString());
                                    rentalStart.setText(order1.getRentalStart().toString());
                                }
                            }
                        } else
                            flag = true;
                    }
                }.execute();
                if (flag) {
                    closeOrder.setEnabled(false);
                    Toast.makeText(getContext(), "You don't have any open order in the system", Toast.LENGTH_LONG).show();
                    builder.show();
                }
            }
        });
        builder.setView(view);
        builder.create().show();
        return order1;
    }

    private void charSumDialog(double sum) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Your payment is : ");
        builder.setMessage(String.valueOf(sum));
        //builder.setView();
        builder.create().show();
    }


}


