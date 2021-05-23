package assignments.assignment4.frontend;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

import static assignments.assignment4.frontend.SistemAkademikGUI.*;

public class TambahMahasiswaGUI {
    private JPanel panel = new JPanel();
    private JFrame frame;
    private ArrayList<Mahasiswa> daftarMahasiswa;
    private ArrayList<MataKuliah> daftarMataKuliah;

    public TambahMahasiswaGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah) {
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
        JLabel titleLabel = new JLabel("Tambah Mahasiswa");
        addTitle(titleLabel, panel);

        JLabel namaLabel = new JLabel("Nama:");
        addComponent(namaLabel, panel);

        JTextField namaTextField = new JTextField();
        namaTextField.setMaximumSize(new Dimension(250, 20));
        addComponent(namaTextField, panel);

        JLabel npmLabel = new JLabel("NPM:");
        addComponent(npmLabel, panel);

        JTextField npmTextField = new JTextField();
        npmTextField.setMaximumSize(new Dimension(250, 20));
        addComponent(npmTextField, panel);

        JButton tambahkanButton = createGreenButton("Tambahkan");
        tambahkanButton.addActionListener(e -> {
            String nama = namaTextField.getText();
            String npmString = npmTextField.getText();
            if (nama.equals("") || npmString.equals("")) {
                JOptionPane.showMessageDialog(frame, "Mohon isi seluruh Field");
            } else {
                long npm = Long.parseLong(npmString);
                Mahasiswa mahasiswa = daftarMahasiswa.stream()
                        .filter(m -> m.getNpm() == npm).findAny().orElse(null);
                if (mahasiswa != null) {
                    JOptionPane.showMessageDialog(frame,
                            String.format("NPM %d sudah pernah ditambahkan sebelumnya", npm));
                } else {
                    daftarMahasiswa.add(new Mahasiswa(nama, npm));
                    JOptionPane.showMessageDialog(frame,
                            String.format("Mahasiswa %d-%s berhasil ditambahkan", npm, nama));
                    namaTextField.setText("");
                    npmTextField.setText("");
                }
            }
        });
        addComponent(tambahkanButton, panel);

        addComponent(createKembaliButton(frame), panel);

    }
}
