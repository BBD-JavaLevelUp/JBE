package com.jbe.client;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.net.http.HttpClient;
import java.io.IOException;

public class APICall {
  public static void makeCall(String endpoint, String[] params)
  {
    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(endpoint))
      .method("GET", HttpRequest.BodyPublishers.noBody())
      .build();

    HttpResponse<String> response = null;
    try
    {
      response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }

    System.out.println(response.body());
  }
}
