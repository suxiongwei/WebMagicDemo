package com.sxw.github.webmagicdemo.webmagic;


import com.sxw.github.webmagicdemo.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class Utils {

    /**
     * 创建Article
     * @return
     */
    public static Article createArticle(String title, String content, String url){
        return Article.builder()
                .title(title)
                .content(content)
                .url(url)
                .build();
    }

    /**
     * html字符过滤
     * @param str
     * @return
     */
    public static String replaceHTML(String str){
        return str != null ? str.replaceAll("\\<.*?>",StringUtils.EMPTY)
                // 替换不换行空格
                .replaceAll("&nbsp;",StringUtils.EMPTY) : StringUtils.EMPTY;
    }

    public static void main(String[] args) {
        String html = "<p>test</p>";
        log.info(html.replaceAll("\\<.*?>", StringUtils.EMPTY));
    }
}
