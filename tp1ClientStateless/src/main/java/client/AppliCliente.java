package client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;

import rest.Automate;
import rest.Session;
import rest.jaxb.FournisseurTraduction;

import java.util.Arrays;

public class AppliCliente {

	public static Client clientJAXRS() {
		ClientConfig config = new ClientConfig();
		config.register(LoggingFeature.class);
		config.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, "INFO");
		config.register(FournisseurTraduction.class);
		config.register(JacksonFeature.class);
		return ClientBuilder.newClient(config);
	}

	public static void main(String[] args) {

		String adresse = "http://localhost:8083/tp1-server-stateless/automate/";

		System.out.println("*************");

		WebTarget cible = clientJAXRS().target(adresse);

		Automate automate = WebResourceFactory.newResource(Automate.class, cible);

		test(automate);

		System.out.println("*************");

	}

	private static void test(Automate automate) {
		Character[] mot = { 'a', 'b', 'a', 'b', 'a', 'b' };

		Session session = automate.initier();

		automate.accepter('a', session);
		automate.accepter('b', session);
		automate.accepter('a', session);
		automate.accepter('b', session);
		automate.accepter('a', session);
//		boolean isValid = Arrays.stream(mot).allMatch(letter -> automate.accepter(letter, session).isValide());

//		String log = isValid ? "Mot Valide" : "Mot Pas valide";

//		System.out.println(log);
	}

//	méthode Http : GET
//	Adresse : http://localhost:8083/tp1-server-stateless/automate/etat/suivant/a?id=UN-3
//	En-tête : Accept: application/json
//	Message (payload) : Pas besoin de payload

// ET La réponse
//	En-tête :
//      Content-Length: 47
//	    Content-Type: application/json
//	    Date: Sat, 08 Apr 2017 07:56:45 GMT
//	Réponse :
//		{
//			"id": {
//			"numero": 3,
//					"etat": "DEUX"
//		},
//			"valide": true
//		}

// Pour avoir du JSON dans la réponse:
//Dans le header de la requete: Accept: application/json

// Pour envoyer du JSON
// Content-Type: application/json
}
