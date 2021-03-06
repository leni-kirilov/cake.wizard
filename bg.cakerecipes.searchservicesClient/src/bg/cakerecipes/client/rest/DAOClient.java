package bg.cakerecipes.client.rest;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import bg.cakerecipes.client.ServicesConstants;
import bg.cakerecipes.daoservices.rest.model.Cake;
import bg.cakerecipes.daoservices.rest.service.CakeServiceConstants;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.api.uri.UriBuilderImpl;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class DAOClient extends AbstractClient {
	
	private final String REST_PATH = "cakes";

	public boolean writeCake(Cake cake) {
		
		final Form form = new Form();
		
		form.add(CakeServiceConstants.PROPERTY_NAME, cake.getName());
		form.add(CakeServiceConstants.PROPERTY_INGREDIENTS, cake.getIngredients());
		form.add(CakeServiceConstants.PROPERTY_RECIPE, cake.getRecipe());
		form.add(CakeServiceConstants.PROPERTY_CATEGORIES, cake.getCategories());
		form.add(CakeServiceConstants.PROPERTY_IMAGEURL, cake.getImageUrl());

		final ClientResponse response = getWebService(REST_PATH)
				.type(MediaType.APPLICATION_FORM_URLENCODED)
				.post(ClientResponse.class, form);
		
		if (response.getStatus() != 204) {
			System.out.println("Something went wrong!");
			return false;
		}
		return true;
	}

	public List<Cake> readCakes() {
		final List<Cake> cakes = Arrays.asList(unparseXmlResponse());
		return cakes;
	}


	public List<Cake> readCakes(String... ids) {
		final MultivaluedMap<String, String> queryParameters = getIdsAsQueryParameters(ids);
		return Arrays.asList(unparseXmlResponse(queryParameters));
	}

	private MultivaluedMap<String, String> getIdsAsQueryParameters(String[] ids) {
		final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
		for (String id : ids) {
			queryParameters.add(CakeServiceConstants.PROPERTY_ID, id);
		}
		return queryParameters;
	}

	@Override
	public URI getServiceURI() {
		return UriBuilderImpl.fromUri(ServicesConstants.DAO_SERVICE_URL).build();
	}

	private Cake[] unparseXmlResponse() {
		return getBuilder(REST_PATH).get(Cake[].class);
	}
	
	private Cake[] unparseXmlResponse(MultivaluedMap<String, String> queryParameters) {
		return getBuilder(queryParameters, REST_PATH).get(Cake[].class);
	}

}
