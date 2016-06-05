package com.taniafontcuberta.basketball.controller.managers;

import com.taniafontcuberta.basketball.model.Player;

import java.util.List;


public interface PlayerCallback {
    void onSuccess(List<Player> playerList);
    void onSucces();

    void onFailure(Throwable t);
}
