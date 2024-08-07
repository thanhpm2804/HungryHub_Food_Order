/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author lenovo
 */
public class Order {
    private int order_id;
    private Account customer;
    private Account diner;
    private Account shipper;
    private String order_status;
    private boolean payment_status;
    private String payment_method;
    private int total_price;
    private String reason; 
    private LocalDateTime created_at = LocalDateTime.now();
    private LocalDateTime updated_at = LocalDateTime.now();

    public Order(int order_id, Account customer, Account diner, Account shipper, String order_status, boolean payment_status, String payment_method, int total_price, String reason) {
        this.order_id = order_id;
        this.customer = customer;
        this.diner = diner;
        this.shipper = shipper;
        this.order_status = order_status;
        this.payment_status = payment_status;
        this.payment_method = payment_method;
        this.total_price = total_price;
        this.reason = reason;
    }

    public Order(int order_id, Account customer, Account diner, Account shipper, String order_status, String payment_method,String reason, int total_price) {
        this.order_id = order_id;
        this.customer = customer;
        this.diner = diner;
        this.shipper = shipper;
        this.order_status = order_status;
        this.payment_method = payment_method;
        this.reason = reason;
        this.total_price = total_price;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Account getCustomer() {
        return customer;
    }

    public void setCustomer(Account customer) {
        this.customer = customer;
    }

    public Account getDiner() {
        return diner;
    }

    public void setDiner(Account diner) {
        this.diner = diner;
    }

    public Account getShipper() {
        return shipper;
    }

    public void setShipper(Account shipper) {
        this.shipper = shipper;
    }

    
    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public boolean isPayment_status() {
        return payment_status;
    }

    public void setPayment_status(boolean payment_status) {
        this.payment_status = payment_status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getTotal_price() {
        return total_price;
    }
    
    public String getTotal_priceString() {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        return numberFormat.format(total_price);
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Order{" + "order_id=" + order_id + ", customer=" + customer + ", diner=" + diner + ", shipper=" + shipper + ", order_status=" + order_status + ", payment_status=" + payment_status + ", payment_method=" + payment_method + ", total_price=" + total_price + ", reason=" + reason + ", created_at=" + created_at + ", updated_at=" + updated_at + '}';
    }

    
}
