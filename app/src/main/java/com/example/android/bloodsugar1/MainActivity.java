package com.example.android.bloodsugar1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;
import static android.R.attr.max;
import static android.R.id.list;
import static com.example.android.bloodsugar1.R.layout.graph;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseMorning databaseMorning;
    DatabaseEvening databaseEvening;
    Bundle bundle;


    Fragment fragment=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        databaseMorning=new DatabaseMorning(this);
        databaseEvening=new DatabaseEvening(this);
        bundle=new Bundle();
        fragment=new Home();
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_main,fragment);
        ft.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.insert_data){
            fragment=new InsertData();
            // Handle the camera action
        }  else if (id == R.id.nav_display_database || id==R.id.nav_graph || id==R.id.nav_grapheve || id== R.id.nav_analysis) {
            DisplayDatabase dd=new DisplayDatabase();
            Log.e("in main", "id display1");
            String sm = "";
            String se="";
            List<Data> dm,de;
            dm = databaseMorning.readData();
            de=databaseEvening.readData();
            Log.e("got","d1");
            int maxmor=0,minmor=0,maxeve=0,mineve=0;
            float avgmor=0,avgeve=0;
            ArrayList<Integer> morlist = new ArrayList<>();
            ArrayList<Integer> evelist = new ArrayList<>();
            ArrayList<String> datelistm=new ArrayList<>();
            ArrayList<String> dateliste=new ArrayList<>();
            boolean start=false;
            for (Data t : dm) {
                if(!start)
                {
                    start=true;
                    maxmor=t.level;
                    minmor=t.level;
                }
                morlist.add(t.level);
                avgmor+=t.level;
                if(t.level>maxmor)
                    maxmor = t.level;
                if(t.level<minmor)
                    minmor = t.level;
                datelistm.add(t.date);
                sm = sm + t.level +"    "+t.date+"\n";
                Log.e("sm=",sm);
            }
            start=false;

            avgmor=avgmor/morlist.size();
            for (Data t : de) {
                if(!start)
                {
                    start=true;
                    maxeve=t.level;
                    mineve=t.level;
                }
                evelist.add(t.level);
                dateliste.add(t.date);
                avgeve+=t.level;
                if(t.level>maxeve)
                    maxeve = t.level;
                if(t.level<mineve)
                    mineve = t.level;

                se = se + t.level +"     "+t.date+"\n";
                Log.e("sm=",sm);
            }
            avgeve=avgeve/evelist.size();
            bundle=new Bundle();
            bundle.putIntegerArrayList("mor", morlist);
            bundle.putIntegerArrayList("eve", evelist);
            bundle.putStringArrayList("date",datelistm);
            bundle.putStringArrayList("dateeve",dateliste);
            Log.e("Display1 int array", "inside bundle");
            bundle.putString("displayDatabasemor", sm);
            bundle.putString("displayDatabaseeve",se);
            bundle.putInt("maxmor",maxmor);
            bundle.putInt("minmor",minmor);
            bundle.putFloat("avgmor",avgmor);
            bundle.putInt("maxeve",maxeve);
            bundle.putInt("mineve",mineve);
            bundle.putFloat("avgeve",avgeve);
            dd.setArguments(bundle);

            fragment=dd;
            Analysis analysis=new Analysis();
            analysis.setArguments(bundle);
            Graph graph=new Graph();
            GraphEve grapheve=new GraphEve();
            graph.setArguments(bundle);
            grapheve.setArguments(bundle);
            if(id==R.id.nav_graph)
                fragment=graph;
            else if(id==R.id.nav_grapheve)
                fragment=grapheve;
            else if(id==R.id.nav_analysis)
                fragment=analysis;
        }
         if(fragment!=null)
    {
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_main,fragment);
        ft.commit();
    }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
