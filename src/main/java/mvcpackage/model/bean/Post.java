package mvcpackage.model.bean;

public class Post {
	protected int Pid;
	protected String Category;
	protected String Title;
	protected String Preview;
	protected String ImageUrl;
	protected String DateIssue;
	protected String Content;

	public Post() {

	}

	public Post(int Pid, String Title, String ImageUrl, String Category, String DateIssue, String Preview) {
		this.Pid = Pid;
		this.Category = Category;
		this.Title = Title;
		this.Preview = Preview;
		this.ImageUrl = ImageUrl;
		this.DateIssue = DateIssue;
	}

	public Post(String Title, String ImageUrl, String Category, String DateIssue, String Preview, String Content) {
		this.Category = Category;
		this.Title = Title;
		this.Preview = Preview;
		this.ImageUrl = ImageUrl;
		this.DateIssue = DateIssue;
		this.Content = Content;
	}

	public Post(int Pid, String Title, String ImageUrl, String Category, String DateIssue, String Preview,
			String Content) {
		this.Pid = Pid;
		this.Category = Category;
		this.Title = Title;
		this.Preview = Preview;
		this.ImageUrl = ImageUrl;
		this.DateIssue = DateIssue;
		this.Content = Content;
	}

	public int getPid() {
		return Pid;
	}

	public void setPid(int pid) {
		Pid = pid;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getPreview() {
		return Preview;
	}

	public void setPreview(String preview) {
		Preview = preview;
	}

	public String getImageUrl() {
		return ImageUrl;
	}

	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}

	public String getDateIssue() {
		return DateIssue;
	}

	public void setDateIssue(String dateIssue) {
		DateIssue = dateIssue;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}