package com.taniafontcuberta.basketball.controller.managers;

import android.content.Context;
import android.util.Log;

import com.taniafontcuberta.basketball.controller.services.TeamService;
import com.taniafontcuberta.basketball.model.Team;
import com.taniafontcuberta.basketball.util.CustomProperties;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TeamManager {
    private static TeamManager ourInstance;
    private List<Team> teams;
    private Retrofit retrofit;
    private Context context;
    private TeamService teamService;

    private TeamManager(Context cntxt) {
        context = cntxt;
        retrofit = new Retrofit.Builder()
                .baseUrl(CustomProperties.getInstance(context).get("app.baseUrl"))
                .addConverterFactory(GsonConverterFactory.create())

                .build();

        teamService = retrofit.create(TeamService.class);
    }

    public static TeamManager getInstance(Context cntxt) {
        if (ourInstance == null) {
            ourInstance = new TeamManager(cntxt);
        }

        ourInstance.context = cntxt;

        return ourInstance;
    }

    /* GET - GET ALL TEAMS */

    public synchronized void getAllTeams(final TeamCallback teamCallback) {
        Call<List<Team>> call = teamService.getAllTeams(UserLoginManager.getInstance(context).getBearerToken());

        call.enqueue(new Callback<List<Team>>() {
            @Override
            public void onResponse(Call<List<Team>> call, Response<List<Team>> response) {
                teams = response.body();

                int code = response.code();

                if (code == 200 || code == 201) {
                    teamCallback.onSuccessTeam(teams);
                } else {
                    teamCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Team>> call, Throwable t) {
                Log.e("TeamManager->", t.toString());
                teamCallback.onFailure(t);
            }

        });
    }

    public Team getTeam(String id) {
        for (Team team : teams) {
            if (team.getId().toString().equals(id)) {
                return team;
            }
        }

        return null;
    }

    /* POST - CREATE PLAYER */

    public synchronized void createTeam(final TeamCallback teamCallback,Team team) {
        Call<Team> call = teamService.createTeam(UserLoginManager.getInstance(context).getBearerToken(), team);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                int code = response.code();

                if (code == 200 || code == 201) {
                    Log.e("Team->","updateTeam: " + Integer.toString(code));

                } else {
                    teamCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Log.e("TeamManager->",  t.toString());

                teamCallback.onFailure(t);
            }
        });
    }

    /* PUT - UPDATE TEAM */
    public synchronized void updateTeam(final TeamCallback teamCallback, Team team) {
        Call <Team> call = teamService.updatePlayer(UserLoginManager.getInstance(context).getBearerToken(), team);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                int code = response.code();

                if (code == 200 || code == 201) {
                    Log.e("Team->","updateTeam: " + Integer.toString(code));

                } else {
                    teamCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Log.e("Team->", "updateTeam: " + t);

                teamCallback.onFailure(t);
            }
        });
    }


}
