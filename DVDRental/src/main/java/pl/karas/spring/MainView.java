package pl.karas.spring;

import java.util.List;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route
@StyleSheet("frontend://styles/styles.css") 
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {

    public MainView() {
    	
    	grid.setColumns("title", "description");
		 grid.setSizeFull();
		 updateList();
    	
       
    	setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    	
    	setSizeFull();
    	
    	H1 title = new H1("DVD Rental");
    	
    	addClassName("main-view");
    	
    	
    	Login login = new Login();
    	
    	getElement().getThemeList().add("dark"); 
    	
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		
		horizontalLayout.add(login);
		
		login.login.addClickListener(click -> {
			
			if(login.username.getValue().equals("Adam") && login.password.getValue().equals("1234")) {
				login.removeAll();
				add(grid);
			}
			
		});
		
    	add(title, horizontalLayout);
    	
    }
    
    private MovieService service = MovieService.getInstance();
    private Grid<Movie> grid = new Grid<>(Movie.class);
    private TextField filterText = new TextField();
	 

	 public void updateList() {
	        List<Movie> movies = service.findAll(filterText.getValue());
	        grid.setItems(movies);
	    }

}
