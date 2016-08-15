package controller;

import java.util.Arrays;

import domain.interfaces.IReadDocAgentHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import view.Constants;

public class ReadDocAgentController extends AuthenticatedUseCaseController {

	@FXML
	private Pane submitPane;

	@FXML
	private TextField agentCodenameField;

	@FXML
	private TextField agentKeyField;

	@FXML
	private ListView<String> referencesListView;

	@FXML
	private Pane documentPane;

	@FXML
	private Label referenceField;

	@FXML
	private TextArea documentTextField;

	private IReadDocAgentHandler h;

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
		h = app.getReadDocAgentHandler();
		if (readDocFromAgent(h))
			readAvailableDocRefs(h);
		else
			h = null;
	}

	private boolean readDocFromAgent(IReadDocAgentHandler h) {
		String codename = agentCodenameField.getText();
		String accessKey = agentKeyField.getText();
		if (!codename.isEmpty() && !accessKey.isEmpty()) {
			if (h.readDoc(codename, accessKey))
				return true;
			else {
				agentCodenameField.clear();
				agentKeyField.clear();
				referencesListView.getItems().clear();
				mainView.showError(Constants.ERROR_ACCESS_FAIL, Constants.ERROR_ACCESS_FAIL_RECOVERY);
			}
		}
		return false;
	}

	private void readAvailableDocRefs(IReadDocAgentHandler h) {
		referencesListView.setItems(FXCollections.observableArrayList(IterableUtils.asList(h.docsOfCurrentAgent())));
	}

	@FXML
	void setCodenameAndAccessKey(ActionEvent event) {
		sendToHandler();
	}

	@FXML
	void cancel(ActionEvent event) {
		mainView.popView();
	}

	@FXML
	void readDocument(ActionEvent event) {
		if (canSubmitReadRequest())
			showDocumentContent();
	}

	private void showDocumentContent() {
		String ref = referencesListView.getSelectionModel().getSelectedItem();
		String content = String.join(System.lineSeparator(), IterableUtils.asList(h.selectDoc(ref)));
		referenceField.setText(ref);
		documentTextField.setText(content);
		submitPane.setVisible(false);
		documentPane.setVisible(true);
	}

	private boolean canSubmitReadRequest() {
		return validCodenameAndAccessKey() && validReference();
	}

	private boolean validCodenameAndAccessKey() {
		return h != null;
	}

	private boolean validReference() {
		return !referencesListView.getSelectionModel().isEmpty();
	}

	@FXML
	void close(ActionEvent event) {
		mainView.popView();
	}

}
