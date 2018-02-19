package com.utad.danieliglesia.examenpmdm_dint;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.common.data.DataHolder;
import com.google.firebase.database.DataSnapshot;
import com.utad.danieliglesia.milib.LoginFragment;
import com.utad.danieliglesia.milib.LoginFragmentListener;
import com.utad.danieliglesia.milib.RegisterFragment;
import com.utad.danieliglesia.milib.RegisterFragmentListener;

public class MainActivity extends AppCompatActivity {
    //Declaración de variables en este caso fragments
    LoginFragment loginFragment;
    RegisterFragment registerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicializado el fragment del Login
        loginFragment = (LoginFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentLogin);
        registerFragment = (RegisterFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentRegister);

        MainActivityEvents mainActivityEvents = new MainActivityEvents(this);
        //implementación de los listeners
        loginFragment.setListener(mainActivityEvents);
        registerFragment.setListener(mainActivityEvents);
        com.utad.danieliglesia.examenpmdm_dint.DataHolder.instance.fireBaseAdmin.setListener(mainActivityEvents);
        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        transition.show(loginFragment);
        transition.hide(registerFragment);
        transition.commit();
    }
}

class MainActivityEvents implements LoginFragmentListener, RegisterFragmentListener, FireBaseAdminListener{
    MainActivity mainActivity;
    public MainActivityEvents(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }

    @Override
    public void loginFragmentLoginButtonClicked(String sUser, String sPass) {
        com.utad.danieliglesia.examenpmdm_dint.DataHolder.instance.fireBaseAdmin.loginConEmailYPassword(sUser,sPass,mainActivity);
    }

    @Override
    public void loginFragmentRegisterButtonClicked() {
        FragmentTransaction transition = mainActivity.getSupportFragmentManager().beginTransaction();
        transition.hide(mainActivity.loginFragment);
        transition.show(mainActivity.registerFragment);
        transition.commit();

    }

    @Override
    public void registerFragmentBtnAceptarClicked(String sUser, String sPass) {
        com.utad.danieliglesia.examenpmdm_dint.DataHolder.instance.fireBaseAdmin.loginConEmailYPassword(sUser,sPass,mainActivity);
    }

    @Override
    public void registerFragmentBtnCancelarClciked() {
        FragmentTransaction transition = mainActivity.getSupportFragmentManager().beginTransaction();
        transition.show(mainActivity.loginFragment);
        transition.hide(mainActivity.registerFragment);
        transition.commit();

    }

    @Override
    public void FireBaseAdmin_RegisterOk(Boolean ok) {
        if(ok){
            Intent intent = new Intent(mainActivity,SecondActivity.class);
            mainActivity.startActivity(intent);
            mainActivity.finish();
        }
        else{

        }
    }

    @Override
    public void FireBaseAdmin_LoginOk(Boolean ok) {
        if(ok){
            Intent intent = new Intent(mainActivity,SecondActivity.class);
            mainActivity.startActivity(intent);
            mainActivity.finish();
        }
        else{

        }
    }

    @Override
    public void FireBaseAdmin_RamaDescargada(String rama, DataSnapshot dataSnapshot) {

    }
}