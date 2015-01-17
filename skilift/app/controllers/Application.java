package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Skilift"));
    }

    public static Result login() {
        return ok(login.render("Skilift login"));
    }

    public static Result favourites() {
        return ok(favourites.render("Skilift favourites"));
    }

    public static Result search() {
        return ok(search.render("search for stations"));
    }
}
