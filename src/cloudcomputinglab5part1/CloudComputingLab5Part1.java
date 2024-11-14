/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cloudcomputinglab5part1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CloudComputingLab5Part1 {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
        try {
            // Step 1: Set up parameters
            Map<String, String> parameters = new HashMap<>();
            System.out.print("Enter longitude: ");
            String lon = scanner.nextLine();  // Get longitude from user input
            System.out.print("Enter latitude: ");
            String lat = scanner.nextLine();  // Get latitude from user input
            parameters.put("lon", lon);
            parameters.put("lat", lat);
            //parameters.put("output", "json");

            // Step 2: Convert Map to query string
            String convertedParamsToString = parameters.entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

            // Step 3: Construct the URL
            URL url = new URL("http://localhost:8080/RESTServiceWeather/webresources/weather?"+convertedParamsToString);
            System.out.println(url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            // Step 4: Check for successful response
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 200 indicates success
                // Step 5: Read response using BufferedReader
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder content = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                // Close the connections
                in.close();
                con.disconnect();

                // Print the raw JSON response to debug structure
                //System.out.println("Raw JSON Response: " + content.toString());

                // Step 6: Parse JSON using Gson
                Gson gson = new GsonBuilder().create();
                WeatherResponse weatherResponse = gson.fromJson(content.toString(), WeatherResponse.class);
                WeatherData[] weatherArray = weatherResponse.getDataSeries();
                List<WeatherData> weatherList = Arrays.asList(weatherArray);

                weatherList.forEach(weather -> {
                    System.out.println("Date: " + weather.getDate());
                    System.out.println("Weather: " + weather.getWeather());
                    System.out.println("Max Temperature: " + weather.getTemp2M().getMax());
                    System.out.println("Min Temperature: " + weather.getTemp2M().getMin());
                    System.out.println("Wind Speed: " + weather.getWind10MMax());
                    System.out.println("-------------");
                });
            } else {
                System.out.println("Error: Unable to fetch weather data, Response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
