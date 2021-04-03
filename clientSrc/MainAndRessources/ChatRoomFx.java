package MainAndRessources;

import Client.massageCodes;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ChatRoomFx {
	
	public ChatRoomFx() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Tab load(String text) {
		Tab ret = null;
		ret = new Tab(text);

		//Init ScrollPane
		ScrollPane s = new ScrollPane();
		s.setPrefHeight(500);
		s.setPrefWidth(500);
		s.setStyle("-fx-background: #3c3c3c; -fx-border-color: #3c3c3c;");
		
		//Init Gridpane
		GridPane g = new GridPane();
		g.setPrefWidth(400);
		g.setPrefHeight(0);

		g.setHgap(25);
		g.setVgap(25);
		g.setTranslateX(50);
		g.setTranslateY(10);

		//Collums
		ColumnConstraints c1 = new ColumnConstraints();
		ColumnConstraints c2 = new ColumnConstraints();
		c1.setPrefWidth(200);
		c2.setPrefWidth(200);
		c1.setMinWidth(50);
		c2.setMinWidth(50);

		//Combind
		g.getColumnConstraints().addAll(c1,c2);
		s.setContent(g);
		ret.setContent(s);

		System.out.println(""+g.getColumnConstraints().size());

		return ret;
	}
}
