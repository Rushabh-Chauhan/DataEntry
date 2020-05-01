package DataEntry;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.JFXTreeView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import data.Employ;
import dataEntryAdd.EmployDataController;
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


	LinkedList<Employ> employData = new LinkedList<Employ>();

	public ObservableList<Employ> employ;
	@FXML
	private JFXTreeTableView<Employ> treeView;

	@FXML
	private AnchorPane employTab;

	@FXML
	private BorderPane boderPane;


	@Override
	public void initialize(URL url, ResourceBundle rb) 
	{

		employ = FXCollections.observableArrayList();
		this.EmployTreeColomns();
		//treeView.getRow(new TreeTableView<Navin> );

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
		stage.showAndWait(); 
		EmployDataController c = loader.getController();
		this.employ.add(c.newEmploy);
		this.employData.add(c.newEmploy);

	}




	@SuppressWarnings("unchecked")
	public void EmployTreeColomns()
	{
		JFXTreeTableColumn<Employ, String> name = new JFXTreeTableColumn<>("Name");
		name.setPrefWidth(200);
		name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Employ, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Employ, String> param) {
				return param.getValue().getValue().getName();
			}
		});
		JFXTreeTableColumn<Employ, String> address = new JFXTreeTableColumn<>("Address");
		address.setPrefWidth(300);
		address.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Employ, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Employ, String> param) {
				return param.getValue().getValue().getAddress();
			}
		});
		JFXTreeTableColumn<Employ, String> phone = new JFXTreeTableColumn<>("Phone Number");
		phone.setPrefWidth(200);
		phone.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Employ, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Employ, String> param) {
				return param.getValue().getValue().getPhone();
			}
		});
		JFXTreeTableColumn<Employ, String> salary = new JFXTreeTableColumn<>("Salary");
		salary.setPrefWidth(200);
		salary.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Employ, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Employ, String> param) {
				return param.getValue().getValue().getSalary();
			}
		});
		JFXTreeTableColumn<Employ, String> tSalary = new JFXTreeTableColumn<>("Total Given Salary");
		tSalary.setPrefWidth(200);
		tSalary.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Employ, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Employ, String> param) {
				return param.getValue().getValue().getGivenSalary();
			}
		});

		//this.employ.add(new Employ("rushabh","rushabh","7458","4587","45edfdf84"));

		TreeItem<Employ> root = new RecursiveTreeItem<Employ>(employ, RecursiveTreeObject::getChildren);
		root.setExpanded(true);
		treeView.getColumns().setAll(name,address,phone,salary,tSalary);
		treeView.setRoot(root);
		treeView.setShowRoot(false);
		treeView.setEditable(true);
		treeView.setOnMouseClicked(e->{
			TreeItem<Employ> dat = treeView.getSelectionModel().getSelectedItem();
			
			if (e.getClickCount() == 2 && treeView.getSelectionModel().getSelectedItem() != null)
			{
				
			System.out.print(dat.getValue().name);
			treeView.getSelectionModel().clearSelection();
			
			}
		});
	}





}
