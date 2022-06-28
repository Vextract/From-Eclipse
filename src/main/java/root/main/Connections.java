package root.main;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {

    private static final String URL_FOR_MYSQL = "jdbc:mysql://localhost:3306/loggerdb?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
    private static final String USERNAME_FOR_MYSQL = "root";
    private static final String PASSWORD_FOR_MYSQL = "root";

    private static final String URL_FOR_POSTGRESQL = "jdbc:postgresql://localhost:5432/loggerdb";
    private static final String USERNAME_FOR_POSTGRESQL = "postgres";
    private static final String PASSWORD_FOR_POSTGRESQL = "root";

    public static final DB MONGO_DATABASE = new MongoClient(new MongoClientURI("mongodb://localhost:27017"))
            .getDB("DataLog");

    private static final String REQUEST_TO_BINANCE_PRICES = "https://api.binance.com/api/v3/ticker/price";
    private static final String REQUEST_TO_BINANCE_EXCHANGE_INFO = "https://api.binance.com/api/v3/exchangeInfo";

    public static Connection getSQLConnection(String databaseName) {
        Connection connection = null;
        if (databaseName.equalsIgnoreCase("postgresql")) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {
                connection = DriverManager.getConnection(
                        URL_FOR_POSTGRESQL,
                        USERNAME_FOR_POSTGRESQL,
                        PASSWORD_FOR_POSTGRESQL);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } else if (databaseName.equalsIgnoreCase("mysql")) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {
                connection = DriverManager.getConnection(
                        URL_FOR_MYSQL,
                        USERNAME_FOR_MYSQL,
                        PASSWORD_FOR_MYSQL);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return connection;
    }

    public static HttpURLConnection getBinanceHttpURLConnection(String filter) {
        try {
            if (filter.equalsIgnoreCase("all")) {
                URL url = new URL(REQUEST_TO_BINANCE_EXCHANGE_INFO);
                return (HttpURLConnection) url.openConnection();
            } else {
                URL url = new URL(REQUEST_TO_BINANCE_PRICES + "?symbol=" + filter);
                return (HttpURLConnection) url.openConnection();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
