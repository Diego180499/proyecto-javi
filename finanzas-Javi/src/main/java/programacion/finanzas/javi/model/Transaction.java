
package programacion.finanzas.javi.model;

import java.util.Date;

public class Transaction {
    
    private Date date;
    private String category;
    private String subCategory;
    private String description;
    private String coment;
    private String image;
    private int amount;
    private int balance;
    private String type;

    public Transaction(Date date, String category, String subCategory, String description, String coment, String image, int amount, int balance, String type) {
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

    
    
    

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
    
    
    
}
