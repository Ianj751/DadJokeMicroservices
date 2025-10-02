package com.ianj751.gateway.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.ianj751.gateway.models.DadJokeDTO;
import com.ianj751.gateway.stubs.DadJokeClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class DadJokeController {
    @GetMapping("/jokes")
    public ResponseEntity<DadJokeDTO> retrieveDadJoke() {

        DadJokeClient client = new DadJokeClient();

        DadJokeDTO resp = client.getDadJoke("pls-gimme-jokes");

        return ResponseEntity.ok(resp);
    }

}
