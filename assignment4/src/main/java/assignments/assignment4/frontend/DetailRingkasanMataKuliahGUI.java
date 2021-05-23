package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

import static assignments.assignment4.frontend.SistemAkademikGUI.*;

public class DetailRingkasanMataKuliahGUI {
    private JPanel panel = new JPanel();
    private JFrame frame;
    private ArrayList<Mahasiswa> daftarMahasiswa;
    private ArrayList<MataKuliah> daftarMataKuliah;
    private MataKuliah mataKuliah;

    public DetailRingkasanMataKuliahGUI(JFrame frame, MataKuliah mataKuliah, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){this.frame = frame;
        this.daftarMahasiswa = daftarMahasiswa;
        this.daftarMataKuliah = daftarMataKuliah;
        this.mataKuliah = mataKuliah;

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(0, 40))); // add top buffer

        initComponents();

        frame.getContentPane().add(panel);
        frame.revalidate();
        
    }

    private void initComponents() {
        addTitle(new JLabel("Detail Ringkasan Mata Kuliah"), panel);
        addComponent(new JLabel(String.format("Nama mata kuliah: %s", mataKuliah.getNama())), panel);
        addComponent(new JLabel(String.format("Kode: %s", mataKuliah.getKode())), panel);
        addComponent(new JLabel(String.format("SKS: %d", mataKuliah.getSKS())), panel);
        addComponent(new JLabel(String.format("Jumlah mahasiswa: %d", mataKuliah.getJumlahMahasiswa())), panel);
        addComponent(new JLabel(String.format("Kapasitas: %d", mataKuliah.getKapasitas())), panel);
        addComponent(new JLabel("Daftar Mahasiswa:"), panel);
        String daftarMhs = "";
        if (mataKuliah.getJumlahMahasiswa() == 0){
            daftarMhs = "Belum ada mahasiswa yang mengambil mata kuliah ini.";
        } else{
            Mahasiswa[] mahasiswas = mataKuliah.getDaftarMahasiswa();
            for (int i = 0; i < mahasiswas.length; i++) {
                if (mahasiswas[i] == null)
                    break;
                daftarMhs += "<html>"+ (i + 1) + ". " + mahasiswas[i].getNama() + "<br>" + "<html>";
            }
        }

        JLabel daftarMhsLabel = new JLabel(daftarMhs, SwingConstants.CENTER);
        addComponent(daftarMhsLabel, panel);
        Font f = daftarMhsLabel.getFont();
        daftarMhsLabel.setFont(f.deriveFont(Font.BOLD));

        JButton kembaliButton = createGreenButton("Kembali");
        kembaliButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            new HomeGUI(frame, daftarMahasiswa, daftarMataKuliah);
        });
        addComponent(kembaliButton, panel);
    }
}
