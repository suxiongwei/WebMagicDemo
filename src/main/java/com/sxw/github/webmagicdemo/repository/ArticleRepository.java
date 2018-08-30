package com.sxw.github.webmagicdemo.repository;


import com.sxw.github.webmagicdemo.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ArticleRepository extends JpaRepository<Article,Long> {
    @Override
    <S extends Article> S save(S s);
}
