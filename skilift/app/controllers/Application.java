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

    static Form<User> userForm = form(User.class);

    static User dummy = null;

    public static Result index() {
    	
    	if (dummy == null) dummy =  new User("dummy@dummymail.com","1234","paypal");
        return ok(index.render("Skilift", Liftstation.find.all(), dummy));
    }

    public static Result login() {
        return ok(login.render("Skilift login"));
    }

    public static Result favourites() {
        if (dummy == null) dummy =  new User("dummy@dummymail.com","1234","paypal");
        return ok(favourites.render("Skilift favourites",dummy.favourites, dummy));
    }

    public static Result addFavourite(Integer LiftstationID) {
        if (dummy == null) dummy = new User("dummy@dummymail.com","1234","paypal");
        Liftstation station = Liftstation.find.byId(LiftstationID);
        dummy.addFavourite(station);
        return ok(favourites.render("Skilift favourites",dummy.favourites, dummy));
    }
    
    public static Result removeFavourite(Integer LiftstationID) {
    	
    	if (dummy == null) dummy = new User("dummy@dummymail.com", "1234", "paypal");
    	Liftstation station = Liftstation.find.byId(LiftstationID);
    	dummy.removeFavourite(station);
    	return ok(favourites.render("Skilift favourites",dummy.favourites, dummy));
    }

    public static Result search() { 
    	
    	if (dummy == null) dummy = new User("dummy@dummymail.com", "1234", "paypal");
    	return ok(search.render("search for stations",new ArrayList<>(), dummy)); 
    }

    public static Result newUser() {
        Form<User> filledForm = userForm.bindFromRequest();
        if(filledForm.hasErrors()) {
            return badRequest(
                views.html.register.render(userForm)
            );
        } else {
            User.create(filledForm.get());

            return redirect(controllers.routes.Application.index());
        }
    }

    public static Result loginUser() {
        Form<User> filledForm = userForm.bindFromRequest();
        User user = filledForm.get();
        if(filledForm.hasErrors()) {
            System.out.println("lol");
            return badRequest(
                    views.html.register.render(userForm)
            );
        } else {
            session("connected",user.mail);
            return redirect(controllers.routes.Application.index());
        }
    }

    public static Result searchLiftstations(String name) {
    	if (dummy == null) dummy = new User("dummy@dummymail.com", "1234", "paypal");
        //DynamicForm requestData = form().bindFromRequest();
        //String name = requestData.get("Search");
        return ok(search.render("search for stations",Liftstation.findForName(name), dummy));
    }
    public static Result registerScreen() {
        return ok(register.render(userForm));
    }

    public static Result jump(Integer LiftstationID) {
        Liftstation station = Liftstation.find.byId(LiftstationID);
        return ok(jump.render("jump the queue!",station));
    }
}
