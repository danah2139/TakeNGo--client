package com.example.dana.android5778_7109_3610_03.controller;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dana.android5778_7109_3610_03.R;
import com.example.dana.android5778_7109_3610_03.model.backend.DBManagerFactory;
import com.example.dana.android5778_7109_3610_03.model.backend.DB_manager;
import com.example.dana.android5778_7109_3610_03.model.entities.Branch;
import com.example.dana.android5778_7109_3610_03.model.entities.Car;
import com.example.dana.android5778_7109_3610_03.model.entities.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.dana.android5778_7109_3610_03.model.datasource.Tools.BranchToContentValues;
import static com.example.dana.android5778_7109_3610_03.model.datasource.Tools.OrderToContentValues;

/**
 * Created by dana on 23/02/2018.
 */

public class CustomAdapter extends BaseExpandableListAdapter implements Filterable {
    Context context;
    List<Branch> branchList;//the whole list
    LayoutInflater layoutInflater;
    List<Car> spinnerArray;
    ArrayAdapter<Car> adapter;
    BranchFilter dataFilter;
    List<Branch> changeBranchList;//list that changes with filter
    Car car;//the car which selected in the spinner for doing order
    DB_manager manager = DBManagerFactory.getManager();



    public CustomAdapter(Context context, List<Branch> branchList) {
        this.context = context;
        changeBranchList  = this.branchList = branchList;
        layoutInflater = LayoutInflater.from(context);
        dataFilter=new BranchFilter();
    }

    @Override
    public int getGroupCount() {
        return changeBranchList.size();
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
    public View getGroupView( int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)  {

        View view = layoutInflater.inflate(R.layout.text_view_layout,parent,false);
        final Branch branch = changeBranchList.get(groupPosition);
        TextView textView = (TextView)view.findViewById(R.id.textView);
        textView.setText("Num Branch: "+String.valueOf(branch.getBranchNum()));
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        View view = layoutInflater.inflate(R.layout.child_branch_item,parent,false);
        final Button addOrder=(Button)view.findViewById(R.id.add_order);
        final Spinner carSpinner =(Spinner)view.findViewById(R.id.carSpinner);
        final TextView textView = (TextView)view.findViewById(R.id.child_item);
        final Branch branch = changeBranchList.get(groupPosition);
        final Button mapLink= (Button) view.findViewById(R.id.button_link);

        mapLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = branch.getCity()+","+branch.getStreet()+","+branch.getNumber();
                String url = "http://maps.google.com/maps?daddr=" + address;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(intent);
            }
        });

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    spinnerArray = manager.getAvailableCarsBranchList(BranchToContentValues(branch));
                    adapter= new ArrayAdapter<Car>(context, android.R.layout.simple_spinner_dropdown_item, spinnerArray);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void voids) {
                if(spinnerArray.isEmpty())
                    addOrder.setEnabled(false);
                carSpinner.setAdapter(adapter);
            }
        }.execute();

        carSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                car = (Car)parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                addOrder.setEnabled(false);
            }
        });

        String address = "Address:  "+branch.getCity()+" - "+branch.getStreet()+" , "+branch.getNumber()+"   ---->";
        textView.setText(address);

        addOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Insert your ID");
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View view = layoutInflater.inflate(R.layout.insert_id_dialog,null);
                final EditText insert_id=(EditText)view.findViewById(R.id.insert_id);
                builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            int numCustomer = Integer.valueOf(insert_id.getText().toString());
                            Date date=new Date();
                            final Order order = new Order(numCustomer, true, car.getCarNum(), date,car.getMile(),0);//create new order

                            final ContentValues o = OrderToContentValues(order);
                            new AsyncTask<Void, Void, Void>() {

                                @Override
                                protected Void doInBackground(Void... params) {
                                    try {
                                           manager.addOrder(o);
                                    } catch (Exception e){
                                        e.printStackTrace();
                                    }
                                    return null;
                                }

                            }.execute();
                        }catch (Exception e){
                            Toast.makeText(context,"Please insert your Id!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                builder.setView(view);
                builder.create().show();
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    /**
     *this inter class  "BranchFilter"  defines for the filter (search xml element)
     * and implement two override func which belong to Filter.
     */

    public class BranchFilter extends android.widget.Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if(constraint == null || constraint.length() == 0){
                results.values = branchList;
                results.count = branchList.size();
            }
            else {
                List<Branch> nBranches = new ArrayList<>();
                for(Branch b : branchList){
                    if(b.getCity().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        nBranches.add(b);
                }
                results.values = nBranches;
                results.count = nBranches.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if(results.count == 0){
                notifyDataSetInvalidated();
            }
            else {
                changeBranchList = (List<Branch>) results.values;
                notifyDataSetChanged();
            }
        }
    }

    @Override
    public Filter getFilter() {
        if(dataFilter == null){
            dataFilter = new BranchFilter();
        }
        return dataFilter;
    }

}
