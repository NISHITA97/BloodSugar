package com.example.android.bloodsugar1;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Shiv Kumar Aggarwal on 17-06-2017.
 */

public class Graph extends Fragment {
    GraphView graph1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.graph,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Graph");
        graph1 = (GraphView) getActivity().findViewById(R.id.graph1);
        //String s=getArguments().getString("disp");
        DataPoint dp1[];

        Log.e("in main"," graph");


        int i=0;
        ArrayList<Integer> mor=getArguments().getIntegerArrayList("mor");
        ArrayList<Integer> eve=getArguments().getIntegerArrayList("eve");
        ArrayList<String> dd=getArguments().getStringArrayList("date");
        ArrayList<String> dde=getArguments().getStringArrayList("dateeve");
        dp1=new DataPoint[mor.size()];
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date result=new Date();
        Date r0=new Date();
        Date rf=new Date();
        while(i<mor.size())
        {
            try {
                r0=df.parse(dd.get(0));
                rf=df.parse(dd.get(3));
                result =  df.parse(dd.get(i));
            } catch (ParseException e) {
                Log.e("date exception",dd.get(i));
                e.printStackTrace();
            }
            dp1[i]=new DataPoint(result,mor.get(i));
            //dp2[i]=new DataPoint(i+1,eve.get(i));
            i++;
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dp1);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(6);
        graph1.addSeries(series);
        graph1.getViewport().setScrollable(true);
        graph1.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
        graph1.getViewport().setXAxisBoundsManual(true);
        graph1.getViewport().setMinX(r0.getTime());
        graph1.getViewport().setMaxX(result.getTime());
        graph1.getViewport().setScalable(false);
        graph1.getViewport().setScrollable(true);
        //graph1.getGridLabelRenderer().setVerticalAxisTitle("Glucose level");
        graph1.getGridLabelRenderer().setHorizontalAxisTitle("Date");
        graph1.getGridLabelRenderer().setHorizontalAxisTitleTextSize(60);
        //graph1.getGridLabelRenderer().setVerticalAxisTitleTextSize(50);

        graph1.getGridLabelRenderer().setNumHorizontalLabels(4); // only 4 because of the space
    }
}
