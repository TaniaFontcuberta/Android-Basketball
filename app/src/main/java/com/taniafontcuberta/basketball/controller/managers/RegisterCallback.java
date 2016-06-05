package com.taniafontcuberta.basketball.controller.managers;


public interface RegisterCallback {
    void onSuccess();
    void onFailure(Throwable t);
}
