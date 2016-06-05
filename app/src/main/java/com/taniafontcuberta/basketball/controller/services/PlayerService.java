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


    /* FILTERS */

    @GET("api/players/byName/{name}")
    Call<List<Player>> getPlayerByName(

            @Header("Authorization") String Authorization,
            @Path("name") String name);

    @GET("api/players/topBaskets/{baskets}")
    Call<List<Player>> getPlayersByBaskets(

            @Header("Authorization") String Authorization,
            @Path("baskets") Integer baskets);

    @GET("api/players/topBirthdate/{birthdate}")
    Call<List<Player>> getPlayersByBirthdate(

            @Header("Authorization") String Authorization,
            @Path("birthdate") String fechaNacimiento);


    @GET("api/players/topBirthdateBetween/{birthdate}/{birthdate2}")
    Call<List<Player>> getPlayersByBirthdateBetween(

            @Header("Authorization") String Authorization,
            @Path("birthdate") String birthdate,
            @Path("birthdate2") String birthdate2);
}