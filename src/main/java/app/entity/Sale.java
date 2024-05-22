package app.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "`Sales`")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = true)
    private double amount;

    @Column(columnDefinition = "TEXT")
    private String customer;

    @Temporal(TemporalType.DATE)
    private Date date;

    // Constructors
    public Sale() {
    }

    public Sale(String title, Integer amount, String customer, Date date) {
        this.title = title;
        this.amount = amount;
        this.customer = customer;
        this.date = date;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // toString method for debugging purposes
    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", customer='" + customer + '\'' +
                ", date=" + date +
                '}';
    }
}
