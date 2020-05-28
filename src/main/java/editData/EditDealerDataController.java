package editData;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import DataEntry.MainController;
import data.BankDetails;
import data.Dealer;
import database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditDealerDataController implements Initializable{

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXTextField companyName;

    @FXML
    private JFXTextField IFCCode;

    @FXML
    private JFXTextField bankName;

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField accountNumber;

    @FXML
    private JFXTextField bankAddress;

    @FXML
    private JFXTextArea policy;
    
    @FXML
    private Button savebutton;
    
    private Dealer dealer;

	private DatabaseHandler database;
    
    @Override
	public void initialize(URL url, ResourceBundle rb) 
	{
    	this.database = DatabaseHandler.getHandler();
    	this.dealer = MainController.getSelectedDealer();
    	this.companyName.setText(dealer.companyname);
    	this.phone.setText(dealer.phone);
    	this.bankName.setText(dealer.bank.bankName);
    	this.accountNumber.setText(dealer.bank.accountNumber);
    	this.IFCCode.setText(dealer.bank.IFCCode);
    	this.policy.setText(dealer.policy);
    	this.bankAddress.setText(dealer.bank.address);
    	
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
		
    	BankDetails bank = new BankDetails(bankName.getText(),accountNumber.getText(),IFCCode.getText(),bankAddress.getText());
		boolean result = database.updateDealer(new Dealer(companyName.getText(),phone.getText(),policy.getText(),bank),dealer.bank.accountNumber);
		if(!result)
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("The Dealer data is not updated please call support");
			alert.showAndWait();
		}
		Stage stage = (Stage) this.pane.getScene().getWindow();
		stage.close();
    	
    }

    @FXML
    void edit(ActionEvent event) {
    	this.companyName.setEditable(true);
    	this.phone.setEditable(true);
    	this.bankAddress.setEditable(true);
    	this.bankName.setEditable(true);
    	this.IFCCode.setEditable(true);
    	this.policy.setEditable(true);
    	this.accountNumber.setEditable(true);
    	this.savebutton.setDisable(false);
    	this.savebutton.setOpacity(1);
    }

}
