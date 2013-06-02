/**
 * 
 */
package bg.cakerecipes.searchservices.service;

import java.util.List;

/**
 * The model of the cake for a search
 * 
 * @author Leni Kirilov
 * 
 */
public class SearchCake {

	private Long id;
	private String name;
	private String recipe;
	private List<String> categories;
	
	public SearchCake() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRecipe() {
		return recipe;
	}

	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
}
