package startup;

import javafx.application.Application;
import view.MainView;
/**
 * Grupo: 045
 * Luis Costa 47082
 * Manuel Caldeira 46371
 * Pedro Caldeira 44219
 * 
 * 
 * Classe que implementa o caso de uso startup usando uma
 * interface com o utilizador grafica
 * @author fmartins & mguimaraes
 */
public class StartUp {

	public static void main(String[] args) {
		// Quando jah quiserem testar a aplicacao sobre o vosso
		// objeto inicial usam a seguinte instrucao em vez da outra
		 MainView.setApp(new domain.Organization());
		//MainView.setApp(new domain.stubs.Organization());
		Application.launch(MainView.class);
	}

}
