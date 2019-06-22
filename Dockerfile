# select image
FROM maven:3.5-jdk-8

# copy your source tree
COPY ./ ./

# build for release
RUN mvn package -Dmaven.test.skip=true
EXPOSE 8443
CMD ["mvn", "spring-boot:run"]