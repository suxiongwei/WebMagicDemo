package com.sxw.github.webmagicdemo.util;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * @Auther suxiongwei
 * @Date 2020-04-03 11:28 上午
 */
@Slf4j
public class JsoupHttpClient {
    private static class innerWebClient{
        private static final WebClient webClient = new WebClient();
    }

    /**
     * 获取指定网页实体
     * https://blog.csdn.net/xiaomin1991222/article/details/50980955
     * @param url
     * @return
     */
    public static HtmlPage getHtmlPage(String url){
        WebRequest webRequest;
        try {
            webRequest = new WebRequest(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        // 调用此方法时加载WebClient
        WebClient webClient = innerWebClient.webClient;
        // 取消 JS 支持
        webClient.getOptions().setJavaScriptEnabled(false);
        // 取消 CSS 支持
        webClient.getOptions().setCssEnabled(false);
        webClient.getCookieManager().setCookiesEnabled(true);
        //设置cookie,如果你有cookie，可以在这里设置
        Set<Cookie> cookies = new HashSet<>();
        cookies.add(new Cookie(".bihukankan.com", "gr_user_id", "310b179f-b028-4876-823d-3e716e268be6"));
        cookies.forEach(e -> webClient.getCookieManager().addCookie(e));

        HtmlPage page = null;
        try{
            // 获取指定网页实体
            page = webClient.getPage(webRequest);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return page;
    }

}
