package com.Shop.DTO;

import com.Shop.beans.Product;

import java.util.List;

/**
 * Created by Administrator on 2016/7/27.
 */
public class IndexDto {
    private List<Product> items;
    private int status;
    private String errorMsg;
    private List<ImageDto> images;

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<ImageDto> getImages() {
        return images;
    }

    public void setImages(List<ImageDto> images) {
        this.images = images;
    }
}
