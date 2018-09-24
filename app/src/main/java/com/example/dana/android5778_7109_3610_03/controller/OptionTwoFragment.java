package com.example.dana.android5778_7109_3610_03.controller;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dana.android5778_7109_3610_03.R;
import com.example.dana.android5778_7109_3610_03.model.backend.DBManagerFactory;
import com.example.dana.android5778_7109_3610_03.model.backend.DB_manager;
import com.example.dana.android5778_7109_3610_03.model.entities.Branch;
import com.example.dana.android5778_7109_3610_03.model.entities.Car;
import com.example.dana.android5778_7109_3610_03.model.entities.CarModel;

import java.util.ArrayList;
import java.util.List;


public class OptionTwoFragment extends Fragment implements SearchView.OnQueryTextListener  {

    View view;
    ExpandableListView branchListView;
    ListView carListView;
    TextView branchTextView;
    CustomAdapter adapter;
    Button mapButton;
    SearchView branchSearchView;
    DB_manager manager = DBManagerFactory.getManager();


    public OptionTwoFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_option_two, container, false);
        //initialization
        branchListView = (ExpandableListView)view.findViewById(R.id.branchesListView);
        mapButton = (Button)view.findViewById(R.id.button_link);
        branchSearchView=(SearchView)view.findViewById(R.id.searchView);

        new AsyncTask<Object, Object, List<Branch>>() {
            @Override
            protected List<Branch> doInBackground(Object... params) {
                try {
                    return manager.getBranchesList();
                }catch (Exception e) {
                    return null;
                }
            }
            protected void onPostExecute(List<Branch> result) {
                super.onPostExecute(result);
                if (result !=null)//message for the user
                    adapter =  new CustomAdapter(getContext(),result);;
                branchListView.setAdapter(adapter);
            }

        }.execute();

        branchSearchView.setOnQueryTextListener(this) ;

        return view;
    }



    /**
     * when the user write or delete to the search view we operate the filter
     * @param newText
     * @return
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.getFilter().filter(newText);
        adapter.notifyDataSetChanged();
        return false;
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        adapter.getFilter().filter(query);
        adapter.notifyDataSetChanged();
        return false;
    }

}
