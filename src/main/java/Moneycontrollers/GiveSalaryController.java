package Moneycontrollers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import DataEntry.MainController;
import data.Employ;
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

	@FXML
	private JFXTextField payment;


	private Employ employ; 

	private DatabaseHandler database;

	private int count = 0;

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
		if(this.payment.getText().isEmpty()||this.salary.getText().isEmpty() || this.commision.getText().isEmpty()||this.deduction.getText().isEmpty())
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
		if(this.payment.getText().toUpperCase().equals("CASH")) 
		{
			// getting the count to its right counter;
			String sql = "SELECT * FROM SALARYTABLE";
			ResultSet rs = database.executeQueri(sql);
			try {
				while(rs.next())
				{
					if(rs.getString("payment_id").equals("@"+this.count))
					{
						count++;
					}	
				}
			} catch (SQLException e) {
				System.out.print("problem in getting count...."+e.getLocalizedMessage());
			}
			this.payment.setText("@"+this.count);
		}

		String sql = "INSERT INTO SALARYTABLE VALUES("
				+"'"+this.payment.getText()+"',"
				+"'"+this.employ.name+"',"
				+"'"+this.date.getValue()+"',"
				+"'"+this.salary.getText()+"',"
				+"'"+this.commision.getText()+"',"
				+"'"+this.deduction.getText()+"',"
				+"'"+this.total.getText()+"',"
				+"'"+this.description.getText()+"');";
		if(this.database.executeAction(sql))
		{
			//Refreshing the table;
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
