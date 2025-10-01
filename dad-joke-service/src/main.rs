// ...existing code...
use tonic::transport::Server;
use tonic::{Request, Response, Status};

mod joke_service {
    tonic::include_proto!("joke_service");
}

use joke_service::dad_joke_service_server::{DadJokeService, DadJokeServiceServer};
use joke_service::{DadJokeRequest, DadJokeResponse};

#[derive(Debug, Default)]
struct MyDadJokeService {}

#[tonic::async_trait]
impl DadJokeService for MyDadJokeService {
    async fn get_dad_joke(
        &self,
        request: Request<DadJokeRequest>,
    ) -> Result<Response<DadJokeResponse>, Status> {
        println!("Got a request: {:?}", request);
        let _input = request.get_ref();

        let response = DadJokeResponse {
            setup: "what do you call a cow with no legs?".into(),
            punchline: "ground beef".into(),
        };

        Ok(Response::new(response))
    }
}

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    let addr = "[::1]:50051".parse()?;
    let service = MyDadJokeService::default();

    Server::builder()
        .add_service(DadJokeServiceServer::new(service))
        .serve(addr)
        .await?;

    Ok(())
}
