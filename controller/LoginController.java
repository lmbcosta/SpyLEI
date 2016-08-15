package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import view.Constants;

public class LoginController extends NotAuthenticatedUseCaseController {

	@FXML
	private TextField usernameField;
	@FXML
	private TextField passwordField;

	private String errorMessage;

	@Override
	public void update() {
		usernameField.clear();
		passwordField.clear();
	}

	/**
	 * Called when the user clicks the SignIn button.
	 */
	@FXML
	private void handleSignIn() {
		if (isInputValid())
			if (app.getLoginHandler().login(usernameField.getText(), passwordField.getText())) {
				mainView.pushView(Constants.MENU);
			} else
				mainView.showError(Constants.LOGIN_FAIL, Constants.ERROR_ACCESS_FAIL_RECOVERY);
		else
			mainView.showError(errorMessage, Constants.ERROR_ACCESS_FAIL_RECOVERY);
	}

	/**
	 * Validates the user input in the text fields.
	 * 
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		errorMessage = "";

		if (usernameField.getText() == null || usernameField.getText().length() == 0)
			errorMessage += Constants.LOGIN_INVALID_USERNAME + "\n";
		if (passwordField.getText() == null || passwordField.getText().length() == 0)
			errorMessage += Constants.LOGIN_INVALID_PASSWORD + "\n";

		return errorMessage.length() == 0;
	}
}
