package data;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

public class Dealer extends RecursiveTreeObject<Dealer>{

	public String companyname;
	public String phone;
	public BankDetails bank;
	public String policy;

	public Dealer(String companyname, String phone, String policy, BankDetails bank) {

		this.companyname = companyname;
		this.phone = phone;
		this.bank = bank;
		this.policy = policy;
	}
	
	public StringProperty getCompanyName() {
		return new SimpleStringProperty(this.companyname);
	}
	public StringProperty getPhone() {
		return new SimpleStringProperty(this.phone);
	}
	
	public StringProperty getAccountNumber() {
		return new SimpleStringProperty(this.bank.accountNumber);
	}

	@SuppressWarnings("unchecked")
	public static void dealerTreeColomns(JFXTreeTableView<Dealer> dealerTreeView, ObservableList<Dealer> dealerOList)
	{
		JFXTreeTableColumn<Dealer, String> name = new JFXTreeTableColumn<>("Company Name");
		name.setPrefWidth(460);
		name.setContextMenu(null);
		name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Dealer, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Dealer, String> param) {
				return param.getValue().getValue().getCompanyName();
			}
		});
		
		JFXTreeTableColumn<Dealer, String> phone = new JFXTreeTableColumn<>("Phone Number");
		phone.setPrefWidth(460);
		phone.setContextMenu(null);
		phone.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Dealer, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Dealer, String> param) {
				return param.getValue().getValue().getAccountNumber();
			}
			
		});
		
		JFXTreeTableColumn<Dealer, String> account = new JFXTreeTableColumn<>("Account Number");
		account.setPrefWidth(460);
		account.setContextMenu(null);
		account.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Dealer, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Dealer, String> param) {
				return param.getValue().getValue().getPhone();
			}
	});
		


		TreeItem<Dealer> root = new RecursiveTreeItem<Dealer>(dealerOList, RecursiveTreeObject::getChildren);
		root.setExpanded(true);
		dealerTreeView.getColumns().setAll(name,phone,account);
		dealerTreeView.setRoot(root);
		dealerTreeView.setShowRoot(false);
		dealerTreeView.setEditable(true);


	}

}
