package org.satellite.progiple.satejewels.storages.db;

import org.bukkit.configuration.ConfigurationSection;
import org.novasparkle.lunaspring.API.database.AsyncExecutor;
import org.novasparkle.lunaspring.API.database.ResultSetHandler;
import org.satellite.progiple.satejewels.storages.Storage;

import java.util.List;
public class DataBase extends AsyncExecutor implements Storage {
    private final String tableName;
    private final ResultSetHandler<Integer> resultSetHandler;
    public DataBase(ConfigurationSection section) {
        super(section);
        this.tableName = section.getString("tableName");

        this.resultSetHandler = rs -> rs.getInt("JewelsAmount");
        this.createTable();
    }

    public void createTable() {
        this.executeSync(String.format("CREATE TABLE IF NOT EXISTS %s (Player varchar(55), JewelsAmount int);", this.tableName));
    }

    @Override
    public int getJewels(String nick) {
        return this.executeQuery(String.format("SELECT JewelsAmount FROM %s WHERE Player=?;", this.tableName), this.resultSetHandler, nick.toLowerCase()).stream().findFirst().orElse(0);
    }

    @Override
    public void setJewels(String nick, int amount) {
        if (amount < 0) amount = 0;
        nick = nick.toLowerCase();

        List<Integer> integerList = this.executeQuery(String.format("SELECT JewelsAmount FROM %s WHERE Player=?;", this.tableName), this.resultSetHandler, nick);
        if (integerList.isEmpty()) {
            this.executeSync(String.format("INSERT INTO %s (Player, JewelsAmount) VALUES (?, ?);", this.tableName), nick, amount);
        }
        else {
            this.executeSync(String.format("UPDATE %s SET JewelsAmount=? WHERE Player=?;", this.tableName), amount, nick);
        }
    }

    @Override
    public void clear() {
        this.executeAsync(String.format("TRUNCATE TABLE %s;", this.tableName));
    }
}

