package com.taniafontcuberta.basketball.controller.managers;

import com.taniafontcuberta.basketball.model.Team;

import java.util.List;

public interface TeamCallback {
    void onSuccessTeam(List<Team> teamList);

    void onFailure(Throwable t);
}
