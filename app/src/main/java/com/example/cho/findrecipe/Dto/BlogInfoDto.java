package com.example.cho.findrecipe.Dto;

/**
 * Created by Cho on 2018-01-10.
 */

public class BlogInfoDto {

    private String href;
    private String imageUrl;
    private String contentTitle;
    private String contentInfo;

    public BlogInfoDto(String href, String imageUrl, String contentTitle, String contentInfo) {
        this.href = href;
        this.imageUrl = imageUrl;
        this.contentTitle = contentTitle;
        this.contentInfo = contentInfo;
    }

    public String getHref() {
        return href;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public String getContentInfo() {
        return contentInfo;
    }

}
