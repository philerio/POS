package application;
	
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	public static Cart cart;
	public static ItemList itemList;
	private Stage primaryStage;
	public static void main(String[] args) throws ClassNotFoundException, IOException 
	{
		File file=new File("Items");
		itemList=new ItemList(file);
		itemList.populate();
		cart= new Cart(file, itemList);
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage=primaryStage;
		editItemWindow();
	}
	public void editItemWindow() throws IOException {
		FXMLLoader loader= new FXMLLoader(Main.class.getResource("POS.fxml"));
		Pane pane= loader.load();
		editItemController editItemConstroller= loader.getController();
		editItemConstroller.setMain(this);
		Scene scene= new Scene(pane);
		this.primaryStage.setScene(scene);
		primaryStage.show();
	}
	

}
