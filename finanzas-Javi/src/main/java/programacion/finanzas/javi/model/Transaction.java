package programacion.finanzas.javi.model;

import programacion.finanzas.javi.enumType.TransactionType;

public class Transaction {

    private String date;
    private String category;
    private String subCategory;
    private String description;
    private String coment;
    private String image;
    private double amount;
    private double balance;
    private TransactionType type;

    public Transaction(String date, String category, String subCategory, String description, String coment, String image, double amount, double balance, TransactionType type) {
        this.date = date;
        this.category = category;
        this.subCategory = subCategory;
        this.description = description;
        this.coment = coment;
        this.image = image;
        this.amount = amount;
        this.balance = balance;
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
    
    
}
