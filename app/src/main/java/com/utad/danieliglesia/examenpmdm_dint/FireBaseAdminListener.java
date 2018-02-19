package com.utad.danieliglesia.examenpmdm_dint;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by daniel.iglesia on 19/02/2018.
 */

public interface FireBaseAdminListener {

    public void FireBaseAdmin_RegisterOk(Boolean ok);
    public void FireBaseAdmin_LoginOk(Boolean ok);
    public void FireBaseAdmin_RamaDescargada(String rama,DataSnapshot dataSnapshot);
    //public void handleFacebookAccessToken();
}
