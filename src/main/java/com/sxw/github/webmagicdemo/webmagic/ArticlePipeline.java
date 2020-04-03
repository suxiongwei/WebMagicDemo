package com.sxw.github.webmagicdemo.webmagic;

import com.sxw.github.webmagicdemo.model.Article;
import com.sxw.github.webmagicdemo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Collections;
import java.util.Map;

@Component
public class ArticlePipeline implements Pipeline {
    @Autowired private ArticleRepository articleRepository;
    @Override
    public void process(ResultItems resultItems, Task task) {
        Map<String,Object> items = resultItems.getAll();
        if(resultItems !=null && !CollectionUtils.isEmpty(items)){
            Article article = Article.builder()
                    .title((String) items.get("title"))
                    .content((String) items.get("content"))
                    .url((String)items.get("url"))
                    .build();

            articleRepository.save(article);
        }
    }
}
