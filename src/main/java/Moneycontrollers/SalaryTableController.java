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

import DataEntry.MainController;
import data.Employ;
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

	private Employ employ;

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL url, ResourceBundle rb) 
	{    	
		this.employ = MainController.getSelectedEmploy();  	
		database = DatabaseHandler.getHandler();
		this.salaryOList = FXCollections.observableArrayList();
		JFXTreeTableColumn<Salary, String> name = new JFXTreeTableColumn<>("Name");
		name.setPrefWidth(300);
		name.setContextMenu(null);
		name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Salary, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Salary, String> param) {
				return param.getValue().getValue().getName();
			}
		});
		salaryTableView.getColumns().setAll(name);

		Salary.salaryTable(this.salaryTableView,this.salaryOList);
		this.loadSalaryTable();
	}

	private void loadSalaryTable()
	{
		boolean check = true;
		if(employ != null)
		{
			check = false;
		}

		String sql = "SELECT * FROM SALARYTABLE";
		ResultSet rs = database.executeQueri(sql);
		try {
			while(rs.next())
			{
				if(check || rs.getString("ID").equals(employ.id))
				{
					String paymentid = rs.getString("payment_id");
					String id = rs.getString("ID");
					Date date = rs.getDate("Date");
					String commission = rs.getString("commission");
					String salary = rs.getString("Salary");
					String deduction = rs.getString("deduction");
					String total = rs.getString("Total");
					String des = rs.getString("description");
					this.salaryOList.add(new Salary(paymentid,id,date,salary,commission,deduction,total,des));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("Error in loading table"+e.getLocalizedMessage());
			
		}
	}

}
