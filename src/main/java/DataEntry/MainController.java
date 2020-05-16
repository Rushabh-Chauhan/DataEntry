package DataEntry;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.JFXTreeView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import data.Employ;
import data.Salary;
import dataEntryAdd.NewEmployDataController;
import database.DatabaseHandler;
import editData.EditEmployDataController;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class MainController implements Initializable {



	private ObservableList<Employ> employOList;
	public static ObservableList<Salary> salaryOList;
	
	@FXML
	private JFXTreeTableView<Employ> treeView;

	@FXML
	private AnchorPane employTab;

	@FXML
	private BorderPane boderPane;
	
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
		this.EmployTreeColomns();
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
	}
	
	  @FXML
	    void deleteEmploy(ActionEvent event) {
		  TreeItem<Employ> selected = treeView.getSelectionModel().getSelectedItem();
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
			alert.showAndWait();
			return;
	    }




	@SuppressWarnings("unchecked")
	private void EmployTreeColomns()
	{
		JFXTreeTableColumn<Employ, String> name = new JFXTreeTableColumn<>("Name");
		name.setPrefWidth(460);
		name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Employ, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Employ, String> param) {
				return param.getValue().getValue().getName();
			}
		});
		JFXTreeTableColumn<Employ, String> address = new JFXTreeTableColumn<>("Address");
		address.setPrefWidth(520);
		address.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Employ, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Employ, String> param) {
				return param.getValue().getValue().getAddress();
			}
		});
		JFXTreeTableColumn<Employ, String> phone = new JFXTreeTableColumn<>("Phone Number");
		phone.setPrefWidth(460);
		phone.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Employ, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Employ, String> param) {
				return param.getValue().getValue().getPhone();
			}
		});
		JFXTreeTableColumn<Employ, String> salary = new JFXTreeTableColumn<>("Salary");
		salary.setPrefWidth(460);
		salary.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Employ, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Employ, String> param) {
				return param.getValue().getValue().getSalary();
			}
		});

		//this.employOList.add(new Employ("rushabh","rushabh","7458","5000","45edfdf84"));

		EmployEditAndSalaryScreen();

		TreeItem<Employ> root = new RecursiveTreeItem<Employ>(employOList, RecursiveTreeObject::getChildren);
		root.setExpanded(true);
		treeView.getColumns().setAll(name,address,phone,salary);
		treeView.setRoot(root);
		treeView.setShowRoot(false);
		treeView.setEditable(true);


	}

	// edit and save screen properties.
	private  void EmployEditAndSalaryScreen()
	{
		treeView.setOnMouseClicked(e->{


			if (e.getClickCount() == 2 && treeView.getSelectionModel().getSelectedItem() != null)
			{
				TreeItem<Employ> dat = treeView.getSelectionModel().getSelectedItem();
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
					stage.setResizable(true);
					stage.setTitle(dat.getValue().name);
					stage.showAndWait(); 
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.print("Error in loading the double clicked window..."+e1.getLocalizedMessage());
				}			
				treeView.getSelectionModel().clearSelection();
			}
		});

	}
	
	private void loadEmployTable()
	{
		String sql = "SELECT * FROM EMPLOYTABLE";
		ResultSet rs = database.executeQueri(sql);
		try {
			while(rs.next())
			{
				String name = rs.getString("Name");
				String address = rs.getString("Address");
				String phone = rs.getString("Phone Number");
				String salary = rs.getString("Salary");
				String des = rs.getString("description");
				
				this.employOList.add(new Employ(name,address,phone,salary,des));
				
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
