package com.taniafontcuberta.basketball.controller.managers;

import android.content.Context;
import android.util.Log;

import com.taniafontcuberta.basketball.controller.services.PlayerService;
import com.taniafontcuberta.basketball.model.Player;
import com.taniafontcuberta.basketball.util.CustomProperties;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlayerManager {
    private static PlayerManager ourInstance;
    private List<Player> players;
    private Retrofit retrofit;
    private Context context;
    private PlayerService playerService;

    private PlayerManager(Context cntxt) {
        context = cntxt;
        retrofit = new Retrofit.Builder()
                .baseUrl(CustomProperties.getInstance(context).get("app.baseUrl"))
                .addConverterFactory(GsonConverterFactory.create())

                .build();

        playerService = retrofit.create(PlayerService.class);
    }

    public static PlayerManager getInstance(Context cntxt) {
        if (ourInstance == null) {
            ourInstance = new PlayerManager(cntxt);
        }

        ourInstance.context = cntxt;

        return ourInstance;
    }

    /* GET - GET ALL PLAYER */

    public synchronized void getAllPlayers(final PlayerCallback playerCallback) {
        Call<List<Player>> call = playerService.getAllPlayer(UserLoginManager.getInstance(context).getBearerToken());

        call.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                players = response.body();

                int code = response.code();

                if (code == 200 || code == 201) {
                    playerCallback.onSuccess(players);
                } else {
                    playerCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                Log.e("PlayerManager->", "getAllPlayers()->ERROR: " + t);

                playerCallback.onFailure(t);
            }
        });
    }

    public Player getPlayer(String id) {
        for (Player player : players) {
            if (player.getId().toString().equals(id)) {
                return player;
            }
        }

        return null;
    }

    /* POST - CREATE PLAYER */

    public synchronized void createPlayer(final PlayerCallback playerCallback,Player player) {
        Call<Player> call = playerService.createPlayer(UserLoginManager.getInstance(context).getBearerToken(), player);
        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                int code = response.code();

                if (code == 200 || code == 201) {
                    //playerCallback.onSuccess1(apuestas1x2);
                    Log.e("Player->", "Realizada: OK" + 100);

                } else {
                    playerCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                Log.e("PlayerManager->", "getAllPlayers()->ERROR: " + t);

                playerCallback.onFailure(t);
            }
        });
    }

    /* PUT - UPDATE PLAYER */
    public synchronized void updatePlayer(final PlayerCallback playerCallback, Player player) {
        Call <Player> call = playerService.updatePlayer(UserLoginManager.getInstance(context).getBearerToken() ,player);
        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                int code = response.code();

                if (code == 200 || code == 201) {
                    Log.e("Player->", "Realizada: OOK" + 100);

                } else {
                    playerCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                Log.e("PlayerManager->", "getAllPlayers()->ERROR: " + t);

                playerCallback.onFailure(t);
            }
        });
    }

    /* DELETE - DELETE PLAYER */
    public synchronized void deletePlayer(final PlayerCallback playerCallback, Long id) {
        Call <Void> call = playerService.deletePlayer(UserLoginManager.getInstance(context).getBearerToken() ,id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int code = response.code();

                if (code == 200 || code == 201) {
                    Log.e("Player->", "Deleted: OK");

                } else {
                    playerCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("PlayerManager->", "getAllPlayers()->ERROR: " + t);

                playerCallback.onFailure(t);
            }
        });
    }

    /* GET - TOP PLAYERS BY NAME */

    public synchronized void getPlayerByName(final PlayerCallback playerCallback,String name) {
        // Call<List<Apuesta>> call = playerService.getAllPlayer(UserLoginManager.getInstance(context).getBearerToken());
        Call<List<Player>> call = playerService.getPlayerByName(UserLoginManager.getInstance(context).getBearerToken(), name);
        call.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                players = response.body();

                int code = response.code();

                if (code == 200 || code == 201) {
                    playerCallback.onSuccess(players);

                } else {
                    playerCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                Log.e("PlayerManager->", "getAllPlayers()->ERROR: " + t);

                playerCallback.onFailure(t);
            }
        });
    }

    /* GET - TOP PLAYERS BY X BASKETS */

    public synchronized void getPlayersByBaskets(final PlayerCallback playerCallback,Integer baskets) {
        Call<List<Player>> call = playerService.getPlayersByBaskets(UserLoginManager.getInstance(context).getBearerToken(), baskets);
        call.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                players = response.body();

                int code = response.code();

                if (code == 200 || code == 201) {
                    playerCallback.onSuccess(players);

                } else {
                    playerCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                Log.e("PlayerManager->", "getAllPlayers()->ERROR: " + t);

                playerCallback.onFailure(t);
            }
        });
    }


    /* GET - TOP PLAYERS BY X DATEBIRTH */

    public synchronized void getPlayersByBirthdate(final PlayerCallback playerCallback,String birthdate) {
        Call<List<Player>> call = playerService.getPlayersByBirthdate(UserLoginManager.getInstance(context).getBearerToken(), birthdate);
        call.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                players = response.body();

                int code = response.code();

                if (code == 200 || code == 201) {
                    playerCallback.onSuccess(players);

                } else {
                    playerCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                Log.e("PlayerManager->", "getAllPlayers()->ERROR: " + t);

                playerCallback.onFailure(t);
            }
        });
    }

}
