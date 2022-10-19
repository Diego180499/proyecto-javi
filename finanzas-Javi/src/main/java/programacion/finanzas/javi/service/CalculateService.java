package programacion.finanzas.javi.service;

import java.util.ArrayList;
import programacion.finanzas.javi.enumType.TransactionType;
import programacion.finanzas.javi.map.ExcelReaderUtil;
import programacion.finanzas.javi.model.Transaction;

public class CalculateService {

    public CalculateService() {

    }

    //metodo para calcular los gastos, ingresos y pagos
    public void calculateTransactions(String path) {

        ArrayList<Transaction> transactions = ExcelReaderUtil.fromExcelToTransactions(path);

        ArrayList<Transaction> entries = getTransactionsByType(transactions,TransactionType.ENTRY);
        ArrayList<Transaction> spents = getTransactionsByType(transactions,TransactionType.SPENT);
        ArrayList<Transaction> payments = getTransactionsByType(transactions,TransactionType.PAYMENT);
        
        /*
        saldo inicial 100€
        - ingresé 130€
        - gaste 20€ restaurantes
        - gaste 10€ regalos
        - pague 5€ internet
        - pague 10 Luz
---------------------
        saldo final-> 185€
     
        gasté -> 30€
        pague -> 15
        
        Ahorre -> saldo final -saldo inical -> 85€
        */
        
        
        
    }

    private ArrayList<Transaction> getTransactionsByType(ArrayList<Transaction> transactions,TransactionType transactionType){
         ArrayList<Transaction> transactionsByType = new ArrayList<>();

        for (Transaction transaction : transactions) {

            if (transaction.getType().equals(transactionType)) {
                transactionsByType.add(transaction);
            }
        }
        return transactionsByType;
    }
}
