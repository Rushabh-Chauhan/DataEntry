package dataEntryAdd;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import DataEntry.MainController;
import data.Employ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

	@Override
	public void initialize(URL url, ResourceBundle rb) 
	{

	}

	@FXML
	void SaveData(ActionEvent event) {
		
		
		this.newEmploy = new Employ(name.getText(),address.getText(),phone.getText(),salary.getText(),description.getText());

		Stage stage = (Stage) this.pane.getScene().getWindow();
		stage.close();
	}
	
	 @FXML
	    void Cancel(ActionEvent event) {
	    	
	    	Stage stage = (Stage) this.pane.getScene().getWindow();
	    	stage.close();

	    }

}
