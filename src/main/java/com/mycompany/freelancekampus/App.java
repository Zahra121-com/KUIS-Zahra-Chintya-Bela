package com.mycompany.freelancekampus;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    String FONT = "-fx-font-family: 'Times New Roman'; -fx-font-size: 14px;";
    String BIRU = "#5DADE2";
    String BABY_BLUE = "#CFEAFB";

    String STYLE_TOMBOL = "-fx-background-color: #5DADE2; -fx-text-fill: black; -fx-background-radius: 10;";
    String STYLE_TOMBOL_PUTIH = "-fx-background-color: white; -fx-text-fill: black; -fx-background-radius: 10;";
    String STYLE_JUDUL = "-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: black;";

    ArrayList<Jasa> daftarJasa = new ArrayList<>();
    ArrayList<Pesanan> daftarPesanan = new ArrayList<>();

    Scene scene;
    VBox halamanDepan, halamanPenyedia, halamanPemesan;

    TextField txtNamaPenyedia = new TextField();
    TextField txtJudul = new TextField();
    TextField txtKategori = new TextField();
    TextField txtHarga = new TextField();
    ListView<String> listJasaPenyedia = new ListView<>();
    ListView<String> listPesananPenyedia = new ListView<>();
    ComboBox<String> cbStatus = new ComboBox<>();

    TextField txtNamaPemesan = new TextField();
    TextField txtBrief = new TextField();
    ListView<String> listJasaPemesan = new ListView<>();
    ListView<String> listPesananPemesan = new ListView<>();

    @Override
    public void start(Stage primaryStage) {
        halamanDepan = buatHalamanDepan();
        halamanPenyedia = buatHalamanPenyedia();
        halamanPemesan = buatHalamanPemesan();

        scene = new Scene(halamanDepan, 600, 650);
        primaryStage.setTitle("FreelanceKampus - Zahra Chintya Bela (2517052012)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox buatHalamanDepan() {
        Label lblJudul = new Label("FreelanceKampus");
        lblJudul.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: black;");

        Label lblSub = new Label("Marketplace jasa mahasiswa");
        lblSub.setStyle("-fx-text-fill: black;");

        Button btnPenyedia = new Button("Masuk sebagai Penyedia");
        btnPenyedia.setPrefWidth(220);
        btnPenyedia.setStyle(STYLE_TOMBOL_PUTIH);
        btnPenyedia.setOnAction(e -> scene.setRoot(halamanPenyedia));

        Button btnPemesan = new Button("Masuk sebagai Pemesan");
        btnPemesan.setPrefWidth(220);
        btnPemesan.setStyle(STYLE_TOMBOL_PUTIH);
        btnPemesan.setOnAction(e -> scene.setRoot(halamanPemesan));

        VBox box = new VBox(15, lblJudul, lblSub, btnPenyedia, btnPemesan);
        box.setAlignment(Pos.CENTER);
        box.setStyle(FONT + "-fx-background-color: " + BIRU + ";");
        return box;
    }

    private VBox buatHalamanPenyedia() {
        Label lblJudul = new Label("Halaman Penyedia Jasa");
        lblJudul.setStyle(STYLE_JUDUL);

        Label lblNama = new Label("Nama Penyedia");
        Label lblJudulJasa = new Label("Judul Jasa");
        Label lblKategori = new Label("Kategori");
        Label lblHarga = new Label("Harga (Rp)");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(8);
        grid.add(lblNama, 0, 0);
        grid.add(txtNamaPenyedia, 1, 0);
        grid.add(lblJudulJasa, 0, 1);
        grid.add(txtJudul, 1, 1);
        grid.add(lblKategori, 0, 2);
        grid.add(txtKategori, 1, 2);
        grid.add(lblHarga, 0, 3);
        grid.add(txtHarga, 1, 3);

        Button btnKembali = new Button("Kembali");
        btnKembali.setStyle(STYLE_TOMBOL);
        btnKembali.setOnAction(e -> scene.setRoot(halamanDepan));

        Button btnTambah = new Button("Tambah Jasa");
        btnTambah.setStyle(STYLE_TOMBOL);
        btnTambah.setOnAction(e -> tambahJasa());

        cbStatus.getItems().addAll("Diterima", "Dikerjakan", "Selesai");
        cbStatus.setPromptText("Pilih status");

        Button btnUbah = new Button("Ubah Status");
        btnUbah.setStyle(STYLE_TOMBOL);
        btnUbah.setOnAction(e -> ubahStatus());

        HBox containerStatus = new HBox(10, cbStatus, btnUbah);

        listJasaPenyedia.setPrefHeight(100);
        listPesananPenyedia.setPrefHeight(100);

        VBox box = new VBox(8, btnKembali, lblJudul, grid, btnTambah,
                new Label("Daftar Jasa:"), listJasaPenyedia,
                new Label("Pesanan Masuk:"), listPesananPenyedia, containerStatus);
        box.setPadding(new Insets(15));
        box.setStyle(FONT + "-fx-background-color: " + BABY_BLUE + ";");
        return box;
    }

    private VBox buatHalamanPemesan() {
        Label lblJudul = new Label("Halaman Pemesan");
        lblJudul.setStyle(STYLE_JUDUL);

        Label lblNama = new Label("Nama Pemesan");
        Label lblBrief = new Label("Brief");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(8);
        grid.add(lblNama, 0, 0);
        grid.add(txtNamaPemesan, 1, 0);
        grid.add(lblBrief, 0, 1);
        grid.add(txtBrief, 1, 1);

        Button btnKembali = new Button("Kembali");
        btnKembali.setStyle(STYLE_TOMBOL);
        btnKembali.setOnAction(e -> scene.setRoot(halamanDepan));

        Button btnPesan = new Button("Pesan Jasa Terpilih");
        btnPesan.setStyle(STYLE_TOMBOL);
        btnPesan.setOnAction(e -> pesanJasa());

        listJasaPemesan.setPrefHeight(130);
        listPesananPemesan.setPrefHeight(130);

        VBox box = new VBox(8, btnKembali, lblJudul,
                new Label("Pilih jasa:"), listJasaPemesan,
                grid, btnPesan,
                new Label("Pesanan Saya:"), listPesananPemesan);
        box.setPadding(new Insets(15));
        box.setStyle(FONT + "-fx-background-color: " + BABY_BLUE + ";");
        return box;
    }

    private void tambahJasa() {
        String strNama = txtNamaPenyedia.getText().trim();
        String strJudul = txtJudul.getText().trim();
        String strKategori = txtKategori.getText().trim();
        String strHarga = txtHarga.getText().trim();

        if (strNama.isEmpty() || strJudul.isEmpty() || strKategori.isEmpty() || strHarga.isEmpty()) {
            tampilkanAlert(AlertType.WARNING, "Peringatan", "Seluruh kolom form wajib diisi!");
            return;
        }

        try {
            int harga = Integer.parseInt(strHarga);

            if (harga <= 0) {
                tampilkanAlert(AlertType.WARNING, "Peringatan", "Harga harus lebih dari 0!");
                return;
            }

            Penyedia penyedia = new Penyedia(strNama, "", "");
            daftarJasa.add(new Jasa(strJudul, strKategori, harga, penyedia));
            txtJudul.clear();
            txtKategori.clear();
            txtHarga.clear();
            refresh();
        } catch (NumberFormatException ex) {
            tampilkanAlert(AlertType.ERROR, "Kesalahan Format", "Harga harus berupa angka, contoh: 50000.");
        }
    }

    private void pesanJasa() {
        int pilih = listJasaPemesan.getSelectionModel().getSelectedIndex();
        String strNama = txtNamaPemesan.getText().trim();
        String strBrief = txtBrief.getText().trim();

        if (pilih < 0) {
            tampilkanAlert(AlertType.WARNING, "Peringatan", "Pilih jasa terlebih dahulu!");
            return;
        }

        if (strNama.isEmpty() || strBrief.isEmpty()) {
            tampilkanAlert(AlertType.WARNING, "Peringatan", "Nama dan brief wajib diisi!");
            return;
        }

        Pemesan pemesan = new Pemesan(strNama, "");
        daftarPesanan.add(new Pesanan(daftarJasa.get(pilih), pemesan, strBrief));
        txtBrief.clear();
        refresh();
        tampilkanAlert(AlertType.INFORMATION, "Pemesanan Berhasil", "Pesanan berhasil dibuat.");
    }

    private void ubahStatus() {
        int pilih = listPesananPenyedia.getSelectionModel().getSelectedIndex();
        String statusBaru = cbStatus.getValue();

        if (pilih < 0) {
            tampilkanAlert(AlertType.WARNING, "Peringatan", "Pilih pesanan terlebih dahulu!");
            return;
        }

        if (statusBaru == null) {
            tampilkanAlert(AlertType.WARNING, "Peringatan", "Pilih status terlebih dahulu!");
            return;
        }

        daftarPesanan.get(pilih).setStatus(statusBaru);
        refresh();
    }

    private void tampilkanAlert(AlertType tipe, String judul, String pesan) {
        Alert alert = new Alert(tipe);
        alert.setTitle(judul);
        alert.setHeaderText(null);
        alert.setContentText(pesan);
        alert.showAndWait();
    }

    private void refresh() {
        listJasaPenyedia.getItems().clear();
        listJasaPemesan.getItems().clear();
        for (int i = 0; i < daftarJasa.size(); i++) {
            String teks = daftarJasa.get(i).getRingkas(i + 1);
            listJasaPenyedia.getItems().add(teks);
            listJasaPemesan.getItems().add(teks);
        }

        listPesananPenyedia.getItems().clear();
        listPesananPemesan.getItems().clear();
        for (int i = 0; i < daftarPesanan.size(); i++) {
            String teks = daftarPesanan.get(i).getRingkas(i + 1);
            listPesananPenyedia.getItems().add(teks);
            listPesananPemesan.getItems().add(teks);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}