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
class Temp2M {
    @SerializedName("max")
    private long max;
    @SerializedName("min")
    private long min;

    public long getMax() { 
        return max;
    }
    public void setMax(long value) {
        this.max = value; 
    }

    public long getMin() { 
        return min; 
    }
    public void setMin(long value) {
        this.min = value;
    }
}
