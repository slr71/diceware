FROM clojure

WORKDIR /opt/diceware
COPY . .
RUN lein do clean, uberjar

FROM eclipse-temurin:21.0.8_9-jre-ubi9-minimal

COPY --from=0 /opt/diceware/target/uberjar/diceware-standalone.jar /opt/diceware/
COPY diceware /usr/bin/

RUN useradd diceware
USER diceware

ENTRYPOINT ["diceware"]
