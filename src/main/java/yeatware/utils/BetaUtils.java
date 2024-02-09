package yeatware.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.UUID;

import static yeatware.Main.mc;

public class BetaUtils {
    public static final boolean beta = isBeta(mc.getGameProfile().getId());
    private static boolean isBeta(UUID userid) {
        try {
            URL url = new URL("https://raw.githubusercontent.com/SplashAni/yeatware/main/beta-uuids");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] elements = line.split(",");
                    for (String element : elements) {
                        if (element.trim().equals(userid.toString())) {
                            return true;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed To Grab Beta Status");
        }
        return false;
    }
}
