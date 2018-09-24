package com.example.dana.android5778_7109_3610_03.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.dana.android5778_7109_3610_03.R;
import com.example.dana.android5778_7109_3610_03.model.backend.DBManagerFactory;
import com.example.dana.android5778_7109_3610_03.model.backend.DB_manager;
import com.example.dana.android5778_7109_3610_03.model.entities.Branch;
import com.example.dana.android5778_7109_3610_03.model.entities.Car;
import com.example.dana.android5778_7109_3610_03.model.entities.CarModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dana on 27/02/2018.
 */

class AvailCarsAdapter extends BaseExpandableListAdapter implements Filterable {

    DB_manager manager;
    Context context;
    LayoutInflater inflater;
    Branch branch;
    ArrayList<Car> carsList = new ArrayList<>();
    ArrayList<CarModel> modelList = new ArrayList<>();
    ArrayList<Branch> branchList = new ArrayList<>();

    //Ctor
    public AvailCarsAdapter(final Context context,ArrayList<Car> carsList,ArrayList<CarModel>modelList ,ArrayList<Branch>branchList) {
        manager = DBManagerFactory.getManager();
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.carsList=carsList;
        this.branchList=branchList;
        this.modelList=modelList;
    }

    @Override
    public int getGroupCount() {
        return carsList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup
            parent) {
        View carsListItem = inflater.inflate(R.layout.min_car, parent, false);
        Car car = carsList.get(groupPosition);

        TextView model = (TextView) carsListItem.findViewById(R.id.modelTextView);
        model.setText(String.valueOf(car.getCarModel()));

        TextView branch = (TextView) carsListItem.findViewById(R.id.brnachTextView);
        branch.setText(String.valueOf(car.getBranchNum()));

        return carsListItem;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View extendCarView = (View) inflater.inflate(R.layout.max_car, parent, false);
        final Car car = carsList.get(groupPosition);

        TextView carNum = (TextView) extendCarView.findViewById(R.id.carNumTextView);
        carNum.setText(String.valueOf("Car " + car.getCarNum() + " details: "));

        TextView modelTxt = (TextView) extendCarView.findViewById(R.id.model1TextView);
        modelTxt.setText(String.valueOf("Model:" + car.getCarModel() + " "));

        TextView colorTxt = (TextView) extendCarView.findViewById(R.id.colorTextView);
        colorTxt.setText(String.valueOf(car.getColor() + " "));

        TextView mileTxt = (TextView) extendCarView.findViewById(R.id.mile1TextView);
        mileTxt.setText(String.valueOf(car.getMile()));

        //finding the branch:
        for (Branch b : branchList) {
            if (b.getBranchNum() == car.getBranchNum())
                branch = b;
        }
//        Log.i("info for me:",String.valueOf(branchList.isEmpty()));
//        Log.i("info for me:",String.valueOf(branch));
        //branch details:
        TextView branchNumTxt = (TextView) extendCarView.findViewById(R.id.brnach1TextView);
        branchNumTxt.setText(String.valueOf("Car's branch det: " + branch.getBranchNum()));

        TextView branchAddTxt = (TextView) extendCarView.findViewById(R.id.addressTextView);
        String address = "Address:  "+branch.getCity()+" - "+branch.getStreet()+" , "+branch.getNumber();
        branchAddTxt.setText(address);

        TextView branchParkTxt = (TextView) extendCarView.findViewById(R.id.parkingTextView);
        branchParkTxt.setText(String.valueOf(branch.getParkingNum()));

        return extendCarView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            private Boolean numOnly(CharSequence cs) {
                for (int i = 0; i < cs.length(); i++)
                    if (!(cs.charAt(i) >= 0 && cs.charAt(i) <= 9))//if not between0-9
                        return false;
                return true;
            }

            private Boolean letterOnly(CharSequence cs) {
                for (int i = 0; i < cs.length(); i++)
                    try {
                        Integer.parseInt(String.valueOf(cs.charAt(i)));
                        return false;
                    } catch (Exception e) {
                    }
                return true;
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint == null) {
                    filterResults.values = carsList;
                    filterResults.count = carsList.size();
                } else {
                    List tempList = new ArrayList();
                    if (letterOnly(constraint)) {
                        //runnig over the cars and finding the model num
                        for (Car c : carsList) {
                            String modelName = "";
                            //finding the name of the model (won't be in numbers only)
                            for (CarModel cm : modelList) {
                                if (c.getCarModel() == cm.getModelCode())
                                    modelName = cm.getModelName();
                            }
                            //checking if the model name is containing our constraint
                            for (int i = 0; i < constraint.length(); i++)
                                if (modelName.toLowerCase().contains(constraint))
                                    tempList.add(c);
                        }
                    } else if (numOnly(constraint)) {
                        //runnig over the cars and finding the model num
                        for (Car c : carsList) {
                            //checking if the model num is containing our constraint, converted to string so we could use "contains" action
                            for (int i = 0; i < constraint.length(); i++)
                                if (String.valueOf(c.getCarModel()).contains(constraint))
                                    tempList.add(c);
                        }
                    }
                    filterResults.values = tempList;
                    filterResults.count = tempList.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results.count == 0)
                    notifyDataSetInvalidated();
                else {
                    carsList = (ArrayList<Car>) results.values;
                    notifyDataSetChanged();
                }
            }
        };
    }

}
