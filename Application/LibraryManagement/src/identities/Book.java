/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package identities;
import java.sql.Date;
/**
 *
 * @author A
 */
public class Book {
    private int id;
    private String name;
    private int catId;
    private String author;
    private String description;
    private Date publacationDate;
    private Date dateCreate;
    private int quantity;
    private float price;

    public Book() {
    }

    public Book(String name, int catId, String author, String description, Date publacationDate, Date dateCreate, int quantity, float price) {
        this.name = name;
        this.catId = catId;
        this.author = author;
        this.description = description;
        this.publacationDate = publacationDate;
        this.dateCreate = dateCreate;
        this.quantity = quantity;
        this.price = price;
    }

    public Book(int id, String name, int catId, String author, String description, Date publacationDate, Date dateCreate, int quantity, float price) {
        this.id = id;
        this.name = name;
        this.catId = catId;
        this.author = author;
        this.description = description;
        this.publacationDate = publacationDate;
        this.dateCreate = dateCreate;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublacationDate() {
        return publacationDate;
    }

    public void setPublacationDate(Date publacationDate) {
        this.publacationDate = publacationDate;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", name=" + name + ", catId=" + catId + ", author=" + author + ", description=" + description + ", publacationDate=" + publacationDate + ", dateCreate=" + dateCreate + ", quantity=" + quantity + ", price=" + price + '}';
    }

    
    
}
