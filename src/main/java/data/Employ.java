package data;



import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

public class Employ extends RecursiveTreeObject<Employ>{


	public String address;
	public double salary;
	public double tSalary;
	public String name;
	public String description;
	public String phone;
	public String id;
	public ObservableList<Salary> salaryOList;


	public Employ(String id,String name, String address, String phone, String salary, String des)
	{
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.description = des;
		this.address = address;
		this.salary = Double.parseDouble(salary);
		this.tSalary = 0;
		this.salaryOList = FXCollections.observableArrayList();
	}

	public StringProperty getAddress() {
		return new SimpleStringProperty(this.address);
	}
	public StringProperty getSalary() {
		return new SimpleStringProperty(this.salary+"");
	}
	public StringProperty getName() {
		return new SimpleStringProperty(this.name);
	}
	public StringProperty getDescription() {
		return new SimpleStringProperty(this.description);
	}
	public StringProperty getPhone() {
		return new SimpleStringProperty(this.phone);
	}
	public StringProperty getGivenSalary() {
		return new SimpleStringProperty(this.tSalary+"");
	}
	public StringProperty getid() {
		return new SimpleStringProperty(this.id);
	}
	public void GivenMoney(int money)
	{
		this.tSalary += money;
	}
	public double gettSalary() {
		return tSalary;
	}

	public void addSalary(ObservableList<Salary> salaryOList)
	{
		this.salaryOList.addAll(salaryOList);
	}

	public void addSalary(Salary salary)
	{
		this.salaryOList.add(salary);
	}

	public ObservableList<Salary> getSalaryList()
	{
		return this.salaryOList;
	}



	@SuppressWarnings("unchecked")
	public static void EmployTreeColomns(JFXTreeTableView<Employ> employTreeView, ObservableList<Employ> employOList)
	{
		JFXTreeTableColumn<Employ, String> name = new JFXTreeTableColumn<>("Name");
		name.setPrefWidth(460);
		name.setContextMenu(null);
		name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Employ, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Employ, String> param) {
				return param.getValue().getValue().getName();
			}
		});
		JFXTreeTableColumn<Employ, String> address = new JFXTreeTableColumn<>("Address");
		address.setPrefWidth(520);
		address.setContextMenu(null);
		address.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Employ, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Employ, String> param) {
				return param.getValue().getValue().getAddress();
			}
		});
		JFXTreeTableColumn<Employ, String> phone = new JFXTreeTableColumn<>("Phone Number");
		phone.setPrefWidth(460);
		phone.setContextMenu(null);
		phone.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Employ, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Employ, String> param) {
				return param.getValue().getValue().getPhone();
			}
		});
		JFXTreeTableColumn<Employ, String> salary = new JFXTreeTableColumn<>("Salary");
		salary.setPrefWidth(460);
		salary.setContextMenu(null);
		salary.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Employ, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Employ, String> param) {
				return param.getValue().getValue().getSalary();
			}
		});
		


		TreeItem<Employ> root = new RecursiveTreeItem<Employ>(employOList, RecursiveTreeObject::getChildren);
		root.setExpanded(true);
		employTreeView.getColumns().setAll(name,address,phone,salary);
		employTreeView.setRoot(root);
		employTreeView.setShowRoot(false);
		employTreeView.setEditable(true);


	}
}









