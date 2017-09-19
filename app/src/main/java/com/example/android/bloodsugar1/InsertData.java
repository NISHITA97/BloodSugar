package com.example.android.bloodsugar1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Shiv Kumar Aggarwal on 17-06-2017.
 */

public class InsertData extends Fragment{

    EditText bsm,bse;
    int m;
    String datedatam;
    DatabaseMorning dbm;
    DatabaseEvening dbe;
    CalendarView cv;
    java.util.Date utilDate=new java.util.Date();
    java.sql.Date d;
    Button submitm,submite;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.menu1,container, false);
        submitm= (Button) v.findViewById(R.id.submitm);
        submite= (Button) v.findViewById(R.id.submite);
        bsm=(EditText) v.findViewById(R.id.bsm);
        bse=(EditText) v.findViewById(R.id.bse);

        java.sql.Date datesqltemp=new Date(utilDate.getTime());
        datedatam=datesqltemp.toString();
        Log.e("datedatam temp=",datedatam);

        return v;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Insert Blood Sugar Level");
        dbm=new DatabaseMorning(this.getContext());
        dbe=new DatabaseEvening(this.getContext());
        final SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");

        cv=(CalendarView)getActivity().findViewById(R.id.calview);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

                                       @Override
                                       public void onSelectedDayChange(CalendarView arg0, int year, int month,
                                                                       int date) {
                                           java.util.Date dateutil = new java.util.Date();
                                           if(month==12)
                                               month=1;
                                           else
                                               month=month+1;
                                           String startDate = date + "-" + month + "-" + year;
                                           try {
                                               dateutil = sdf1.parse(startDate);
                                           } catch (Exception e) {
                                               Log.e("date error==",e.toString());
                                           }
                                           Date datesql = new Date(dateutil.getTime());
                                           d = datesql;
                                           datedatam=d.toString();
                                           Log.e("datedatam by cal=",datedatam);
                                           Toast.makeText(getContext(), date + "/" + month + "/" + year + "\n" + datedatam, Toast.LENGTH_SHORT).show();
                                       }
                                   });

        Log.e("m="," "+m);

        submite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("inside function","submitm");
                //String lvl=bsm.getText().toString();
                String e=bse.getText().toString();
                Log.e("e=",e);
                int e1=Integer.parseInt(e);
                Log.e("e1=",String.valueOf(e1));
                Data a=new Data(e1,datedatam);
                Log.e("data a","initialised");
                dbe.addData(a);
                Log.e("values added","to database");
                Log.e("ad submit","success");
            }
        });

        submitm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("inside function","submitm");
                //String lvl=bsm.getText().toString();
                String e=bsm.getText().toString();
                Log.e("e=",e);
                int e1=Integer.parseInt(e);
                Log.e("e1=",String.valueOf(e1));
                Data a=new Data(e1,datedatam);
                Log.e("data a","initialised");
                dbm.addData(a);
                Log.e("values added","to database");
                Log.e("ad submit","success");
            }
        });
    }

}
