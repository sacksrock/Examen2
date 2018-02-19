package com.utad.danieliglesia.examenpmdm_dint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;

/**
 * Created by daniel.iglesia on 19/02/2018.
 */

public class SecondActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        SecondActivityEvents events = new SecondActivityEvents(this);
        DataHolder.instance.fireBaseAdmin.setListener(events);

    }

    class SecondActivityEvents implements FireBaseAdminListener {

        SecondActivity secondActivity;


        public SecondActivityEvents(SecondActivity secondActivity) {
            this.secondActivity = secondActivity;
        }


        @Override
        public void FireBaseAdmin_RegisterOk(Boolean ok) {

        }

        @Override
        public void FireBaseAdmin_LoginOk(Boolean ok) {

        }

        @Override
        public void FireBaseAdmin_RamaDescargada(String rama, DataSnapshot dataSnapshot) {

        }
    }
}
