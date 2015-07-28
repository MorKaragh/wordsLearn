package gui;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Component.Alignment;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.terminal.TerminalSize;

public class ModeSelectionWindow  extends Window {

	private Window self;
	
	public ModeSelectionWindow() {
		super("  MODE  ");
		self = this;
		buildLayout();
	}

	
	private void buildLayout(){
		addComponent(new Label(""));
		addComponent(buildLearnButton());
		addComponent(buildTestButton());
		addComponent(buildExitButton());		
	}
	
	
	private Button buildLearnButton(){
		Action action = new Action(){
			public void doAction() {
				MainView.mode = "LOOK";
				self.close();
			}
		};
		Button btn = new Button("Look",action);
		btn.setAlignment(Alignment.CENTER);
		return btn;
	}
	
	private Button buildTestButton(){
		Action action = new Action(){
			public void doAction() {
				MainView.mode = "TEST";
				self.close();
			}
		};
		Button btn = new Button("Test",action);
		btn.setPreferredSize(new TerminalSize(8,2));
		btn.setAlignment(Alignment.CENTER);
		return btn;
	}
	
	private Button buildExitButton(){
		Action action = new Action(){
			public void doAction() {
				MainView.working = false;
				self.close();
			}
		};
		Button btn = new Button("EXIT",action);
		btn.setAlignment(Alignment.CENTER);
		return btn;
	}
	
	
}
