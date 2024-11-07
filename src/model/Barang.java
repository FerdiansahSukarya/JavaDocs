package model;

/**
 * Kelas model untuk merepresentasikan data barang.
 */
public class Barang {
    private int id;
    private String nama;
    private int jumlah;
    private double harga;

    /**
     * Konstruktor untuk kelas Barang.
     *
     * @param id     ID barang.
     * @param nama   Nama barang.
     * @param jumlah Jumlah barang.
     * @param harga  Harga barang.
     */
    public Barang(int id, String nama, int jumlah, double harga) {
        this.id = id;
        this.nama = nama;
        this.jumlah = jumlah;
        this.harga = harga;
    }

    /**
     * Mendapatkan ID barang.
     *
     * @return ID barang.
     */
    public int getId() {
        return id;
    }

    /**
     * Mengatur ID barang.
     *
     * @param id ID barang yang baru.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Mendapatkan nama barang.
     *
     * @return Nama barang.
     */
    public String getNama() {
        return nama;
    }

    /**
     * Mengatur nama barang.
     *
     * @param nama Nama barang yang baru.
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * Mendapatkan jumlah barang.
     *
     * @return Jumlah barang.
     */
    public int getJumlah() {
        return jumlah;
    }

    /**
     * Mengatur jumlah barang.
     *
     * @param jumlah Jumlah barang yang baru.
     */
    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    /**
     * Mendapatkan harga barang.
     *
     * @return Harga barang.
     */
    public double getHarga() {
        return harga;
    }

    /**
     * Mengatur harga barang.
     *
     * @param harga Harga barang yang baru.
     */
    public void setHarga(double harga) {
        this.harga = harga;
    }

}