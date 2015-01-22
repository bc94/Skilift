package controllers;

import models.Liftstation;
import models.User;
import play.data.Form;
import play.mvc.Call;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.favourites;
import views.html.jump;
import views.html.login;
import views.html.register;

import static play.data.Form.form;

public class Usermanager extends Controller {

	public static Result registerScreen() {
		return play.mvc.Results.TODO;
	}

	public static Result logout() {
		session().clear();
		flash("success", "You've been logged out");
		return redirect(
				routes.Usermanager.login()
		);
	}

	public static class Login {
		public String email;
		public String password;

		public String validate() {
			if (User.authenticate(email, password) == false) {
				return "Invalid user or password";
			}
			return null;
		}

	}



	public static Result authenticateLogin() {
		Form<Login> loginForm = form(Login.class).bindFromRequest();
		if (loginForm.hasErrors())
			return badRequest(login.render("Login to Skilift",form(Login.class)));
		else {
			session().clear();
			session("email", loginForm.get().email);
			return redirect(
					routes.Application.index()
			);
		}

	}

	public static Result newUser() {
		Form<User> registerForm =  form(User.class).bindFromRequest();
		if(registerForm.hasErrors()) {
			return badRequest(
					register.render("Register for Skilift",form(User.class))
			);
		} else {
			User.create(registerForm.get());
			return redirect((Call) Application.index());
		}
	}

	public static Result login() {
		return ok(login.render("Login to Skilift",form(Login.class)));
	}




	public void login(User user){
		
	}
	
	public void logout(User user){
		
	}


}
