package com.example.cho.findrecipe.service;

import com.example.cho.findrecipe.Dto.BlogInfoDto;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cho on 2018-02-02.
 */

public class HtmlParseService {

    private final String prefix = "https://search.naver.com/search.naver?query=";

    public List<BlogInfoDto> queryAndHtmlParse(String query) {

        List<BlogInfoDto> parsedBlogInfoList = new ArrayList<>();

        try {
            Connection.Response response = Jsoup.connect(prefix + query)
                    .method(Connection.Method.GET)
                    .header("User-agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1;)")
                    .execute();

            Document document = response.parse();

            Elements blogList = document.getElementsByClass("sh_blog_top");


            for (Element blog : blogList) {

                Element hrefElement = blog.getElementsByClass("sp_thmb thmb80").first();
                String href = hrefElement.attr("href");

                Element imageElement = blog.getElementsByClass("sh_blog_thumbnail").first();
                String imageUrl = imageElement.attr("src");

                Element contentTitleElement = blog.getElementsByClass("sh_blog_title").first();
                String contentTitle = contentTitleElement.attr("title");

                Element contentInfoElement = blog.getElementsByClass("sh_blog_passage").first();
                String contentInfo = contentInfoElement.html()
                        .replace("<strong class=\"hl\">", "")
                        .replace("</strong>", "")
                        .replace("\n", " ");

                BlogInfoDto blogInfoDto = new BlogInfoDto(href, imageUrl, contentTitle, contentInfo);

                parsedBlogInfoList.add(blogInfoDto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parsedBlogInfoList;

    }
}
