alias b := build
alias bi := build-image
alias pi := push-image

build:
    lein do clean, uberjar

build-image:
    docker buildx build \
        --platform=linux/amd64 \
        --attest type=provenance,mode=max \
        --sbom=true \
        --rm \
        -t slr71/diceware:latest .

push-image:
    docker push slr71/diceware:latest
