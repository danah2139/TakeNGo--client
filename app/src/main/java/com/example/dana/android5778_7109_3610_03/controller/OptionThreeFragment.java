package com.example.dana.android5778_7109_3610_03.controller;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.dana.android5778_7109_3610_03.R;
import com.example.dana.android5778_7109_3610_03.model.backend.DBManagerFactory;
import com.example.dana.android5778_7109_3610_03.model.backend.DB_manager;
import com.example.dana.android5778_7109_3610_03.model.entities.Branch;
import com.example.dana.android5778_7109_3610_03.model.entities.Car;
import com.example.dana.android5778_7109_3610_03.model.entities.CarModel;

import java.util.ArrayList;

public class OptionThreeFragment extends Fragment {

    ArrayList<Car> carsList = new ArrayList<>();
    ArrayList<CarModel> modelList = new ArrayList<>();
    ArrayList<Branch> branchList = new ArrayList<>();
    View view;
    AvailCarsAdapter adapter;
    SearchView searchView;


    public OptionThreeFragment() {
        // Required empty public constructor
    }
    DB_manager manager = DBManagerFactory.getManager();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_option_three, container, false);
        searchView=(SearchView) view.findViewById(R.id.searchView1);

        try {
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        carsList = (ArrayList<Car>) manager.getAllAvailableCarsList();
                        modelList = (ArrayList<CarModel>) manager.getCarModelsList();
                        branchList = (ArrayList<Branch>) manager.getBranchesList();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    adapter = new AvailCarsAdapter(getContext(),carsList,modelList,branchList);

                    ExpandableListView expandableListView= (ExpandableListView) view.findViewById(R.id.CarsList);
                    expandableListView.setAdapter(adapter);

                    //searchView
                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            adapter.getFilter().filter(newText);
                            adapter.notifyDataSetChanged();
                            return false;
                        }
                    });
                }
            }.execute();

        } catch (Exception e) {
            throw e;
        }
        return view;
    }
}
