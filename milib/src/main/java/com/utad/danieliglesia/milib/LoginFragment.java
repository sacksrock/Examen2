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


import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

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
        callbackManager = CallbackManager.Factory.create();
        //asignacion de elementos

        etUsername=v.findViewById(R.id.etusername);
        etPass=v.findViewById(R.id.etpass);
        btnLogin=v.findViewById(R.id.btnlogin);
        btnRegister=v.findViewById(R.id.btnregister);
        loginButton=v.findViewById(R.id.login_button);

        //login_button.setReadPermissions("email");

        //inicializacion de events
        events = new LoginFragmentEvents(this);
        //asignacion de controlador de eventos a los botones
        btnLogin.setOnClickListener(events);
        btnRegister.setOnClickListener(events);
        loginButton.setOnClickListener(events);

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

        return v;


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        listener.cambiarPantalla();

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
