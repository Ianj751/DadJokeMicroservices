package com.ianj751.gateway.stubs;

import org.springframework.stereotype.Service;

import com.ianj751.gateway.DadJokeRequest;
import com.ianj751.gateway.DadJokeResponse;
import com.ianj751.gateway.DadJokeServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Service
public class DadJokeClient {
    private final DadJokeServiceGrpc.DadJokeServiceBlockingStub blockingStub;

    public DadJokeClient() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8081).usePlaintext().build();
        this.blockingStub = DadJokeServiceGrpc.newBlockingStub(channel);
    }

    public DadJokeResponse getDadJoke(String apiKey) {
        DadJokeRequest request = DadJokeRequest.newBuilder().setApiKey(apiKey).build();
        DadJokeResponse response = blockingStub.getDadJoke(request);

        return response;
    }
}
