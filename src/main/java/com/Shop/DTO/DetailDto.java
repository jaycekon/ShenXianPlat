package com.Shop.DTO;

import com.Shop.beans.Comment;
import com.Shop.beans.Product;

import java.util.List;

/**
 * Created by Administrator on 2016/7/27.
 */
public class DetailDto {
    private Product detail;
    private List<ImageDto> imageUrl;
    private List<Comment> comments;

    public Product getProduct() {
        return detail;
    }

    public void setProduct(Product detail) {
        this.detail = detail;
    }

    public List<ImageDto> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<ImageDto> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
