/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// 1. Menggunakan Package
package Pertemuan7;

/**
 *
 * @author Putri Azizah
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import javax.swing.JOptionPane;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.WindowEvent;


public class AplikasiBiodata extends JFrame {
       private JTextField namaTextField, handphoneTextField;
       private JRadioButton lakiLakiRadioButton, perempuanRadioButton;
       private JTextArea alamatTextArea;
        private int selectedRowIndex = -1;

public AplikasiBiodata() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Aplikasi Biodata");
        this.setLayout(new FlowLayout());

        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(10, 30, 100, 30);

        // X,Y,WIDTH,HEIGHT
        namaTextField = new JTextField();
        namaTextField.setBounds(15, 60, 350, 30);
             
        JLabel labelHandphone = new JLabel("Nomor HP:");
        labelHandphone.setBounds(15, 95, 100, 10);

        handphoneTextField = new JTextField();
        handphoneTextField.setBounds(15, 110, 350, 30);

        JLabel labelJenisKelamin = new JLabel("Jenis Kelamin:");
        labelJenisKelamin.setBounds(15,150,350,10);

        lakiLakiRadioButton = new JRadioButton("Laki-Laki");
        lakiLakiRadioButton.setBounds(15,165, 350, 30);

        perempuanRadioButton = new JRadioButton("Perempuan");
        perempuanRadioButton.setBounds(15,195, 350, 30);

        ButtonGroup jenisKelaminGroup = new ButtonGroup();
        jenisKelaminGroup.add(lakiLakiRadioButton);
        jenisKelaminGroup.add(perempuanRadioButton);

        JLabel labelAlamat = new JLabel("Alamat");
        labelAlamat.setBounds(15,210,150,50);  
       
        alamatTextArea = new JTextArea("");
        alamatTextArea.setBounds(10,250,290,90);

        JButton simpanButton = new JButton("Simpan");
        simpanButton.setBounds(10, 350, 100, 40);

        JButton editButton = new JButton("Edit");
        editButton.setBounds(115, 350, 100, 40);

        JButton hapusButton = new JButton("Hapus");
        hapusButton.setBounds(220, 350, 100, 40);
 
        JButton fileButton = new JButton("Simpan Ke File");
        fileButton.setBounds(325, 350, 120, 40);

       JButton exitButton = new JButton("Keluar");
       exitButton.setBounds(430, 350, 100, 40);

        javax.swing.JTable table = new JTable();

        
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(10, 400, 500,200);

        
        tableModelApp tableModel3 = new tableModelApp();
        table.setModel(tableModel3);

        // Simpan Data
         simpanButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nama = namaTextField.getText();
                String handphone = handphoneTextField.getText();
                String alamat = alamatTextArea.getText();
                String jenisKelamin = "";
      
             if(lakiLakiRadioButton.isSelected()) {
                    jenisKelamin = lakiLakiRadioButton.getText();
            } if(perempuanRadioButton.isSelected()) {
                   jenisKelamin = perempuanRadioButton.getText();
      } 


        if (nama.isEmpty() || handphone.isEmpty() || alamat.isEmpty() || jenisKelamin.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Semua kolom wajib terisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return; 
        }
           
          int konfirmasi_simpan = JOptionPane.showConfirmDialog(null, "Apakah yakin ingin menyimpan data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        
            if (konfirmasi_simpan == JOptionPane.YES_OPTION) {
               tableModel3.add(new ArrayList<>(Arrays.asList(nama,handphone,jenisKelamin,alamat)));
        
                namaTextField.setText("");
                handphoneTextField.setText("");
                alamatTextArea.setText("");
             
                lakiLakiRadioButton.setSelected(false);
                perempuanRadioButton.setSelected(false);
               }
            }
        });

            // 4. Dapat menghapus data yang telah disimpan di tabel
            hapusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int dialogResult = JOptionPane.showConfirmDialog(AplikasiBiodata.this, "Anda yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        tableModel3.removeRow(selectedRow);
                        clearInputFields();
                    }
                } else {
                    JOptionPane.showMessageDialog(AplikasiBiodata.this, "Pilih baris yang ingin dihapus.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
         
        // 3. Dapat mengedit data yang telah disimpan di tabel
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    ArrayList<String> rowData = tableModel3.getData().get(selectedRow);
                   namaTextField.setText(rowData.get(0));
                   handphoneTextField.setText(rowData.get(1));
                    if (rowData.get(2).equals("Laki-Laki")) {
                        lakiLakiRadioButton.setSelected(true);
                        perempuanRadioButton.setSelected(false);
                    } else {
                         lakiLakiRadioButton.setSelected(false);
                        perempuanRadioButton.setSelected(true);
                    }
                    alamatTextArea.setText(rowData.get(3));
                    tableModel3.remove(selectedRow);
                    JOptionPane.showMessageDialog(AplikasiBiodata.this, "Silahkan edit data!");
                } else {
                    JOptionPane.showMessageDialog(AplikasiBiodata.this, "Pilih baris data yang akan diedit!");
                }
            }
        });



   //2. Simpan data dalam file text (.txt)
   fileButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Simpan Data ke File");
       
        FileNameExtensionFilter filter = new FileNameExtensionFilter("File Teks (.txt)", "txt");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showSaveDialog(AplikasiBiodata.this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            try (PrintWriter writer = new PrintWriter(fileToSave)) {
                for (int i = 0; i < tableModel3.getRowCount(); i++) {
                    String nama = tableModel3.getValueAt(i, 0).toString();
                    String handphone = tableModel3.getValueAt(i, 1).toString();
                    String jenisKelamin = tableModel3.getValueAt(i, 2).toString();
                    String alamat = tableModel3.getValueAt(i, 3).toString();

                    writer.println("Nama: " + nama);
                    writer.println("Nomor HP: " + handphone);
                    writer.println("Jenis Kelamin: " + jenisKelamin);
                    writer.println("Alamat: " + alamat);
                    writer.println();
                }

                writer.close();
                JOptionPane.showMessageDialog(AplikasiBiodata.this, "Data telah tersimpan ke file.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(AplikasiBiodata.this, "Gagal menyimpan data ke file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
});
       // 5. Buat konfirmasi ketika keluar dari aplikasi menggunakan tombol X di jendela aplikas
       this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog(AplikasiBiodata.this, "Apakah Anda yakin ingin keluar dari aplikasi?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    System.exit(0); 
                } else {
                   setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
}
            }
        });

        this.add(labelNama);
        this.add(namaTextField);
        this.add(labelHandphone);
        this.add(handphoneTextField);
        this.add(labelJenisKelamin);
        this.add(lakiLakiRadioButton);
        this.add(perempuanRadioButton);
        this.add(labelAlamat);
        this.add(alamatTextArea);
        this.add(simpanButton);
        this.add(editButton);
        this.add(hapusButton);
        this.add(fileButton);
        this.add(scrollableTable);   

      
        this.setSize(400, 500);
        this.setLayout(null);

    }


    private void clearInputFields() {
        namaTextField.setText("");
        handphoneTextField.setText("");
        alamatTextArea.setText("");
        lakiLakiRadioButton.setSelected(false);
        perempuanRadioButton.setSelected(false);
    }

    

    public static void main(String[] args) {
     javax.swing.SwingUtilities.invokeLater(new Runnable() {
       public void run() {
         AplikasiBiodata h = new AplikasiBiodata();
        h.setVisible(true);
}
 });

}
}
