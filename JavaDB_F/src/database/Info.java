package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
public class Info {

	
	// This function is used to show all the books in the library.
	public static void books(Connection conn) throws SQLException {
		 String myQuery = "select * from books";
		 PreparedStatement pstmt ;
		 pstmt = conn.prepareStatement(myQuery);
		 ResultSet rst = null;
		 rst = pstmt.executeQuery();
		 System.out.println("BookTitle\n");
		 while(rst.next()) {
	            
	           
	            System.out.print(rst.getString(2));
	            System.out.println();
	         }
		 System.out.println();
	}
	
	
	
	// This function is used to show all the authors name and their country of origin.
	public static void authors(Connection conn) throws SQLException {
		 String myQuery = "select * from authors";
		 PreparedStatement pstmt ;
		 pstmt = conn.prepareStatement(myQuery);
		 ResultSet rst = null;
		 rst = pstmt.executeQuery();
		 System.out.println("Name\t\tCountry\t\n");
		 while(rst.next()) {
	            
	           
	            System.out.print(rst.getString(2));
	            System.out.print("\t\t"+rst.getString(3));
	            System.out.println();
	         }
		 System.out.println();
	}
	
	
	// This function is used to show books along with their author name.
	public static void bookauthors(Connection conn) throws SQLException {
		 String myQuery = "SELECT a.Name AuthorName,b.Title BookTitle FROM BooksAuthors ba INNER JOIN Authors a ON a.id = ba.authorid INNER JOIN Books b ON b.id = ba.bookid";
		 PreparedStatement pstmt ;
		 pstmt = conn.prepareStatement(myQuery);
		 ResultSet rst = null;
		 rst = pstmt.executeQuery();
		 System.out.println("AuthorName\t\t\tBookTitle\t\n");
		 
		 while(rst.next()) {           
	            System.out.print(rst.getString(1));	            
	            System.out.print("\t\t\t"+rst.getString(2));	      
	            System.out.println();
	         }
		 System.out.println();
	}
	
	
}
