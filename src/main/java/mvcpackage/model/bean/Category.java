package mvcpackage.model.bean;

public class Category {
	protected int CategoryId;
	protected String CategoryName;

	public Category() {

	}

	public Category(String CategoryName) {
		this.CategoryName = CategoryName;

	}

	public int getCategoryId() {
		return CategoryId;
	}

	public void setCategoryId(int categoryId) {
		CategoryId = categoryId;
	}

	public String getCategoryName() {
		return CategoryName;
	}

	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}

}
