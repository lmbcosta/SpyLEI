package controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import domain.interfaces.ICreateAgentHandler;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.util.Callback;
import view.Constants;

public class CreateAgentController extends AuthenticatedUseCaseController {

	private final class MySetChangeListener implements SetChangeListener<String> {
		@Override
		public void onChanged(javafx.collections.SetChangeListener.Change<? extends String> change) {
			sendToHandler();
		}
	}

	private final class ValueListener<T> implements ChangeListener<T> {
		@Override
		public void changed(ObservableValue<? extends T> obs, T oldValue, T newValue) {
			sendToHandler();
		}
	}

	private final class FocusListener implements ChangeListener<Boolean> {
		@Override
		public void changed(ObservableValue<? extends Boolean> obs, Boolean lostFocus, Boolean gotFocus) {
			if (lostFocus)
				sendToHandler();
		}
	}

	@FXML
	private TextField codenameField;

	@FXML
	private TextField accessKeyField;

	@FXML
	private ChoiceBox<String> accessKeyVerifierField;

	@FXML
	private ChoiceBox<String> docCodifierField;

	@FXML
	private TextField docKeyField;

	@FXML
	private ListView<String> languagesListView;

	@FXML
	private ListView<String> usersListView;

	private ObservableSet<String> selectedLanguages;
	private ObservableSet<String> selectedUsers;

	public CreateAgentController() {
		selectedLanguages = FXCollections.observableSet();
		selectedUsers = FXCollections.observableSet();
	}

	@Override
	public void init() {
		super.init();
		Arrays.asList(codenameField, accessKeyField, docKeyField)
				.forEach(x -> x.focusedProperty().addListener(new FocusListener()));
		Arrays.asList(accessKeyVerifierField, docCodifierField)
				.forEach(x -> x.valueProperty().addListener(new ValueListener<String>()));
		Arrays.asList(selectedLanguages, selectedUsers).forEach(x -> x.addListener(new MySetChangeListener()));
		loadAccessKeyVerifiers();
		loadDocumentCodifiers();
		loadLanguages();
		loadUsers();
	}

	@FXML
	void create(ActionEvent event) {
		ICreateAgentHandler h = sendToHandler();
		if (h != null) {
			h.confirmAgentCreation();
			mainView.popView();
		}
	}

	@FXML
	void cancel(ActionEvent event) {
		mainView.popView();
	}

	private ICreateAgentHandler sendToHandler() {
		ICreateAgentHandler h = app.getCreateAgentHandler();
		boolean readyToConfirm = initiateRegister(h) && defineAccessKey(h) && selectLanguages(h)
				&& defineKeyAndCoding(h) && selectUsers(h);
		return readyToConfirm ? h : null;
	}

	private boolean initiateRegister(ICreateAgentHandler h) {
		String codename = codenameField.getText();
		if (!codename.isEmpty())
			if (h.initiateRegister(codename))
				return true;
			else
				mainView.showError(Constants.ERROR_AGENT_EXISTS, Constants.ERROR_CODENAME_EXISTS_RECOVERY);
		return false;
	}

	private boolean defineAccessKey(ICreateAgentHandler h) {
		String access = accessKeyField.getText();
		Optional<String> verifier = Optional.ofNullable(accessKeyVerifierField.getValue());
		if (!access.isEmpty() && verifier.isPresent())
			if (h.defineAccessKey(access, verifier.get()))
				return true;
			else
				mainView.showError(Constants.ERROR_UNACCEPTED_ACCESSKEY, Constants.ERROR_UNACCEPTED_ACCESSKEY_RECOVERY);
		return false;
	}

	private boolean selectLanguages(ICreateAgentHandler h) {
		for (String language : selectedLanguages)
			h.selectLanguage(language);
		return true;
	}

	private boolean defineKeyAndCoding(ICreateAgentHandler h) {
		String key = docKeyField.getText();
		Optional<String> codifier = Optional.ofNullable(docCodifierField.getValue());
		if (!key.isEmpty() && codifier.isPresent()) {
			h.defineKeyAndCoding(key, codifier.get());
			return true;
		}
		return false;
	}

	private boolean selectUsers(ICreateAgentHandler h) {
		boolean userExists;
		for (String user : selectedUsers) {
			userExists = h.selectUser(user);
			assert userExists; // because we load the existing users
		}
		return true;
	}

	private void loadAccessKeyVerifiers() {
		ICreateAgentHandler handler = app.getCreateAgentHandler();
		accessKeyVerifierField
				.setItems(FXCollections.observableArrayList(IterableUtils.asList(handler.getPwdStrengthVerifiers())));
	}

	private void loadDocumentCodifiers() {
		ICreateAgentHandler handler = app.getCreateAgentHandler();
		docCodifierField
				.setItems(FXCollections.observableArrayList(IterableUtils.asList(handler.getDocumentCodifiers())));
	}

	private void loadLanguages() {
		ICreateAgentHandler handler = app.getCreateAgentHandler();
		loadListView(languagesListView, selectedLanguages, IterableUtils.asList(handler.getLanguages()));
	}

	private void loadUsers() {
		ICreateAgentHandler handler = app.getCreateAgentHandler();
		loadListView(usersListView, selectedUsers, IterableUtils.asList(handler.getUsers()));
	}

	private void loadListView(ListView<String> listView, Set<String> listSelection, Collection<String> data) {
		listView.setItems(FXCollections.observableArrayList(data));
		listView.setCellFactory(CheckBoxListCell.forListView(new Callback<String, ObservableValue<Boolean>>() {
			@Override
			public ObservableValue<Boolean> call(String item) {
				BooleanProperty observable = new SimpleBooleanProperty();
				observable.addListener((obs, wasSelected, isNowSelected) -> {
					if (isNowSelected)
						listSelection.add(item);
					else
						listSelection.remove(item);
				});
				return observable;
			}
		}));
	}

}
