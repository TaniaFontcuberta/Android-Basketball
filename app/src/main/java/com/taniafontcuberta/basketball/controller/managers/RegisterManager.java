package com.taniafontcuberta.basketball.controller.managers;

import android.content.Context;
import android.widget.Toast;

import com.taniafontcuberta.basketball.controller.services.RegisterService;
import com.taniafontcuberta.basketball.model.UserDTO;
import com.taniafontcuberta.basketball.util.CustomProperties;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterManager {
    private static RegisterManager ourInstance;
    private Retrofit retrofit;
    private Context context;
    private RegisterService registerService;

    private RegisterManager(Context cntxt) {
        context = cntxt;
        retrofit = new Retrofit.Builder()
                .baseUrl(CustomProperties.getInstance(context).get("app.baseUrl"))
                .addConverterFactory(GsonConverterFactory.create())

                .build();

        registerService = retrofit.create(RegisterService.class);
    }

    public static RegisterManager getInstance(Context cntxt) {
        if (ourInstance == null) {
            ourInstance = new RegisterManager(cntxt);
        }

        ourInstance.context = cntxt;

        return ourInstance;
    }

    /* POST - REGISTER ACCOUNT */

    public synchronized void registerAccount(final RegisterCallback registerCallback, UserDTO userDTO) {
        Call<Void> call = registerService.registerAccount(userDTO);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int code = response.code();

                if (code == 200 || code == 201) {
                    Toast.makeText(context, "User created", Toast.LENGTH_LONG);

                } else {
                    registerCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
//                registerCallback.onFailure(t);
            }
        });
    }
}
