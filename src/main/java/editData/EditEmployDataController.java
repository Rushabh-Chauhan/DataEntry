package editData;


import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import DataEntry.MainController;
import data.Employ;
import data.Salary;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class EditEmployDataController  implements Initializable{

	@FXML
	private JFXTextField address;

	@FXML
	private JFXTextField phone;

	@FXML
	private Button cancle;

	@FXML
	private JFXButton editButton;

	@FXML
	private Button save;

	@FXML
	private JFXTextField name;

	@FXML
	private JFXTextArea description;

	@FXML
	private JFXTextField givenSalary;

	@FXML
	private JFXTextField salary;

	@FXML
	private JFXTextArea salaryDescription;

	@FXML
	private DatePicker date;

	@FXML
	private JFXTabPane pane;

	@FXML
	private JFXTextField salary1;

	@FXML
	private AnchorPane employDetails;

	@FXML
	private JFXTextField deduction;

	@FXML
	private JFXTextField commission;

	@FXML
	private JFXTreeTableView<Salary> treeView;

	@FXML
	private Button editSave;

	public int addTotalSalary;


	public  ObservableList<Salary> salaryOList;

	private Employ employ;




	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{	

		// setting the employ tap to information.
		this.employ = MainController.getSelectedEmploy();
		this.date.setValue(LocalDate.now());
		this.name.setText(this.employ.name);
		this.address.setText(this.employ.address);
		this.phone.setText(this.employ.phone);
		this.salary.setText(this.employ.salary+"");
		this.description.setText(this.employ.description);
		this.salary1.setText(this.salary.getText());
		// initilazing the observable list.
		this.salaryOList = FXCollections.observableArrayList();
		this.salaryOList.addAll(this.employ.getSalaryList());// getting the prexited salary list.


		// setting the table tree columns
		JFXTreeTableColumn<Salary, String> date = new JFXTreeTableColumn<>("Date");
		date.setPrefWidth(300);
		date.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Salary, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Salary, String> param) {
				return param.getValue().getValue().getDate();
			}
		});

		JFXTreeTableColumn<Salary, String> salaryC = new JFXTreeTableColumn<>("Salary");
		salaryC.setPrefWidth(300);
		salaryC.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Salary, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Salary, String> param) {
				return param.getValue().getValue().getSalay();
			}
		});

		JFXTreeTableColumn<Salary, String> commission = new JFXTreeTableColumn<>("Commission");
		commission.setPrefWidth(300);
		commission.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Salary, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Salary, String> param) {
				return param.getValue().getValue().getCommision();
			}
		});

		JFXTreeTableColumn<Salary, String> deduction = new JFXTreeTableColumn<>("Commission");
		deduction.setPrefWidth(300);
		deduction.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Salary, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Salary, String> param) {
				return param.getValue().getValue().getDeduction();
			}
		});

		JFXTreeTableColumn<Salary, String> description = new JFXTreeTableColumn<>("Description");
		description.setPrefWidth(300);
		description.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Salary, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Salary, String> param) {
				return param.getValue().getValue().getSalaryDescription();
			}
		});

		// adding the columns to the table
		TreeItem<Salary> root = new RecursiveTreeItem<Salary>(salaryOList, RecursiveTreeObject::getChildren);
		root.setExpanded(true);
		treeView.getColumns().setAll(date,salaryC,commission,deduction,description);
		treeView.setRoot(root);
		treeView.setShowRoot(false);
		treeView.setEditable(true);

	}

	@FXML
	void SaveData(ActionEvent event)   {


		int total = Integer.parseInt(this.commission.getText()) + Integer.parseInt(this.salary.getText())-Integer.parseInt(this.deduction.getText());
		this.givenSalary.setText(total+"");
		this.addTotalSalary = Integer.parseInt(this.givenSalary.getText());

		Salary s = new Salary(this.employ,this.date.getValue(),this.salary.getText(),this.commission.getText(),this.deduction.getText(),this.salaryDescription.getText());
		this.salaryOList.add(s);
		this.employ.addSalary(s);

	}

	@FXML
	void Cancel(ActionEvent event) {
		Stage stage = (Stage) this.pane.getScene().getWindow();
		stage.close();
	}

	@FXML
	void Edit(ActionEvent event) {
		this.editButton.setDisable(true);
		this.editButton.setOpacity(0);
		this.editSave.setDisable(false);
		this.editSave.setOpacity(1);


	}
	@FXML
    void EditSave(ActionEvent event) {

    }



	public LocalDate getDate() {
		return date.getValue();
	}

	public String getSalaryDescription() {
		return salaryDescription.getText();
	}

	@FXML
	void calculate(ActionEvent event) {
		int total = Integer.parseInt(this.commission.getText()) + Integer.parseInt(this.salary.getText())-Integer.parseInt(this.deduction.getText());
		this.givenSalary.setText(total+"");

	}



}
