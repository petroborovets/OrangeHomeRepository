package com.boro.black.component;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by petroborovets on 11/10/15.
 */
@Component
public class CrawlerUtil {
    public String getDomainFromUrl(String url) throws URISyntaxException {
        String domainUrl;
        if (url != null && url.length() > 0) {
            URI uri = new URI(url);
            if (uri.getHost() != null && uri.getHost().length() > 0) {
                domainUrl = uri.getHost();
            } else domainUrl = url;
        } else {
            domainUrl = "Undefined";
        }

        return domainUrl;
    }

    public static long compareTwoTimeStamps(java.sql.Timestamp currentTime, java.sql.Timestamp oldTime) {
        long milliseconds1 = oldTime.getTime();
        long milliseconds2 = currentTime.getTime();

        long diff = milliseconds2 - milliseconds1;
        long diffSeconds = diff / 1000;
        long diffMinutes = diff / (60 * 1000);
        long diffHours = diff / (60 * 60 * 1000);
        long diffDays = diff / (24 * 60 * 60 * 1000);

        return diffMinutes;
    }
}
