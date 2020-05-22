package Moneycontrollers;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import data.Salary;
import database.DatabaseHandler;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

public class SalaryTableController implements Initializable {

    @FXML
    private JFXTreeTableView<Salary> salaryTableView;
    
    public ObservableList<Salary> salaryOList;
    
    private DatabaseHandler database;
    
    @Override
	public void initialize(URL url, ResourceBundle rb) 
	{    	
    	database = DatabaseHandler.getHandler();
    	this.salaryOList = FXCollections.observableArrayList();
    	this.salarytable();
    	this.loadEmployTable();
	}
    
    
	@SuppressWarnings("unchecked")
	private void salarytable()
	{
		JFXTreeTableColumn<Salary, String> name = new JFXTreeTableColumn<>("Name");
		name.setPrefWidth(300);
		name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Salary, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Salary, String> param) {
				return param.getValue().getValue().getName();
			}
		});
		JFXTreeTableColumn<Salary, String> date = new JFXTreeTableColumn<>("Date");
		date.setPrefWidth(250);
		date.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Salary, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Salary, String> param) {
				return param.getValue().getValue().getDate();
			}
		});
		JFXTreeTableColumn<Salary, String> salary = new JFXTreeTableColumn<>("Salary");
		salary.setPrefWidth(250);
		salary.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Salary, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Salary, String> param) {
				return param.getValue().getValue().getSalay();
			}
		});
		JFXTreeTableColumn<Salary, String> commision = new JFXTreeTableColumn<>("Commision");
		commision.setPrefWidth(250);
		commision.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Salary, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Salary, String> param) {
				return param.getValue().getValue().getCommision();
			}
		});
		JFXTreeTableColumn<Salary, String> deduction = new JFXTreeTableColumn<>("Deduction");
		deduction.setPrefWidth(250);
		deduction.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Salary, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Salary, String> param) {
				return param.getValue().getValue().getDeduction();
			}
		});
		JFXTreeTableColumn<Salary, String> total = new JFXTreeTableColumn<>("Total");
		total.setPrefWidth(250);
		total.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Salary, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Salary, String> param) {
				return param.getValue().getValue().gettotal();
			}
		});
		JFXTreeTableColumn<Salary, String> id = new JFXTreeTableColumn<>("Payment ID");
		id.setPrefWidth(300);
		id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Salary, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Salary, String> param) {
				return param.getValue().getValue().getPaymentID();
			}
		});
		
		
		TreeItem<Salary> root = new RecursiveTreeItem<Salary>(salaryOList, RecursiveTreeObject::getChildren);
		root.setExpanded(true);
		salaryTableView.getColumns().setAll(name,date,salary,commision,deduction,total,id);
		salaryTableView.setRoot(root);
		salaryTableView.setShowRoot(false);
		salaryTableView.setEditable(true); 
	}
	private void loadEmployTable()
	{
		String sql = "SELECT * FROM SALARYTABLE";
		ResultSet rs = database.executeQueri(sql);
		try {
			while(rs.next())
			{
				String paymentid = rs.getString("payment_id");
				String name = rs.getString("Name");
				Date date = rs.getDate("Date");
				String commission = rs.getString("commission");
				String salary = rs.getString("Salary");
				String deduction = rs.getString("deduction");
				String total = rs.getString("Total");
				String des = rs.getString("description");

				this.salaryOList.add(new Salary(paymentid,name,date,salary,commission,deduction,total,des));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
