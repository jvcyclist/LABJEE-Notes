package pl.karas.spring;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

public class Login extends FormLayout {

	VerticalLayout verticalLayout = new VerticalLayout();
	
	TextField username = new TextField("Username");
	PasswordField password = new PasswordField("Password");
	
	Button login = new Button("Login");
	
	String loginAuth = "Adam";
	String passAuth = "1234";
	
	
	public Login() {
		
		
		
		verticalLayout.add(username, password, login);
		
		add(verticalLayout);
	
	}

	public boolean isValid() {
		if(username.getValue().equals(loginAuth) && password.getValue().equals("1234") ) {removeAll(); return true;}
		else {
			return false;
		}
		
	}
	
}
