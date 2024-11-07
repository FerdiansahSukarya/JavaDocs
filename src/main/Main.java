package main;

import model.Gudang;
import view.GudangView;
import controller.GudangController;

/**
 * Kelas utama untuk menjalankan aplikasi gudang.
 */
public class Main {
    /**
     * Method main untuk menjalankan aplikasi.
     *
     * @param args Argumen command line (tidak digunakan).
     */
    public static void main(String[] args) {
        Gudang model = new Gudang();
        GudangView view = new GudangView();
        GudangController controller = new GudangController(model, view);
        view.setVisible(true);
    }
}