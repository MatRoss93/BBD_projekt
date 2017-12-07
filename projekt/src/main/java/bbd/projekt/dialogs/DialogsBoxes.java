package bbd.projekt.dialogs;

import java.util.Optional;
import bbd.projekt.utils.FxmlUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class DialogsBoxes {

	
	public static void dialogAboutApplication() {
		
		Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
		informationAlert.setTitle(FxmlUtils.getString("menu.bar.pomoc.info.title"));
		informationAlert.setHeaderText(FxmlUtils.getString("menu.bar.pomoc.info.header"));
		informationAlert.setContentText(FxmlUtils.getString("menu.bar.pomoc.info.content"));
		informationAlert.showAndWait();
		
	}
	
	public static Optional<ButtonType> confirmCloseApplication() {
		
		Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
		confirmationDialog.setTitle(FxmlUtils.getString("aplikacja.zamknij.title"));
		confirmationDialog.setHeaderText(FxmlUtils.getString("aplikacja.zamknij.header"));
		Optional<ButtonType> result = confirmationDialog.showAndWait();
		return result;
	}
	
}
