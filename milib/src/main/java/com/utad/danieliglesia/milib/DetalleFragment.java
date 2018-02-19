package com.utad.danieliglesia.milib;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by daniel.iglesia on 19/02/2018.
 */
/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleFragment extends Fragment {
    public TextView tvcuerpo;
    public TextView tvtitulo;

    public DetalleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detalles, container, false);

        tvcuerpo = v.findViewById(R.id.tvcuerpo);
        tvtitulo = v.findViewById(R.id.tvtitulo);

        return v;
    }
}


