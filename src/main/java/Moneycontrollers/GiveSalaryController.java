package Moneycontrollers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;

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

	@Override
	public void initialize(URL url, ResourceBundle rb) 
	{
		this.date.setValue(LocalDate.now());

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

	}
	
	public int calculate() {
		int total = Integer.parseInt(this.commision.getText()) + Integer.parseInt(this.salary.getText())-Integer.parseInt(this.deduction.getText());
		return total;
	}

}
