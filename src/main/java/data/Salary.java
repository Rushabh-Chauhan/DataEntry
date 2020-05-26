package data;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import database.DatabaseHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

public class Salary extends RecursiveTreeObject<Salary>{

	public Date date;
	public double commission;
	public double salary;
	public double deduction;
	public double total;
	public String salaryDescription;
	public String name;
	public String paymentID;
	//public  ObservableList<Salary> salaryOList;

	public Salary(String payment_id,String id, Date date, String salary, String Commision, String Deduction,String total, String des)
	{
		// converting id to name using database.
		String sql = "SELECT * FROM EMPLOYTABLE";
		DatabaseHandler database = DatabaseHandler.getHandler();
		ResultSet rs = database.executeQueri(sql);
		try {
			while(rs.next())
			{
				if(rs.getString("ID").equals(id))
				{
					this.name = rs.getString("Name");
				}
			}
		} catch (SQLException e) {
			e.getLocalizedMessage();
		}
		
		// checks in deleted employ data if name is not in main employ data.
		if(name == null)
		{
			sql = "SELECT * FROM DELETEDEMPLOYTABLE";
			rs = database.executeQueri(sql);
			try {
				while(rs.next())
				{
					if(rs.getString("ID").equals(id))
					{
						this.name = rs.getString("Name");
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.print("error...."+e1.getLocalizedMessage());
			}
		}
		this.date = date;
		this.salary = Double.parseDouble(salary);
		this.commission = Double.parseDouble(Commision);
		this.deduction = Double.parseDouble(Deduction);
		this.total = Double.parseDouble(total);
		this.salaryDescription = des;
		if(payment_id.startsWith("@"))
		{
			this.paymentID = "CASH";
		}
		else
		{
			this.paymentID = payment_id;
		}
	}


	public StringProperty getName() {
		return new SimpleStringProperty(this.name.toString());
	}
	public StringProperty getPaymentID() {
		return new SimpleStringProperty(this.paymentID.toString());
	}
	public StringProperty getDate() {
		return new SimpleStringProperty(this.date.toString());
	}
	public StringProperty getCommision() {
		return new SimpleStringProperty(this.commission+"");
	}
	public StringProperty getDeduction() {
		return new SimpleStringProperty(this.deduction+"");
	}
	public StringProperty getSalaryDescription() {
		return new SimpleStringProperty(this.salaryDescription+"");
	}
	public StringProperty gettotal() {
		return new SimpleStringProperty(this.total+"");
	}
	public StringProperty getSalay() {
		return new SimpleStringProperty(this.salary+"");
	}
	
	
	@SuppressWarnings("unchecked")
	public static void salaryTable(JFXTreeTableView<Salary> salaryTableView,ObservableList<Salary> salaryOList)
	{
		JFXTreeTableColumn<Salary, String> date = new JFXTreeTableColumn<>("Date");
		date.setPrefWidth(250);
		date.setContextMenu(null);
		date.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Salary, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Salary, String> param) {
				return param.getValue().getValue().getDate();
			}
		});
		JFXTreeTableColumn<Salary, String> salary = new JFXTreeTableColumn<>("Salary");
		salary.setPrefWidth(250);
		salary.setContextMenu(null);
		salary.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Salary, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Salary, String> param) {
				return param.getValue().getValue().getSalay();
			}
		});
		JFXTreeTableColumn<Salary, String> commision = new JFXTreeTableColumn<>("Commision");
		commision.setPrefWidth(250);
		commision.setContextMenu(null);
		commision.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Salary, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Salary, String> param) {
				return param.getValue().getValue().getCommision();
			}
		});
		JFXTreeTableColumn<Salary, String> deduction = new JFXTreeTableColumn<>("Deduction");
		deduction.setPrefWidth(250);
		deduction.setContextMenu(null);
		deduction.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Salary, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Salary, String> param) {
				return param.getValue().getValue().getDeduction();
			}
		});
		JFXTreeTableColumn<Salary, String> total = new JFXTreeTableColumn<>("Total");
		total.setPrefWidth(250);
		total.setContextMenu(null);
		total.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Salary, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Salary, String> param) {
				return param.getValue().getValue().gettotal();
			}
		});
		JFXTreeTableColumn<Salary, String> id = new JFXTreeTableColumn<>("Payment ID");
		id.setPrefWidth(300);
		id.setContextMenu(null);
		id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Salary, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Salary, String> param) {
				return param.getValue().getValue().getPaymentID();
			}
		});
		TreeItem<Salary> root = new RecursiveTreeItem<Salary>(salaryOList, RecursiveTreeObject::getChildren);
		root.setExpanded(true);
		salaryTableView.getColumns().addAll(date,salary,commision,deduction,total,id);
		salaryTableView.setRoot(root);
		salaryTableView.setShowRoot(false);
		salaryTableView.setEditable(true); 
	}



}
