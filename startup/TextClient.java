package startup;

import java.util.ArrayList;
import java.util.List;

import domain.CodifierFactory;
import domain.ICodifierStrategy;
import domain.Organization;
import domain.User;
import domain.interfaces.IAddDocForAgentHandler;
import domain.interfaces.ICreateAgentHandler;
import domain.interfaces.ICreateMissionHandler;
import domain.interfaces.ILoginHandler;
import domain.interfaces.IOrganization;
import domain.interfaces.IReadDocAgentHandler;
import domain.interfaces.ISearchAgentInfoHandler;
import domain.interfaces.ISearchMissionInfoHandler;
import domain.interfaces.ISetUnavailabilityHandler;
/**
 * Classe que testa a execucao dos vários casos de uso da SpyLEI
 * para os alunos poderem comparar os seus resultados
 * @author inunes
 *
 */
public class TextClient {
	static IOrganization objIni;
	static User authentic;

	public static void main(String[] args) {

		startup();
		runLoginUseCase(objIni);
		runPrintUsers(objIni);

		runCreateAgentUseCase(objIni);
		runPrintUsers(objIni);
		
		runCreateMissionUseCase(objIni);
		runPrintUsers(objIni);
		
		runSearchAgentUseCase(objIni);
		runPrintUsers(objIni);
		
		runSearchMissionUseCase(objIni);
		runPrintUsers(objIni);
		
		runChangeAgentAvailability(objIni);
		runPrintUsers(objIni);
		
		runAddDocForAgent(objIni);
		runPrintUsers(objIni);

		runReadDocAgent(objIni);
	}

	/////////////////////////////////////////
	// STARTUP : CRIACAO DO OBJECTO INICIAL
	////////////////////////////////////////
	private static void startup() {
		objIni = new Organization();
	}
	

	/////////////////////////////////////////
	// LOGIN DE UM USER
	////////////////////////////////////////
	private static void runLoginUseCase(IOrganization org) {
		ILoginHandler hand = org.getLoginHandler();
		hand.login("Mary", "123");
	}
	

	/////////////////////////////////////////
	// CRIACAO DE DOIS AGENTES
	////////////////////////////////////////
	private static void runCreateAgentUseCase(IOrganization org) {

		ICreateAgentHandler hand = org.getCreateAgentHandler();
		Iterable<String> verifiers = hand.getPwdStrengthVerifiers();
        Iterable<String> users = hand.getUsers();

        hand.initiateRegister("Darth Vader");
		System.out.println("The following password strength verifiers are "
				+ "available");
		for(String s : verifiers)
			System.out.println(s);
		boolean b = hand.defineAccessKey("999", "Exigent");
		while (!b){
			System.out.println("Very weak password. Repeat");
			b = hand.defineAccessKey("999", "EasyGoing");
		}

		Iterable<String> languages = hand.getLanguages();
		System.out.println("The following languages are admissible");
		for(String s : languages)
			System.out.println(s);
		hand.selectLanguage("English");
		hand.selectLanguage("Portuguese");

		Iterable<String> codifiers = hand.getDocumentCodifiers();
		System.out.println("The following codifiers are available");
		for(String s : codifiers)
			System.out.println(s);
		hand.defineKeyAndCoding("666", "Round13");

		System.out.println("The following users exist:");
		for(String s : users)
			System.out.println(s);
		hand.selectUser("John Snow");
		
		hand.confirmAgentCreation();

		//Create another agent
		hand = org.getCreateAgentHandler();
		hand.initiateRegister("Houdini");

		hand.defineAccessKey("111", "EasyGoing");

		hand.selectLanguage("French");
		hand.selectLanguage("German");

		hand.defineKeyAndCoding("111", "Identity");

		hand.selectUser("Peter");
		hand.selectUser("John Snow");

		hand.confirmAgentCreation();
	}
	

	/////////////////////////////////////////
	// CRIACAO DE UMA MISSAO
	////////////////////////////////////////
	private static void runCreateMissionUseCase(IOrganization org) {

		ICreateMissionHandler hand = org.getCreateMissionHandler();
        Iterable<String> verifiers = hand.getPwdStrengthVerifiers();
        Iterable<String> users = hand.getUsers();

        boolean b = hand.initiateRegister("Roling Stones");

		b = hand.defineAccessKey("rolSto", "Exigent");
		while (!b){
			System.out.println("Very weak password. Repeat");
			b = hand.defineAccessKey("rolSto", "EasyGoing");
		}

		b = hand.selectResponsible("Bonnie", "");
		while (!b){
			System.out.println("The agent does not exist or you do not have"
					+ " access to its info. Repeat");
			b = hand.selectResponsible("Houdini", "111");
		}
		hand.selectAgent("Darth Vader", "999");
		hand.defineKeyword("Africa");
		hand.defineKeyword("Trafico");

        hand.selectUser("John Snow");

        hand.confirmMissionCreation();
	}
	

