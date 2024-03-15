# Johannesburg Bean Exchange

## Infrastructure as Code
Steps to run in the Terraform folder:

1. Create a key pair and copy the private key into the Terraform folder.
2. terraform init
3. terraform plan --var-file="{SECRETS.tfvars} (Replace SECRETS with the name of your secrets file. This file should contain values for "ec2_db_password" and "public_key").
4. terraform apply --var-file="{SECRETS.tfvars}

## Installing Java 21 on EC2
1. ssh -i "jbe_kp.pem" ubuntu@{EC2_ENDPOINT} (Replace EC2_ENDPOINT with the actual endpoint).
2. wget -O - https://apt.corretto.aws/corretto.key | sudo gpg --dearmor -o /usr/share/keyrings/corretto-keyring.gpg && \
   echo "deb [signed-by=/usr/share/keyrings/corretto-keyring.gpg] https://apt.corretto.aws stable main" | sudo tee /etc/apt/sources.list.d/corretto.list
3. sudo apt-get update; sudo apt-get install -y java-21-amazon-corretto-jdk
4. sudo apt-get update && sudo apt-get install java-common
5. wget https://corretto.aws/downloads/latest/amazon-corretto-21-x64-linux-jdk.deb
6. sudo dpkg --install amazon-corretto-21-x64-linux-jdk.deb

## Adding Data to the Database
1. Connect to the database using the endpoint, username, and password.
2. Run the latest SQL script under db/migrations.

## Building & Running the Server
1. Set the required environment variables, as defined in the application.properties file, in your runtime environment.
2. In the server folder, run "mvn clean package"
3. Execute the jar file using "java -jar target/server.jar"

## Building & Running the Client
Navigate to the client folder:
1. Change the endpoint in the client file to the EC2 endpoint.
2. Build client using "mvn clean package"
3. Create JRE runtimes "jlink --module-path "{JAVA_PATH}\jmods" --add-modules java.base,java.net.http --output jre" (Replace JAVA_PATH with the path to your JDK folder).
4. Copy the client.jar file from client/target/ into the newly created jre folder.
5. Run the client using: ".\jre\bin\java -jar client.jar"

