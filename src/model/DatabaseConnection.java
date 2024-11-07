package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Kelas utilitas untuk menyediakan koneksi ke database.
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/gudang_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    /**
     * Mendapatkan koneksi ke database.
     *
     * @return Koneksi ke database.
     * @throws SQLException Jika terjadi kesalahan saat membuat koneksi.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}