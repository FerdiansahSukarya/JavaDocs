package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Kelas model untuk mengelola data barang dalam database.
 */
public class Gudang {
    private Connection connection;

    /**
     * Konstruktor untuk kelas Gudang.
     * Membuat koneksi ke database.
     */
    public Gudang() {
        try {
            String url = "jdbc:mysql://localhost:3306/gudang_db";
            String user = "root";
            String password = "";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Menambahkan barang baru ke database.
     *
     * @param barang Objek Barang yang akan ditambahkan.
     */
    public void tambahBarang(Barang barang) {
        String query = "INSERT INTO barang (nama, jumlah, harga) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, barang.getNama());
            stmt.setInt(2, barang.getJumlah());
            stmt.setDouble(3, barang.getHarga());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                barang.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Memperbarui data barang di database.
     *
     * @param barang Objek Barang dengan data yang diperbarui.
     */
    public void perbaruiBarang(Barang barang) {
        String query = "UPDATE barang SET nama = ?, jumlah = ?, harga = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, barang.getNama());
            stmt.setInt(2, barang.getJumlah());
            stmt.setDouble(3, barang.getHarga());
            stmt.setInt(4, barang.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Menghapus barang dari database.
     *
     * @param id ID barang yang akan dihapus.
     */
    public void hapusBarang(int id) {
        String query = "DELETE FROM barang WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mendapatkan daftar semua barang dari database.
     *
     * @return List objek Barang.
     */
    public List<Barang> getDaftarBarang() {
        List<Barang> barangList = new ArrayList<>();
        String query = "SELECT * FROM barang";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama");
                int jumlah = rs.getInt("jumlah");
                double harga = rs.getDouble("harga");
                barangList.add(new Barang(id, nama, jumlah, harga));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return barangList;
    }

    /**
     * Mendapatkan barang berdasarkan ID.
     *
     * @param id ID barang yang dicari.
     * @return Objek Barang jika ditemukan, null jika tidak.
     */
    public Barang getBarangById(int id) {
        String query = "SELECT * FROM barang WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Barang(
                        rs.getInt("id"),
                        rs.getString("nama"),
                        rs.getInt("jumlah"),
                        rs.getDouble("harga")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Mendapatkan daftar barang berdasarkan nama.
     *
     * @param nama Nama barang yang dicari.
     * @return List objek Barang.
     */
    public List<Barang> getBarangByNama(String nama) {
        List<Barang> barangList = new ArrayList<>();
        String query = "SELECT * FROM barang WHERE nama LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, "%" + nama + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                barangList.add(new Barang(
                        rs.getInt("id"),
                        rs.getString("nama"),
                        rs.getInt("jumlah"),
                        rs.getDouble("harga")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return barangList;
    }

    /**
     * Mencari barang berdasarkan nama.
     *
     * @param nama Nama barang yang dicari.
     * @return List objek Barang yang cocok dengan kriteria pencarian.
     */
    public List<Barang> cariBarang(String nama) {
        List<Barang> barangList = new ArrayList<>();
        String query = "SELECT * FROM barang WHERE nama LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, "%" + nama + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String namaBarang = rs.getString("nama");
                    int jumlah = rs.getInt("jumlah");
                    double harga = rs.getDouble("harga");
                    barangList.add(new Barang(id, namaBarang, jumlah, harga));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return barangList;
    }
}