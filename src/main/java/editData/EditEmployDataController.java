package editData;



import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import DataEntry.MainController;
import data.Employ;
import database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class EditEmployDataController implements Initializable{

	@FXML
	private JFXTextField address;

	@FXML
	private JFXTextField phone;

	@FXML
	private JFXButton editButton;

	@FXML
	private JFXTextField name;
	
	@FXML
    private AnchorPane pane;

	@FXML
	private JFXTextArea description;

	@FXML
	private Button save;

	@FXML
	private JFXTextField salary;

	@FXML
	private Label id;
	
	private Employ employ;
	
	private DatabaseHandler database;
	
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		this.database = DatabaseHandler.getHandler();
		this.employ = MainController.getSelectedEmploy();
		this.name.setText(this.employ.name);
		this.address.setText(this.employ.address);
		this.phone.setText(this.employ.phone);
		this.salary.setText(this.employ.salary+"");
		this.description.setText(this.employ.description);
		this.id.setText("Adhar Number: "+this.employ.id);
	}

	@FXML
	void Edit(ActionEvent event) {
		this.name.setEditable(true);
		this.phone.setEditable(true);
		this.address.setEditable(true);
		this.salary.setEditable(true);
		this.description.setEditable(true);
		this.save.setDisable(false);
		this.save.setOpacity(1);

	}

	@FXML
	void save(ActionEvent event) {
		if(name.getText().isEmpty()|| address.getText().isEmpty()|| phone.getText().isEmpty()||salary.getText().isEmpty())
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Please add all the fields");
			alert.showAndWait();
			return;
		}
		else
		{
			// checking if the salary doesn't contain any text;
			try {
				Double.parseDouble(salary.getText());
			} catch (NumberFormatException e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("Salary not Correct");
				alert.showAndWait();
				return;
			}
		}
		
		
		boolean result = database.updateEmploy(new Employ(this.employ.id,name.getText(),address.getText(),phone.getText(),salary.getText(),description.getText()));
		if(!result)
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("The employ data is not updated please call support");
			alert.showAndWait();
		}

		
		Stage stage = (Stage) this.pane.getScene().getWindow();
		stage.close();
	}
}


