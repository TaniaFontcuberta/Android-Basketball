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
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.taniafontcuberta.basketball.R;
import com.taniafontcuberta.basketball.controller.activities.master_detail.PlayerListActivity;
import com.taniafontcuberta.basketball.controller.managers.PlayerCallback;
import com.taniafontcuberta.basketball.controller.managers.PlayerManager;
import com.taniafontcuberta.basketball.model.Player;

import java.util.List;

/**
 * A Add screen that offers Add via username/basketsView.
 */
public class AddEditActivity extends AppCompatActivity implements PlayerCallback {

    // UI references.
    private EditText nameView;
    private EditText basketsView;
    private EditText reboundsView;
    private EditText assistsView;
    private Spinner fieldPositionView;
    private Spinner teamView;
    private DatePicker birthdateView;
    private Player player;
    private Bundle extras;

    // ATTR
    private String name;
    private String baskets;
    private String assists;
    private String rebounds;
    private String birthdate;
    private String fieldPosition;
    private String id;
    private ArrayAdapter adapterTeams;

    private View mProgressView;
    private View mAddFormView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extras = getIntent().getExtras();
        if (extras.getString("type").equals("add")) {
            setTitle("Add Player");
        } else {
            setTitle("Edit Player");
        }
        setContentView(R.layout.activity_add_edit);

        /* GET ALL TEAMS */

        player = new Player();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Set up the Add form.
        nameView = (EditText) findViewById(R.id.playerName);
        basketsView = (EditText) findViewById(R.id.baskets);
        reboundsView = (EditText) findViewById(R.id.rebounds);
        assistsView = (EditText) findViewById(R.id.assists);
        fieldPositionView = (Spinner) findViewById(R.id.fieldPosition);
        birthdateView = (DatePicker) findViewById(R.id.birthdate);


        // FieldPosition Spinner
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.posiciones, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fieldPositionView.setAdapter(adapter);

        /* SET FIELDPOSITION TO PLAYER */
        fieldPositionView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View view, int position, long id) {
                fieldPosition = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button addButton = (Button) findViewById(R.id.add_edit_button);
        Log.e("Tania - extras", extras.getString("type"));

        switch (extras.getString("type")) {
            case "add":
                addButton.setText("Add Player");
                break;
            case "edit":
                addButton.setText("Edit Player");

                id = extras.getString("id");
                nameView.setText(PlayerManager.getInstance(this).getPlayer(id).getName());
                basketsView.setText(PlayerManager.getInstance(this).getPlayer(id).getBaskets().toString());
                reboundsView.setText(PlayerManager.getInstance(this).getPlayer(id).getRebounds().toString());
                assistsView.setText(PlayerManager.getInstance(this).getPlayer(id).getAssists().toString());

                switch (PlayerManager.getInstance(this).getPlayer(id).getFieldPosition()) {
                    case "PointGuard":
                        fieldPositionView.setSelection(adapter.getPosition("PointGuard"));
                        break;
                    case "ShootingGuard":
                        fieldPositionView.setSelection(adapter.getPosition("ShootingGuard"));
                        break;
                    case "SmallForward":
                        fieldPositionView.setSelection(adapter.getPosition("SmallForward"));
                        break;
                    case "PowerForward":
                        fieldPositionView.setSelection(adapter.getPosition("PowerForward"));
                        break;
                    case "Center":
                        fieldPositionView.setSelection(adapter.getPosition("Center"));
                        break;
                }


            /* Get birthDate, split by "-", and update datePicker */
                String birthdateGet = PlayerManager.getInstance(this).getPlayer(id).getBirthdate().toString();
                String[] date = birthdateGet.split("-");
                birthdateView.updateDate(Integer.parseInt(date[0]), Integer.parseInt(date[1]) - 1, Integer.parseInt(date[2]));


        }
        addButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptAdd(view);
            }
        });

        mAddFormView = findViewById(R.id.add_edit_form);
        mProgressView = findViewById(R.id.add_edit_progress);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    /**
     * Attempts to log in the account specified by the Add form.
     * If there are form errors (invalid username, missing fields, etc.), the
     * errors are presented and no actual Add attempt is made.
     */
    private void attemptAdd(View v) {
        // Reset errors.
        nameView.setError(null);
        basketsView.setError(null);
        reboundsView.setError(null);
        assistsView.setError(null);
        //    fieldPositionView.setError(null);

        // Store values at the  Add attempt.
        name = nameView.getText().toString();
        baskets = this.basketsView.getText().toString();
        rebounds = this.reboundsView.getText().toString();
        assists = this.assistsView.getText().toString();

        String year = String.valueOf(this.birthdateView.getYear());
        String month = String.valueOf(this.birthdateView.getMonth() + 1);
        String day = String.valueOf(this.birthdateView.getDayOfMonth());

        if (this.birthdateView.getDayOfMonth() < 10) {
            day = "0" + day;
        }
        if (this.birthdateView.getMonth() < 10) {
            month = "0" + month;
        }
        birthdate = year + "-" + month + "-" + day;

        boolean cancel = false;
        View focusView = null;

        // Check for a valid basketsView, if the user entered one.

        if (TextUtils.isEmpty(name)) {
            nameView.setError(getString(R.string.error_field_required));
            focusView = nameView;
            cancel = true;
        }
        if (TextUtils.isEmpty(baskets)) {
            basketsView.setError(getString(R.string.error_field_required));
            focusView = basketsView;
            cancel = true;
        }
        if(Integer.parseInt(baskets) < 0){
            reboundsView.setError(" < 0");
            focusView = reboundsView;
            cancel = true;
        }

        if (TextUtils.isEmpty(rebounds)) {
            reboundsView.setError(getString(R.string.error_field_required));
            focusView = reboundsView;
            cancel = true;
        }
        if(Integer.parseInt(rebounds) < 0){
            reboundsView.setError(" < 0");
            focusView = reboundsView;
            cancel = true;
        }
        if (TextUtils.isEmpty(assists)) {
            assistsView.setError(getString(R.string.error_field_required));
            focusView = assistsView;
            cancel = true;
        }
        if(Integer.parseInt(assists) < 0){
            reboundsView.setError(" < 0");
            focusView = reboundsView;
            cancel = true;
        }



        if (cancel) {
            // There was an error; don't attempt Add and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user Add attempt.
            showProgress(true);

            player.setName(name);
            player.setBaskets(Integer.parseInt(baskets));
            player.setAssists(Integer.parseInt(assists));
            player.setRebounds(Integer.parseInt(rebounds));
            player.setFieldPosition(fieldPosition);
            player.setBirthdate(birthdate);

            if (extras.getString("type").equals("add")) {
                PlayerManager.getInstance(v.getContext()).createPlayer(AddEditActivity.this, player);
                Toast.makeText(AddEditActivity.this, "Created :  " + player.getName(), Toast.LENGTH_LONG).show();
            } else {
                player.setId(Long.parseLong(id));
                PlayerManager.getInstance(v.getContext()).updatePlayer(AddEditActivity.this, player);
                Toast.makeText(AddEditActivity.this, "Edited  :   " + player.getName(), Toast.LENGTH_LONG).show();

            }
            Intent i = new Intent(v.getContext(), PlayerListActivity.class);
            startActivity(i);

        }
    }

    @Override
    public void onSuccess(List<Player> playerList) {

    }

    @Override
    public void onSucces() {

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

