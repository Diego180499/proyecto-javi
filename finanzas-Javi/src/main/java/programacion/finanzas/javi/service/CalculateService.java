package programacion.finanzas.javi.service;

import java.util.ArrayList;
import programacion.finanzas.javi.enumType.TransactionType;
import programacion.finanzas.javi.map.TransactionMap;
import programacion.finanzas.javi.model.Transaction;

public class CalculateService {

    private int entry; //ingreso
    private int spent; //gasto
    private int payment; //pago

    public CalculateService() {

    }

    //metodo para calcular los gastos, ingresos y pagos
    public void calculateTransactions(String path) {

        ArrayList<Transaction> transactions = TransactionMap.mapToTransactions("C:\\Users\\HP\\Proyectos Programacion\\Java\\Proyecto Finanzas Javi\\docs\\Movements.xls");

        ArrayList<Transaction> entries = getEntries(transactions);
        ArrayList<Transaction> spents = getSpents(transactions);
        ArrayList<Transaction> payments = getPayments(transactions);
        
        

    }

    private ArrayList<Transaction> getEntries(ArrayList<Transaction> transactions) {
        ArrayList<Transaction> entries = new ArrayList<>();

        for (Transaction transaction : transactions) {

            if (transaction.getType().equals(TransactionType.ENTRY)) {
                entries.add(transaction);
            }
        }
        return entries;
    }

    private ArrayList<Transaction> getSpents(ArrayList<Transaction> transactions) {
        ArrayList<Transaction> spents = new ArrayList<>();

        for (Transaction transaction : transactions) {

            if (transaction.getType().equals(TransactionType.SPENT)) {
                spents.add(transaction);
            }
        }
        return spents;
    }

    private ArrayList<Transaction> getPayments(ArrayList<Transaction> transactions) {
        ArrayList<Transaction> payments = new ArrayList<>();

        for (Transaction transaction : transactions) {

            if (transaction.getType().equals(TransactionType.PAYMENT)) {
                payments.add(transaction);
            }
        }
        return payments;
    }

    //METODOS GETTER Y SETTERS
    public int getEntry() {
        return entry;
    }

    public void setEntry(int entry) {
        this.entry = entry;
    }

    public int getSpent() {
        return spent;
    }

    public void setSpent(int spent) {
        this.spent = spent;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }
    
    
    public static void main(String[] args) {
        
        CalculateService service = new CalculateService();
        service.calculateTransactions("asd");
        
        
        
    }

}
