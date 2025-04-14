package com.example.controllers;

import com.example.Repositories.UserRepository;
import com.example.models.App;
import com.example.models.IO.Request;
import com.example.models.IO.Response;
import com.example.models.User;
import com.example.models.enums.SecurityQuestion;
import com.example.models.enums.types.MenuTypes;
import com.example.utilities.Validation;
import com.example.views.AppView;

public class SignInMenuController extends Controller {

    private static User userOfForgetPassword = null;

    public static User getUserOfForgetPassword() {
        return userOfForgetPassword;
    }

    public static Response handleAccountRecovery(Request request) {
        User user = userOfForgetPassword;
        userOfForgetPassword = null;
        String newPass = request.command;
        if (Validation.hashPassword(newPass).equals(user.getHashedPassword())) {
            return new Response(false, "Select a new password!");
        }

        if (newPass.equals("random")) {
            newPass = Validation.createRandomPassword();
        } else {
            if (!Validation.validatePasswordFormat(newPass)) {
                return new Response(false, "Password Format is invalid!");
            }
            if (!Validation.validatePasswordSecurity(newPass).equals("Success")) {
                return new Response(false, "Password isn't secure! " +
                        Validation.validatePasswordSecurity(newPass));
            }
        }
        user.setHashedPassword(Validation.hashPassword(newPass));
        UserRepository.saveUser(user);
        return new Response(true, "Successfully logged in! Password updated to: " + newPass);
    }

    public static Response handleRegister(Request request) {
        String username = request.body.get("username");
        String password = request.body.get("password");
        String email = request.body.get("email");
        String passwordConfirm = request.body.get("passwordConfirm");
        String nickname = request.body.get("nickname");
        String gender = request.body.get("gender");
        if (!Validation.validateUsername(username)) {
            return new Response(false, "Username is invalid!");
        }
        while (UserRepository.findUserByUsername(username) != null) {
            username = username + (int)(Math.random() * 69420);
        }
        if (!Validation.validateEmail(email)) {
            return new Response(false, "Email is invalid!");
        }

        if (password.equals(passwordConfirm) && password.equals("random")) {
            password = Validation.createRandomPassword();
            passwordConfirm = password;
        } else {
            if (!Validation.validatePasswordFormat(password)) {
                return new Response(false, "Password Format is invalid!");
            }
            if (!Validation.validatePasswordSecurity(password).equals("Success")) {
                return new Response(false, "Password isn't secure! " +
                        Validation.validatePasswordSecurity(password));
            }
            if (!password.equals(passwordConfirm)) {
                return new Response(false, "Passwords do not match!");
            }
        }
        User user = new User(gender, email, nickname, Validation.hashPassword(password), username);
        UserRepository.saveUser(user);
        return new Response(true, "User created! Password is: " + password);
    }

    public static Response handlePickQuestion(Request request) {
        int questionNumber = Integer.parseInt(request.body.get("questionNumber"));
        String answer = request.body.get("answer");
        String answerConfirm = request.body.get("answerConfirm");
        if (questionNumber < 1 || questionNumber > 4) {
            return new Response(false, "Invalid question number!");
        }
        if (!answer.equals(answerConfirm)) {
            return new Response(false, "Answer doesn't match!");
        }
        User user = UserRepository.findAllUsers().getLast();
        user.setAnswer(answer);
        user.setQuestion(SecurityQuestion.values()[questionNumber - 1]);
        UserRepository.saveUser(user);
        return new Response(true, "Question Picked!");
    }

    public static Response handleLogin(Request request) {
        String username = request.body.get("username");
        String password = request.body.get("password");
        String loginFlag = request.body.get("loginFlag");

        User user = UserRepository.findUserByUsername(username);
        if (user == null) {
            return new Response(false, "User not found!");
        }
        if (!Validation.hashPassword(password).equals(user.getHashedPassword())) {
            return new Response(false, "Password doesn't match!");
        }
        if (loginFlag != null) {
            UserRepository.saveStayLoggedInUser(user);
        }
        App.setLoggedInUser(user);
        App.setCurrMenuType(MenuTypes.MainMenu);
        return new Response(true, "Login Successful. Going to Main Menu!");
    }

    public static Response handleForgetPassword(Request request) {
        String username = request.body.get("username");
        User user = UserRepository.findUserByUsername(username);
        if (user == null) {
            return new Response(false, "User not found!");
        }
        userOfForgetPassword = user;
        return new Response(true, "User " + user.getUsername()
                + ": Answer your security question next.");
    }

    public static Response handleAnswer(Request request) {
        if (userOfForgetPassword == null) {
            return new Response(false, "You haven't entered your username.");
        }
        String answer = request.body.get("answer");
        User user = userOfForgetPassword;
        if (!answer.equals(user.getAnswer())) {
            userOfForgetPassword = null;
            return new Response(false, "Answer doesn't match!");
        }
        return new Response(true, "Your answer is correct; select your new password.");
    }

    public static Response handleListQuestions(Request request) {
        Response response = new Response();
        response.setSuccess(true);

        StringBuilder stringBuilder = new StringBuilder("List of questions:\n");
        int index = 1;
        for (SecurityQuestion question : SecurityQuestion.values()) {
            stringBuilder.append(index).append("- ").append(question).append("\n");
            index++;
        }
        response.setMessage(stringBuilder.toString());
        return response;
    }
}
