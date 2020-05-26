package DataEntry;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTreeTableView;

import data.Employ;
import data.Salary;
import dataEntryAdd.NewEmployDataController;
import database.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController implements Initializable {



	private  ObservableList<Employ> employOList;

	public static ObservableList<Salary> salaryOList;

	@FXML
	private JFXTreeTableView<Employ> employTreeView;

	@FXML
	private JFXTreeTableView<Salary> salaryTreeView;

	@FXML
	private AnchorPane employTab;

	@FXML
	private BorderPane boderPane;

	@FXML
	private JFXTreeTableView<?> dealerTreeTable;

	private static Employ rEmploy;

	public static DatabaseHandler database;



	@Override
	public void initialize(URL url, ResourceBundle rb) 
	{
		try {
			database = DatabaseHandler.getHandler();
		} catch (Exception e) {
			System.out.print("problem in loading database in maincontroller....."+e.getLocalizedMessage());
		}
		employOList = FXCollections.observableArrayList();
		Employ.EmployTreeColomns(employTreeView, employOList);
		this.loadEmployTable(); 
	}

	@FXML
	void NewEmployData(ActionEvent event)throws IOException,NullPointerException{

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/dataEntryAdd/NewEmployDataFXML.fxml"));
		Parent root = loader.load();
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		//stage.initStyle(StageStyle.TRANSPARENT);
		stage.setResizable(false);
		stage.showAndWait(); 

		NewEmployDataController c = loader.getController();
		this.employOList.add(c.newEmploy);
	}

	@FXML
	void deleteEmploy(ActionEvent event) {
		TreeItem<Employ> selected = employTreeView.getSelectionModel().getSelectedItem();
		if(selected == null)
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Please Select The Employ To Delete");
			alert.showAndWait();
			return;
		}

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText("Delete Employ");
		alert.setContentText("Are you sure do you want to delete "+selected.getValue().name);
		Optional<ButtonType> ans = alert.showAndWait();
		if(ans.get() == ButtonType.OK)
		{
			boolean result = database.deleteEmploy(selected.getValue(),"employtable");
			if(result)
			{
				// Refreshes the list after the deletion; 
				this.employOList.clear();
				this.loadEmployTable();

			}
			else// error when not deleted
			{
				Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
				alert1.setHeaderText(null);
				alert1.setContentText("Deletion failed. Try again");
				alert1.showAndWait();
				return;
			}
		}
		return;
	}


	@FXML
	void giveSalary(ActionEvent event) throws IOException {

		TreeItem<Employ> selected = employTreeView.getSelectionModel().getSelectedItem();
		if(selected == null)
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Please Select The Employ To Delete");
			alert.showAndWait();
			return;
		}

		rEmploy = employTreeView.getSelectionModel().getSelectedItem().getValue();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Moneycontrollers/GiveSalaryFXML.fxml"));
		Parent root = loader.load();
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		//stage.initStyle(StageStyle.TRANSPARENT);
		stage.setTitle(rEmploy.name);
		stage.setResizable(false);
		employTreeView.getSelectionModel().clearSelection();
		stage.showAndWait(); 
	}

	@FXML
	void SalaryTable(ActionEvent event) {
		rEmploy = null;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Moneycontrollers/SalaryTableFXML.fxml"));
		Parent root = null;
		try {
			root = loader.load();
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			//stage.initStyle(StageStyle.TRANSPARENT);
			stage.setTitle("Salary Table");
			stage.setMaximized(true);
			stage.showAndWait(); 
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("problem in loading salary table...."+e.getLocalizedMessage());
		}
	}

	@FXML
	void employSalaryTable(ActionEvent event) {
		TreeItem<Employ> selected = employTreeView.getSelectionModel().getSelectedItem();
		if(selected == null)
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Please Select The Employ To Delete");
			alert.showAndWait();
			return;
		}

		TreeItem<Employ> dat = employTreeView.getSelectionModel().getSelectedItem();
		rEmploy = dat.getValue();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Moneycontrollers/SalaryTableFXML.fxml"));
		Parent root = null;
		try {
			root = loader.load();
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			//stage.initStyle(StageStyle.TRANSPARENT);
			stage.setTitle("Salary Table");
			stage.showAndWait(); 
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("problem in loading salary table...."+e.getLocalizedMessage());
		}
	}


	// opens a new tab shows all the deleted items. 
	@FXML
	void deletedItems(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/deletedItems/DeletedEmployFXML.fxml"));
		Parent root = null;
		try {
			root = loader.load();
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			//stage.initStyle(StageStyle.TRANSPARENT);
			stage.setTitle("Salary Table");
			stage.setMaximized(true);
			stage.showAndWait();
			this.employOList.clear();
			loadEmployTable();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("problem in loading DeletedEmployFXML...."+e.getLocalizedMessage());
		}
	}

	@FXML
	void editemploy(MouseEvent e) {
		if (e.getClickCount() == 2 && employTreeView.getSelectionModel().getSelectedItem() != null)
		{
			TreeItem<Employ> dat = employTreeView.getSelectionModel().getSelectedItem();
			rEmploy = dat.getValue();	
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/editData/EditEmployDataFXML.fxml"));
			Parent root = null;
			try {
				root = loader.load();
				Stage stage = new Stage();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.initModality(Modality.APPLICATION_MODAL);
				//stage.initStyle(StageStyle.TRANSPARENT);
				stage.setResizable(false);
				stage.setTitle(dat.getValue().name);
				stage.showAndWait(); 
			} catch (IOException e1) {
				System.out.print("Error in loading the double clicked window..."+e1.getLocalizedMessage());
			}			
			employTreeView.getSelectionModel().clearSelection();
			//Refreshes the table. 
			this.employOList.clear();
			this.loadEmployTable();
		}
	}

	private void loadEmployTable()
	{
		String sql = "SELECT * FROM EMPLOYTABLE";
		ResultSet rs = database.executeQueri(sql);
		try {
			while(rs.next())
			{
				String id = rs.getString("ID");
				String name = rs.getString("Name");
				String address = rs.getString("Address");
				String phone = rs.getString("Phone");
				String salary = rs.getString("Salary");
				String des = rs.getString("description");

				employOList.add(new Employ(id,name,address,phone,salary,des));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Employ getSelectedEmploy()
	{
		return rEmploy;
	}










}
