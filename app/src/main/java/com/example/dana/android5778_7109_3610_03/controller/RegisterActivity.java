package com.example.dana.android5778_7109_3610_03.controller;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dana.android5778_7109_3610_03.R;
import com.example.dana.android5778_7109_3610_03.model.backend.DBManagerFactory;
import com.example.dana.android5778_7109_3610_03.model.backend.DB_manager;
import com.example.dana.android5778_7109_3610_03.model.datasource.Tools;
import com.example.dana.android5778_7109_3610_03.model.entities.Client;

public class RegisterActivity extends AppCompatActivity {
    //private Button addUserButton;
    ContentValues clientCV;
    Boolean re;
    DB_manager manager= DBManagerFactory.getManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
       Button addUserButton= findViewById(R.id.add_User_Button);
        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    final EditText fnET = (EditText) findViewById(R.id.fnBox);
                    final EditText lnET = (EditText) findViewById(R.id.lnBox);
                    final EditText idET = (EditText) findViewById(R.id.idBox);
                    final EditText phoneET = (EditText) findViewById(R.id.phoneBox);
                    final EditText emailET = (EditText) findViewById(R.id.emailBox);
                    final EditText cardET = (EditText) findViewById(R.id.cardBox);

                    if (fnET.getText().toString().isEmpty() || lnET.getText().toString().isEmpty() || idET.getText().toString().isEmpty() || phoneET.getText().toString().isEmpty() ||
                            emailET.getText().toString().isEmpty() || cardET.getText().toString().isEmpty())
                        throw new Exception("INSERT MISSING VALUE");
                    if (idET.getText().length() != 9)
                        throw new Exception("WRONG ID FORMAT");
                    boolean b = manager.isExist(Integer.parseInt(idET.getText().toString()));
                    if (b)
                        Toast.makeText(RegisterActivity.this, "Client is already exists", Toast.LENGTH_LONG).show();
                    else {
                        final Client client = new Client(lnET.getText().toString(), fnET.getText().toString(), Integer.parseInt(idET.getText().toString()), Integer.parseInt(phoneET.getText().toString()), emailET.getText().toString(), Integer.parseInt(cardET.getText().toString()));
                        new AsyncTask<Client, Void, Void>() {
                            @Override
                            protected Void doInBackground(Client... clients) {
                                try {
                                    clientCV = Tools.ClientToContentValues(clients[0]);
                                    if (manager.addClient(clientCV)) re = true;
                                    else re = false;
                                    // Log.d(users[0].getFirstName(),result);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Void aVoid) {
                                super.onPostExecute(aVoid);
                                if (re)
                                    Toast.makeText(RegisterActivity.this, "Added succesfuly", Toast.LENGTH_LONG).show();
                            }
                        }.execute(client);
                    }
                } catch(Exception e){
                    Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}
