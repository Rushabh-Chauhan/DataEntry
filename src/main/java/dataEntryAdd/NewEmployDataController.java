package dataEntryAdd;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;

import DataEntry.MainController;
import data.Employ;
import database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NewEmployDataController implements Initializable{
	@FXML
	public JFXTextField name;

	@FXML
	public JFXTextField address;

	@FXML
	public JFXTextField phone;

	@FXML
	public JFXTextField salary;

	@FXML
	public JFXTextArea description;

	@FXML
	public Pane pane;

	@FXML
	private Pane infopane;

	@FXML
	private JFXTextField id;

	public Employ newEmploy;

	public DatabaseHandler database;

	@Override
	public void initialize(URL url, ResourceBundle rb) 
	{
		this.database = MainController.database;
		JFXDepthManager.setDepth(infopane, 1);
		JFXDepthManager.setDepth(pane, 1);		
	}

	@FXML
	void SaveData(ActionEvent event) {
		// checking if any data is not empty
		if(id.getText().isEmpty()||name.getText().isEmpty()|| address.getText().isEmpty()|| phone.getText().isEmpty()||salary.getText().isEmpty()|| id.getText().equals("Adhar Number"))
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Please add all the fields");
			alert.showAndWait();
			return;
		}
		else
		{
			// checking if the salary doesn't contain any text;
			try {
				Double.parseDouble(salary.getText());
			} catch (NumberFormatException e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("Salary not Correct");
				alert.showAndWait();
				return;
			}
			// inserting the data into the database.
			String sql = "INSERT INTO EMPLOYTABLE VALUES("
					+"'"+id.getText()+"',"
					+"'"+name.getText()+"',"
					+"'"+address.getText()+"',"
					+"'"+phone.getText()+"',"
					+"'"+salary.getText()+"',"
					+"'"+description.getText()+"');";
			if(this.database.executeAction(sql))
			{
				//Refreshing the table;
				newEmploy = new Employ(id.getText(),name.getText(),address.getText(),phone.getText(),salary.getText(),description.getText());
				Stage stage = (Stage) this.pane.getScene().getWindow();
				stage.close();
				return;
			}
			else// error
			{
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("Adding to the database failed");
				alert.showAndWait();
				return;
			}
		}
	}

	@FXML
	void Cancel(ActionEvent event) {
		Stage stage = (Stage) this.pane.getScene().getWindow();
		stage.close();

	}
}
