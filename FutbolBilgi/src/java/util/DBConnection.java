package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public Connection connect() {
        Connection c = null;

        try {
            Class.forName("org.postgresql.Driver").newInstance();
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/futboldb", "postgres", "123");
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.out.println(ex.getMessage());
        }

        return c;
    }

}
