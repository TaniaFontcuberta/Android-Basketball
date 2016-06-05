package com.taniafontcuberta.basketball.controller.activities.add_edit;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.taniafontcuberta.basketball.R;
import com.taniafontcuberta.basketball.controller.activities.master_detail.PlayerListActivity;
import com.taniafontcuberta.basketball.controller.managers.TeamCallback;
import com.taniafontcuberta.basketball.controller.managers.TeamManager;
import com.taniafontcuberta.basketball.model.Team;

import java.util.List;

/**
 * A Add screen that offers Add via username/localityView.
 */
public class AddEditTeamActivity extends AppCompatActivity implements TeamCallback {

    // UI references.
    private EditText nameView;
    private EditText localityView;
    private Bundle extras;

    // ATTR
    private String name;
    private String locality;
    private String id;

    private Team team;
    private View mProgressView;
    private View mAddFormView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extras = getIntent().getExtras();
        if(extras.getString("type").equals("add")){
            setTitle("Add Team");
        }else{
            setTitle("Edit Team");
        }
        setContentView(R.layout.activity_add_edit_team);
        TeamManager.getInstance(this.getApplicationContext()).getAllTeams(AddEditTeamActivity.this);
        team = new Team();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Set up the Add form.
        nameView = (EditText) findViewById(R.id.nameTeam);
        localityView = (EditText) findViewById(R.id.localityTeam);


        Button addButton = (Button) findViewById(R.id.add_edit_team_button);
        Log.e("Tania - extras", extras.getString("type"));

        switch(extras.getString("type")){
            case "add" :
                addButton.setText("Add Team");
                break;
            case "edit":
                addButton.setText("Edit Team");

                id = extras.getString("id");
                Log.e("Tania - idTeam", id);
                nameView.setText(TeamManager.getInstance(this).getTeam(id).getName());
                localityView.setText(TeamManager.getInstance(this).getTeam(id).getLocality());
        }

        addButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptAdd(view);
            }
        });

        mAddFormView = findViewById(R.id.add_edit_form);
        mProgressView = findViewById(R.id.add_edit_progress);
    }

    /**
     * Attempts to log in the account specified by the Add form.
     * If there are form errors (invalid username, missing fields, etc.), the
     * errors are presented and no actual Add attempt is made.
     */
    private void attemptAdd(View v) {
        // Reset errors.
        nameView.setError(null);
        localityView.setError(null);
    //    fieldPositionView.setError(null);

        // Store values at the  Add attempt.
        name = nameView.getText().toString();
        locality = this.localityView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid localityView, if the user entered one.
     /*   if (!TextUtils.isEmpty(localityView)) {
            localityView.setError(getString(R.string.error_field_required));
            focusView = localityView;
            cancel = true;
        }

        if (TextUtils.isEmpty(name)) {
            nameView.setError(getString(R.string.error_field_required));
            focusView = nameView;
            cancel = true;
        }*/


        if (cancel) {
            // There was an error; don't attempt Add and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user Add attempt.
            showProgress(true);

            team.setName(name);
            team.setLocality(locality);

            if(extras.getString("type").equals("add")) {
                TeamManager.getInstance(v.getContext()).createTeam(AddEditTeamActivity.this, team);
                Toast.makeText(AddEditTeamActivity.this, "Created :  " + team.getName(), Toast.LENGTH_LONG).show();
            }else{
                team.setId(Long.parseLong(id));
                TeamManager.getInstance(v.getContext()).updateTeam(AddEditTeamActivity.this, team);
                Toast.makeText(AddEditTeamActivity.this, "Edited  :   " + team.getName(), Toast.LENGTH_LONG).show();

            }
            Intent i = new Intent(v.getContext(), PlayerListActivity.class);
            startActivity(i);

        }
    }



    @Override
    public void onSuccessTeam(List<Team> teamList) {

    }

    @Override
    public void onFailure(Throwable t) {
        Log.e("AddEditActivity->", "performAdd->onFailure ERROR " + t.getMessage());

        // TODO: Gestionar los diversos tipos de errores. Por ejemplo, no se ha podido conectar correctamente.
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

