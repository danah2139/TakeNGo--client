package com.example.dana.android5778_7109_3610_03.controller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dana.android5778_7109_3610_03.R;


public class OptionOneFragment extends Fragment {

    public OptionOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_option_one, container, false);


       ImageButton imageButton=(ImageButton) view.findViewById(R.id.callButton);
       ImageButton imageButton2=(ImageButton) view.findViewById(R.id.emailButton);
        TextView textView = (TextView) view.findViewById(R.id.url);


        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"danah2139@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Take and Go");
                try {
                    startActivity(Intent.createChooser(intent, "Send via email"));
                }
                catch (Exception e)
                {
                    Toast.makeText(getContext(),"There are no email clients installed",Toast.LENGTH_LONG).show();
                }
            }
        });

        //connect the button to the dial app
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse("tel:0509912802"));
                startActivity(intent1);
            }
        });

        //connect the button to our website
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getContext(),WebViewActivity.class);
                startActivity(intent2);
            }
        });

        return view;
    }


}
