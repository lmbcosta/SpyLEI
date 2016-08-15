package controller;

import java.util.Arrays;

import domain.interfaces.IAddDocForAgentHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import view.Constants;

public class AddDocForAgentController extends AuthenticatedUseCaseController {

	@FXML
	private TextField agentCodenameField;

	@FXML
	private TextField agentKeyField;

	@FXML
	private Label referenceField;

	@FXML
	private TextField filepathField;

	@FXML
	private Pane submitPane;

	@FXML
	private Pane referencePane;

	private IAddDocForAgentHandler h;

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
		Arrays.asList(agentCodenameField, agentKeyField)
				.forEach(x -> x.focusedProperty().addListener(new FocusListener()));
	}

	private void sendToHandler() {
		h = app.getAddDocForAgentHandler();
		if (!giveAgentName(h))
			h = null;
	}

	private boolean giveAgentName(IAddDocForAgentHandler h) {
		String codename = agentCodenameField.getText();
		String accessKey = agentKeyField.getText();
		if (!codename.isEmpty() && !accessKey.isEmpty()) {
			if (h.giveAgentName(codename, accessKey))
				return true;
			else {
				agentCodenameField.clear();
				agentKeyField.clear();
				mainView.showError(Constants.ERROR_ACCESS_FAIL, Constants.ERROR_ACCESS_FAIL_RECOVERY);
			}
		}
		return false;
	}

	@FXML
	void setCodenameAndAccessKey(ActionEvent event) {
		sendToHandler();
	}

	@FXML
	void openFile(ActionEvent event) {
		filepathField.setText(mainView.openFileChooseDialog());
	}

	@FXML
	void cancel(ActionEvent event) {
		mainView.popView();
	}

	@FXML
	void submitDocument(ActionEvent event) {
		if (canSubmitDocument())
			showDocumentReference(h.giveDocumentName(filepathField.getText()));
	}

	private void showDocumentReference(String ref) {
		submitPane.setVisible(false);
		referencePane.setVisible(true);
		referenceField.setText(ref);
	}

	private boolean canSubmitDocument() {
		return validCodenameAndAccessKey() && validFilepath();
	}

	private boolean validCodenameAndAccessKey() {
		return h != null;
	}

	private boolean validFilepath() {
		return !filepathField.getText().isEmpty();
	}

	@FXML
	void close(ActionEvent event) {
		mainView.popView();
	}

}
