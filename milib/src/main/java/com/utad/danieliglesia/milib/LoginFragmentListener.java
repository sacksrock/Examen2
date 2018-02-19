package com.utad.danieliglesia.milib;

import android.support.v4.app.FragmentActivity;

/**
 * Created by daniel.iglesia on 19/02/2018.
 */


public interface LoginFragmentListener {
    public void loginFragmentLoginButtonClicked(String sUser, String sPass);
    public void loginFragmentRegisterButtonClicked();
}
