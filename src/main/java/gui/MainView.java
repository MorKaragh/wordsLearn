package gui;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Theme;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.screen.Screen;

import data.LadderFileDAO;

public class MainView {
	
	static boolean working = true;
	static String mode = "MENU";
	
	public static void main(String... args) throws InterruptedException{
		run();	
	}
	
	
	private static void run(){
		Screen screen = TerminalFacade.createScreen();
		GUIScreen gui = new GUIScreen(screen);
	    gui.getScreen().startScreen();	    
	    Theme theme = new MyTheme();	    
	    gui.setTheme(theme);	    
	    int cnt = -1;
	    LadderFileDAO dao = new LadderFileDAO();	    
	    
	    while(working){	    	
		    if("LOOK".equals(mode)){
			    if(++cnt > dao.getWords().size()-1){
			    	cnt = 0;
			    }
		    	WordWindow myWindow = new WordWindow(dao.getWords().get(cnt));
			    gui.showWindow(myWindow, GUIScreen.Position.NEW_CORNER_WINDOW);	
		    } else if("BACK".equals(mode)){
			    if(--cnt < 0){
			    	cnt = dao.getWords().size()-1;
			    }
		    	WordWindow myWindow = new WordWindow(dao.getWords().get(cnt));
			    myWindow.setPrevButtonFocused();
			    gui.showWindow(myWindow, GUIScreen.Position.NEW_CORNER_WINDOW);			    	
		    } else if("TEST".equals(mode)){
			    if(++cnt > dao.getWords().size()-1){
			    	cnt = 0;
			    }
		    	TestWordWindow myWindow = new TestWordWindow(dao.getWords().get(cnt));
			    gui.showWindow(myWindow, GUIScreen.Position.NEW_CORNER_WINDOW);			    	
		    } else if("MENU".equals(mode)){
			    Window selectWindow = new ModeSelectionWindow();
			    gui.showWindow(selectWindow, GUIScreen.Position.CENTER);	
		    }
		    System.out.println(cnt);
	    }
	    
	    gui.getScreen().stopScreen();
	    System.out.println("* * * SCREEN STOPPED * * *");
		System.exit(0);
	}
	
}
