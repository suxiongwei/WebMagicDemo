package com.sxw.github.webmagicdemo.webmagic;

import com.sxw.github.webmagicdemo.model.Article;
import com.sxw.github.webmagicdemo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Map;

@Component
public class ArticlePipeline implements Pipeline {
    @Autowired private ArticleRepository articleRepository;
    @Override
    public void process(ResultItems resultItems, Task task) {
        Map<String,Object> items = resultItems.getAll();
        if(resultItems!=null&&resultItems.getAll().size()>0){
            Article article = new Article();
            article.setTitle((String) items.get("title"));
            article.setContent((String) items.get("content"));
            article.setUrl((String)items.get("url"));

            articleRepository.save(article);
        }
    }
}
