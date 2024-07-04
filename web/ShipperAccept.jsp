<%@page import="model.OrderItem"%>
<%@page import="model.Order"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>View Order</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <style>
            body, html {
                height: 100%;
                margin: 0;
                padding: 0;
                font-family: Arial, sans-serif;
                background-color: #f0f8ff;
                display: flex;
                flex-direction: column;
                overflow: hidden;
                overflow-y: auto;
            }
            #map {
                height: 100%;
            }
            .container {
                display: flex;
                height: 100%;
                padding: 20px;
                box-sizing: border-box;
            }
            .order-info {
                flex: 3;
                padding: 20px;
                box-sizing: border-box;
                background-color: #ffffff;
                overflow-y: auto;
                display: flex;
                flex-direction: column;
                border-radius: 10px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }
            .sidebar {
                flex: 1;
                display: flex;
                flex-direction: column;
                background-color: #fff;
                border-left: 1px solid #ccc;
                border-radius: 10px;
                margin-left: 20px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }
            .map, .chat {
                padding: 20px;
                box-sizing: border-box;
                border-bottom: 1px solid #ccc;
                height: 50%;
            }
            .map {
                flex: 1;
            }
            .chat {
                flex: 1;
            }
            h1, h2 {
                margin-top: 0;
                color: #333;
                font-weight: normal;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
                border-radius: 10px;
                overflow: hidden;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }
            table th, table td {
                padding: 12px;
                border: 1px solid #ccc;
                text-align: left;
            }
            table th {
                background-color: #8bc34a;
                color: #fff;
            }
            table tr:nth-child(even) {
                background-color: #f9f9f9;
            }
            #chat-box {
                height: calc(70% - 40px);
                overflow-y: auto;
                border: 1px solid #ccc;
                padding: 10px;
                border-radius: 10px;
                box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.1);
            }
            #chat-form {
                display: flex;
                margin-top: 10px;
            }
            #chat-form input[type="text"] {
                flex: 1;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 4px 0 0 4px;
                box-sizing: border-box;
            }
            #chat-form button {
                padding: 10px;
                border: 1px solid #007bff;
                background-color: #007bff;
                color: #fff;
                border-radius: 0 4px 4px 0;
                cursor: pointer;
                transition: background-color 0.3s;
            }
            #chat-form button:hover {
                background-color: #0056b3;
            }
            .header {
                display: flex;
                align-items: center;
                padding: 15px;
                background-color: #8bc34a;
                box-shadow: 0 2px 4px rgba(0,0,0,0.1);
                position: relative;
            }
            .header a {
                text-decoration: none;
                color: #000;
                font-family: 'Brush Script MT', sans-serif;
                font-size: 50px;
            }
            .header .order_online {
                position: absolute;
                top: 30px;
                right: 20px;
                font-size: 40px;
                color: white;
            }
            .message {
                padding: 10px;
                margin-bottom: 20px;
                border: 1px solid transparent;
                border-radius: 4px;
            }
            .message-success {
                color: #155724;
                background-color: #d4edda;
                border-color: #c3e6cb;
            }
            .message-error {
                color: #721c24;
                background-color: #f8d7da;
                border-color: #f5c6cb;
            }
            .accept-button-container {
                display: flex;
                justify-content: flex-end;
                margin-top: 20px;
                 gap: 0.5cm;
            }
            .accept-button-container input[type="submit"] {
                padding: 15px 30px;
                font-size: 16px;
                background-color: #8bc34a;
                color: #fff;
                border: none;
                border-radius: 10px;
                cursor: pointer;
                transition: background-color 0.3s;
            }
            .accept-button-container input[type="submit"]:hover {
                background-color: #7cb342;
            }
            .accept-button-container input[type="submitCancel"]:hover {
                background-color: #dc3545;
            }
             .accept-button-container input[type="submitCancel"] {
                 height: 15px;
                 width: 60px;
                padding: 15px 30px;
                font-size: 16px;
                background-color: #e66365;
                color: #fff;
                border: none;
                border-radius: 10px;
                cursor: pointer;
                transition: background-color 0.3s;
            }
            .popup, .popupcancel {
                background-color: #ffffff;
                border-radius: 6px;
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%) scale(0.1);
                text-align: center;
                padding: 20px;
                visibility: hidden;
                transition: transform 0.4s, top 0.4s;
                z-index: 9999;
                width: 300px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            .popup.open-popup, .popupcancel.open-popupcancel {
                visibility: visible;
                transform: translate(-50%, -50%) scale(1);
            }
            .popup h2, .popupcancel h2 {
                margin-top: 0;
                color: #333;
            }
            .popup p, .popupcancel p {
                color: #333;
            }
            .popup button, .popupcancel button {
                width: 80%;
                padding: 10px 0;
                color: #fff;
                border: 0;
                outline: none;
                font-size: 18px;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s;
            }
            .popup button.ok {
                background: #45a049;
                margin-top: 20px;
            }
            .popup button.close {
                background: #c3e6cb;
                margin-top: 20px;
            }
            .popupcancel button.ok {
                background: #dc3545;
                margin-top: 20px;
            }
            .popupcancel button.close {
                background: #f8d7da;
                color: #333;
                margin-top: 20px;
            }
            .popupcancel button.close:hover {
                background: #f5c6cb;
            }
            .back-button {
    margin-top: 5px;
    margin-bottom: 5px;
    text-align: left;
      padding-left: 0.5cm;
}

 

