package org.satellite.progiple.satejewels.api;

import org.bukkit.event.Listener;

public class ZAuctionHouse implements Listener {
//    public void register() {
//        AuctionManager auctionManager = SateJewels.getINSTANCE().getProvider(AuctionManager.class);
//        if (auctionManager == null) return;
//
//        auctionManager.registerEconomy(new SateAucEconomy());
//    }
//
//    public static class SateAucEconomy implements AuctionEconomy {
//        @Override
//        public long getMoney(OfflinePlayer offlinePlayer) {
//            return SateJewels.getINSTANCE().getSjapi().getJewels(offlinePlayer);
//        }
//
//        @Override
//        public boolean hasMoney(OfflinePlayer offlinePlayer, long l) {
//            return this.getMoney(offlinePlayer) >= l;
//        }
//
//        @Override
//        public void depositMoney(OfflinePlayer offlinePlayer, long l) {
//            SateJewels.getINSTANCE().getSjapi().giveJewels(offlinePlayer, (int) l);
//        }
//
//        @Override
//        public void withdrawMoney(OfflinePlayer offlinePlayer, long l) {
//            SateJewels.getINSTANCE().getSjapi().removeJewels(offlinePlayer, (int) l);
//        }
//
//        @Override
//        public String getCurrency() {
//            return "%price%" + this.getFormat();
//        }
//
//        @Override
//        public String getFormat() {
//            return SateJewels.getINSTANCE().getSjapi().getJewelNames().values().stream().findFirst().orElse("SJ");
//        }
//
//        @Override
//        public boolean isEnable() {
//            return SateJewels.getINSTANCE().getSjapi() != null;
//        }
//
//        @Override
//        public String getName() {
//            return "satejewels";
//        }
//    }
}
