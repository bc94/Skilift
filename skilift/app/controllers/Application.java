package controllers;

import models.Liftstation;
import models.User;
import play.*;
import play.data.Form;
import play.mvc.*;
import java.io.*;

import views.html.*;

import java.util.ArrayList;

public class Application extends Controller {

    static Form<User> registerForm = Form.form(User.class);

    static User dummy = null;

    public static Result index() {

        return ok(index.render("Skilift", Liftstation.find.all()));
    }

    public static Result login() {
        return ok(login.render("Skilift login"));
    }

    public static Result favourites() {
        if (dummy == null) dummy =  new User("dummy@dummymail.com","1234","paypal");
        return ok(favourites.render("Skilift favourites",dummy.favourites));
    }

    public static Result search() { return ok(search.render("search for stations",new ArrayList<>())); }

    public static Result newUser() {
        Form<User> filledForm = registerForm.bindFromRequest();
        if(filledForm.hasErrors()) {
            return badRequest(
                //    views.html.register.render(registerForm)
            );
        } else {
            User.create(filledForm.get());
            return redirect(routes.Application.index());
        }
    }

    public static Result searchLiftstations(String name) {
        return ok(search.render("search for stations",Liftstation.findForName(name)));
    }
    public static Result registerScreen() {
        return ok(register.render(registerForm));
    }

    public static Result jump(Integer LiftstationID) {
        Liftstation station = Liftstation.find.byId(LiftstationID);
        return ok(jump.render("jump the queue!",station));
    }
}
