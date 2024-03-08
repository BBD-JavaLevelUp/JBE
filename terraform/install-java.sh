#!/bin/bash

#Add new package manager
wget -O - https://apt.corretto.aws/corretto.key | sudo gpg --dearmor -o /usr/share/keyrings/corretto-keyring.gpg && \
echo "deb [signed-by=/usr/share/keyrings/corretto-keyring.gpg] https://apt.corretto.aws stable main" | sudo tee /etc/apt/sources.list.d/corretto.list

#Install Corretto
sudo apt-get update; sudo apt-get install -y java-21-amazon-corretto-jdk

#Install Java common package
sudo apt-get update && sudo apt-get install java-common

#Install .deb file
sudo dpkg --install java-21-amazon-corretto-jdk_21.0.2.13-1_amd64.deb

#Create Jbe directory
sudo mkdir jbe