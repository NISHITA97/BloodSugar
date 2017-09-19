package com.example.android.bloodsugar1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Shiv Kumar Aggarwal on 17-06-2017.
 */

public class DisplayDatabase extends Fragment {


    TextView dispmor,dispeve;
    String sm,se=null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sm=null;
        View v=inflater.inflate(R.layout.display,container,false);
        dispmor=(TextView) v.findViewById(R.id.dispmor);
        dispeve=(TextView) v.findViewById(R.id.dispeve);
        sm=getArguments().getString("displayDatabasemor");
        se=getArguments().getString("displayDatabaseeve");
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Daily Sugar Level");
        ((TextView) getActivity().findViewById(R.id.dispmor)).setText(sm);
        dispeve.setText(se);
    }
}
