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

public class CloudComputingLab5Part1 {
    public static void main(String[] args) {
        try {
            // Step 1: Set up parameters
            Map<String, String> parameters = new HashMap<>();
            parameters.put("lon", "-1.15");
            parameters.put("lat", "52.95");
            parameters.put("lang", "en");
            parameters.put("unit", "metric");
            parameters.put("output", "json");

            // Step 2: Convert Map to query string
            String convertedParamsToString = parameters.entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

            // Step 3: Construct the URL
            URL url = new URL("https://www.7timer.info/bin/civillight.php?" + convertedParamsToString);
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
                System.out.println("Raw JSON Response: " + content.toString());

                // Step 6: Parse JSON using Gson
                Gson gson = new GsonBuilder().create();
                WeatherResponse weatherResponse = gson.fromJson(content.toString(), WeatherResponse.class);

                // Step 7: Display specific weather information
                if (weatherResponse.getDataSeries() != null && weatherResponse.getDataSeries().length >0) {
                    WeatherData weatherData = weatherResponse.getDataSeries()[0]; // First day's data
                    System.out.println("Date: " + weatherData.getDate());
                    System.out.println("Weather: " + weatherData.getWeather());

                    if (weatherData.getTemp2M() != null) {
                        System.out.println("Max Temperature: " + weatherData.getTemp2M().getMax());
                        System.out.println("Min Temperature: " + weatherData.getTemp2M().getMin());
                    } else {
                        System.out.println("Temperature data is not available.");
                    }

                    System.out.println("Wind Speed: " + weatherData.getWind10MMax());
                } else {
                    System.out.println("Weather data is not available.");
                }
            } else {
                System.out.println("Error: Unable to fetch weather data, Response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
