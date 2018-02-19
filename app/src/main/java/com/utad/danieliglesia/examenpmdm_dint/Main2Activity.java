package com.utad.danieliglesia.examenpmdm_dint;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.utad.danieliglesia.milib.FragmentAmarillo;
import com.utad.danieliglesia.milib.FragmentRojo;
import com.utad.danieliglesia.milib.FragmentVerde;
import com.utad.danieliglesia.milib.SettingsFragment;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView imageView;
    FragmentRojo fragmentRojo;
    FragmentAmarillo fragmentAmarillo;
    FragmentVerde fragmentVerde;
    SettingsFragment settingsFragment;
    //terminado
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentRojo = (FragmentRojo) getSupportFragmentManager().findFragmentById(R.id.fragmentRojo);
        fragmentAmarillo = (FragmentAmarillo) getSupportFragmentManager().findFragmentById(R.id.fragmentAmarillo);
        fragmentVerde = (FragmentVerde) getSupportFragmentManager().findFragmentById(R.id.fragmentVerde);
        settingsFragment = (SettingsFragment) getSupportFragmentManager().findFragmentById(R.id.settingsFragment);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
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
    public void botonClicked(View v){
        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        FragmentTransaction transition = this.getSupportFragmentManager().beginTransaction();
        transition.hide(fragmentRojo);
        transition.hide(fragmentAmarillo);
        transition.hide(fragmentVerde);
        transition.show(settingsFragment);
        transition.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        SettingsFragment miFragmentSettings = null;
        boolean fragmentSelecionadoS = false;

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            miFragmentSettings = new SettingsFragment();
            fragmentSelecionadoS = true;
            if (fragmentSelecionadoS == true) {
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main2, miFragmentSettings).commit();

            }

            //return super.onOptionsItemSelected(item);
            return true;

        }
        return fragmentSelecionadoS;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentRojo miFragmentRojo=null;
        FragmentVerde miFragmentVerde=null;
        FragmentAmarillo miFragmentAmarillo=null;

        boolean fragmentSelecionadoR=false;
        boolean fragmentSelecionadoA=false;
        boolean fragmentSelecionadoV=false;

        if (id == R.id.nav_camera) {
            miFragmentRojo=new FragmentRojo();
            fragmentSelecionadoR=true;
            if(fragmentSelecionadoR==true){
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main2,miFragmentRojo).commit();

            }
        } else if (id == R.id.nav_gallery) {
            miFragmentAmarillo=new FragmentAmarillo();
            fragmentSelecionadoA=true;
            if(fragmentSelecionadoA==true){
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main2,miFragmentAmarillo).commit();

            }
        } else if (id == R.id.nav_slideshow) {
            miFragmentVerde=new FragmentVerde();
            fragmentSelecionadoV=true;
            if(fragmentSelecionadoV==true){
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main2, miFragmentVerde).commit();

            }
        }else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}