package com.taniafontcuberta.basketball.controller.activities.master_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.taniafontcuberta.basketball.R;
import com.taniafontcuberta.basketball.controller.activities.add_edit.AddEditActivity;
import com.taniafontcuberta.basketball.controller.activities.login.LoginActivity;
import com.taniafontcuberta.basketball.controller.managers.PlayerCallback;
import com.taniafontcuberta.basketball.controller.managers.PlayerManager;
import com.taniafontcuberta.basketball.model.Player;

import java.util.List;

/**
 * An activity representing a list of Players. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link PlayerDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class PlayerListActivity extends AppCompatActivity implements PlayerCallback {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private RecyclerView recyclerView;
    private List<Player> players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),AddEditActivity.class);
                intent.putExtra("type","add");
                startActivityForResult(intent, 0);
            }
        });

        FloatingActionButton searchName = (FloatingActionButton) findViewById(R.id.topName);
        searchName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PlayerTopActivity.class);
                intent.putExtra("id", "name");
                startActivityForResult(intent, 0);
            }
        });

        FloatingActionButton topBaskets = (FloatingActionButton) findViewById(R.id.topBaskets);
        topBaskets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),PlayerTopActivity.class);
                intent.putExtra("id", "baskets");
                startActivityForResult(intent, 0);
            }
        });

        FloatingActionButton topFechaNacimiento = (FloatingActionButton) findViewById(R.id.topFechaNacimiento);
        topFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),PlayerTopActivity.class);
                intent.putExtra("id", "birthdate");
                startActivityForResult(intent, 0);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.player_list);
        assert recyclerView != null;

        if (findViewById(R.id.player_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        PlayerManager.getInstance(this.getApplicationContext()).getAllPlayers(PlayerListActivity.this);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        Log.i("setupRecyclerView", "                     " + players);
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(players));
    }

    @Override
    public void onSuccess(List<Player> playerList) {
        players = playerList;
        setupRecyclerView(recyclerView);
    }

    @Override
    public void onSucces() {

    }

    @Override
    public void onFailure(Throwable t) {
        Intent i = new Intent(PlayerListActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Player> mValues;

        public SimpleItemRecyclerViewAdapter(List<Player> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.player_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(mValues.get(position).getId().toString());
            holder.mContentView.setText(mValues.get(position).getName());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(PlayerDetailFragment.ARG_ITEM_ID, holder.mItem.getId().toString());
                        PlayerDetailFragment fragment = new PlayerDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.player_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, PlayerDetailActivity.class);
                        intent.putExtra(PlayerDetailFragment.ARG_ITEM_ID, holder.mItem.getId().toString());

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public Player mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
