fn main() -> Result<(), Box<dyn std::error::Error>> {
    println!("cargo:rerun-if-changed=protos/DadJokeService.proto");
    let out = std::env::var("OUT_DIR").unwrap_or_else(|_| "<OUT_DIR not set>".into());
    println!("cargo:warning=build.rs running; OUT_DIR={}", out);
    tonic_build::compile_protos("protos/DadJokeService.proto")?;
    println!("cargo:warning=tonic_build completed");
    Ok(())
}
