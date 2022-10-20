package programacion.finanzas.javi.service;

import java.util.ArrayList;
import programacion.finanzas.javi.enumType.TransactionType;
import programacion.finanzas.javi.exception.AppException;
import programacion.finanzas.javi.map.ExcelReaderUtil;
import programacion.finanzas.javi.model.Transaction;

public class CalculateService {

    public CalculateService() {

    }

    //metodo para calcular los gastos, ingresos y pagos
    public ArrayList<Double> calculateTransactions(String path) throws AppException {

        ArrayList<Transaction> transactions = ExcelReaderUtil.fromExcelToTransactions(path);

        Double initialBalance = transactions.get(transactions.size() - 1).getBalance();
        Double entries = getTotalByType(transactions, TransactionType.ENTRY);
        Double spents = getTotalByType(transactions, TransactionType.SPENT);
        Double payments = getTotalByType(transactions, TransactionType.PAYMENT);
        Double finalBalance = initialBalance - spents - payments + entries;
        Double saving = finalBalance - initialBalance; //ahorro

        ArrayList<Double> data = new ArrayList<>();

        data.add(entries);
        data.add(spents);
        data.add(payments);
        data.add(initialBalance);
        data.add(finalBalance);
        data.add(saving);

        return data;
    }

    private Double getTotalByType(ArrayList<Transaction> transactions, TransactionType transactionType) {

        Double total = 0.0;

        for (Transaction transaction : transactions) {

            if (transaction.getType().equals(transactionType)) {
                if (transactionType.equals(TransactionType.ENTRY)) {
                    total += transaction.getAmount();
                } else if (transactionType.equals(TransactionType.SPENT) || transactionType.equals(TransactionType.PAYMENT)) {
                    total -= transaction.getAmount();
                }
            }
        }
        return total;
    }
}

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
