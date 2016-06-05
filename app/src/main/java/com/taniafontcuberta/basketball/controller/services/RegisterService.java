package com.taniafontcuberta.basketball.controller.services;

import com.taniafontcuberta.basketball.model.UserDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Tania on 28/04/2016.
 */

public interface RegisterService {
    @POST("api/register")
    Call<Void> registerAccount(
            @Body UserDTO userDTO
    );

}