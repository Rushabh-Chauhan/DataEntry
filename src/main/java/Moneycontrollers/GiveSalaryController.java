package Moneycontrollers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import DataEntry.MainController;
import data.Employ;
import data.Salary;
import database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GiveSalaryController implements Initializable {

	@FXML
	private DatePicker date;

	@FXML
	private JFXTextField total;

	@FXML
	private JFXTextField deduction;

	@FXML
	private JFXTextArea description;

	@FXML
	private JFXTextField salary;

	@FXML
	private JFXTextField commision;

	@FXML
	private AnchorPane pane;

	private Employ employ; 

	private DatabaseHandler database;

	@Override
	public void initialize(URL url, ResourceBundle rb) 
	{
		this.employ = MainController.getSelectedEmploy();
		this.date.setValue(LocalDate.now());
		this.salary.setText(employ.salary+"");
		this.database = DatabaseHandler.getHandler();

	}

	@FXML
	void save(ActionEvent event) {
		if(this.salary.getText().isEmpty() || this.commision.getText().isEmpty()||this.deduction.getText().isEmpty())
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Please add all the fields");
			alert.showAndWait();
			return;
		}
		else
		{
			try {
				Double.parseDouble(salary.getText());
				Double.parseDouble(commision.getText());
				Double.parseDouble(deduction.getText());
			} catch (NumberFormatException e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("details not Correct");
				alert.showAndWait();
				return;
			}
		}
		this.total.setText(this.calculate()+"");

		String sql = "INSERT INTO SALARYTABLE VALUES("
				+"'"+employ.id+"',"
				+"'"+this.date.getValue()+"',"
				+"'"+this.salary.getText()+"',"
				+"'"+this.commision.getText()+"',"
				+"'"+this.deduction.getText()+"',"
				+"'"+this.total.getText()+"',"
				+"'"+this.description.getText()+"');";
		if(this.database.executeAction(sql))
		{
			//Refreshing the table;
			//Salary money = new Salary(this.employ.id,this.date.getValue(),this.salary.getText(),this.commision.getText(),this.deduction.getText(),description.getText());
			Stage stage = (Stage) this.pane.getScene().getWindow();
			stage.close();
			return;
		}
		else// error
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Adding to the database failed");
			alert.showAndWait();
			return;
		}

	}

	@FXML
	void calculate(ActionEvent event) {
		this.total.setText(this.calculate()+"");

	}

	public Double calculate() {
		Double total = Double.parseDouble(this.commision.getText()) + Double.parseDouble(this.salary.getText())-Double.parseDouble(this.deduction.getText());
		return total;
	}

}
