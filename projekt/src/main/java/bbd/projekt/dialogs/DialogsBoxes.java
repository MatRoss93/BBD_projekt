package bbd.projekt.dialogs;

import java.util.Optional;
import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class DialogsBoxes {
	
	static ResourceBundle bundle = ResourceBundle.getBundle("bundles.Application_pl_PL");
	
	public static void dialogAboutApplication() {
		
		Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
		informationAlert.setTitle(bundle.getString("menu.bar.pomoc.info.title"));
		informationAlert.setHeaderText(bundle.getString("menu.bar.pomoc.info.header"));
		informationAlert.setContentText(bundle.getString("menu.bar.pomoc.info.content"));
		informationAlert.showAndWait();
		
	}
	
	public static Optional<ButtonType> confirmCloseApplication() {
		
		Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
		confirmationDialog.setTitle(bundle.getString("aplikacja.zamknij.title"));
		confirmationDialog.setHeaderText(bundle.getString("aplikacja.zamknij.header"));
		Optional<ButtonType> result = confirmationDialog.showAndWait();
		return result;
	}
}
