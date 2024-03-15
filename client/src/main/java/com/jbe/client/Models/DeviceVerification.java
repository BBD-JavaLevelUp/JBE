package com.jbe.client.Models;

public record DeviceVerification(String device_code, String user_code, String verification_uri, Integer expires_in, Integer interval) {
}