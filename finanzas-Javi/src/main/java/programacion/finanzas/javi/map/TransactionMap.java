/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion.finanzas.javi.map;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import programacion.finanzas.javi.model.Transaction;

/**
 *
 * @author HP
 */
public class TransactionMap {

    //metodo static que convierte un archivo excel en un arreglo de objetos Transaction
    public static ArrayList<Transaction> FileToTransactions() throws IOException {

        String path = "C:\\Users\\HP\\Proyectos Programacion\\Java\\Proyecto Finanzas Javi\\docs\\Movements.xls";

        Workbook workbook = WorkbookFactory.create(new File(path));
        //Iterator<Sheet> hoja = workbook.sheetIterator();  arreglo de hojas del archivo excel

        Sheet hoja = workbook.getSheetAt(0); //nos devuelve la primer hoja del archivo de Excel
        Iterator<Row> filas = hoja.rowIterator(); //obtenemos las filas de la hoja

        ArrayList<ArrayList<Object>> arrayFilas = new ArrayList<>(); //este arreglo contiene cada fila, de izquierda a derecha

        //iteramos sobre cada fila
        while (filas.hasNext()) {
            Row fila = filas.next(); //obtenemos una fila

            Iterator<Cell> celdas = fila.cellIterator();//obtenemos las celdas usadas por fila

            ArrayList<Object> valueCeldas = new ArrayList<>(); //este array contiene los valores de cada celda de las filas

            //iteramos sobre las celdas de una fila
            while (celdas.hasNext()) {
                Cell celda = celdas.next();//obtenemos cada celda(o columna) de una fila

                if (celda.getCellType() == CellType.STRING) {
                    System.out.print(celda.getStringCellValue());
                    valueCeldas.add(celda.getStringCellValue());
                    System.out.print("  ");
                } else {
                    System.out.print(celda.getNumericCellValue());
                    valueCeldas.add(celda.getNumericCellValue());
                    System.out.print("  ");
                }
            }
            arrayFilas.add(valueCeldas);
        }
        System.out.println("---FIN---");

        return null;
    }

    public static void main(String[] args) {
        //Test
        try {
            FileToTransactions();
        } catch (IOException ex) {
            Logger.getLogger(TransactionMap.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
