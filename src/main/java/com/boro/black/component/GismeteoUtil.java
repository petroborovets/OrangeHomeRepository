package com.boro.black.component;

import com.boro.black.entity.GismeteoData;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by petroborovets on 6/9/15.
 */
@Component
public class GismeteoUtil {

    static Logger log = Logger.getLogger(GismeteoUtil.class.getName());

    public static final String GISMETEO_URL = "http://www.gismeteo.ua/ua/weather-lviv-4949/";
    private WebClient webClient;
    // Pattern for recognizing a URL, based off RFC 3986
    private static final Pattern urlPattern = Pattern.compile(
            "(?:^|[\\W])(\\/\\/)"
                    + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                    + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);


    public GismeteoUtil() {
        webClient = new WebClient(BrowserVersion.FIREFOX_24);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getCookieManager().setCookiesEnabled(true);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
    }

    public static void main(String[] args) {
        GismeteoUtil gismeteoUtil = new GismeteoUtil();
        gismeteoUtil.getCurrentWeather();
    }

    public GismeteoData getCurrentWeather() {
        GismeteoData gismeteoData = new GismeteoData();

        try {
            HtmlPage homePage = webClient.getPage(GISMETEO_URL);

            //Getting temperature
            HtmlDefinitionDescription temperature = (HtmlDefinitionDescription) homePage.getByXPath("//DIV[@class='section higher']/DIV[@class='temp']/DD[1]").get(0);
            gismeteoData.setTemperature(((DomText) temperature.getFirstChild()).getData());

            //Getting wind speed
            HtmlDefinitionDescription windSpeed = (HtmlDefinitionDescription) homePage.getByXPath("//DIV[@class='section higher']/DIV[@class='wicon wind']/DL[1]/DD[1]").get(0);
            gismeteoData.setWindSpeed(((DomText) windSpeed.getFirstChild()).getData());

            //Getting pressure
            HtmlDefinitionDescription pressure = (HtmlDefinitionDescription) homePage.getByXPath("//DIV[@class='section higher']/DIV[@class='wicon barp']/DD[1]").get(0);
            gismeteoData.setPressure(((DomText) pressure.getFirstChild()).getData());

            //Getting humidity
            HtmlDivision humidity = (HtmlDivision) homePage.getByXPath("//DIV[@class='section higher']/DIV[@class='wicon hum']").get(0);
            gismeteoData.setHumidity(((DomText) humidity.getFirstChild()).getData());

            //Gettyng waterTemperature
            HtmlDefinitionDescription waterTemperature = (HtmlDefinitionDescription) homePage.getByXPath("//DIV[@class='section higher']/DIV[@class='wicon water']/DD[1]").get(0);
            gismeteoData.setWaterTemperature(((DomText) waterTemperature.getFirstChild()).getData());

            //Getting weather image URL
            HtmlDefinitionTerm weatherImageUrlDT = (HtmlDefinitionTerm) homePage.getByXPath("//DIV[@class='section higher']/DL[@class='cloudness']/DT[1]").get(0);
            String styleTextContainingImageURL = weatherImageUrlDT.getAttributeNode("style").getValue();

            String weatherImageUrl = getWeatherImageUrlFromStyleDiv(styleTextContainingImageURL);
            gismeteoData.setWeatherImageUrl(weatherImageUrl);

            log.info("Gismeteo weather data parsed. Temperature: [" + gismeteoData.getTemperature() + "]," +
                    " Humidity: [" + gismeteoData.getHumidity() + "]," +
                    " Pressure: [" + gismeteoData.getPressure() + "]," +
                    " Water temperature: [" + gismeteoData.getWaterTemperature() + "]," +
                    " Wind speed: [" + gismeteoData.getWindSpeed() + "]," +
                    " Weather image url: [" + gismeteoData.getWeatherImageUrl() + "],");

        } catch (IOException e) {
            log.error("Failed to parse Gismeteo home page weather data, page must be changed," + e.getMessage());
        }

        return gismeteoData;
    }

    private String getWeatherImageUrlFromStyleDiv(String weatherImageStyleString) throws IOException {
        String weatherImageUrl = null;

        Matcher matcher = urlPattern.matcher(weatherImageStyleString);
        while (matcher.find()) {
            int matchStart = matcher.start(1);
            int matchEnd = matcher.end();

            weatherImageUrl = weatherImageStyleString.substring(matchStart, matchEnd);
        }
        if (weatherImageUrl == null)
            throw new IOException("Failed to parse gismeteo weatherImageUrl.");

        StringBuilder weatherImageUrlBuilder;

        //Removing first two slashes
        if (weatherImageUrl.lastIndexOf(")") == weatherImageUrl.length() - 1) {
            weatherImageUrl = weatherImageUrl.substring(2);
            weatherImageUrlBuilder = new StringBuilder(weatherImageUrl);
            weatherImageUrlBuilder.deleteCharAt(weatherImageUrl.length() - 1);
        } else {
            weatherImageUrlBuilder = new StringBuilder(weatherImageUrl);
            weatherImageUrlBuilder.substring(2);
        }
        weatherImageUrl = weatherImageUrlBuilder.toString();

        log.info("Parsed Gismeteo weather image URL [" + weatherImageUrl + "]");

        return weatherImageUrl;
    }
}
