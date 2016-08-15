package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import view.Constants;

public class MenuController extends NotAuthenticatedUseCaseController {

	@FXML
	void createAgent(ActionEvent event) {
		mainView.pushView(Constants.VIEW_CREATE_AGENT);
	}

	@FXML
	void addDocumentToAgent(ActionEvent event) {
		mainView.pushView(Constants.VIEW_ADD_DOCUMENT_FOR_AGENT);
	}

	@FXML
	void readDocumentFromAgent(ActionEvent event) {
		mainView.pushView(Constants.VIEW_READ_DOCUMENT_FROM_AGENT);
	}

	@FXML
	void unavailableAgent(ActionEvent event) {
		mainView.pushView(Constants.VIEW_AVAILABILITY_AGENT);
	}

	@FXML
	void readAgentInfo(ActionEvent event) {
		mainView.pushView(Constants.VIEW_SEARCH_AGENT);
	}

	@FXML
	void createMission(ActionEvent event) {
		mainView.pushView(Constants.VIEW_CREATE_MISSION);
	}

	@FXML
	void readMissionInfo(ActionEvent event) {
		mainView.pushView(Constants.VIEW_SEARCH_MISSION);
	}

	@FXML
	void logout(ActionEvent event) {
		System.out.println("Logged out!");
		app.getLogoutHandler().logout();
		mainView.popView();
	}

}
