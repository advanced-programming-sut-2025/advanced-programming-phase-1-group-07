package com.example.views;

import com.example.models.IO.Response;

public interface Menu {
    void handleMenu(String input);

    default void printResponse(Response response) {
        if (response != null && response.getMessage() != null) {
        }
        System.out.println(response.getMessage());
    }

    default Response getInvalidCommand() {
        return new Response(false, "Invalid command");
    }

}
