package dataEntryAdd;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import data.BankDetails;
import data.Dealer;
import database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NewDealerDataController implements Initializable{

	@FXML
	private JFXTextField phone;

	@FXML
	private JFXTextField companyName;

	@FXML
	private JFXTextField IFCCode;

	@FXML
	private JFXTextField bankAddress;

	@FXML
	private JFXTextField bankName;

	@FXML
	private AnchorPane pane;

	@FXML
	private JFXTextField accountNumber;

	@FXML
	private JFXTextArea policy;

	private DatabaseHandler database;
	
	public static Dealer dealer;



	@Override
	public void initialize(URL url, ResourceBundle rb) 
	{
		database = DatabaseHandler.getHandler();
	}


	@FXML
	void save(ActionEvent event) {
		if(companyName.getText().isEmpty()||phone.getText().isEmpty()|| bankAddress.getText().isEmpty()|| bankName.getText().isEmpty()||accountNumber.getText().isEmpty()|| IFCCode.getText().isEmpty())
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Please add all the fields");
			alert.showAndWait();
			return;
		}

		// inserting the data into the database.
		String sql = "INSERT INTO dealertable VALUES("
				+"'"+companyName.getText()+"',"
				+"'"+phone.getText()+"',"
				+"'"+bankName.getText()+"',"
				+"'"+accountNumber.getText()+"',"
				+"'"+IFCCode.getText()+"',"
				+"'"+bankAddress.getText()+"',"
				+"'"+policy.getText()+"');";
		if(this.database.executeAction(sql))
		{	
//			BankDetails bank = new BankDetails(bankName.getText(),accountNumber.getText(),IFCCode.getText(),bankAddress.getText());
//			dealer = new Dealer(companyName.getText(),phone.getText(),policy.getText(),bank);
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

}

