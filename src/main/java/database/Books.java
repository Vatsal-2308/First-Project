package database;



import javax.validation.constraints.NotNull;
import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Scanner;


@Path("/books")
public class Books {

	@POST
	@Path("/Update-book")
	//This function is used to update the name of the book if there is some error in the name using its Old name.
	public Response books(@NotNull
						  @QueryParam("bookname") String bookname,
						  @NotNull
						  @QueryParam("correct") String correct) throws SQLException
	{

		Connection conn = DBUtil.getConnection();
		System.out.print("Enter the Name of the Book:");
		Scanner sc = new Scanner(System.in);
		String i = bookname;

		String myQuery1 = "select * from books";
		PreparedStatement pstmt_books ;
		pstmt_books = conn.prepareStatement(myQuery1);
		ResultSet rst_books = null;
		rst_books = pstmt_books.executeQuery();
		int y_books=0;
		while(rst_books.next()) {

			String com_books = rst_books.getString(2);
			if(i.equals(com_books))
			{
				y_books=1;
				break;
			}
		}
		if(y_books==0)
		{
			System.out.println("Book Already exists");
			return Response.status(500).entity("Book Does not exists").build();
		}


		System.out.print("Enter the Correct name of the book:");
		String n = correct;

		if(i.equals(n))
		{
			return Response.status(500).entity("Same names Entered.").build();
		}
		String myQuery = "UPDATE Books "
				+ "SET Title = ?"
				+ " WHERE Title = ?";
		PreparedStatement pstmt ;
		pstmt = conn.prepareStatement(myQuery);
		pstmt.setString(1, n);
		pstmt.setString(2, i);

		pstmt.executeUpdate();
		return Response.status(200).entity("Book Name Changed!!! ").build();

	}
	// This function is used to show all the books in the library.


	@GET
	@Path("/books_table")

	@Produces(MediaType.APPLICATION_JSON)
	public Response books() throws SQLException
	{
		Connection conn = DBUtil.getConnection();
		 String myQuery = "select * from books";
		 PreparedStatement pstmt ;
		 pstmt = conn.prepareStatement(myQuery);
		 ResultSet rst = null;
		 rst = pstmt.executeQuery();
		 System.out.println("BookTitle\n");
		HashMap<Integer,String> b = new HashMap<>();
		int k=1;
		while(rst.next()) {

	           b.put(k,rst.getString(2));
			   k++;
	           // System.out.print(rst.getString(2));
	            //System.out.println();
	         }
		 System.out.println();
		return Response.ok(b,MediaType.APPLICATION_JSON).build();

	}



	@GET
	@Path("/authorfrombook")
	@Produces(MediaType.APPLICATION_JSON)
	public Response authorfrombook(@NotNull
									@QueryParam("bookname") String bookname) throws SQLException
	{
		Connection conn = DBUtil.getConnection();
		//pojo p1 = new pojo();
		String myQuery = "select * from books";
		String i = bookname;
		PreparedStatement pstmt = conn.prepareStatement(myQuery);
		ResultSet rst = pstmt.executeQuery();
		int y1=0;
		while(rst.next())
		{
			String str = rst.getString(2);
			if(i.equals(str))
			{
				y1=rst.getInt(1);
				break;
			}
		}
		if(y1==0)
		{
			//System.out.println("No book found");
			String result = "No book found";
			return Response.ok(result,MediaType.APPLICATION_JSON).build();
			//System.exit(0);
		}
		myQuery = "select * from booksauthors";
		pstmt = conn.prepareStatement(myQuery);
		rst = pstmt.executeQuery();
		int y2=0;
		while(rst.next())
		{
			if(y1==rst.getInt(2))
			{
				y2=rst.getInt(1);
				break;
			}
		}

		myQuery = "select * from authors";
		pstmt = conn.prepareStatement(myQuery);
		rst = pstmt.executeQuery();
		HashMap<String,String> b = new HashMap<>();
		while(rst.next())
		{
			if(y2==rst.getInt(1))
			{
				System.out.println(rst.getString(2));
				b.put(i,rst.getString(2));
				break;
			}
		}
		return Response.ok(b,MediaType.APPLICATION_JSON).build();


	}



