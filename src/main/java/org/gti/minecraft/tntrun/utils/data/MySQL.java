package org.gti.minecraft.tntrun.utils.data;

import java.sql.*;

public class MySQL {

    private static Connection connection;

    private static String host;
    private static int port;
    public static String database;
    private static String username;
    private static String password;
    public static String table;

    public MySQL(String host, int port, String database, String username, String password, String table){

        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
        this.table = table;

    }


    public void initialize() {

        try {

            synchronized (this) {

                if (getConnection() != null && getConnection().isClosed()){return;}

                Class.forName("com.mysql.jdbc.Driver");
                setConnection(DriverManager.getConnection("jdbc:mysql//"+this.host+":"+this.port+"/"+
                                this.database,
                        this.username,
                        this.password));
                System.out.println("[Database] MySQL connection success");
                System.out.println("[Database] Initializing the table..");
                PreparedStatement statement = getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS "+table+" " +
                        "(UUID VARCHAR(100), WINS BIGINT(100), LOSES BIGINT(100))");
                statement.executeUpdate();
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("[Database] MySQL connection failed");
            throw new RuntimeException(e);
        }

    }

    public static Connection getConnection() {
        return connection;
    }
    private void setConnection(Connection connection) {
        this.connection = connection;
    }
}
