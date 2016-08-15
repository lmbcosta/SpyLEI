package controller;

import java.util.Arrays;
import java.util.Optional;

import domain.interfaces.ISetUnavailabilityHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import view.Constants;

public class SetUnavailabilityController extends AuthenticatedUseCaseController {

	@FXML
	private TextField codenameField;

	@FXML
	private TextField accessKeyField;

	@FXML
	private Label currentAvailabilityField;

	@FXML
	private ComboBox<String> newAvailabilityField;

	private final class FocusListener implements ChangeListener<Boolean> {
		@Override
		public void changed(ObservableValue<? extends Boolean> obs, Boolean lostFocus, Boolean gotFocus) {
			if (lostFocus)
				fetchCurrentAvailability();
		}
	}

	@Override
	public void init() {
		super.init();
		Arrays.asList(codenameField, accessKeyField).forEach(x -> x.focusedProperty().addListener(new FocusListener()));
		loadAvailability();
	}

	private void loadAvailability() {
		ISetUnavailabilityHandler handler = app.getSetUnavailabilityHandler();
		newAvailabilityField
				.setItems(FXCollections.observableArrayList(IterableUtils.asList(handler.kindsOfUnavailability())));
	}

	@FXML
	void setAvailability(ActionEvent event) {
		fetchCurrentAvailability();
	}

	private void fetchCurrentAvailability() {
		fetchCurrentAvailability(app.getSetUnavailabilityHandler());
	}

	private boolean fetchCurrentAvailability(ISetUnavailabilityHandler h) {
		String codename = codenameField.getText();
		String accessKey = accessKeyField.getText();
		if (!codename.isEmpty() && !accessKey.isEmpty()) {
			String currentAvailability = h.setUnavailable(codename, accessKey);
			if (currentAvailability != null) {
				currentAvailabilityField.setText(currentAvailability);
				return true;
			} else {
				codenameField.clear();
				accessKeyField.clear();
				mainView.showError(Constants.ERROR_ACCESS_FAIL, Constants.ERROR_ACCESS_FAIL_RECOVERY);
			}
		}
		return false;
	}

	private boolean sendToHandler() {
		ISetUnavailabilityHandler h = app.getSetUnavailabilityHandler();
		return fetchCurrentAvailability(h) && selectUnavailability(h);
	}

	private boolean selectUnavailability(ISetUnavailabilityHandler h) {
		Optional<String> kind = Optional.ofNullable(newAvailabilityField.getValue());
		if (kind.isPresent()) {
			h.selectUnavailability(kind.get()); // always true because we loaded the existing kinds
			return true;
		}
		return false;
	}

	@FXML
	void change(ActionEvent event) {
		if (sendToHandler())
			mainView.popView();
	}

	@FXML
	void cancel(ActionEvent event) {
		mainView.popView();
	}

}
