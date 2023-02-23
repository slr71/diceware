FROM clojure:lein

WORKDIR /opt/diceware
COPY . .
RUN lein do clean, uberjar

FROM openjdk:21-slim

COPY --from=0 /opt/diceware/target/uberjar/diceware-standalone.jar /opt/diceware/
COPY diceware /usr/bin/

ENTRYPOINT ["diceware"]
