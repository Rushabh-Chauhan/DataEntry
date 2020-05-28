package deletedItems;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import data.Employ;
import database.DatabaseHandler;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class DeletedEmployController implements Initializable{

    @FXML
    private JFXTreeTableView<Employ> employTreeView;

    @FXML
    private AnchorPane pane;
    
    private  ObservableList<Employ> employOList;
    
    private DatabaseHandler database;
    
    

	@Override
	public void initialize(URL url, ResourceBundle rb) 
	{
		this.database = DatabaseHandler.getHandler();
    	employOList = FXCollections.observableArrayList();
    	Employ.EmployTreeColomns(employTreeView, employOList);
    	
    	JFXTreeTableColumn<Employ, String> adhar_Number = new JFXTreeTableColumn<>("Adhar Number");
    	adhar_Number.setPrefWidth(460);
    	adhar_Number.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Employ, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Employ, String> param) {
				return param.getValue().getValue().getid();
			}
		});
    	
    	TreeItem<Employ> root = new RecursiveTreeItem<Employ>(employOList, RecursiveTreeObject::getChildren);
		employTreeView.getColumns().add(adhar_Number);
		employTreeView.setRoot(root);
    	loadEmployTable();
	}
	
	  @FXML
	    void restore(ActionEvent event) {
		  TreeItem<Employ> selected = employTreeView.getSelectionModel().getSelectedItem();
			if(selected == null)
			{
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("Please Select The Employ To restore");
				alert.showAndWait();
				return;
			}

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setHeaderText("Delete Employ");
			alert.setContentText("Are you sure do you want to restore "+selected.getValue().name);
			Optional<ButtonType> ans = alert.showAndWait();
			if(ans.get() == ButtonType.OK)
			{
				boolean result = database.deleteEmploy(selected.getValue(),"Deletedemploytable");
				if(result)
				{
					// Refreshes the list after the deletion; 
					this.employOList.clear();
					this.loadEmployTable();

				}
				else// error when not restoring
				{
					Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
					alert1.setHeaderText(null);
					alert1.setContentText("Deletion failed. Try again");
					alert1.showAndWait();
					return;
				}
			}
			return;

	    }
	
	private void loadEmployTable()
	{
		String sql = "SELECT * FROM DELETEDEMPLOYTABLE";
		DatabaseHandler database = DatabaseHandler.getHandler();
		ResultSet rs = database.executeQueri(sql);
		try {
			while(rs.next())
			{
				String id = rs.getString("ID");
				String name = rs.getString("Name");
				String address = rs.getString("Address");
				String phone = rs.getString("Phone");
				String salary = rs.getString("Salary");
				String des = rs.getString("description");
				employOList.add(new Employ(id,name,address,phone,salary,des));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
