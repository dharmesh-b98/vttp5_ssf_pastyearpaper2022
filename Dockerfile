FROM maven:3.9.9-eclipse-temurin-23 AS builder

WORKDIR /src

# copy files
COPY mvnw .
COPY pom.xml .

COPY .mvn .mvn
COPY src src

# make mvnw executable
RUN chmod a+x mvnw && /src/mvnw package -Dmaven.test.skip=true
# /src/target/revision-0.0.1-SNAPSHOT.jar

FROM maven:3.9.9-eclipse-temurin-23

WORKDIR /app

COPY --from=builder /src/target/vttp5_ssf_pastyearpaper2022-0.0.1-SNAPSHOT.jar app.jar

ENV PORT=8080
ENV SECRET_TEST_NUMBER=xyz789

EXPOSE ${PORT}

HEALTHCHECK --interval=30s --timeout=5s --start-period=5s --retries=3 \
   CMD curl http://localhost:${PORT}/mnv/health || exit 1

ENTRYPOINT SERVER_PORT=${PORT} java -jar app.jar