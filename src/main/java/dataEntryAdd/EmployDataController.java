package dataEntryAdd;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import data.Data;
import data.Employ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EmployDataController implements Initializable{
	@FXML
	private JFXTextField name;

	@FXML
	private JFXTextField address;

	@FXML
	private JFXTextField phone;

	@FXML
	private JFXTextField salary;

	@FXML
	private JFXTextArea description;

	@FXML
	private Pane pane;
	
	public Data newEmploy;

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
