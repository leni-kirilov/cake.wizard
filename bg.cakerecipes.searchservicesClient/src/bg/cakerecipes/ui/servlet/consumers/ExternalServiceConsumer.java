package bg.cakerecipes.ui.servlet.consumers;

import java.io.PrintStream;
import java.util.List;

import bg.cakerecipes.client.rest.ExternalServicesClient;
import bg.cakerecipes.externalservices.model.ExternalCake;

public class ExternalServiceConsumer {
	
	private ExternalServicesClient client = new ExternalServicesClient();
	
	public void displayExternalServiceConsumed(PrintStream out) {
		out.println("<br><br>----------displayExternalServiceConsumed-----------<br><br><br>");

		List<ExternalCake> cakes = client.retrieveExternalCakes();

		for (ExternalCake cake : cakes) {
			out.printf("%s <br>", cake.toString());
		}
	}
	
	public List<ExternalCake> getExternalCakes(){
		return new ExternalServicesClient().retrieveExternalCakes();
	}

}
