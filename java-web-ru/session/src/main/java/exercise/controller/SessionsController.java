package exercise.controller;

import exercise.dto.LoginPage;
import exercise.dto.MainPage;
import exercise.repository.UsersRepository;
import exercise.util.Security;
import io.javalin.http.Context;
import static io.javalin.rendering.template.TemplateUtil.model;

public class SessionsController {

    // BEGIN
    public static void build(Context ctx) {
        var page = new LoginPage("", null);
        ctx.render("build.jte", model("page", page));
    }

    public static void create(Context ctx) {
        var name = ctx.formParam("name");
        var password = Security.encrypt(ctx.formParam("password"));
        if (UsersRepository.findByName(name).isPresent()
            && password.equals(UsersRepository.findByName(name).get().getPassword())) {
            ctx.sessionAttribute("currentUser", name);
            ctx.redirect("/");
        } else {
            var e = "Wrong username or password.";
            var page = new LoginPage(name, e);
            ctx.render("build.jte", model("page", page)).status(422);
        }
    }

    public static void index(Context ctx) {
        var page = new MainPage(ctx.sessionAttribute("currentUser"));
        ctx.render("index.jte", model("page", page));
    }

    public static void destroy(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect("/sessions/build");
    }
    // END
}
