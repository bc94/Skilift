package controllers;

import models.Liftstation;
import models.User;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import java.io.*;

import views.html.*;

import java.util.ArrayList;

import static play.data.Form.form;

public class Application extends Controller {

    public static Result index() {

        User user = getLoggedInUser();
        return ok(index.render("Skilift", Liftstation.find.all(), user));
    }

    @Security.Authenticated(Secured.class)
    public static Result jump(Integer LiftstationID) {
        Liftstation station = Liftstation.find.byId(LiftstationID);
        return ok(jump.render("jump the queue!", station));
    }

    @Security.Authenticated(Secured.class)
    public static Result favourites() {
        User user = getLoggedInUser();
        return ok(favourites.render("Skilift favourites",user.favourites, user));
    }

    @Security.Authenticated(Secured.class)
    public static Result addFavourite(Integer LiftstationID) {
        User user = getLoggedInUser();
        Liftstation station = Liftstation.find.byId(LiftstationID);
        user.addFavourite(station);
        return ok(favourites.render("Skilift favourites", user.favourites, user));
    }

    @Security.Authenticated(Secured.class)
    public static Result removeFavourite(Integer LiftstationID) {
        User user = getLoggedInUser();

        Liftstation station = Liftstation.find.byId(LiftstationID);
        user.removeFavourite(station);
        return ok(favourites.render("Skilift favourites",user.favourites, user));
    }


    public static Result search() {

        User user = getLoggedInUser();
    	return ok(search.render("search for stations", new ArrayList<Liftstation>(), user));
    }


    public static Result searchLiftstations(String name) {
        User user = getLoggedInUser();
        //DynamicForm requestData = form().bindFromRequest();
        //String name = requestData.get("Search");
        return ok(search.render("search for stations",Liftstation.findForName(name), user));
    }
    public static Result registerScreen() {
        return ok(register.render("Register to Skilift",form(User.class)));
    }


    @Security.Authenticated(Secured.class)
    public static Result account(){
        User user = getLoggedInUser();
        return ok(account.render("account settings", user));
    }

    private static User getLoggedInUser() {
        String email;
        if ((email = session().get("email")) != null)
            return User.find.byId(email);
        return null;
    }
}
