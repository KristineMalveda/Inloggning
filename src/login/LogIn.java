package login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LogIn extends Application {

	String email;
	String userPassword;

	@Override
	public void start(Stage primaryStage) {
		// Application Stage
		primaryStage.setTitle("JavaFX Welcome");

		primaryStage.show();

		BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(10, 50, 50, 50));

		// GridPane with Gap and Padding Properties
		GridPane grid = new GridPane();
		grid.setId("pane");
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(50, 50, 50, 50));

		Scene scene = new Scene(grid, 300, 300);
		scene.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);

		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(5.0);
		dropShadow.setOffsetX(3.0);
		dropShadow.setOffsetY(3.0);
		dropShadow.setColor(Color.color(0.4, 0.5, 0.5));

		Text scenetitle = new Text("Log In");
		grid.add(scenetitle, 0, 0, 2, 1);
		scenetitle.setEffect(dropShadow);
		scenetitle.setCache(true);
		scenetitle.setFill(Color.web("0x3b596d"));
		scenetitle.setFont(Font.font("Comic Sans", FontWeight.BOLD, 25));

		Label userEmail = new Label("E-post:");
		userEmail.setId("label");
		grid.add(userEmail, 0, 1);

		TextField userEmailTextField = new TextField("kristine@yahoo.com");
		grid.add(userEmailTextField, 1, 1);

		Label pw = new Label("Password:");
		pw.setId("label");
		grid.add(pw, 0, 2);

		TextField userPassword = new TextField("KRistine2974&");
		grid.add(userPassword, 1, 2);

		// Buttons
		Button btn = new Button("Sign in");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);

		final Text actiontarget = new Text();
		grid.add(actiontarget, 1, 6);

		btn.setOnAction((event) -> {

			System.out.println("Button is clicked");
			String email = userEmailTextField.getText();
			String password = userPassword.getText();
			boolean isValidEpost = isValidEmail(email);
			boolean isValidPass = isValidPassword(password);

			if (isValidEpost && isValidPass) {
				JOptionPane.showMessageDialog(null, "Given Email or Password is Valid!");

			} else {
				JOptionPane.showInternalMessageDialog(null, "Oh no! It is either Email or password is invalid! ");
			}
			;

		});

	}

	public boolean isValidEmail(String email) {
		// Regular expression to accept valid email id
		String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
		// Creating a pattern object
		Pattern pattern = Pattern.compile(regex);
		// Creating a Matcher object
		Matcher matcher = pattern.matcher(email);
		boolean isValid = false;

		EmailValidatorApache emailvalidator = new EmailValidatorApache();

		if (emailvalidator.isValid(email) || matcher.matches()) {
			isValid= true;
		} else {
			isValid=false;
		}

		return isValid;
	}

	public boolean isValidPassword(String pass) {
		String regexUpperCase = "[A-Z]{2}";
		String regexLowerCase = "[a-z]{2}";
		String regexNumber = ".*\\d.*\\d.*{2}";
		String specialChars = "[\\W\\D]";

		String[] array = { regexUpperCase, regexLowerCase, regexNumber, specialChars };

		int count = 0;
		// boolean specialChar = false;
		boolean passed = false;

		for (int i = 0; i < array.length; i++) {

			Pattern thispattern = Pattern.compile(array[i]);
			Matcher m = thispattern.matcher(pass);

			if (m.find()) {
				count++;

			}

			if (count == array.length) {
				passed = true;
			}

		}
		return passed;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
