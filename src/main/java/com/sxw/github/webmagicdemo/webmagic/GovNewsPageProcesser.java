package com.sxw.github.webmagicdemo.webmagic;

import com.sxw.github.webmagicdemo.model.Article;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 爬取中央政府网要闻、热点、部门新闻、地方报道、执法监管等新闻信息
 */
@Component
public class GovNewsPageProcesser implements PageProcessor {

    // 新闻列表
    private final static String URL_LIST = "http://new\\.sousuo\\.gov\\.cn\\/column\\/\\d+\\/\\d+\\.htm";

    // 新闻列表页正文url
    private final static String URL_POST = "http://www\\.gov\\.cn/[\\w/-]+\\.htm";

    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36");

    @Override
    public void process(Page page) {
        // 列表页
        if(page.getUrl().regex(URL_LIST).match()){
            // 添加详情页请求链接
            page.addTargetRequests(page.getHtml().links().regex(URL_POST).all());
            // 添加列表页请求链接
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
        }else{// 详情页
            page.putField("title", Utils.replaceHTML(page.getHtml().xpath("//div[@class='pages-title']").toString()));
            page.putField("content", Utils.replaceHTML(page.getHtml().xpath("//div[@class='article-colum']/div[@class='pages_content']/table[@id='printContent']/tbody/tr/td").toString()));
            page.putField("url",page.getUrl().get());

            String title = page.getResultItems().get("title");
            String content = page.getResultItems().get("content");
            String url = page.getResultItems().get("url");

            // 创建article
            Article article = Utils.createArticle(title, content, url);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
