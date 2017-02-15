package pl.mw;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Created by mwisniewski.
 */
public class Configuration {

    private static final Config config;

    static {
        System.setProperty("logback.configurationFile", "conf/logback.xml");
        System.setProperty("config.file", "conf/application.conf");
        config = ConfigFactory.load();
    }

    public static int getPort() {
        return config.getInt("system.port");
    }

    public static Config getConfig() {
        return config;
    }

    public static String getRootDir() {
        return config.getString("system.rootDir");
    }

}
