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
class WeatherResponse {
     @SerializedName("dataseries")
    private WeatherData[] dataSeries;

    public WeatherData[] getDataSeries() {
        return dataSeries;
    }
     public void setDataseries(WeatherData[] dataseries) {
        this.dataSeries = dataseries;
    }
}
