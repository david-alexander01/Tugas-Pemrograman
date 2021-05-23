package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class SistemAkademik {

    
    public static void main(String[] args) { 
        new SistemAkademikGUI();
    }
}

class SistemAkademikGUI extends JFrame{
    private static ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<Mahasiswa>();
    private static ArrayList<MataKuliah> daftarMataKuliah = new ArrayList<MataKuliah>();
    public static Font fontGeneral = new Font("Century Gothic", Font.PLAIN , 14);
    public static Font fontTitle = new Font("Century Gothic", Font.BOLD, 20);
    public static Color green = new Color(156, 198, 52);
    public static Color blue = new Color(132, 174, 204);

    public SistemAkademikGUI(){

        super("Administrator - Sistem Akademik");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null); // place window in the center of the screen
        setResizable(false);
        
        new HomeGUI(this, daftarMahasiswa, daftarMataKuliah);
        setVisible(true);


    }

    public static void addTitle(JLabel label, JPanel panel){
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(SistemAkademikGUI.fontTitle);
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 40))); // create space between title and content
    }

    public static void addComponent(JComponent component, JPanel panel){
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        component.setFont(SistemAkademikGUI.fontGeneral);
        panel.add(component);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    public static JButton createGreenButton(String text){
        JButton button = new JButton(text);
        button.setBackground(SistemAkademikGUI.green);
        button.setForeground(Color.white);

        return button;
    }

    public static JButton createKembaliButton(JFrame frame) {
        JButton button = new JButton("Kembali");
        button.setBackground(SistemAkademikGUI.blue);
        button.setForeground(Color.white);
        button.addActionListener(e -> {
            frame.getContentPane().removeAll();
            new HomeGUI(frame, daftarMahasiswa, daftarMataKuliah);
        });

        return button;
    }

    /**
     * Implement selection sort to sort daftarMahasiswa based on npm.
     * Taken from textbook (Introduction to Java Programming and Data Structures,
     * Comprehensive Version by Y. Daniel Liang, 12th edition) page 274,
     * Listing 7.8: SelectionSort.java
     */
    public static void sortDaftarMahasiswa(){
        for (int i = 0; i < daftarMahasiswa.size()-1; i++) {
            // Find the minimum in the daftarMahasiswa[i..daftarMahasiswa.size()-1]
            Mahasiswa currentMin = daftarMahasiswa.get(i);
            int currentMinIndex = i;

            for (int j = i + 1; j < daftarMahasiswa.size(); j++) {
                // if currentMin > daftarMahasiswa[j]
                if (currentMin.compareTo(daftarMahasiswa.get(j)) > 0){
                    currentMin = daftarMahasiswa.get(j);
                    currentMinIndex = j;
                }
            }

            // Swap daftarMahasiswa[i] with daftarMahasiswa[currentMinIndex] if necessary
            if (currentMinIndex != i){
                daftarMahasiswa.set(currentMinIndex, daftarMahasiswa.get(i));
                daftarMahasiswa.set(i, currentMin);
            }
        }
    }

    /**
     * Implement selection sort to sort daftarMataKuliah based on npm.
     * Taken from textbook (Introduction to Java Programming and Data Structures,
     * Comprehensive Version by Y. Daniel Liang, 12th edition) page 274,
     * Listing 7.8: SelectionSort.java
     */
    public static void sortDaftarMatakuliah(){
        for (int i = 0; i < daftarMataKuliah.size()-1; i++) {
            // Find the minimum in the daftarMataKuliah[i..daftarMataKuliah.size()-1]
            MataKuliah currentMin = daftarMataKuliah.get(i);
            int currentMinIndex = i;

            for (int j = i+1; j < daftarMataKuliah.size(); j++) {
                // if currentMin > daftarMataKuliah[j]
                if (currentMin.compareTo(daftarMataKuliah.get(j)) > 0){
                    currentMin = daftarMataKuliah.get(j);
                    currentMinIndex = j;
                }
            }

            // swap daftarMataKuliah[i] with daftarMataKuliah[currentMinIndex] if necessary
            if (currentMinIndex != i){
                daftarMataKuliah.set(currentMinIndex, daftarMataKuliah.get(i));
                daftarMataKuliah.set(i, currentMin);
            }
        }
    }

    public static JComboBox<String> createNPMChooser(JPanel panel){
        addComponent(new JLabel("Pilih NPM"), panel);

        String[] npms = new String[daftarMahasiswa.size()];
        sortDaftarMahasiswa();
        for (int i = 0; i < npms.length; i++) {
            npms[i] = daftarMahasiswa.get(i).getNpm() + "";
        }

        JComboBox<String> npmList = new JComboBox<>(npms);
        npmList.setMaximumSize(npmList.getPreferredSize());
        addComponent(npmList, panel);

        return npmList;
    }

    public static JComboBox<String> createMatkulChooser(JPanel panel){
        addComponent(new JLabel("Pilih Nama Matkul"), panel);

        String[] names = new String[daftarMataKuliah.size()];
        sortDaftarMatakuliah();
        for (int i = 0; i < names.length; i++) {
            names[i] = daftarMataKuliah.get(i).getNama();
        }

        JComboBox<String> namaList = new JComboBox<>(names);
        namaList.setMaximumSize(namaList.getPreferredSize());
        addComponent(namaList, panel);

        return namaList;
    }
}
