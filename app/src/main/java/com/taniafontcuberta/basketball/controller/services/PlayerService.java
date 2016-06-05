package com.taniafontcuberta.basketball.controller.services;

import com.taniafontcuberta.basketball.model.Player;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface PlayerService {

    @GET("/api/players")
    Call<List<Player>> getAllPlayer(
            @Header("Authorization") String Authorization
    );

    @POST("api/players") // Se tiene que cambiar en un interfaz propia
    Call<Player> createPlayer(
            @Header("Authorization") String Authorization,
            @Body Player player);


    @PUT("api/players")
    Call<Player> updatePlayer(
            @Header("Authorization") String Authorization,
            @Body Player player);

    @DELETE("api/players/{id}")
    Call<Void> deletePlayer(
            @Header("Authorization") String Authorization,
            @Path("id") Long id);

}