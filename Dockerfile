FROM registry.access.redhat.com/ubi9/openjdk-17:1.14-2.1681917140 AS builder

WORKDIR /app

RUN mkdir -p /app/containers/payara/deployments /app/containers/payara/domains/domain1/lib

COPY pom.xml .
COPY --chown=185 sales-manager-api/ sales-manager-api/
COPY --chown=185 sales-manager-ejb/ sales-manager-ejb/
COPY --chown=185 sales-manager-web/ sales-manager-web/

RUN mvn clean install -P payara

FROM payara/server-full:5.2022.5-jdk17

COPY --from=builder --chown=payara:payara /app/containers/payara/deployments/ $DEPLOY_DIR
COPY --from=builder --chown=payara:payara /app/containers/payara/domains/domain1/lib/ $PAYARA_DIR/glassfish/domains/$DOMAIN_NAME/lib
COPY --chown=payara:payara containers/payara/config/post-boot-commands.asadmin $POSTBOOT_COMMANDS_FINAL
