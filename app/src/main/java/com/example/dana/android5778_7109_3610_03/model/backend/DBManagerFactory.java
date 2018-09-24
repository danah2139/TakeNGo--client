package com.example.dana.android5778_7109_3610_03.model.backend;

import com.example.dana.android5778_7109_3610_03.model.datasource.DatabaseMySQL;

/**
 * Created by DELL on 25 דצמבר 2017.
 */

public class DBManagerFactory {
    static DB_manager manager=null;

    public static DB_manager getManager(){
        if (manager== null)
            manager =new DatabaseMySQL();
        return manager;
    }

}
