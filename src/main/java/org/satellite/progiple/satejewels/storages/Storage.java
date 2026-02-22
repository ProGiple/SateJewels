package org.satellite.progiple.satejewels.storages;

public interface Storage {
    int getJewels(String playerName);
    void setJewels(String playerName, int amount);
    void clear();
}
