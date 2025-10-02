# Proof Of Concept Dad Joke Project

This project doesn't really serve any purpose but to prove to my self that I can make stuff with gRPC

## Minimal Architecture Diagram
Client -> Entry Point (Handles Auth and Routing) -gRPC-> Dad Joke Service (Produces a random Dad Joke)

## Dad Joke Service
Pulls a random dad joke from a sqlite DB, and returns it to the user if they have the secret key in their request header 

## Entry Point Service Happy Path
1. Service Recieves Registration Request
2. Stores Registered User in DB
3. User Makes Request for Dad Joke to Entry Point
4. Entry Point Forwards Request To Dad Joke Service w/ gRPC
5. Entry Point Recieves Response and Forwards it back to client

## Example Postman Screenshot
<img width="1568" height="856" alt="image" src="https://github.com/user-attachments/assets/83f5a172-1c61-481d-a150-edd48174f8b0" />



