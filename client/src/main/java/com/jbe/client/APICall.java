package com.jbe.client;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.net.http.HttpClient;

public class APICall {
  public static String url = "http://ec2-34-242-203-156.eu-west-1.compute.amazonaws.com:8080";

  public static String get(String endpoint, String[] params)
  {
    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(url+endpoint))
      .method("GET", HttpRequest.BodyPublishers.noBody())
      .build();

    HttpResponse<String> response = null;
    try
    {
      System.out.println(RestApiHandler.ANSI_MAGENTA + "loading..." + RestApiHandler.ANSI_RESET);
      response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }
    catch (Exception e)
    {
      System.out.println(RestApiHandler.ANSI_RED + "An error occured making get request to " + endpoint + RestApiHandler.ANSI_RESET);
    }

    return response.body();
  }

  public static HttpResponse<String> post(String endpoint, String body)
  {
    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(url+endpoint))
      .method("POST", HttpRequest.BodyPublishers.ofString(body))
      .setHeader("Content-Type", "application/json")
      .build();

    HttpResponse<String> response = null;
    try
    {
      response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }
    catch (Exception e)
    {
      System.out.println(RestApiHandler.ANSI_RED + "An error occured making post request to " + endpoint + RestApiHandler.ANSI_RESET);
    }

    return response;
  }
}
