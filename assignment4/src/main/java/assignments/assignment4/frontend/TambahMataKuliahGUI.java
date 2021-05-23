package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

import static assignments.assignment4.frontend.SistemAkademikGUI.*;

public class TambahMataKuliahGUI {
    private JPanel panel = new JPanel();
    private JFrame frame;
    private ArrayList<Mahasiswa> daftarMahasiswa;
    private ArrayList<MataKuliah> daftarMataKuliah;

    public TambahMataKuliahGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah) {
        this.frame = frame;
        this.daftarMahasiswa = daftarMahasiswa;
        this.daftarMataKuliah = daftarMataKuliah;

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(0, 40))); // add top buffer

        initComponents();

        frame.getContentPane().add(panel);
        frame.revalidate();

    }

    private void initComponents() {
        JLabel titleLabel = new JLabel("Tambah Mata Kuliah");
        addTitle(titleLabel, panel);

        JLabel kodeLabel = new JLabel("Kode Mata Kuliah:");
        addComponent(kodeLabel, panel);

        JTextField kodeTextField = new JTextField();
        kodeTextField.setMaximumSize(new Dimension(250, 20));
        addComponent(kodeTextField, panel);

        JLabel namaLabel = new JLabel("Nama Mata Kuliah:");
        addComponent(namaLabel, panel);

        JTextField namaTextField = new JTextField();
        namaTextField.setMaximumSize(new Dimension(250, 20));
        addComponent(namaTextField, panel);

        JLabel sksLabel = new JLabel("SKS:");
        addComponent(sksLabel, panel);

        JTextField sksTextField = new JTextField();
        sksTextField.setMaximumSize(new Dimension(250, 20));
        addComponent(sksTextField, panel);

        JLabel kapasitasLabel = new JLabel("Kapasitas:");
        addComponent(kapasitasLabel, panel);

        JTextField kapasitasTextField = new JTextField();
        kapasitasTextField.setMaximumSize(new Dimension(250, 20));
        addComponent(kapasitasTextField, panel);

        JButton tambahkanButton = createGreenButton("Tambahkan");
        tambahkanButton.addActionListener(e -> {
            String kode = kodeTextField.getText();
            String nama = namaTextField.getText();
            String sksString = sksTextField.getText();
            String kapasitasString = kapasitasTextField.getText();
            if (kode.equals("") || nama.equals("") || sksString.equals("") || kapasitasString.equals("")) {
                JOptionPane.showMessageDialog(frame, "Mohon isi seluruh Field");
            } else {
                int sks = Integer.parseInt(sksString);
                int kapasitas = Integer.parseInt(kapasitasString);
                MataKuliah mataKuliah = daftarMataKuliah.stream()
                        .filter(m -> m.getNama().equals(nama)).findAny().orElse(null);
                if (mataKuliah != null) {
                    JOptionPane.showMessageDialog(frame,
                            String.format("Mata Kuliah %s sudah pernah ditambahkan sebelumnya", nama));
                } else {
                    daftarMataKuliah.add(new MataKuliah(kode, nama, sks, kapasitas));
                    JOptionPane.showMessageDialog(frame, String.format("Mata Kuliah %s berhasil ditambahkan", nama));
                    kodeTextField.setText("");
                    namaTextField.setText("");
                    sksTextField.setText("");
                    kapasitasTextField.setText("");
                }
            }
        });
        addComponent(tambahkanButton, panel);

        addComponent(createKembaliButton(frame), panel);
    }

}
