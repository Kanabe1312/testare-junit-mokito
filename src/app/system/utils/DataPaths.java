package app.system.utils;

import java.nio.file.Path;

public final class DataPaths {

    private DataPaths() {
    }

    public static String of(String... parts) {
        return Path.of(System.getProperty("user.dir"), parts).toString();
    }
}
