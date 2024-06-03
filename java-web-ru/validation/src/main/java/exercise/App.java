package exercise;

import io.javalin.Javalin;
import io.javalin.validation.ValidationException;

import java.nio.Buffer;
import java.util.Collections;
import java.util.List;
import exercise.model.Article;
import exercise.dto.articles.ArticlesPage;
import exercise.dto.articles.BuildArticlePage;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;

import exercise.repository.ArticleRepository;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/articles", ctx -> {
            List<Article> articles = ArticleRepository.getEntities();
            var page = new ArticlesPage(articles);
            ctx.render("articles/index.jte", model("page", page));
        });

        // BEGIN
        app.post("/articles", ctx -> {
            String title = ctx.formParam("title");
            String  content = ctx.formParam("content");
            try {
                ctx.formParamAsClass("title", String.class)
                    .check(str -> str.length() > 1, "Название не должно быть короче двух символов")
                    .check(str -> !ArticleRepository.existsByTitle(str), "Статья с таким названием уже существует")
                    .get();
                ctx.formParamAsClass("content", String.class)
                    .check(str -> str.length() > 9, "Статья должна быть не короче 10 символов")
                    .get();

                var article = new Article(title, content);
                ArticleRepository.save(article);
                ctx.redirect("/articles");
            } catch(ValidationException e) {
                var page = new BuildArticlePage(title, content, e.getErrors());
                ctx.status(422).render("articles/build.jte", model("page", page));
            }     
                
        });

        app.get("/articles/build", ctx -> {
            var page = new BuildArticlePage();
            ctx.render("articles/build.jte", model("page", page));          
        });
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
