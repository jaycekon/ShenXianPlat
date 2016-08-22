package com.Shop.DTO;

import java.util.List;

/**
 * Created by Administrator on 2016/8/12.
 */
public class PictureDto {
    private List<ImageDto> imageUrl;

    public List<ImageDto> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<ImageDto> imageUrl) {
        this.imageUrl = imageUrl;
    }
}
