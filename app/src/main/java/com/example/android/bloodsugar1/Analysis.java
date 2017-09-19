package com.example.android.bloodsugar1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Shiv Kumar Aggarwal on 22-07-2017.
 */

public class Analysis extends Fragment {

    TextView maxsugarmor,maxsugareve, minsugarmor, minsugareve, avgsugarmor,avgsugareve;
    int masm,mism;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.layout_analysis,container,false);
        Log.e("maxmor=",String.valueOf(getArguments().getInt("maxmor")));
        maxsugarmor=(TextView) v.findViewById(R.id.maxsugarmor);

        maxsugareve=(TextView)v.findViewById(R.id.maxsugareve);

        minsugarmor=(TextView)v.findViewById(R.id.minsugarmor);

        minsugareve=(TextView)v.findViewById(R.id.minsugareve);
        avgsugarmor=(TextView)v.findViewById(R.id.avgsugarmor);
        avgsugareve=(TextView)v.findViewById(R.id.avgsugareve);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Analysis");
        masm=getArguments().getInt("maxmor");
        Log.e("maxmor=",String.valueOf(masm));
        maxsugarmor.setText(String.valueOf(getArguments().getInt("maxmor")));
        Log.e("maxeve=",String.valueOf(getArguments().getInt("maxeve")));
        maxsugareve.setText(String.valueOf(getArguments().getInt("maxeve")));
        minsugarmor.setText(String.valueOf(getArguments().getInt("minmor")));
        minsugareve.setText(String.valueOf(getArguments().getInt("mineve")));
        avgsugarmor.setText(String.valueOf( getArguments().getFloat("avgmor")));
        avgsugareve.setText(String.valueOf( getArguments().getFloat("avgeve")));
    }
}

