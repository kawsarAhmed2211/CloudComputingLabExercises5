/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cloudcomputinglab5part1;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Kawsar
 */
public class WeatherData {
    @SerializedName("date")
    private long date;
    @SerializedName("weather")
    private String weather;
    @SerializedName("temp2m")
    private Temp2M temp2M;
    @SerializedName("wind10m_max")
    private long wind10MMax;

    public long getDate() { 
        return date;
    }
    public void setDate(long value) { 
        this.date = value;
    }

    public String getWeather() { 
        return weather;
    }
    public void setWeather(String value) { 
        this.weather = value;
    }

    public Temp2M getTemp2M() { 
        return temp2M;
    }
    public void setTemp2M(Temp2M value) {
        this.temp2M = value; 
    }

    public long getWind10MMax() { 
        return wind10MMax; 
    }
    public void setWind10MMax(long value) {
        this.wind10MMax = value;
    }
}
