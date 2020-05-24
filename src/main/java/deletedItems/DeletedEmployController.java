package deletedItems;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    
    

	@Override
	public void initialize(URL url, ResourceBundle rb) 
	{
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
