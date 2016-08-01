package com.Shop.DTO;

import com.Shop.beans.Product;

import java.util.List;

/**
 * Created by Administrator on 2016/7/27.
 */
public class DetailDto {
    private Product detail;
    private List<String> imageUrl;

    public Product getProduct() {
        return detail;
    }

    public void setProduct(Product detail) {
        this.detail = detail;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }
}
