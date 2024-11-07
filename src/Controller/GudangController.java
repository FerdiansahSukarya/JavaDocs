package controller;

import model.Gudang;
import model.Barang;
import view.GudangView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Kelas controller untuk mengelola interaksi antara model Gudang dan view GudangView.
 */
public class GudangController {
    private Gudang model;
    private GudangView view;

    /**
     * Konstruktor untuk kelas GudangController.
     *
     * @param model Model Gudang yang akan digunakan.
     * @param view  View GudangView yang akan digunakan.
     */
    public GudangController(Gudang model, GudangView view) {
        this.model = model;
        this.view = view;

        // Menambahkan ActionListener untuk tombol "Tambah"
        this.view.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = view.getNamaField();
                int jumlah = view.getJumlahField();
                double harga = view.getHargaField();

                Barang barang = new Barang(0, nama, jumlah, harga);
                model.tambahBarang(barang);
                view.clearInputFields();
                view.displayBarang(model.getDaftarBarang());
            }
        });

        // Menambahkan ActionListener untuk tombol "Perbarui"
        this.view.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = view.getSelectedRowId();
                if (id != -1) {
                    String nama = view.getNamaField();
                    int jumlah = view.getJumlahField();
                    double harga = view.getHargaField();

                    Barang barang = new Barang(id, nama, jumlah, harga);
                    model.perbaruiBarang(barang);
                    view.clearInputFields();
                    view.displayBarang(model.getDaftarBarang());
                } else {
                    JOptionPane.showMessageDialog(view, "Pilih data yang ingin diedit.");
                }
            }
        });

        // Menambahkan ActionListener untuk tombol "Hapus"
        this.view.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = view.getSelectedRowId();
                if (id != -1) {
                    model.hapusBarang(id);
                    view.clearInputFields();
                    view.displayBarang(model.getDaftarBarang());
                } else {
                    JOptionPane.showMessageDialog(view, "Pilih data yang ingin dihapus.");
                }
            }
        });

        // Menambahkan ActionListener untuk tombol "Lihat"
        this.view.getViewButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.displayBarang(model.getDaftarBarang());
            }
        });

        // Menambahkan ActionListener untuk tombol "Cari"
        this.view.getSearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = view.getSearchField().getText();
                List<Barang> hasilPencarian = model.cariBarang(keyword);
                view.displaySearchResults(hasilPencarian);
            }
        });
    }
}