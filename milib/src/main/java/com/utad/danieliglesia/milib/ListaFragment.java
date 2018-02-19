package com.utad.danieliglesia.milib;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by daniel.iglesia on 19/02/2018.
 */

public class ListaFragment extends Fragment {

    public RecyclerView recyclerView;


    public ListaFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v  = inflater.inflate(R.layout.fragment_lista_mensajes, container, false);
        recyclerView = v.findViewById(R.id.listamensajes);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));

        return v;
    }

    public void setAdapter(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter){
        recyclerView.setAdapter(adapter);

    }

}
