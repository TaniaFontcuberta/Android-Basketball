package com.taniafontcuberta.basketball.controller.activities.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.taniafontcuberta.basketball.R;
import com.taniafontcuberta.basketball.controller.managers.RegisterCallback;
import com.taniafontcuberta.basketball.controller.managers.RegisterManager;
import com.taniafontcuberta.basketball.model.UserDTO;

;

/**
 * A Add screen that offers Add via username/baskets.
 */
public class RegisterActivity extends AppCompatActivity implements RegisterCallback {

    // UI references.
    private EditText username;
    private EditText email;
    private EditText password;
    private EditText password2;
    private UserDTO userDTO;

    // ATTR
    String playerPosicionCampo;

    private View mProgressView;
    private View mAddFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        // Set up the Add form.
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        password2 = (EditText) findViewById(R.id.password2);

      Button registerButton= (Button) findViewById(R.id.register_button2);
        registerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister(view);
            }
        });

        mAddFormView = findViewById(R.id.register_form);
        mProgressView = findViewById(R.id.register_progress);
    }

    /**
     * Attempts to log in the account specified by the Add form.
     * If there are form errors (invalid username, missing fields, etc.), the
     * errors are presented and no actual Add attempt is made.
     */
    private void attemptRegister(View v) {
        // Reset errors.
        username.setError(null);
        password.setError(null);
        password2.setError(null);
        email.setError(null);

        userDTO = new UserDTO();
        // Store values at the  Add attempt.

        String usernameDTO = username.getText().toString().toLowerCase();
        String emailDTO = email.getText().toString();
        String passwordDTO = password.getText().toString();
        String password2DTO = password2.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid baskets, if the user entered one.
         if (TextUtils.isEmpty(usernameDTO)) {
            username.setError(getString(R.string.error_field_required));
            focusView = username;
            cancel = true;
        }
        if (TextUtils.isEmpty(emailDTO)) {
            email.setError(getString(R.string.error_field_required));
            focusView = email;
            cancel = true;
        }
        if(passwordDTO.length() < 5){
            password.setError("Ha de tener mínimo 4 carácteres");
            focusView = password;
            cancel = true;
        }
        if (!passwordDTO.equals(password2DTO)) {
            password.setError("Han de ser iguales.");
            focusView = password;
            cancel = true;
        }

        userDTO.setLogin(usernameDTO);
        userDTO.setEmail(emailDTO);

        userDTO.setPassword(passwordDTO);


        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            RegisterManager.getInstance(v.getContext()).registerAccount(RegisterActivity.this, userDTO);
            Intent i = new Intent(v.getContext(), LoginActivity.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(),"Creado nuevo usuario " + userDTO.getLogin(),Toast.LENGTH_LONG);
        }
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure(Throwable t) {
        showProgress(false);
    }

    /**
     * Shows the progress UI and hides the Add form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mAddFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mAddFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mAddFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mAddFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

}

