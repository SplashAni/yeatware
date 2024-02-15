package yeatware.utils.player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class BetaUtils {
    private static BetaUtils instance;

    private BetaUtils() {
    }

    public static BetaUtils get() {
        if (instance == null) {
            instance = new BetaUtils();
        }
        return instance;
    }

    private List<UUID> uuids;
    private Map<UUID, Boolean> betaCache;

    public void init() {
        uuids = new ArrayList<>();
        betaCache = new HashMap<>();
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

                for (String element : elements) {
                    UUID uuid = UUID.fromString(element);
                    uuids.add(uuid);
                    betaCache.put(uuid, true);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isBeta(UUID uuid) {
        if (betaCache.containsKey(uuid)) {
            return betaCache.get(uuid);
        }

        boolean isBeta = uuids.contains(uuid);

        betaCache.put(uuid, isBeta);
        return isBeta;
    }
}
