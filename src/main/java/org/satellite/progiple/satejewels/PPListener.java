package org.satellite.progiple.satejewels;

import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.black_ixx.playerpoints.event.PlayerPointsChangeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.satellite.progiple.satejewels.api.SJAPI;
import org.satellite.progiple.satejewels.storages.configs.managers.ConfigManager;

public class PPListener implements Listener {
    @EventHandler
    public void event(PlayerPointsChangeEvent e) {
        if (ConfigManager.getBool("jewelsSettings.playerPointsAutoTransfer")) {
            PlayerPointsAPI ppapi = PlayerPoints.getInstance().getAPI();
            if (ppapi == null || e.getChange() <= 0) return;
            SJAPI.setJewels(e.getPlayerId(), ppapi.look(e.getPlayerId()));
        }
    }
}
