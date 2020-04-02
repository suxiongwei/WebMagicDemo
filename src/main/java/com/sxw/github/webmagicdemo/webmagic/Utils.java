package com.sxw.github.webmagicdemo.webmagic;


import com.sxw.github.webmagicdemo.model.Article;

public class Utils {

    /**
     * 创建Article
     * @return
     */
    public static Article createArticle(String title, String content, String url){
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setUrl(url);
        return article;
    }

    /**
     * html字符过滤
     * @param str
     * @return
     */
    public static String replaceHTML(String str){
        return str != null ? str.replaceAll("\\<.*?>","").replaceAll("&nbsp;","") : "";
    }
}
