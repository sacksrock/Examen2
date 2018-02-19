package com.utad.danieliglesia.examenpmdm_dint;

/**
 * Created by daniel.iglesia on 19/02/2018.
 */

public class DataHolder {
    public static DataHolder instance= new DataHolder();

    public FireBaseAdmin fireBaseAdmin;
    public DataHolder(){
        fireBaseAdmin = new FireBaseAdmin();
    }
}

