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
import java.util.LinkedList;
import java.util.List;

import static controllers.Usermanager.getLoggedInUser;
import static play.data.Form.form;

public class Application extends Controller {

    public static Result index() {
        User user = getLoggedInUser();
        return ok(index.render("Skilift", new LinkedList<Liftstation>(), user));
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
        return index();
    }

    @Security.Authenticated(Secured.class)
    public static Result removeFavourite(Integer LiftstationID) {

        User user = getLoggedInUser();
        Liftstation station = Liftstation.find.byId(LiftstationID);
        user.removeFavourite(station);
        return index();
    }

    @Security.Authenticated(Secured.class)
    public static Result confirm() {

        return ok(confirm.render("Confirmation"));
    }
    public static Result search() {
        User user = getLoggedInUser();
    	return ok(search.render("search for stations", new ArrayList<Liftstation>(), user));
    }

    public static Result displayPLZstations() {
        DynamicForm plzForm = Form.form().bindFromRequest();
        int plz = Integer.parseInt(plzForm.get("plz"));
        System.out.println("displayPLZstations called");
        List<Liftstation> stations = Liftstation.find.where().eq("plz",plz).findList();
        return ok(listelements.render(stations, getLoggedInUser()));
    }

    public static class Search {
        public String name;
    }


    public static Result searchLiftstations() {
        Form<Search> searchForm = form(Search.class).bindFromRequest();
        User user = getLoggedInUser();
        return ok(search.render("search for stations",Liftstation.search(searchForm.get().name), user));
    }


    @Security.Authenticated(Secured.class)
    public static Result account(){
        User user = getLoggedInUser();
        return ok(account.render("account settings", user));
    }

}
