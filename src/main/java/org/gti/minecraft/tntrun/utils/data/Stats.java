package org.gti.minecraft.tntrun.utils.data;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class Stats {

    private void close(PreparedStatement statement, ResultSet resultSet){
        try{statement.close();resultSet.close();}
        catch (SQLException e){throw new RuntimeException(e);}
    }

    public boolean playersExists(UUID uuid){

        try {
            PreparedStatement statement = MySQL.getConnection().prepareStatement("SELECT * FROM "+MySQL.table+" WHERE UUID=?");
            statement.setString(1, uuid.toString());
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){close(statement, resultSet);return true;}
            else {statement.close();resultSet.close();return false;}

        }catch(SQLException e){throw new RuntimeException(e);}

    }

    public void createPlayer(UUID uuid){

        try {
            PreparedStatement statement = MySQL.getConnection().prepareStatement("INSERT INTO "+MySQL.table+" (UUID, WINS, LOSES) VALUES (?, ?, ?)");
            statement.setString(1, uuid.toString());
            statement.setInt(2, 0);
            statement.setInt(3, 0);
            statement.executeUpdate();
            String pName = Bukkit.getPlayer(uuid).getName();
            System.out.println("[Database] registered "+pName+" into "+MySQL.database+" database.");
        } catch (SQLException s){throw new RuntimeException(s);}

    }

    public String getUUID(UUID uuid) {
        try {
            PreparedStatement statement;
            statement = MySQL.getConnection().prepareStatement("SELECT * FROM " + MySQL.table + " WHERE UUID=?");
            statement.setString(1, uuid.toString());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){close(statement, resultSet);return resultSet.getString("UUID");}
            else{close(statement, resultSet);return "UUID cannot be found, player's row might not exist.";}

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getWins(UUID uuid){
        try {
            PreparedStatement statement;
            statement = MySQL.getConnection().prepareStatement("SELECT * FROM " + MySQL.table + " WHERE UUID=?");
            statement.setString(1, uuid.toString());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){close(statement, resultSet);return resultSet.getInt("WINS");}
            else{close(statement, resultSet);return 0;}

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getLoses(UUID uuid){
        try {
            PreparedStatement statement;
            statement = MySQL.getConnection().prepareStatement("SELECT * FROM " + MySQL.table + " WHERE UUID=?");
            statement.setString(1, uuid.toString());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){close(statement, resultSet);return resultSet.getInt("LOSES");}
            else{close(statement, resultSet);return 0;}

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setWins(UUID uuid, int wins){

        try {

            PreparedStatement statement = MySQL.getConnection().prepareStatement("UPDATE "+MySQL.table+" SET WINS=? WHERE UUID=?");
            statement.setInt(1, wins);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();

        } catch (SQLException s){throw new RuntimeException(s);}

    }

    public void setLoses(UUID uuid, int loses){

        try {

            PreparedStatement statement = MySQL.getConnection().prepareStatement("UPDATE "+MySQL.table+" SET LOSES=? WHERE UUID=?");
            statement.setInt(1, loses);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();

        } catch (SQLException s){throw new RuntimeException(s);}

    }

}
