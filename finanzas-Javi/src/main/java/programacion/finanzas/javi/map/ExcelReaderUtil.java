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

            var rows = new ArrayList<ArrayList<String>>(); //este arreglo contiene cada fila, de izquierda a derecha

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
            System.out.println(ex.getStackTrace());
            throw new AppException("Error en el archivo");
        }
    }

    private static TransactionType getTransactionType(String type, String pay, String spent, String entry) {

        if (type.equals(entry)) {
            return TransactionType.ENTRY;
        } else if (type.equals(spent)) {
            return TransactionType.SPENT;
        } else if (type.equals(pay)) {
            return TransactionType.PAYMENT;
        } else {
            return TransactionType.NO_TYPE;
        }
    }

    //Método que mapea los datos en String y los convierte a un array de objetos de tipo Transaction
    public static ArrayList<Transaction> fromExcelToTransactions(String path, int column, String textPayment, String textSpent, String textPEntry) {

        /*
        parametros a agregar:
        int columnaTipo   -> sera el numero de columna donde se encuentra el tipo de transaccion en el archivo excel
        string pago   -> nombre representativo para identificar el tipo PAGO de una transaccion
        string gasto   -> nombre representativo para identificar el tipo GASTO de una transaccion
        string ingreso   -> nombre representativo para identificar el tipo INGRESO de una transaccion
        
        POSIBLE SOLUCION:
        tendremos un array de posiciones de 0 a 7 que cuando yo ingrese el numero de columna donde se encuentra el tipo,
        los numeros del array que van desde el numero de columna y mayores, aumentaran +1, asi todos se corren un espacio.
        ejemplo:
        
        Array inicial = [0,1,2,3,4,5,6,7]
        
        columnaTipo = 2
        array final = [0,1,3,4,5,6,7,8] la posicion 2 quedó ocupada por la variable type
         */
        int columnType = column - 1;
        ArrayList<Integer> posiciones = new ArrayList<>();
        posiciones.add(0);
        posiciones.add(1);
        posiciones.add(2);
        posiciones.add(3);
        posiciones.add(4);
        posiciones.add(5);
        posiciones.add(6);
        posiciones.add(7);

        for (int i = 0; i < posiciones.size(); i++) {

            if (posiciones.get(i) >= columnType) {
                int newPosition = posiciones.get(i) + 1;
                posiciones.set(i, newPosition);
            }
        }

        ArrayList<ArrayList<String>> filas = getValueCells(path);
        ArrayList<Transaction> transactions = new ArrayList<>();

        for (int i = 1; i < filas.size(); i++) {
            ArrayList<String> row = filas.get(i);
            if (!isRowValid(row)) {
                continue;
            }

            String date = row.get(posiciones.get(0));
            String category = row.get(posiciones.get(1));
            String subCategory = row.get(posiciones.get(2));
            String description = row.get(posiciones.get(3));
            String coment = row.get(posiciones.get(4));
            String image = row.get(posiciones.get(5));
            String importe = row.get(posiciones.get(6)).replaceAll(",", ""); //quitamos la coma por si es un numero como 1,500.00
            String saldo = row.get(posiciones.get(7)).replaceAll(",", ""); //quitamos la coma por si es un numero como 1,500.00
            Double amount = Double.valueOf(importe);
            Double balance = Double.valueOf(saldo);
            TransactionType type = getTransactionType(filas.get(i).get(columnType), textPayment, textSpent, textPEntry);

            Transaction transaction = new Transaction(date, category, subCategory, description, coment, image, amount, balance, type);
            transactions.add(transaction);

        }
        return transactions;
    }

    private static boolean isRowValid(ArrayList<String> row) {
        return row.size() >= 9;

    }
}
