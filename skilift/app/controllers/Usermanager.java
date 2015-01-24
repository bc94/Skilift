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
import views.html.changePass;
import controllers.Application;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;

import static play.data.Form.form;

public class Usermanager extends Controller {

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

	public static class PassChanger {

		public String oldPassword;
		public String newPassword;

		public String validate() {

			User user = Application.getLoggedInUser();
			if(User.authenticate(user.mail, oldPassword) == false) {

				return "Invalid password, try again.";
			} else {

				user.changePW(newPassword);
				return null;
			}
		}
	}

	public static Result login() {
		return ok(login.render("Login to Skilift",form(Login.class)));
	}

	public static Result registerScreen() {
		return ok(register.render("Register to Skilift",form(User.class)));
	}

	@Security.Authenticated(Secured.class)
	public static Result changePass() { return ok(changePass.render("Change password", form(PassChanger.class)));}

	public static Result authenticateLogin() {
		Form<Login> loginForm = form(Login.class).bindFromRequest();
		if (loginForm.hasErrors()) {
			System.out.println("lol");
			return badRequest(login.render("Login to Skilift", form(Login.class)));
		}
		else {
			session().clear();
			session("email", loginForm.get().email);
			return redirect(
					routes.Application.index()
			);
		}

	}

	@Security.Authenticated(Secured.class)
	public static Result authenticateChange() {

		Form<PassChanger> changeForm = form(PassChanger.class).bindFromRequest();
		if(changeForm.hasErrors()) {
			System.out.println("lol");
			return badRequest(changePass.render("Change password", form(PassChanger.class)));
		} else {
			return redirect(routes.Application.account());
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
			return redirect(routes.Application.index());
		}
	}


	public static Result logout() {
		session().clear();
		flash("success", "You've been logged out");
		return redirect(
				routes.Usermanager.login()
		);
	}


}