//public class EditEmployDataController  implements Initializable{
//
//	@FXML
//	private JFXTextField address;
//
//	@FXML
//	private JFXTextField phone;
//
//	@FXML
//	private Button cancle;
//
//	@FXML
//	private JFXButton editButton;
//
//	@FXML
//	private Button save;
//
//	@FXML
//	private JFXTextField name;
//
//	@FXML
//	private JFXTextArea description;
//
//	@FXML
//	private JFXTextField givenSalary;
//
//	@FXML
//	private JFXTextField salary;
//
//	@FXML
//	private JFXTextArea salaryDescription;
//
//	@FXML
//	private DatePicker date;
//
//	@FXML
//	private JFXTabPane pane;
//
//	@FXML
//	private JFXTextField salary1;
//
//	@FXML
//	private AnchorPane employDetails;
//
//	@FXML
//	private JFXTextField deduction;
//
//	@FXML
//	private JFXTextField commission;
//
//	@FXML
//	private JFXTreeTableView<Salary> treeView;
//
//	@FXML
//	private Button editSave;
//
//	public int addTotalSalary;
//
//
//	public  ObservableList<Salary> salaryOList;
//
//	private Employ employ;
//
//
//
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public void initialize(URL url, ResourceBundle rb)
//	{	
//
//		// setting the employ tap to information.
//		this.employ = MainController.getSelectedEmploy();
//		this.date.setValue(LocalDate.now());
//		this.name.setText(this.employ.name);
//		this.address.setText(this.employ.address);
//		this.phone.setText(this.employ.phone);
//		this.salary.setText(this.employ.salary+"");
//		this.description.setText(this.employ.description);
//		this.salary1.setText(this.salary.getText());
//		// initilazing the observable list.
//		this.salaryOList = FXCollections.observableArrayList();
//		this.salaryOList.addAll(this.employ.getSalaryList());// getting the prexited salary list.
//
//
//		// setting the table tree columns
//		JFXTreeTableColumn<Salary, String> date = new JFXTreeTableColumn<>("Date");
//		date.setPrefWidth(300);
//		date.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Salary, String>, ObservableValue<String>>() {
//			@Override
//			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Salary, String> param) {
//				return param.getValue().getValue().getDate();
//			}
//		});
//
//		JFXTreeTableColumn<Salary, String> salaryC = new JFXTreeTableColumn<>("Salary");
//		salaryC.setPrefWidth(300);
//		salaryC.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Salary, String>, ObservableValue<String>>() {
//			@Override
//			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Salary, String> param) {
//				return param.getValue().getValue().getSalay();
//			}
//		});
//
//		JFXTreeTableColumn<Salary, String> commission = new JFXTreeTableColumn<>("Commission");
//		commission.setPrefWidth(300);
//		commission.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Salary, String>, ObservableValue<String>>() {
//			@Override
//			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Salary, String> param) {
//				return param.getValue().getValue().getCommision();
//			}
//		});
//
//		JFXTreeTableColumn<Salary, String> deduction = new JFXTreeTableColumn<>("Commission");
//		deduction.setPrefWidth(300);
//		deduction.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Salary, String>, ObservableValue<String>>() {
//			@Override
//			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Salary, String> param) {
//				return param.getValue().getValue().getDeduction();
//			}
//		});
//
//		JFXTreeTableColumn<Salary, String> description = new JFXTreeTableColumn<>("Description");
//		description.setPrefWidth(300);
//		description.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Salary, String>, ObservableValue<String>>() {
//			@Override
//			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Salary, String> param) {
//				return param.getValue().getValue().getSalaryDescription();
//			}
//		});
//
//		// adding the columns to the table
//		TreeItem<Salary> root = new RecursiveTreeItem<Salary>(salaryOList, RecursiveTreeObject::getChildren);
//		root.setExpanded(true);
//		treeView.getColumns().setAll(date,salaryC,commission,deduction,description);
//		treeView.setRoot(root);
//		treeView.setShowRoot(false);
//		treeView.setEditable(true);
//
//	}
//
//	@FXML
//	void SaveData(ActionEvent event)   {
//
//
//		int total = Integer.parseInt(this.commission.getText()) + Integer.parseInt(this.salary.getText())-Integer.parseInt(this.deduction.getText());
//		this.givenSalary.setText(total+"");
//		this.addTotalSalary = Integer.parseInt(this.givenSalary.getText());
//
//		Salary s = new Salary(this.employ,this.date.getValue(),this.salary.getText(),this.commission.getText(),this.deduction.getText(),this.salaryDescription.getText());
//		this.salaryOList.add(s);
//		this.employ.addSalary(s);
//
//	}
//
//	@FXML
//	void Cancel(ActionEvent event) {
//		Stage stage = (Stage) this.pane.getScene().getWindow();
//		stage.close();
//	}
//
//	@FXML
//	void Edit(ActionEvent event) {
//		this.editButton.setDisable(true);
//		this.editButton.setOpacity(0);
//		this.editSave.setDisable(false);
//		this.editSave.setOpacity(1);
//
//
//	}
//	@FXML
//    void EditSave(ActionEvent event) {
//
//    }
//
//
//
//	public LocalDate getDate() {
//		return date.getValue();
//	}
//
//	public String getSalaryDescription() {
//		return salaryDescription.getText();
//	}
//
//	@FXML
//	void calculate(ActionEvent event) {
//		int total = Integer.parseInt(this.commission.getText()) + Integer.parseInt(this.salary.getText())-Integer.parseInt(this.deduction.getText());
//		this.givenSalary.setText(total+"");
//
//	}
//
//
//
//}