	/////////////////////////////////////////
	// CONSULTA DA INFORMACAO DE UM AGENTE
	////////////////////////////////////////
   private static void runSearchAgentUseCase(IOrganization org) {
		ISearchAgentInfoHandler hand = org.getSearchAgentInfoHandler();
		boolean accessible = hand.initiateSearch("Darth Vader", "999");
		if (accessible) {
			System.out.println("Codifier: " + hand.getCodifierName());
			System.out.print("Spoken languages: ");
			Iterable<String> langs = hand.getSpokenLanguages();
			for (String l : langs)
				System.out.print(l + "    ");
			System.out.println();
		}
		else
			System.out.println("The agent does not exist or you do not have"
					+ " access to its info.");			
	}
	

	/////////////////////////////////////////
	// CONSULTA DA INFORMACAO DE UMA MISSAO
	////////////////////////////////////////
   private static void runSearchMissionUseCase(IOrganization org) {
		ISearchMissionInfoHandler hand = org.getSearchMissionInfoHandler();
		boolean accessible = hand.initiateSearch("Roling Stones", "rolSto");
		if (accessible) {
			System.out.println("Responsible agent: " + hand.getResponsibleAgent());
			System.out.print("Participating agents: ");
			Iterable<String> procura = hand.getParticipatingAgents();
			for (String p : procura)
				System.out.print(p + "    ");
			System.out.println();
			System.out.print("Mission characteristics: ");
			procura = hand.getCharacteristics();
			for (String p : procura)
				System.out.print(p + "    ");
			System.out.println();
		}
		else
			System.out.println("The mission does not exist or you do not have"
					+ " access to its info.");			
	}
	

	/////////////////////////////////////////
	// TORNAR UM AGENTE UNAVAILABLE
	////////////////////////////////////////
   private static void runChangeAgentAvailability(IOrganization org) {
		ISetUnavailabilityHandler hand = org.getSetUnavailabilityHandler();
		String avail = hand.setUnavailable("Darth Vader", "999");
		System.out.println("The agent is presently " + avail);
		System.out.print("Kinds of unavailability: ");
		Iterable<String> kinds = hand.kindsOfUnavailability();
		for (String l : kinds)
			System.out.print(l + "    ");
		System.out.println();
		hand.selectUnavailability("ARRESTED");
	}
   

	/////////////////////////////////////////
	// ADICIONAR UM DOCUMENTO A UM AGENTE
	////////////////////////////////////////
   private static void runAddDocForAgent(IOrganization org) {
		IAddDocForAgentHandler hand = org.getAddDocForAgentHandler();
		boolean accessible = hand.giveAgentName("Darth Vader", "999");
		if (accessible) {
			String uniqueId = hand.giveDocumentName("meeting.txt");
			System.out.println("The reference of the new document is: " + 
			                                   uniqueId);
		}
		else
			System.out.println("The agent does not exist or you do not have"
					+ " access to its info.");			
   }
   

	/////////////////////////////////////////
	// LER UM DOCUMENTO DE UM AGENTE
	////////////////////////////////////////
   private static void runReadDocAgent(IOrganization org) {
		IReadDocAgentHandler hand = org.getReadDocAgentHandler();
		boolean accessible = hand.readDoc("Darth Vader", "999");
		if (accessible) {
			System.out.print("References of available docs: ");
			Iterable<String> strs = hand.docsOfCurrentAgent();
			for (String d : strs)
				System.out.print(d + "    ");
			System.out.println();
			strs = hand.selectDoc("ToAg0");
			System.out.println("The selected document, already decodified:");
			for (String d : strs)
				System.out.println(d);			
		}
		else
			System.out.println("The agent does not exist or you do not have"
					+ " access to its info.");			
   }

	/////////////////////////////////////////
	// IMPRIMIR USERS
	////////////////////////////////////////
	private static void runPrintUsers(IOrganization objIni){
		System.out.println("=============================================");
		System.out.println("=============    USERS INFO    ==============");
		System.out.println("=============================================");
		System.out.println(((Organization)objIni).getUserInfo("Peter"));
		System.out.println("---------------------------------------------");
		System.out.println(((Organization)objIni).getUserInfo("Mary"));
		System.out.println("---------------------------------------------");
		System.out.println(((Organization)objIni).getUserInfo("John Snow"));
		System.out.println("=============================================");
	}	

	/////////////////////////////////////////
	// PARA TESTAR RESULTADOS DE CODIFICADORES
	////////////////////////////////////////
	private static void testCodifiers() {
		CodifierFactory fact = CodifierFactory.getInstance();
		List<String> text = new ArrayList<String>();
		text.add("On the corner of the 1st with the 3rd");
		text.add("at four of the afternoon");
		text.add("wearing a red coat and a black hat");
		ICodifierStrategy coder = fact.getCodifier("domain.Round13CodifierStrategy");
		codeAndDecode(text,coder);
	}
	
	private static void codeAndDecode(Iterable<String> text,
			                                    ICodifierStrategy coder){
		Iterable<String> sCod = coder.code("a", text);
        for(String s : sCod)
			System.out.println(s);
		Iterable<String> sDecod = coder.decode("a", sCod);
        for(String s : sDecod)
			System.out.println(s);
        
	}
	
}
