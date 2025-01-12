package org.satellite.progiple.satejewels.other.configs.managers;

import lombok.Getter;
import org.satellite.progiple.satejewels.SateJewels;
import org.satellite.progiple.satejewels.other.configs.DataConfig;

public class DataManager {
    @Getter
    private final static DataConfig dataConfig;
    static {
        dataConfig = new DataConfig("data.yml", SateJewels.getPlugin());
    }

    public static void reload() {
        dataConfig.reload();
    }

    public static int getValue(String nick) {
        return dataConfig.getValue(nick);
    }
}
