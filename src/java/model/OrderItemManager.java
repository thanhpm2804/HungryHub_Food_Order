/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import static java.nio.file.Files.list;
import java.util.ArrayList;
import static java.util.Collections.list;

/**
 *
 * @author PC
 */
public class OrderItemManager {
         private  ArrayList<OrderItem> List;

    public OrderItemManager() {
        List = OrderItemDao.getAllOrderItems();
    }
    public OrderItem getOderItemById(int id) {
        for (OrderItem facc : List) {
            if (id == facc.getOrder_item_id()) {
                return facc;
            }
        }
        return null;
    }
 
     
    public ArrayList<OrderItem> getOderItemByOrderId(int id) {
        ArrayList<OrderItem> list = new ArrayList<>();
        for (OrderItem facc : List) {
            if (id == facc.getOrder_id()) {
                list.add(facc);
            }
        }
        return list;
    }
    public int getTotalOderItem(ArrayList<OrderItem> oiList) {
        int sum = 0;
        for (OrderItem facc : oiList) {
            sum+=facc.getQuantity();
        }
        return sum;
    }
    public int getTotalPrice(ArrayList<OrderItem> oiList) {
        int sum = 0;
        for (OrderItem facc : oiList) {
            sum+=facc.getPrice();
        }
        return sum;
    }
    
    public double getTotalPriceOrderId(int id){
        ArrayList<OrderItem> oiList = getOderItemByOrderId(id);
        double total = 0;
        for(OrderItem oi : oiList){
            total += oi.getPrice();
        }
        return total;
 
    }
     public ArrayList<OrderItem> getList() {
        return List;
    }
     
     
    public static void main(String[] args) {
        OrderItemManager om = new OrderItemManager();
        for(OrderItem o : om.getOderItemByOrderId(100001)){
            System.out.println(o.getPrice());
        }
        System.out.println(om.getTotalPriceOrderId(100001));
    }
}
