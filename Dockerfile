FROM maven:3.6.3-jdk-11

WORKDIR /usr/source/app

COPY pom.xml .

# Download the dependencies and generate the Maven dependency cache
RUN mvn dependency:go-offline -B

# Copy the entire project excluding the target directory
COPY . .

CMD mvn test -Dkarate.options="--tags ~@dockerignore"
