package controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import domain.interfaces.ICreateMissionHandler;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.util.Callback;
import view.Constants;

public class CreateMissionController extends AuthenticatedUseCaseController {

	private final class MySetChangeListener implements SetChangeListener<String> {
		@Override
		public void onChanged(javafx.collections.SetChangeListener.Change<? extends String> change) {
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

	private final static class AgentData {
		final StringProperty codename, accessKey;

		AgentData(String codename, String accessKey) {
			this.codename = new SimpleStringProperty(codename);
			this.accessKey = new SimpleStringProperty(accessKey);
		}
	}

	@FXML
	private TextField codenameField;

	@FXML
	private TextField accessKeyField;

	@FXML
	private ComboBox<String> accessKeyVerifierField;

	@FXML
	private TextField responsibleCodenameField;

	@FXML
	private TextField responsibleAccessKeyField;

	@FXML
	private TableView<AgentData> agentsTable;

	@FXML
	private TableColumn<AgentData, String> agentAccessKeyColumn;

	@FXML
	private TableColumn<AgentData, String> agentCodenameColumn;

	@FXML
	private TextField agentCodenameField;

	@FXML
	private TextField agentAccessKeyField;

	@FXML
	private ListView<String> wordsListView;

	@FXML
	private TextField wordField;

	@FXML
	private ListView<String> usersListView;

	private ObservableList<AgentData> agentsData;
	private ObservableSet<String> selectedUsers;

	public CreateMissionController() {
		agentsData = FXCollections.observableArrayList();
		selectedUsers = FXCollections.observableSet();
	}

	@Override
	public void init() {
		super.init();
		agentCodenameColumn.setCellValueFactory(cell -> cell.getValue().codename);
		agentAccessKeyColumn.setCellValueFactory(cell -> cell.getValue().accessKey);
		agentsTable.setItems(agentsData);

		Arrays.asList(codenameField, accessKeyField, responsibleCodenameField, responsibleAccessKeyField)
				.forEach(x -> x.focusedProperty().addListener(new FocusListener()));
		Arrays.asList(selectedUsers).forEach(x -> x.addListener(new MySetChangeListener()));
		loadAccessKeyVerifiers();
		loadUsers();
	}

	@FXML
	void accessKeyVerifierChanged(ActionEvent event) {
		sendToHandler();
	}

	@FXML
	void addAgent(ActionEvent event) {
		String agentCodename = agentCodenameField.getText();
		String agentAccessKey = agentAccessKeyField.getText();
		if (!agentCodename.isEmpty() && !agentAccessKey.isEmpty()
				&& !agentsData.stream().anyMatch(x -> x.codename.getValue().equals(agentCodename))) {
			AgentData elem = new AgentData(agentCodename, agentAccessKey);
			agentsData.add(elem);
			agentCodenameField.clear();
			agentAccessKeyField.clear();
			sendToHandler();
		}
	}

	@FXML
	void addWord(ActionEvent event) {
		String word = wordField.getText();
		ObservableList<String> items = wordsListView.getItems();
		if (!items.contains(word)) {
			items.add(word);
			sendToHandler();
		}
		wordField.clear();
	}

	@FXML
	void create(ActionEvent event) {
		ICreateMissionHandler h = sendToHandler();
		if (h != null) {
			h.confirmMissionCreation();
			mainView.popView();
		}
	}

	@FXML
	void cancel(ActionEvent event) {
		mainView.popView();
	}

	private ICreateMissionHandler sendToHandler() {
		ICreateMissionHandler h = app.getCreateMissionHandler();
		boolean readyToConfirm = initiateRegister(h) && defineAccessKey(h) && selectResponsible(h) && selectAgents(h)
				&& defineKeywords(h) && selectUsers(h);
		return readyToConfirm ? h : null;
	}

	private boolean initiateRegister(ICreateMissionHandler h) {
		String codename = codenameField.getText();
		if (!codename.isEmpty())
			if (h.initiateRegister(codename))
				return true;
			else
				mainView.showError(Constants.ERROR_MISSION_EXISTS,
						Constants.ERROR_CODENAME_EXISTS_RECOVERY);
		return false;

	}

	private boolean defineAccessKey(ICreateMissionHandler h) {
		String access = accessKeyField.getText();
		Optional<String> verifier = Optional.ofNullable(accessKeyVerifierField.getValue());
		if (!access.isEmpty() && verifier.isPresent())
			if (h.defineAccessKey(access, verifier.get()))
				return true;
			else
				mainView.showError(Constants.ERROR_UNACCEPTED_ACCESSKEY, 
						Constants.ERROR_UNACCEPTED_ACCESSKEY_RECOVERY);
		return false;
	}

	private boolean selectResponsible(ICreateMissionHandler h) {
		String responsibleCodename = responsibleCodenameField.getText();
		String responsibleAccessKey = responsibleAccessKeyField.getText();
		if (!responsibleCodename.isEmpty() && !responsibleAccessKey.isEmpty())
			if (h.selectResponsible(responsibleCodename, responsibleAccessKey))
				return true;
			else
				mainView.showError(Constants.ERROR_RESPONSIBLE_ACCESS_FAIL,
						Constants.ERROR_ACCESS_FAIL_RECOVERY);
		return false;
	}

	private boolean selectAgents(ICreateMissionHandler h) {
		Set<AgentData> invalid = new HashSet<>();
		for (AgentData elem : agentsData)
			if (!h.selectAgent(elem.codename.getValue(), elem.accessKey.getValue())) {
				mainView.showError(Constants.ERROR_ACCESS_FAIL, 
						Constants.ERROR_ACCESS_FAIL_RECOVERY);
				invalid.add(elem);
			}
		agentsData.removeAll(invalid);
		return true;
	}

	private boolean defineKeywords(ICreateMissionHandler h) {
		for (String word : wordsListView.getItems())
			h.defineKeyword(word);
		return true;
	}

	private boolean selectUsers(ICreateMissionHandler h) {
		boolean userExists;
		for (String user : selectedUsers) {
			userExists = h.selectUser(user);
			assert userExists; // because we load the existing users
		}
		return true;
	}

	private void loadAccessKeyVerifiers() {
		ICreateMissionHandler handler = app.getCreateMissionHandler();
		accessKeyVerifierField.setItems(FXCollections.observableArrayList(IterableUtils.asList(handler.getPwdStrengthVerifiers())));
	}

	private void loadUsers() {
		ICreateMissionHandler handler = app.getCreateMissionHandler();
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