	@GET
	@Path("/bookauthors")

	@Produces(MediaType.APPLICATION_JSON)
	// This function is used to show books along with their author name.
	public Response bookauthors() throws SQLException
	{
		Connection conn = DBUtil.getConnection();
		 String myQuery = "SELECT a.Name AuthorName,b.Title BookTitle FROM BooksAuthors ba INNER JOIN Authors a ON a.id = ba.authorid INNER JOIN Books b ON b.id = ba.bookid";
		 PreparedStatement pstmt ;
		 pstmt = conn.prepareStatement(myQuery);
		 ResultSet rst = null;
		 rst = pstmt.executeQuery();
		 System.out.println("AuthorName\t\t\tBookTitle\t\n");
		HashMap<String,String> b = new HashMap<>();
		 while(rst.next()) {
			 b.put(rst.getString(2),rst.getString(1));
	            System.out.print(rst.getString(1));	            
	            System.out.print("\t\t\t"+rst.getString(2));	      
	            System.out.println();
	         }
		 System.out.println();
		return Response.ok(b,MediaType.APPLICATION_JSON).build();
	}




	@GET
	@Path("/book_exists")
	@Produces(MediaType.APPLICATION_JSON)
	public Response book_exists(@NotNull
								  @QueryParam("name") String name) throws SQLException
	{
		Connection conn = DBUtil.getConnection();
		String i = name;
		String myQuery = "select * from books";
		PreparedStatement pstmt = conn.prepareStatement(myQuery);
		ResultSet rst = pstmt.executeQuery();
		while(rst.next())
		{
			if(i.equals(rst.getString(2)))
			{
				String result = "book Exists";
				return Response.ok(result,MediaType.APPLICATION_JSON).build();
				//System.out.println("Author Exists");
				//System.exit(0);
			}
		}
		return Response.ok("No such Book Exists",MediaType.APPLICATION_JSON).build();

	}



	@DELETE
	@Path("/delete-book")

	@Produces(MediaType.APPLICATION_JSON)
	public Response delete_book(@com.sun.istack.NotNull
								@QueryParam("Name") String Name) throws SQLException
	{

		Connection conn = DBUtil.getConnection();

		String result ="Book deleted Successfully.";
		// Enter the Name of the book to delete

		String book_delete = Name;

		//Checking if the book exists in the library or not
		String query = "SELECT * FROM books";
		PreparedStatement check = conn.prepareStatement(query);
		ResultSet rst = check.executeQuery();
		int p=0;
		while(rst.next()) {
			if(book_delete.equals(rst.getString(2)))
			{
				p=rst.getInt(1);
				break;
			}
		}
		if(p==0) {
			System.out.println("The entered Book does not exists.");
			return Response.status(500).entity("Book does not exists").build();                                             //Exit as the entered book does not exists in the library.
		}


		//Checking if the author of the book has more books under his name.
		//Getting the authorid from bookid.

		String query1 = "SELECT * FROM booksauthors";
		PreparedStatement check1 = conn.prepareStatement(query1);
		ResultSet rst1 = check1.executeQuery();
		int p1=0;                                                           //Used to store the authorid
		while(rst1.next()) {
			if(p==rst1.getInt(2))
			{
				p1=rst1.getInt(1);
				break;
			}
		}


		//Counting the number of books written by author.
		int count=0;
		String query2 = "SELECT * FROM booksauthors";
		PreparedStatement check2 = conn.prepareStatement(query2);
		ResultSet rst2 = check2.executeQuery();
		while(rst2.next()) {
			if(p1-rst2.getInt(1)==0)
			{
				count++;
			}
		}


		//Deleteing entry from booksauthors table.
		String query3 = "DELETE from booksauthors where BookId = ?";
		PreparedStatement check3 = conn.prepareStatement(query3);
		check3.setInt(1,p);
		check3.executeUpdate();

		//Delete from books table.
		String query4 = "DELETE from books where Id = ?";
		PreparedStatement check4 = conn.prepareStatement(query4);
		check4.setInt(1,p);
		check4.executeUpdate();

		//Delete from author table if only one book written.
		if(count==1)
		{
			String query5 = "DELETE from authors where Id = ?";
			PreparedStatement check5 = conn.prepareStatement(query5);
			check5.setInt(1,p1);
			check5.executeUpdate();
		}

		return Response.status(200).entity("Book Removed sucessfully!!! ").build();
	}




