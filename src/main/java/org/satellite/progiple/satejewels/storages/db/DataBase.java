package org.satellite.progiple.satejewels.storages.db;

import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.satellite.progiple.satejewels.SateJewels;
import org.satellite.progiple.satejewels.Utils.Tools;
import org.satellite.progiple.satejewels.storages.Storage;

import java.sql.*;

public class DataBase implements Storage {
    private final String username;
    private final String dbName;
    private final String password;
    private final int port;
    private final String tableName;

    private Connection connection;

    @SneakyThrows
    public DataBase(ConfigurationSection section) {
        this.username = section.getString("username");
        this.dbName = section.getString("username");
        this.password = section.getString("username");
        this.port = section.getInt("port");
        this.tableName = section.getString("username");
        this.initialize();
    }

    public void initialize() throws SQLException, ClassNotFoundException {
        synchronized (SateJewels.getPlugin()) {
            if (this.connection != null && !this.connection.isClosed()) {
                System.out.println("Текущее соединение еще открыто!");;
            } else {
                Class.forName("com.mysql.cj.jdbc.Driver");
                this.connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", this.username, this.port, this.dbName), this.username, this.password);

                try (PreparedStatement statement =
                             this.connection.prepareStatement(String.format("CREATE TABLE IF NOT EXISTS %s (ID int PRIMARY KEY NOT NULL AUTO_INCREMENT, Player varchar(55), JewelsAmount int)", this.tableName))) {
                    statement.executeUpdate();
                }

            }
        }
    }

    @Override
    @SneakyThrows
    public int getJewels(String nick) {
        String playerName = nick.toLowerCase();
        PreparedStatement statement = this.connection.prepareStatement(String.format("SELECT JewelsAmount FROM %s WHERE Player=? FOR UPDATE", this.tableName));

        statement.setString(1, playerName);
        ResultSet resultSet = statement.executeQuery();

        return resultSet.getInt("JewelsAmount");
    }

    @Override
    public void setJewels(String nick, int amount) {
        String playerName = nick.toLowerCase();
        Bukkit.getScheduler().runTaskAsynchronously(SateJewels.getPlugin(), () -> {
            try {
                PreparedStatement statement = this.connection.prepareStatement(String.format("SELECT JewelsAmount FROM %s WHERE Player=? FOR UPDATE", this.tableName));
                statement.setString(1, playerName);

                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    PreparedStatement updateState = this.connection.prepareStatement(String.format("UPDATE %s SET JewelsAmount=? WHERE Player=?", this.tableName));
                    updateState.setInt(1, amount);
                    updateState.setString(2, playerName);

                    updateState.executeUpdate();
                } else {
                    PreparedStatement insertState = this.connection.prepareStatement(String.format("INSERT INTO %s (JewelsAmount, Player) VALUES (?, ?)", this.tableName));
                    insertState.setInt(1, amount);
                    insertState.setString(1, playerName);

                    insertState.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        Tools.syncSJtoPP(playerName);
    }

    @Override
    public void clear() {
        Bukkit.getScheduler().runTaskAsynchronously(SateJewels.getPlugin(), () -> {
            try {
                PreparedStatement state = connection.prepareStatement("TRUNCATE TABLE " + this.tableName);
                state.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
