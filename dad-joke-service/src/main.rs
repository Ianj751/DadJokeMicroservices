use sqlx::{Pool, Sqlite};
use tonic::transport::Server;
use tonic::{Request, Response, Status};

// ..\target\debug\build\dad-joke-service-7d9aaad0e7401a98\out\joke_service.rs
mod joke_service {
    tonic::include_proto!("joke_service");
}

use joke_service::dad_joke_service_server::{DadJokeService, DadJokeServiceServer};
use joke_service::{DadJokeRequest, DadJokeResponse};

#[derive(Debug)]
struct MyDadJokeService {
    pool: Pool<Sqlite>,
}

#[tonic::async_trait]
impl DadJokeService for MyDadJokeService {
    async fn get_dad_joke(
        &self,
        request: Request<DadJokeRequest>,
    ) -> Result<Response<DadJokeResponse>, Status> {
        println!("Got a request: {:?}", request);

        let record = sqlx::query!("SELECT setup, punchline FROM tbl_jokes ORDER BY random()")
            .fetch_one(&self.pool)
            .await
            .map_err(|e| tonic::Status::internal(format!("failed to query data: {}", e)))?;

        let response = DadJokeResponse {
            setup: record.setup.unwrap(),
            punchline: record.punchline.unwrap(),
        };

        Ok(Response::new(response))
    }
}

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    let addr = "[::1]:50051".parse()?;

    let pool = sqlx::sqlite::SqlitePool::connect("sqlite:dadJokes.db").await?;
    let service = MyDadJokeService { pool };

    Server::builder()
        .add_service(DadJokeServiceServer::new(service))
        .serve(addr)
        .await?;

    Ok(())
}
