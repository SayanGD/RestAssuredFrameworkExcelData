package utilities;

public enum APIEndPoints
{
	AddBook("Library/Addbook.php"),
	GetBook("Library/GetBook.php"),
	DeleteBook("Library/DeleteBook.php");

	private String resource;

	APIEndPoints(String resource)
	{
		this.resource=resource;
	}

	public String getResource()
	{
		return resource;
	}
}