package com.boro.black.dto;

/**
 * Created by petroborovets on 6/10/15.
 */
public class GismeteoDataDTO {
    private Long id;
    private String temperature;
    private String windSpeed;
    private String pressure;
    private String humidity;
    private String waterTemperature;
    private String weatherImageUrl;
    private String createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(String waterTemperature) {
        this.waterTemperature = waterTemperature;
    }

    public String getWeatherImageUrl() {
        return weatherImageUrl;
    }

    public void setWeatherImageUrl(String weatherImageUrl) {
        this.weatherImageUrl = weatherImageUrl;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
