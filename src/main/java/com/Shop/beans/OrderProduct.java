package com.Shop.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/6/28.
 */
@Entity
@Table
public class OrderProduct {
    private int id;
    private int count;
    private int status;
    private int comment;
    private float productPrices;
    private String productName;
    private String productDescripes;        //
    private int cartId;
    private int ProductId;
    private int OrderId;
    private String img;
    private int userId;
    private float preprice;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getProductPrices() {
        return productPrices;
    }

    public void setProductPrices(float productPrices) {
        this.productPrices = productPrices;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescripes() {
        return productDescripes;
    }

    public void setProductDescripes(String productDescripes) {
        this.productDescripes = productDescripes;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getPreprice() {
        return preprice;
    }

    public void setPreprice(float preprice) {
        this.preprice = preprice;
    }
}
