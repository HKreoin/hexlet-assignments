package exercise.mapper;

import exercise.dto.ArticleCreateDTO;
import exercise.dto.ArticleDTO;
import exercise.dto.ArticleUpdateDTO;
import exercise.model.Article;
import exercise.model.User;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-10T17:00:04+0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.39.0.v20240820-0604, environment: Java 17.0.12 (Eclipse Adoptium)"
)
@Component
public class ArticleMapperImpl extends ArticleMapper {

    @Autowired
    private JsonNullableMapper jsonNullableMapper;

    @Override
    public Article map(ArticleCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Article article = new Article();

        article.setContent( dto.getContent() );
        article.setSlug( dto.getSlug() );
        article.setTitle( dto.getTitle() );

        return article;
    }

    @Override
    public ArticleDTO map(Article model) {
        if ( model == null ) {
            return null;
        }

        ArticleDTO articleDTO = new ArticleDTO();

        articleDTO.setAuthor( modelAuthorName( model ) );
        articleDTO.setContent( model.getContent() );
        articleDTO.setCreatedAt( model.getCreatedAt() );
        articleDTO.setId( model.getId() );
        articleDTO.setSlug( model.getSlug() );
        articleDTO.setTitle( model.getTitle() );
        articleDTO.setUpdatedAt( model.getUpdatedAt() );

        return articleDTO;
    }

    @Override
    public void update(ArticleUpdateDTO dto, Article model) {
        if ( dto == null ) {
            return;
        }

        if ( jsonNullableMapper.isPresent( dto.getContent() ) ) {
            model.setContent( jsonNullableMapper.unwrap( dto.getContent() ) );
        }
        if ( jsonNullableMapper.isPresent( dto.getTitle() ) ) {
            model.setTitle( jsonNullableMapper.unwrap( dto.getTitle() ) );
        }
    }

    private String modelAuthorName(Article article) {
        if ( article == null ) {
            return null;
        }
        User author = article.getAuthor();
        if ( author == null ) {
            return null;
        }
        String name = author.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