	@POST
	@Path("/Insert-book")
	public Response books(@NotNull
						  @QueryParam("Name") String Name,
						  @NotNull
						  @QueryParam("author_name") String author_name,
						  @NotNull
						  @QueryParam("city_name") String city_name) throws SQLException
	{
		Connection conn = DBUtil.getConnection();
		//Calculating the new Id which is to be given to the new book.
		String s =" SELECT max(Id) FROM books ";
		PreparedStatement pst = conn.prepareStatement(s);
		ResultSet rst = pst.executeQuery();
		int m=0;
		while(rst.next())
		{
			m = rst.getInt(1);
		}
		m=m+1;


		//Calculating the new ID for author.
		String s1 =" SELECT max(Id) FROM authors ";
		PreparedStatement pst1 = conn.prepareStatement(s1);
		ResultSet rst1 = pst1.executeQuery();
		int m1=0;
		while(rst1.next())
		{
			m1 = rst1.getInt(1);
		}
		m1=m1+1;


		//First we have to give the name of book as input.
		System.out.print("Enter the name of the book:");

		String n = Name;


		//Now I am checking if the book exits or not in the library.
		String myQuery1 = "select * from books";
		PreparedStatement pstmt_books ;
		pstmt_books = conn.prepareStatement(myQuery1);
		ResultSet rst_books = null;
		rst_books = pstmt_books.executeQuery();
		int y_books=0;
		while(rst_books.next()) {

			String com_books = rst_books.getString(2);
			if(n.equals(com_books))
			{
				y_books=rst_books.getInt(1);
				break;
			}
		}
		if(y_books!=0)
		{
			System.out.println("Book Already exists");
			return Response.status(500).entity("Book already exists").build();
			//If the book exists then we will not store it again.
		}


		//As book does not exists so storing it in database.
		String str = "INSERT INTO books VALUES(?,?)";
		PreparedStatement pstmt = conn.prepareStatement(str);
		pstmt.setInt(1, m);
		pstmt.setString(2, n);
		pstmt.executeUpdate();
		pstmt.close();


		//Taking the input name of author.
		System.out.print("Enter the Name of Author:");

		String n1 = author_name;


		//Checking if the name of the author already exists or not as if it exists then I will not give it another Id again as it can create confusion.
		String myQuery = "select * from authors";
		PreparedStatement pstmt1 ;
		pstmt1 = conn.prepareStatement(myQuery);
		ResultSet rst2 = null;
		rst2 = pstmt1.executeQuery();
		int y=0;
		while(rst2.next()) {

			String com = rst2.getString(2);
			if(n1.equals(com))
			{
				y=rst2.getInt(1);
				break;
			}
		}
		if(y!=0)
		{
			m1=y;
			System.out.println("Name of the Author Already Exists");
		}
		else
		{

			//It the name is new then will take country input for the database and then storing it in the author table.
			System.out.print("Enter the Name of the Country:");

			String city = city_name;
			String str_author = "INSERT INTO authors VALUES(?,?,?)";
			PreparedStatement pstmt_author = conn.prepareStatement(str_author);
			pstmt_author.setInt(1, m1);
			pstmt_author.setString(2, n1);
			pstmt_author.setString(3, city);
			pstmt_author.executeUpdate();
			pstmt_author.close();

		}


		//Now here i am connecting the book with the author by using book id and author id in the booksauthors table.
		String str_booksauthors = "INSERT INTO booksauthors VALUES(?,?)";
		PreparedStatement pstmt_booksauthors = conn.prepareStatement(str_booksauthors);
		pstmt_booksauthors.setInt(1,m1);
		pstmt_booksauthors.setInt(2,m);
		pstmt_booksauthors.executeUpdate();
		pstmt_booksauthors.close();
		return Response.status(200).entity("Book Added sucessfully!!! ").build();
	}
}
