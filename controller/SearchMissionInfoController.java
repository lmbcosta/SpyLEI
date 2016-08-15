package controller;

import java.util.Arrays;

import domain.interfaces.ISearchMissionInfoHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import view.Constants;

public class SearchMissionInfoController extends AuthenticatedUseCaseController {

	@FXML
	private TextField codenameField;

	@FXML
	private TextField accessKeyField;

	@FXML
	private Label responsibleField;

	@FXML
	private ListView<String> wordsListView;

	@FXML
	private ListView<String> agentsListView;

	private final class FocusListener implements ChangeListener<Boolean> {
		@Override
		public void changed(ObservableValue<? extends Boolean> obs, Boolean lostFocus, Boolean gotFocus) {
			if (lostFocus)
				sendToHandler();
		}
	}

	@Override
	public void init() {
		super.init();
		Arrays.asList(codenameField, accessKeyField).forEach(x -> x.focusedProperty().addListener(new FocusListener()));
	}

	private void sendToHandler() {
		ISearchMissionInfoHandler h = app.getSearchMissionInfoHandler();
		if (initiateSearch(h))
			readMissionInfo(h);
	}

	private boolean initiateSearch(ISearchMissionInfoHandler h) {
		String codename = codenameField.getText();
		String accessKey = accessKeyField.getText();
		if (!codename.isEmpty() && !accessKey.isEmpty()) {
			if (h.initiateSearch(codename, accessKey))
				return true;
			else {
				codenameField.clear();
				accessKeyField.clear();
				responsibleField.setText("");
				wordsListView.getItems().clear();
				agentsListView.getItems().clear();
				mainView.showError(Constants.ERROR_ACCESS_FAIL, Constants.ERROR_ACCESS_FAIL_RECOVERY);
			}
		}
		return false;
	}

	private void readMissionInfo(ISearchMissionInfoHandler h) {
		responsibleField.setText(h.getResponsibleAgent());
		wordsListView.setItems(FXCollections.observableArrayList(IterableUtils.asList(h.getCharacteristics())));
		agentsListView.setItems(FXCollections.observableArrayList(IterableUtils.asList(h.getParticipatingAgents())));
	}

	@FXML
	void searchMission(ActionEvent event) {
		sendToHandler();
	}

	@FXML
	void close(ActionEvent event) {
		mainView.popView();
	}

}
