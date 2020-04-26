package DataEntry;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;

import data.Data;
import dataEntryAdd.EmployDataController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController implements Initializable {
	

	LinkedList<Data> employ = new LinkedList<Data>();
	
	@Override
	public void initialize(URL url, ResourceBundle rb) 
	{
		
	}

	@FXML
	void NewEmployData(ActionEvent event)throws IOException{

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/dataEntryAdd/EmployDataFXML.fxml"));
		Parent root = loader.load();
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		//stage.initStyle(StageStyle.TRANSPARENT);
		stage.setResizable(false);
		stage.show(); 
		EmployDataController c = loader.getController();
		this.employ.add(c.newEmploy);
		
	}



}
