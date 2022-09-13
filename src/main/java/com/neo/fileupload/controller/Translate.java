package com.neo.fileupload.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Translate {

  public static void main(String[] args) throws IOException {
    String text = "good morning!";
    // Translated text: Hallo Welt!
    //System.out.println("Translated text: " + translate("en", "de", text));
  }

  public String translate(String langFrom, String langTo, String text) throws IOException {
    // INSERT YOU URL HERE
    String urlStr =
        "https://script.google.com/macros/s/AKfycbwOy-4_m64SUT4NEeUk83OVgrpewoz83Lgk_Woe3JvTPHSAocF-ABsZMeGTCXxffv0c/exec"
            + "?q="
            + URLEncoder.encode(text, "UTF-8")
            + "&target="
            + langTo
            + "&source="
            + langFrom;
    URL url = new URL(urlStr);
    StringBuilder response = new StringBuilder();
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestProperty("User-Agent", "Mozilla/5.0");
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();
    return response.toString();
  }
}
