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
//		final TreeItem<Employ> rootn = new RecursiveTreeItem<Employ>(MainController.employ, RecursiveTreeObject::getChildren);
//        rootn.setExpanded(true);
//        MainController.treeView.getColumns().setAll(MainController.name,MainController.address);
//        MainController.treeView.setRoot(rootn);
//        MainController.treeView.setShowRoot(false);
//        MainController.treeView.setEditable(true);
//        MainController.treeView.getSelectionModel().selectFirst();
	}
	
	 @FXML
	    void Cancel(ActionEvent event) {
	    	
	    	Stage stage = (Stage) this.pane.getScene().getWindow();
	    	stage.close();

	    }

}
