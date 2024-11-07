package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
/**
 * Kelas view untuk menampilkan antarmuka pengguna aplikasi gudang.
 */
public class GudangView extends JFrame {
    private JTextField idField;
    private JTextField namaField;
    private JTextField jumlahField;
    private JTextField hargaField;
    private JTextField searchField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton viewButton;
    private JButton searchButton;
    private JButton exitButton;
    private JTable mainTable;
    private JTable searchTable;
    private DefaultTableModel mainTableModel;
    private DefaultTableModel searchTableModel;
    private NumberFormat currencyFormat;

    private static final Font DEFAULT_FONT = new Font("SansSerif", Font.PLAIN, 14);
    private static final Font BUTTON_FONT = DEFAULT_FONT.deriveFont(DEFAULT_FONT.getSize() + 2f);
    private static final Font LABEL_FONT = DEFAULT_FONT.deriveFont(DEFAULT_FONT.getSize() + 2f); 
    private static final Font TEXT_FIELD_FONT = DEFAULT_FONT.deriveFont(DEFAULT_FONT.getSize() + 2f); 

     /**
     * Konstruktor untuk kelas GudangView.
     * Inisialisasi komponen UI dan mengatur tampilan.
     */
    
    public GudangView() {
        idField = new JTextField(15);
        namaField = new JTextField(15);
        jumlahField = new JTextField(15);
        hargaField = new JTextField(15);

        searchField = new JTextField(15);
        searchButton = new JButton("Cari");

        addButton = new JButton("Tambah Barang");
        updateButton = new JButton("Edit Barang");
        deleteButton = new JButton("Hapus Barang");
        viewButton = new JButton("Lihat Data Barang");
        exitButton = new JButton("Keluar");

        mainTableModel = new DefaultTableModel();
        mainTableModel.setColumnIdentifiers(new String[]{"ID", "Nama", "Jumlah", "Harga"});
        mainTable = new JTable(mainTableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        searchTableModel = new DefaultTableModel();
        searchTableModel.setColumnIdentifiers(new String[]{"ID", "Nama", "Jumlah", "Harga"});
        searchTable = new JTable(searchTableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

        setComponentFont(idField, TEXT_FIELD_FONT);
        setComponentFont(namaField, TEXT_FIELD_FONT);
        setComponentFont(jumlahField, TEXT_FIELD_FONT);
        setComponentFont(hargaField, TEXT_FIELD_FONT);
        setComponentFont(searchField, TEXT_FIELD_FONT);
        setComponentFont(addButton, BUTTON_FONT);
        setComponentFont(updateButton, BUTTON_FONT);
        setComponentFont(deleteButton, BUTTON_FONT);
        setComponentFont(viewButton, BUTTON_FONT);
        setComponentFont(searchButton, BUTTON_FONT);
        setComponentFont(exitButton, BUTTON_FONT);
        setTableFont(mainTable, TEXT_FIELD_FONT);
        setTableFont(searchTable, TEXT_FIELD_FONT);

        setLayout(new BorderLayout());

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel1.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        panel1.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel1.add(new JLabel("Nama:"), gbc);
        gbc.gridx = 1;
        panel1.add(namaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel1.add(new JLabel("Jumlah:"), gbc);
        gbc.gridx = 1;
        panel1.add(jumlahField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel1.add(new JLabel("Harga:"), gbc);
        gbc.gridx = 1;
        panel1.add(hargaField, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 5, 5)); 
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(exitButton);

        JPanel panel1Container = new JPanel(new BorderLayout());
        panel1Container.add(panel1, BorderLayout.CENTER);
        panel1Container.add(buttonPanel, BorderLayout.EAST);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Cari Nama:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        panel2.add(searchPanel, BorderLayout.NORTH);
        panel2.add(new JScrollPane(searchTable), BorderLayout.CENTER);

        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.add(new JScrollPane(mainTable), BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel1Container, panel2);
        splitPane.setResizeWeight(0.7);
        splitPane.setDividerLocation(0.7); 

        add(splitPane, BorderLayout.CENTER);
        add(panel3, BorderLayout.SOUTH);

        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        mainTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = mainTable.getSelectedRow();
                if (row != -1) {
                    idField.setText(mainTableModel.getValueAt(row, 0).toString());
                    namaField.setText(mainTableModel.getValueAt(row, 1).toString());
                    jumlahField.setText(mainTableModel.getValueAt(row, 2).toString());
                    hargaField.setText(mainTableModel.getValueAt(row, 3).toString());
                }
            }
        });

        exitButton.addActionListener(e -> dispose()); 
    }
     /**
     * Mengatur font untuk komponen UI.
     *
     * @param component Komponen UI yang akan diatur font-nya.
     * @param font      Font yang akan digunakan.
     */
    
    private void setComponentFont(JComponent component, Font font) {
        component.setFont(font);
    }
    /**
     * Mengatur font untuk tabel.
     *
     * @param table Tabel yang akan diatur font-nya.
     * @param font  Font yang akan digunakan.
     */
    private void setTableFont(JTable table, Font font) {
        table.setFont(font);
        table.getTableHeader().setFont(font.deriveFont(Font.BOLD));
    }
    /**
     * Mendapatkan nilai harga dari field harga.
     *
     * @return Nilai harga dalam bentuk double.
     */
    public double getHargaField() {
        try {
            return Double.parseDouble(hargaField.getText().replace("Rp", "").replace(".", "").replace(",", ".").trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
     /**
     * Mendapatkan nilai jumlah dari field jumlah.
     *
     * @return Nilai jumlah dalam bentuk integer.
     */
    public int getJumlahField() {
        try {
            return Integer.parseInt(jumlahField.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    /**
     * Mendapatkan nilai nama dari field nama.
     *
     * @return Nilai nama dalam bentuk string.
     */

    public String getNamaField() {
        return namaField.getText();
    }
    /**
     * Mendapatkan nilai ID dari field ID.
     *
     * @return Nilai ID dalam bentuk integer.
     */
    public int getIdField() {
        try {
            return Integer.parseInt(idField.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
     /**
     * Mendapatkan ID baris yang dipilih di tabel utama.
     *
     * @return ID baris yang dipilih, atau -1 jika tidak ada baris yang dipilih.
     */
    public int getSelectedRowId() {
        int row = mainTable.getSelectedRow();
        if (row != -1) {
            return (int) mainTableModel.getValueAt(row, 0);
        }
        return -1;
    }
    /**
     * Menampilkan daftar barang di tabel utama.
     *
     * @param barangList Daftar barang yang akan ditampilkan.
     */
    public void displayBarang(List<model.Barang> barangList) {
        mainTableModel.setRowCount(0);
        for (model.Barang b : barangList) {
            mainTableModel.addRow(new Object[]{
                b.getId(),
                b.getNama(),
                b.getJumlah(),
                currencyFormat.format(b.getHarga()) 
            });
        }
    }
     /**
     * Menampilkan hasil pencarian barang di tabel pencarian.
     *
     * @param barangList Daftar barang hasil pencarian.
     */
    public void displaySearchResults(List<model.Barang> barangList) {
        searchTableModel.setRowCount(0);
        for (model.Barang b : barangList) {
            searchTableModel.addRow(new Object[]{
                b.getId(),
                b.getNama(),
                b.getJumlah(),
                currencyFormat.format(b.getHarga())
            });
        }
    }
     /**
     * Menghapus isi dari semua field input.
     */
    public void clearInputFields() {
        idField.setText("");
        namaField.setText("");
        jumlahField.setText("");
        hargaField.setText("");
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getViewButton() {
        return viewButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }
}
