package com.taniafontcuberta.basketball.controller.managers;

import com.taniafontcuberta.basketball.model.UserToken;

public interface LoginCallback {
    void onSuccess(UserToken userToken);
    void onFailure(Throwable t);
}
