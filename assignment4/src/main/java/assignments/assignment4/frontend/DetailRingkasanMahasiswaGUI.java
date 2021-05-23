package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

import static assignments.assignment4.frontend.SistemAkademikGUI.*;

public class DetailRingkasanMahasiswaGUI {
    private JPanel panel = new JPanel();
    private JFrame frame;
    private ArrayList<Mahasiswa> daftarMahasiswa;
    private ArrayList<MataKuliah> daftarMataKuliah;
    private Mahasiswa mahasiswa;

    public DetailRingkasanMahasiswaGUI(JFrame frame, Mahasiswa mahasiswa, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah) {
        this.frame = frame;
        this.daftarMahasiswa = daftarMahasiswa;
        this.daftarMataKuliah = daftarMataKuliah;
        this.mahasiswa = mahasiswa;

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(0, 40))); // add top buffer

        initComponents();

        frame.getContentPane().add(panel);
        frame.revalidate();
    }

    private void initComponents() {
        addTitle(new JLabel("Detail Ringkasan Mahasiswa"), panel);

        addComponent(new JLabel(String.format("Nama: %s", mahasiswa.getNama())), panel);
        addComponent(new JLabel(String.format("NPM: %d", mahasiswa.getNpm())), panel);
        addComponent(new JLabel(String.format("Jurusan: %s", mahasiswa.getJurusan())), panel);
        addComponent(new JLabel("Daftar Mata Kuliah:"), panel);
        String daftarMatkul = "";
        if (mahasiswa.getBanyakMatkul() == 0) {
            daftarMatkul = "Belum ada mata kuliah yang diambil.";
        } else {
            MataKuliah[] mataKuliahs = mahasiswa.getMataKuliah();
            for (int i = 0; i < mataKuliahs.length; i++) {
                if (mataKuliahs[i] == null)
                    break;
                daftarMatkul += "<html>" + (i + 1) + ". " + mataKuliahs[i].getNama() + "<br>" + "<html>";
            }
        }

        JLabel daftarMatkulLabel = new JLabel(daftarMatkul, SwingConstants.CENTER);
        addComponent(daftarMatkulLabel, panel);
        Font f = daftarMatkulLabel.getFont();
        daftarMatkulLabel.setFont(f.deriveFont(Font.BOLD));

        addComponent(new JLabel(String.format("SKS: %d", mahasiswa.getTotalSKS())), panel);
        addComponent(new JLabel("Hasil Pengecekan IRS"), panel);
        String daftarMasalahIRS = "";
        if (mahasiswa.getBanyakMasalahIRS() == 0) {
            daftarMasalahIRS = "IRS tidak bermasalah.";
        } else {
            mahasiswa.cekIRS();
            String[] masalahIRS = mahasiswa.getMasalahIRS();
            for (int i = 0; i < masalahIRS.length; i++) {
                daftarMasalahIRS += "<html>" + (i + 1) + ". " + masalahIRS[i] + "<br>" + "<html>";
            }
        }

        JLabel daftarMasalahIRSLabel = new JLabel(daftarMasalahIRS, SwingConstants.CENTER);
        addComponent(daftarMasalahIRSLabel, panel);
        daftarMasalahIRSLabel.setFont(f.deriveFont(Font.BOLD));

        JButton kembaliButton = createGreenButton("Kembali");
        kembaliButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            new HomeGUI(frame, daftarMahasiswa, daftarMataKuliah);
        });
        addComponent(kembaliButton, panel);
    }
}
