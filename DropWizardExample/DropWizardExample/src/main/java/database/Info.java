package database;



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.HashMap;

@Path("/Info")
public class Info {

	
	// This function is used to show all the books in the library.

	@GET
	@Path("/books")

	@Produces(MediaType.APPLICATION_JSON)
	public Response books() throws SQLException {
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
	@Path("/authors")

	@Produces(MediaType.APPLICATION_JSON)
	// This function is used to show all the authors name and their country of origin.
	public Response authors() throws SQLException {
		Connection conn = DBUtil.getConnection();
		 String myQuery = "select * from authors";
		 PreparedStatement pstmt ;
		 pstmt = conn.prepareStatement(myQuery);
		 ResultSet rst = null;
		 rst = pstmt.executeQuery();
		HashMap<String,String> b = new HashMap<>();
		 System.out.println("Name\t\tCountry\t\n");
		 while(rst.next()) {

			 b.put(rst.getString(2),rst.getString(3));
	            System.out.print(rst.getString(2));
	            System.out.print("\t\t"+rst.getString(3));
	            System.out.println();
	         }
		 System.out.println();
		return Response.ok(b,MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/bookauthors")

	@Produces(MediaType.APPLICATION_JSON)
	// This function is used to show books along with their author name.
	public Response bookauthors() throws SQLException {
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
	
	
}
