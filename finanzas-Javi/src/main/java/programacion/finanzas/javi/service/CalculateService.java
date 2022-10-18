package programacion.finanzas.javi.service;

import java.util.ArrayList;
import programacion.finanzas.javi.model.Transaction;

public class CalculateService {
    
    private int entry; //ingreso
    private int spent; //gasto
    private int payment; //pago
    private ArrayList<Transaction> transactions;

    public CalculateService(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }
    
    
    //metodo para calcular los gastos, ingresos y pagos
    public void calculateTransactions(){
        
    
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

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }
    
    
    
    
    
    
}
