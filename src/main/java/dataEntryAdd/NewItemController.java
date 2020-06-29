package dataEntryAdd;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
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

public class NewItemController implements Initializable{

	@FXML
	private JFXCheckBox ss;

	@FXML
	private JFXCheckBox s4xl;

	@FXML
	private JFXComboBox<String> itemType;

	@FXML
	private JFXCheckBox s5xl;

	@FXML
	private JFXCheckBox sxl;

	@FXML
	private JFXCheckBox sxxxl;

	@FXML
	private JFXCheckBox s40;

	@FXML
	private JFXCheckBox sxxl;

	@FXML
	private JFXCheckBox s42;

	@FXML
	private JFXCheckBox s30;

	@FXML
	private JFXTextField itemName;

	@FXML
	private JFXCheckBox s44;

	@FXML
	private JFXCheckBox s32;

	@FXML
	private JFXCheckBox s34;

	@FXML
	private JFXCheckBox s36;

	@FXML
	private JFXComboBox<String> dealer;

	@FXML
	private JFXCheckBox sl;

	@FXML
	private JFXCheckBox s38;

	@FXML
	private JFXCheckBox sm;

	@FXML
	private AnchorPane pane;

	private DatabaseHandler database;

	//Map<JFXCheckBox, Integer> map;
	ArrayList<JFXCheckBox> sizes;

	@Override
	public void initialize(URL url, ResourceBundle rb) 
	{
		database = DatabaseHandler.getHandler();
		dealer.getItems().addAll(this.getDealer());
		//itemType.getItems().addAll(this.getItems());

		sizes =  new ArrayList<JFXCheckBox>();
		sizes.add(ss);
		sizes.add(sm);
		sizes.add(sl);
		sizes.add(sxl);
		sizes.add(sxxl);
		sizes.add(sxxxl);
		sizes.add(s4xl);
		sizes.add(s5xl);
		sizes.add(s30);
		sizes.add(s32);
		sizes.add(s34);
		sizes.add(s36);
		sizes.add(s38);
		sizes.add(s40);
		sizes.add(s42);
		sizes.add(s44);
	}



	@FXML
	void save(ActionEvent event) throws SQLException  {

		String sql = "CREATE TABLE `"+ itemName.getText() +"` (";

		for(JFXCheckBox data : sizes)
		{
			if(data.isSelected())
			{
				sql = sql + " `"+ data.getText() +"` varchar(50),";
			}
		}

		sql = sql + " `Dealer_Name` varchar(50));";

		DatabaseHandler.newTables(sql);


		Stage stage = (Stage) this.pane.getScene().getWindow();
		stage.close();


	}

	private Collection<String> getDealer()
	{
		Collection<String> c =  new ArrayList<String>();

		String sql = "SELECT * FROM dealerTable";
		ResultSet rs = database.executeQueri(sql);
		try {
			while(rs.next())
			{
				String companyName = rs.getString("Company_Name");
				c.add(companyName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return c;
	}

	//	private Collection<String> getItems()
	//	{
	//		Collection<String> c =  new ArrayList<String>();
	//
	//		String sql = "SELECT * FROM itemstable";
	//		ResultSet rs = database.executeQueri(sql);
	//		try {
	//			while(rs.next())
	//			{
	//				String itemName = rs.getString("Item_Name");
	//				if(c.isEmpty() || !c.contains(itemName))
	//				{
	//					c.add(itemName);
	//				}
	//			}
	//		} catch (SQLException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//
	//		return c;
	//	}


}
