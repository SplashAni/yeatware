package yeatware.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class BetaUtils {

    public static boolean isBeta(UUID username) {
        URL url;
        try {
            url = new URL("https://raw.githubusercontent.com/SplashAni/yeatware/main/beta-uuids");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] elements = line.split(",");

                for (String element : elements)
                    if (element.trim().equals(username.toString())) {
                        return true;
                    }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
