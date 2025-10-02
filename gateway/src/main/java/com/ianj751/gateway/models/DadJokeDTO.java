package com.ianj751.gateway.models;

import com.ianj751.gateway.DadJokeResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DadJokeDTO {
    public String setup;
    public String punchline;

    public static DadJokeDTO from(DadJokeResponse response) {
        DadJokeDTO dto = new DadJokeDTO();
        dto.setSetup(response.getSetup());
        dto.setPunchline(response.getPunchline());
        return dto;
    }
}