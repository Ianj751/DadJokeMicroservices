package com.ianj751.gateway.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.ianj751.gateway.DadJokeResponse;
import com.ianj751.gateway.stubs.DadJokeClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class DadJokeController {
    @GetMapping("/jokes")
    public ResponseEntity<DadJokeResponse> retrieveDadJoke(@RequestParam String jwt) {
        // TODO: verify jwt, if valid continue and retrieve dad joke
        DadJokeClient client = new DadJokeClient();

        DadJokeResponse resp = client.getDadJoke("pls-gimme-jokes");

        return ResponseEntity.ok(resp);
    }

}
