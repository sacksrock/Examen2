package com.utad.danieliglesia.examenpmdm_dint;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;
import com.utad.danieliglesia.examenpmdm_dint.Adapters.ListaCochesAdapter;
import com.utad.danieliglesia.examenpmdm_dint.FBObjects.FBCoche;
import com.utad.danieliglesia.milib.DetalleFragment;
import com.utad.danieliglesia.milib.ListaFragment;

import java.util.ArrayList;

/**
 * Created by daniel.iglesia on 19/02/2018.
 */

public class SecondActivity extends AppCompatActivity {
    ListaFragment ListaFragmentCoches;
    SupportMapFragment mapFragment;
    ListaCochesAdapter listaCochesAdapter;
    DetalleFragment mapDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        SecondActivityEvents events = new SecondActivityEvents(this);
        DataHolder.instance.fireBaseAdmin.setListener(events);


        ListaFragmentCoches = (ListaFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentListCoches);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMapa);
        mapFragment.getMapAsync(events);
        mapDetailFragment = (DetalleFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMapaDetalles);


        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        transition.hide(ListaFragmentCoches);
        transition.hide(mapDetailFragment);
        transition.show(mapFragment);
        transition.commit();
    }

        class SecondActivityEvents implements FireBaseAdminListener, OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
        SecondActivity secondActivity;
        ListaCochesAdapter listaCochesAdapter;
        ArrayList<FBCoche> noticias;
        GoogleMap mMap;

        public SecondActivityEvents(SecondActivity secondActivity) {
            this.secondActivity = secondActivity;
        }

        @Override
        public void FireBaseAdmin_RamaDescargada(String rama, DataSnapshot dataSnapshot) {
            if(rama.equals("Noticias")){
                quitarViejosPines();
                GenericTypeIndicator<ArrayList<FBCoche>> indicator = new GenericTypeIndicator<ArrayList<FBCoche>>(){};
                noticias = dataSnapshot.getValue(indicator);

                secondActivity.listaCochesAdapter = new ListaCochesAdapter(noticias,secondActivity);
                secondActivity.ListaFragmentCoches.recyclerView.setAdapter(secondActivity.listaCochesAdapter);
                //secondActivity.listaCochesAdapter.setListener(this);
                agregarPines();
            }
        }
        public void quitarViejosPines(){
            if (noticias != null) {
                for (int i = 0; i < noticias.size(); i++) {
                    FBCoche cocheTemp = noticias.get(i);
                    if (cocheTemp.getMarker() != null) {
                        cocheTemp.getMarker().remove();
                    }
                }
            }
        }
        public void agregarPines(){
            for (int i=0; i<noticias.size();i++){
                FBCoche cocheTemp=noticias.get(i);
                LatLng cochePos = new LatLng(cocheTemp.lat, cocheTemp.lon);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(cochePos);
                markerOptions.title(cocheTemp.titulo);

                if(mMap!=null) {
                    Marker marker=mMap.addMarker(markerOptions);
                    cocheTemp.setMarker(marker);
                    marker.setTag(cocheTemp);
                    if(i==0)mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cochePos,7));
                }
            }
        }



        @Override
        public void FireBaseAdmin_RegisterOk(Boolean ok) {

        }

        @Override
        public void FireBaseAdmin_LoginOk(Boolean ok) {

        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            mMap.setOnMarkerClickListener(this);

            // Add a marker in Sydney and move the camera
            // LatLng sydney = new LatLng(-34, 151);
            // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

            DataHolder.instance.fireBaseAdmin.descargarYObservarRama("Noticias");
        }

        @Override
        public boolean onMarkerClick(Marker marker) {
            FBCoche noticias=(FBCoche)marker.getTag();
            Log.v("SecondActivity","Presionando pin:"+noticias.titulo);

            secondActivity.mapDetailFragment.tvtitulo.setText(noticias.titulo);
            secondActivity.mapDetailFragment.tvcuerpo.setText(noticias.cuerpo);

            FragmentTransaction transition = secondActivity.getSupportFragmentManager().beginTransaction();
            transition.show(secondActivity.mapDetailFragment);
            transition.commit();

            return false;
        }

        }
    }

