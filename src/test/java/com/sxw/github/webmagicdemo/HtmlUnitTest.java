package com.sxw.github.webmagicdemo;

import com.gargoylesoftware.htmlunit.html.*;
import com.sxw.github.webmagicdemo.util.JsoupHttpClient;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @Auther suxiongwei
 * @Date 2020-04-03 11:42 上午
 */
@Slf4j
public class HtmlUnitTest {
    @SneakyThrows
    @Test
    public void testHtmlUnitFromBaidu(){
        // 获取指定网页实体
        HtmlPage page = JsoupHttpClient.getHtmlPage("https://www.baidu.com/");
//        log.info(page.asText());  //asText()是以文本格式显示
//        log.info(page.asXml());   //asXml()是以xml格式显示
        // 获取搜索输入框
        HtmlInput input = page.getHtmlElementById("kw");
        // 往输入框 “填值”
        input.setValueAttribute("绿林寻猫");
        // 获取搜索按钮
        HtmlInput btn = page.getHtmlElementById("su");
        // “点击” 搜索
        HtmlPage page2 = btn.click();
        // 选择元素
        List<HtmlElement> spanList= page2.getByXPath("//h3[@class='t']/a");
        for(int i=0;i<spanList.size();i++) {
            // 输出新页面的文本
            log.info("匹配到的元素:[{}、,{}]," ,i+1, spanList.get(i).asText());
        }
    }

    @SneakyThrows
    @Test
    public void testHtmlUnitFromBiHuKanKan(){
        // 获取指定网页实体
        HtmlPage page = JsoupHttpClient.getHtmlPage("https://www.bihukankan.com/");
        // 获取[找主播]dom节点
        DomElement domElement = page.getAnchorByHref("/find/anchor");
        // “点击”
        HtmlPage page2 = domElement.click();

        // 选择元素
        // 排名
        List<HtmlElement> uSortList= page2.getByXPath("//div[@class=\"table-list-1 number-font\"]/span");
        // 头像
        List<HtmlImage> uHeadList= page2.getByXPath("//div[@class=\"table-list-2\"]/img");
        // 名称
        List<HtmlElement> uNameList= page2.getByXPath("//div[@class=\"user-name tabs-hover\"]");
        // 类目
        List<HtmlElement> uTagList= page2.getByXPath("//div[@class=\"table-list-4 flex\"]");
        // 开播场次
        List<HtmlElement> playCountList= page2.getByXPath("//div[@class=\"table-list-5 number-font\"]/span");
        // 礼物收入
        List<HtmlElement> giftMoneyList= page2.getByXPath("//div[@class=\"table-list-6 number-font\"]/span");
        // 销售总额
        List<HtmlElement> totalCountList= page2.getByXPath("//div[@class=\"table-list-7 number-font\"]/span");
        // 带货能力
        List<HtmlElement> abilityList= page2.getByXPath("//div[@class=\"table-list-8 number-font\"]");



        int currentPageSize = uSortList.size();
        Assert.assertTrue(currentPageSize == uHeadList.size());
        Assert.assertTrue(currentPageSize == uNameList.size());

        for(int i = 0; i < currentPageSize; i++) {
            log.info("------------抓取达人数据start-------------");

            log.info("排名:[{}]", uSortList.get(i).asText());
            log.info("头像:[{}]", "https:" + uHeadList.get(i).getSrcAttribute());
            log.info("用户名:[{}]", uNameList.get(i).asText());

            HtmlElement tagElement = uTagList.get(i);
            Iterable<DomElement> tagChildElements = tagElement.getChildElements();
            StringBuilder tagStringBuilder = new StringBuilder();
            tagChildElements.forEach(e -> tagStringBuilder.append(e.asText()).append("、"));
            log.info("带货类目:[{}]", tagStringBuilder);
            log.info("开播场次:[{}]", playCountList.get(i).asText());
            log.info("礼物收入:[{}]", giftMoneyList.get(i).asText());
            log.info("销售总额:[{}]", totalCountList.get(i).asText());
            log.info("带货能力:[{}]", abilityList.get(i).asText());

            log.info("------------end------------- \n");
        }
    }
}
