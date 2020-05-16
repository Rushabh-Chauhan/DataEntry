package dataEntryAdd;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import DataEntry.MainController;
import Database.DatabaseHandler;
import data.Employ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
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

	public Employ newEmploy;
	
	public DatabaseHandler database;

	@Override
	public void initialize(URL url, ResourceBundle rb) 
	{
		this.database = MainController.database;
	}

	@FXML
	void SaveData(ActionEvent event) {

		if(name.getText().isEmpty()|| address.getText().isEmpty()|| phone.getText().isEmpty()||salary.getText().isEmpty())
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Please add all the fields");
			alert.showAndWait();
			return;
		}
		else
		{
			try {
				Double.parseDouble(salary.getText());
			} catch (NumberFormatException e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("Salary not Correct");
				alert.showAndWait();
				return;
			}
			this.newEmploy = new Employ(name.getText(),address.getText(),phone.getText(),salary.getText(),description.getText());
			String sql = "INSERT INTO EMPLOYTABLE VALUES("
					+"'"+name.getText()+"',"
					+"'"+address.getText()+"',"
					+"'"+phone.getText()+"',"
					+"'"+salary.getText()+"',"
					+"'"+description.getText()+"');";
			if(this.database.executeAction(sql))
			{
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setContentText("Success");
				alert.showAndWait();
				MainController.employOList.add(this.newEmploy);
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
