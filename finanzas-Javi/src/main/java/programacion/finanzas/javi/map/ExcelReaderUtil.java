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
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import programacion.finanzas.javi.enumType.TransactionType;
import programacion.finanzas.javi.exception.AppException;
import programacion.finanzas.javi.model.Transaction;

/**
 *
 * @author HP
 */
//ExcelReaderUtil.java
public class ExcelReaderUtil {

    //metodo static que extrae el contenido del archivo Excel
    private static ArrayList<ArrayList<String>> getValueCells(String path) {
        try {
            Workbook workbook = WorkbookFactory.create(new File(path));

            //Iterator<Sheet> hoja = workbook.sheetIterator();  arreglo de hojas del archivo excel
            Sheet sheet = workbook.getSheetAt(0); //nos devuelve la primer hoja del archivo de Excel
            Iterator<Row> rowIterator = sheet.rowIterator(); //obtenemos las filas de la hoja

            var rows = new  ArrayList<ArrayList<String>>(); //este arreglo contiene cada fila, de izquierda a derecha

            //iteramos sobre cada fila
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next(); //obtenemos una fila

                Iterator<Cell> cellIterator = row.cellIterator();//obtenemos las celdas usadas por fila

                var cellsValues = new ArrayList<String>(); //este array contiene los valores de cada celda de las filas

                //iteramos sobre las celdas de una fila
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();//obtenemos cada celda(o columna) de una fila
                    DataFormatter dataFormatter = new DataFormatter();
                    String valor = dataFormatter.formatCellValue(cell);
                    cellsValues.add(valor);
                }
                rows.add(cellsValues);
            }
            return rows;
        } catch (IOException ex) {
            ex.getStackTrace();
            throw new AppException("Error en el archivo");
        }
    }

    private static TransactionType getTransactionType(String type) {

        if (type.equals("I")) {
            return TransactionType.ENTRY;
        } else if (type.equals("G")) {
            return TransactionType.SPENT;
        } else if (type.equals("P")) {
            return TransactionType.PAYMENT;
        } else {
            return TransactionType.NO_TYPE;
        }
    }

    //Método que mapea los datos en String y los convierte a un array de objetos de tipo Transaction
    public static ArrayList<Transaction> fromExcelToTransactions(String path) {
        ArrayList<ArrayList<String>> filas = getValueCells(path);
        ArrayList<Transaction> transactions = new ArrayList<>();

        for (int i = 1; i < filas.size(); i++) {
            ArrayList<String> row = filas.get(i);
            if (!isRowValid(row)) {
                continue;
            }

            String date = row.get(0);
            String category = row.get(1);
            String subCategory = row.get(2);
            String description = row.get(3);
            String coment = row.get(4);
            String image = row.get(5);
            String importe = row.get(6).replaceAll(",", ""); //quitamos la coma por si es un numero como 1,500.00
            String saldo = row.get(7).replaceAll(",", ""); //quitamos la coma por si es un numero como 1,500.00
            Double amount = Double.valueOf(importe);
            Double balance = Double.valueOf(saldo);
            TransactionType type = getTransactionType(filas.get(i).get(8));

            Transaction transaction = new Transaction(date, category, subCategory, description, coment, image, amount, balance, type);
            transactions.add(transaction);

        }
        return transactions;
    }

    private static boolean isRowValid(ArrayList<String> row) {
        return row.size() >= 9;

    }
}