.back-link i {
    margin-right: 5px;
    color: #555; /* Màu mũi tên xám */
    font-size: 20px;
    display: inline-block;
    transform: scaleX(1.75);/* Độ dài và kích thước của mũi tên */
}
            
        </style>
    </head>
    <body>
        <div class="header">
            <a href="ShipperPage">HungryHub</a>
            <a href="ShipperAccountPage" class="order_online">
                <i class="fas fa-user"></i>
            </a>
        </div>
        <div class="back-button">
    <a href="ShipperListAcceptPage" class="back-link">
        <i class="fas fa-arrow-left"></i>
    </a>
</div>
        <div class="container">
            <div class="order-info">
                <h1>Order Information</h1>
                <%
                    OrderItem orderItem = null;
                    ArrayList<OrderItem> orderItemList = (ArrayList<OrderItem>) request.getAttribute("orderItemList");
                    if (orderItemList == null || orderItemList.isEmpty()) {
                %>
                <div class="message message-error">Order not found or empty.</div>
                <%
                    } else {
                        orderItem = orderItemList.get(0);
                        double total = 0.0;
                        for (OrderItem item : orderItemList) {
                            total += item.getDish().getPrice() * item.getQuantity();
                        }
                %>
                <div>
                    <p><strong>Order ID:</strong> <%= orderItem.getOrder().getOrder_id()%></p>
                    <p><strong>Shipper ID:</strong> <%= orderItem.getOrder().getShipper().getAccount_id()%></p>
                    <p><strong>Name Customer:</strong> <%= orderItem.getOrder().getCustomer().getName()%></p>
                    <p><strong>Phone Number:</strong> <%= orderItem.getOrder().getCustomer().getPhoneNumber()%></p>
                    <p><strong>Diner address:</strong> <%= orderItem.getOrder().getDiner().getAddress()%></p>
                    <p><strong>Customer address:</strong> <%= orderItem.getOrder().getCustomer().getAddress()%></p>
                    <p><strong>Status Order:</strong> <%= orderItem.getOrder().getOrder_status()%></p>
                    <p><strong>Total:</strong> <%= total%></p>
                </div>
                <%
                    }
                %>

                <div class="accept-button-container">
                    <input type="submit" onclick="openPopup()" value="Complete">
                    <div class="popup">
                        <h2>--------------------------------</h2>
                        <h2>Complete</h2>
                        <p>Your Order Completed</p>
                        <form id="completeForm" action="OrderItemServlet" method="GET">
                            <input type="hidden" name="command" value="Complete">
                            <input type="hidden" name="orderId" value="<%=orderItem.getOrder().getOrder_id()%>">
                            <button class="ok" type="submit" onclick="submitFormAndRedirect()">OK</button>
                            <button class="close" type="button" onclick="closePopup()">Close</button>
                        </form>
                    </div>   
                            
                            <input type="submitCancel" onclick="openPopupCancel()" value="Cancel">
                    <div class="popupcancel">
                        <h2>--------------------------------</h2>
                        <h2>CANCEL</h2>
                        <p>Are You Sure !!!</p>
                        <form id="cancelForm" action="OrderItemServlet" method="GET">
                            <input type="hidden" name="command" value="Canceled">
                            <input type="hidden" name="orderId" value="<%=orderItem.getOrder().getOrder_id()%>">
                            <textarea name="cancelReason" placeholder="Enter reason for cancellation" required></textarea>
                            <button class="ok" type="submit" onclick="submitFormCancel()">OK</button>
                            <button class="close" type="button" onclick="closePopupCancel()">Close</button>
                        </form>
                    </div>   
                </div>

 
            </div>

            <div class="sidebar">
                <div class="map">
                    <h2>Map</h2>
                    <iframe 
                        src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3834.3088216083095!2d108.21228731533485!3d16.06030858888254!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x314218d5c026f0b9%3A0x3a9c61e77580055e!2sFPT%20University%20Da%20Nang!5e0!3m2!1sen!2s!4v1614121511140!5m2!1sen!2s"
                        width="100%" 
                        height="80%" 
                        style="border:0;" 
                        allowfullscreen="" 
                        loading="lazy">
                    </iframe>
                </div>
                <div class="chat">
                    <h2>Chat</h2>
                    <div id="chat-box"></div>
                    <form id="chat-form" method="post" action="sendMessage">
                        <input type="text" name="message" placeholder="Type a message...">
                        <button type="submit">Send</button>
                    </form>
                </div>
            </div>
        </div>

        <script>
            function openPopup() {
                var popup = document.querySelector('.popup');
                popup.classList.add('open-popup');
            }

            function closePopup() {
                var popup = document.querySelector('.popup');
                popup.classList.remove('open-popup');
            }

            function submitFormAndRedirect() {
                document.getElementById("completeForm").submit();
            }

            function openPopupCancel() {
                var popupcancel = document.querySelector('.popupcancel');
                popupcancel.classList.add('open-popupcancel');
            }

            function closePopupCancel() {
                var popupcancel = document.querySelector('.popupcancel');
                popupcancel.classList.remove('open-popupcancel');
            }

            function submitFormCancel() {
                document.getElementById("cancelForm").submit();
            }
        </script>
    </body>
</html>
