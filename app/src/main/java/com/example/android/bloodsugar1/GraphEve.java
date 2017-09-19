package com.example.android.bloodsugar1;

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

import static com.example.android.bloodsugar1.R.id.graph1;
import static com.example.android.bloodsugar1.R.layout.graph;

/**
 * Created by Shiv Kumar Aggarwal on 22-07-2017.
 */

public class GraphEve extends Fragment {
    GraphView graph2;
    DataPoint dp2[];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.graph_eve,container,false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Graph");
        graph2 = (GraphView) getActivity().findViewById(R.id.graph2);
        //String s=getArguments().getString("disp");


        Log.e("in main"," grapheve");


        int i=0;
        ArrayList<Integer> eve=getArguments().getIntegerArrayList("eve");

        ArrayList<String> dde=getArguments().getStringArrayList("dateeve");

        dp2=new DataPoint[eve.size()];
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date result=new Date();
        Date r0=new Date();


        Log.e("Graph2","started");
        i=0;
        while(i<eve.size())
        {
            try {
                r0=df.parse(dde.get(0));
                result =  df.parse(dde.get(i));
                Log.e("result=",result.toString());
            } catch (ParseException e) {
                Log.e("date exception",dde.get(i));
                e.printStackTrace();
            }
            dp2[i]=new DataPoint(result,eve.get(i));
            i++;
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dp2);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(6);
        graph2.addSeries(series);
        graph2.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));

        graph2.getViewport().setXAxisBoundsManual(true);
        graph2.getViewport().setMinX(r0.getTime());
        graph2.getViewport().setMaxX(result.getTime());
        graph2.getViewport().setScrollable(true);
        graph2.canScrollHorizontally(1);
        //graph2.getGridLabelRenderer().setVerticalAxisTitle("Glucose level");
        graph2.getGridLabelRenderer().setHorizontalAxisTitle("Date");
        graph2.getGridLabelRenderer().setHorizontalAxisTitleTextSize(60);
        //graph2.getGridLabelRenderer().setVerticalAxisTitleTextSize(50);


        //graph1.getGridLabelRenderer().setHumanRounding(false);
        Log.e("linegraphseries","added declared");
        Log.e("Arg","assigned");
    }
}
