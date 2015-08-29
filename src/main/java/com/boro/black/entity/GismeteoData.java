package com.boro.black.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by petroborovets on 6/9/15.
 */
@Entity
@Table(name = "GISMETEO_DATA")
public class GismeteoData {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "TEMPERATURE", nullable = false, length = 3)
    private String temperature;
    @Column(name = "WIND_SPEED", nullable = false, length = 3)
    private String windSpeed;
    @Column(name = "PRESSURE", nullable = false, length = 4)
    private String pressure;
    @Column(name = "HUMIDITY", nullable = false, length = 3)
    private String humidity;
    @Column(name = "WATER_TEMPERATURE", nullable = false, length = 4)
    private String waterTemperature;
    @Column(name = "WEATHER_IMAGE_URL", nullable = false, length = 100)
    private String weatherImageUrl;
    @Column(name = "CREATE_DATE", nullable = false)
    private Date createDate;

    public GismeteoData() {
        createDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWeatherImageUrl() {
        return weatherImageUrl;
    }

    public void setWeatherImageUrl(String weatherImageUrl) {
        this.weatherImageUrl = weatherImageUrl;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
