package controller;

import java.util.Arrays;

import domain.interfaces.ISearchAgentInfoHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import view.Constants;

public class SearchAgentInfoController extends AuthenticatedUseCaseController {

	@FXML
	private TextField codenameField;

	@FXML
	private TextField accessKeyField;

	@FXML
	private Label docCodifierField;

	@FXML
	private ListView<String> languagesListView;

	@FXML
	private ListView<String> usersListView;

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

	private boolean sendToHandler() {
		ISearchAgentInfoHandler h = app.getSearchAgentInfoHandler();
		return initiateSearch(h) && readAgentInfo(h);
	}

	private boolean initiateSearch(ISearchAgentInfoHandler h) {
		String codename = codenameField.getText();
		String accessKey = accessKeyField.getText();
		if (!codename.isEmpty() && !accessKey.isEmpty()) {
			if (h.initiateSearch(codename, accessKey))
				return true;
			else {
				codenameField.clear();
				accessKeyField.clear();
				docCodifierField.setText("");
				languagesListView.getItems().clear();
				mainView.showError(Constants.ERROR_ACCESS_FAIL, Constants.ERROR_ACCESS_FAIL_RECOVERY);
			}
		}
		return false;
	}

	private boolean readAgentInfo(ISearchAgentInfoHandler h) {
		docCodifierField.setText(h.getCodifierName());
		languagesListView.setItems(FXCollections.observableArrayList(IterableUtils.asList(h.getSpokenLanguages())));		
		return true;
	}

	@FXML
	void searchAgent(ActionEvent event) {
		sendToHandler();
	}

	@FXML
	void close(ActionEvent event) {
		mainView.popView();
	}

}
