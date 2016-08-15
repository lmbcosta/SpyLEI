package view;

import java.io.File;
import java.io.IOException;
import java.util.Stack;

import controller.UseCaseController;
import domain.interfaces.IOrganization;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Pair;

public class MainView extends Application {

	private final Stack<Pair<Scene, UseCaseController>> views;
	private Stage stage;
	private static IOrganization app;

	public MainView() {
		views = new Stack<>();
	}

	public static void setApp(IOrganization obj) {
		app = obj;
	}

	@Override
	public void start(Stage stage) throws IOException {
		this.stage = stage;
		this.stage.setTitle("SpyLEI");
		this.stage.show();
		pushView("Login.fxml");
	}

	private FXMLLoader loadFXML(String fxml) {
		return new FXMLLoader(MainView.class.getResource(fxml));
	}

	public void pushView(String view) {
		try {
			FXMLLoader loader = loadFXML(view);
			Pane pane = loader.load();
			UseCaseController controller = loader.getController();
			controller.setViewAndDomain(this, app);
			if (controller.preConditionVerified()) {
				controller.init();
				Scene scene = new Scene(pane);
				views.push(new Pair<>(scene, controller));
				stage.setScene(scene);
			} else
				controller.preConditionError();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void popView() {
		views.pop();
		Pair<Scene, UseCaseController> pc = views.peek();
		Scene scene = pc.getKey();
		stage.setScene(scene);
		UseCaseController controller = pc.getValue();
		controller.update();
	}

	public void showError(String errorHeader, String errorText) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(Constants.ERROR_DIALOG_TITLE);
		alert.setHeaderText(errorHeader);
		alert.setContentText(errorText);
		alert.showAndWait();
	}

	public void showInfo(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(Constants.INFO_DIALOG_TITLE);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	public String openFileChooseDialog() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Plain Text (.txt)", "*.txt"));
		File file = fileChooser.showOpenDialog(stage);
		return file != null ? file.getAbsolutePath() : "";
	}

}