package com.utad.danieliglesia.milib;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.AuthCredential;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;

import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    //Declaración de variables
    public EditText etUsername;
    public EditText etPass;
    public Button btnLogin;
    public Button btnRegister;
    public LoginFragmentEvents events;
    public LoginFragmentListener listener;
    CallbackManager callbackManager;
    LoginButton loginButton;



    public LoginFragment() {
        // Required empty public constructor
    }

    public void setListener(LoginFragmentListener listener){
        this.listener = listener;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        //Inicializar Facebook y del boton
        callbackManager = CallbackManager.Factory.create();
        loginButton = v.findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile","email"));
        //asignacion de elementos
        etUsername=v.findViewById(R.id.etusername);
        etPass=v.findViewById(R.id.etpass);
        btnLogin=v.findViewById(R.id.btnlogin);
        btnRegister=v.findViewById(R.id.btnregister);
        //inicializacion de events
        events = new LoginFragmentEvents(this);
        //asignacion de controlador de eventos a los botones
        btnLogin.setOnClickListener(events);
        btnRegister.setOnClickListener(events);
        loginButton.setOnClickListener(events);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                                Log.v("Correcto","Metodo OnSuccess");
                           }
            @Override
            public void onCancel() {

                   }

            @Override
            public void onError(FacebookException exception) {
                    }
            });


        return v;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
class LoginFragmentEvents implements View.OnClickListener{

    private LoginFragment loginFragment;

    public LoginFragmentEvents(LoginFragment fragment){
        this.loginFragment = fragment;

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==this.loginFragment.btnLogin.getId()){
            this.loginFragment.listener.loginFragmentLoginButtonClicked(this.loginFragment.etUsername.getText().toString(),this.loginFragment.etPass.getText().toString());
        }
        else if(view.getId()==this.loginFragment.btnRegister.getId()){
            this.loginFragment.listener.loginFragmentRegisterButtonClicked();
        }
    }
}
