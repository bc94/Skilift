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
import views.html.changePay;
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

			User user = getLoggedInUser();
			if(User.authenticate(user.mail, oldPassword) == false) {
				System.out.println(user.mail + " " + oldPassword + " " + newPassword);
				return "Invalid password, try again.";
			} else {
				return null;
			}
		}
	}

	public static class Payment {
		public String payment;
	}

	public static Result login() {
		return ok(login.render("Login to Skilift",form(Login.class)));
	}

	public static Result registerScreen() {
		return ok(register.render("Register to Skilift",form(User.class)));
	}

	@Security.Authenticated(Secured.class)
	public static Result changePass() { return ok(changePass.render("Change password", form(PassChanger.class)));}

	@Security.Authenticated(Secured.class)
	public static Result changePay() { return ok(changePay.render("Change payment method", form(Payment.class)));}

	public static Result authenticateLogin() {
		Form<Login> loginForm = form(Login.class).bindFromRequest();
		if (loginForm.hasErrors()) {
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
			return badRequest(changePass.render("Change password", form(PassChanger.class)));
		} else {
			getLoggedInUser().changePW(changeForm.get().newPassword);
			return redirect(routes.Application.account());
		}
	}

	@Security.Authenticated(Secured.class)
	public static Result authenticatePayment() {

		Form<Payment> payForm = form(Payment.class).bindFromRequest();
		if(payForm.hasErrors()){
			return badRequest(changePay.render("Change payment method", form(Payment.class)));
		} else {
			getLoggedInUser().changePayment(payForm.get().payment);
			System.out.println(getLoggedInUser().paymentMethod);
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
			session().clear();
			session("email", registerForm.get().mail);
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

	public static User getLoggedInUser() {
		String email;
		if ((email = session().get("email")) != null)
			return User.find.byId(email);
		return null;
	}
}
