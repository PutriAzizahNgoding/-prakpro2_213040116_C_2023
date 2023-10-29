/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan7;

/**
 *
 * @author Putri Azizah
 */
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.List;


class tableModelApp extends AbstractTableModel {
  
    private String[] columnNames = {"Nama","Nomor HP","Jenis Kelamin","Alamat"};

    private ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

  
    public int getColumnCount() {
 
       return columnNames.length;
}
   
     public int getRowCount() {
       //  mengembalikan mengembalikan panjang (jumlah elemen) dari ArrayList data.
       return data.size();
}
    
      public String getColumnName(int col) {
     
       return columnNames[col];
}
  
     public Object getValueAt(int row, int col) {
 
         List<String> rowItem = data.get(row);
    
         return rowItem.get(col);
}
 
   
    public boolean isCellEditable(int row, int col) {
     
        return false;
}

    
    public void add(ArrayList<String> value) {
       
       data.add(value);
        
       fireTableRowsInserted(data.size() -1, data.size()-1);
}

public List<String> getRowData(int rowIndex) {
        return data.get(rowIndex);
    }


    public void removeRow(int rowIndex) {
        data.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

     public void remove(int row) {
        data.remove(row);
        fireTableRowsDeleted(row, row);
    }


      public ArrayList<ArrayList<String>> getData() {
        return data;
    }


 
}
    
