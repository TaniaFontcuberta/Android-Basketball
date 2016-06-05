package com.taniafontcuberta.basketball.controller.services;

import com.taniafontcuberta.basketball.model.Team;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by Tania on 28/04/2016.
 */

public interface TeamService {

    @GET("/api/teams")
    Call<List<Team>> getAllTeams(
            @Header("Authorization") String Authorization
    );

    @POST("api/teams")
    Call<Team> createTeam(
            @Header("Authorization") String Authorization,
            @Body Team team);


    @PUT("api/teams")
    Call<Team> updatePlayer(
            @Header("Authorization") String Authorization,
            @Body Team team);

}