package com.sxw.github.webmagicdemo;

import com.sxw.github.webmagicdemo.model.Article;
import com.sxw.github.webmagicdemo.repository.ArticleRepository;
import com.sxw.github.webmagicdemo.webmagic.ArticlePipeline;
import com.sxw.github.webmagicdemo.webmagic.NeteaseNewsPageProcesser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.codecraft.webmagic.Spider;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebmagicDemoApplicationTests {
    @Autowired private ArticlePipeline articlePipeline;
    @Test
    public void NeteaseNewsPageProcesserTest() {
        long startTime, endTime;
        System.out.println("开始爬取...");
        startTime = System.currentTimeMillis();

        Spider.create(new NeteaseNewsPageProcesser())
                .addUrl("http://news.163.com/domestic")
                .addUrl("http://news.163.com/shehui")
                .addPipeline(articlePipeline)
                .thread(5)
                .run();

        endTime = System.currentTimeMillis();
        System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒");
    }

    @Test
    public void GovNewsPageProcesserTest() {
        long startTime, endTime;
        System.out.println("开始爬取...");
        startTime = System.currentTimeMillis();

        Spider.create(new NeteaseNewsPageProcesser())
                .addUrl("http://new.sousuo.gov.cn/column/19769/0.htm") //要闻
                .addUrl("http://new.sousuo.gov.cn/column/16704/0.htm") //热点
                .addUrl("http://new.sousuo.gov.cn/column/16700/0.htm") //部门新闻
                .addUrl("http://new.sousuo.gov.cn/column/16699/0.htm") //地方报道
                .addUrl("http://new.sousuo.gov.cn/column/16697/0.htm") //执法监管
                .addUrl("http://new.sousuo.gov.cn/column/19423/0.htm") //国务院信息
                .addUrl("http://new.sousuo.gov.cn/column/16622/0.htm") //讲话
                .addUrl("http://new.sousuo.gov.cn/column/16623/0.htm") //会议
                .addUrl("http://new.sousuo.gov.cn/column/16621/0.htm") //活动
                .addUrl("http://new.sousuo.gov.cn/column/16620/0.htm") //出访
                .addUrl("http://new.sousuo.gov.cn/column/16740/0.htm") //专题信息-最新
                .addUrl("http://new.sousuo.gov.cn/column/16739/0.htm") //专题信息-聚焦
                .addUrl("http://new.sousuo.gov.cn/column/16743/0.htm") //事件
                .addUrl("http://new.sousuo.gov.cn/column/16744/0.htm") //预案
                .addUrl("http://new.sousuo.gov.cn/column/16742/0.htm") //工作
                .addUrl("http://new.sousuo.gov.cn/column/16765/0.htm") //政策法规解读-专家
                .addUrl("http://new.sousuo.gov.cn/column/16764/0.htm") //政策法规解读-媒体
                .addUrl("http://new.sousuo.gov.cn/column/17999/0.htm") //评论-要论
                .addUrl("http://new.sousuo.gov.cn/column/18000/0.htm") //评论-时评
                .addUrl("http://new.sousuo.gov.cn/column/18001/0.htm") //评论-网评
                .addUrl("http://new.sousuo.gov.cn/column/16852/0.htm") //数据要闻
                .addPipeline(articlePipeline)
                .thread(5)
                .run();

        endTime = System.currentTimeMillis();
        System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒");
    }

}
