package com.example.dana.android5778_7109_3610_03.controller;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dana.android5778_7109_3610_03.R;
import com.example.dana.android5778_7109_3610_03.model.backend.DBManagerFactory;
import com.example.dana.android5778_7109_3610_03.model.backend.DB_manager;
//import com.example.dana.android5778_7109_3610_03.model.backend.MyReceiver;
//import com.example.dana.android5778_7109_3610_03.model.backend.MyService;
import com.example.dana.android5778_7109_3610_03.model.entities.Client;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText userTxt;
    private EditText passTxt;
    private CheckBox saveCheck;
    private Button signInButton;
    Button registerButton;
    TextView clearText;

    String prefName = "savedPreferences";
    String nameVal = "user";
    String passVal = "pass";
    DB_manager manager;
    List<Client> users=new ArrayList<>();
    //  MyReceiver receiver;
    IntentFilter intent;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //service
        //    intent=new IntentFilter();
        //  receiver=new MyReceiver();//create variable in type the exist class "Receiver"
        // intent.addAction("INVITATION_SET");
        // Intent serviceIntent=new Intent(getBaseContext(), MyService.class);//create intent in type MyService
        // startService(serviceIntent);//that run an the background
        // registerReceiver(receiver,intent);//register this app as receiver of the service

        manager = DBManagerFactory.getManager();

        userTxt = (EditText) findViewById(R.id.userName);
        passTxt = (EditText) findViewById(R.id.passName);
        saveCheck=(CheckBox) findViewById(R.id.saveCheck);
        //adding the info from the shared perfances in the text views
        prefs = getSharedPreferences(prefName, MODE_PRIVATE);
        userTxt.setText(prefs.getString(nameVal,null));
        passTxt.setText(prefs.getString(passVal,null));

        signInButton = (Button) findViewById(R.id.signIn);
        registerButton = (Button) findViewById(R.id.register);
        clearText = (TextView) findViewById(R.id.clear);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(myIntent);
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (userTxt.getText().toString().isEmpty() || passTxt.getText().toString().isEmpty())
                        throw new Exception("INSERT MISSING VALUE");

                  /*
                   //translating editable input text in the edittext to string
                  */
                    final String user = String.valueOf(userTxt.getText());
                    final Integer pass = Integer.valueOf(passTxt.getText().toString());

                    //this asynctask get  the user list from the database and checks if this user exsist.
                    new AsyncTask<Void, Void, Void>() {

                        @Override
                        protected Void doInBackground(Void... voids) {
                            try {
                                users = manager.getClientsList();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute (Void voids) {
                            boolean flag = false;
                            for (Client client : users) {
                                if ((client.geteMail().equals(user)) && (client.getId() == pass))
                                    flag = true;
                            }

                            // in case that chose to save the username and password we use in shared preference
                            if (flag) {
                                if(saveCheck.isChecked()) {
                                    // in case that chose to save the username and password we use in shared preference
                                    SharedPreferences.Editor editor = prefs.edit();
                                    String user=userTxt.getText().toString();
                                    String pass=passTxt.getText().toString();

                                    if(prefs.getString(nameVal,null)!=user)
                                        editor.putString(nameVal,user);
                                    if(prefs.getString(passVal,null)!=pass)
                                        editor.putString(passVal, pass);
                                    editor.commit();
                                }
                                Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(myIntent);
                            } else {
                                Toast.makeText(LoginActivity.this, "Wrong username or password", Toast.LENGTH_LONG).show();
                            }
                        }
                    }.execute();
                } catch (Exception e) {
                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });
        //clearing the edit texts
        clearText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userTxt.setText("");
                passTxt.setText("");
            }
        });

    }
}
