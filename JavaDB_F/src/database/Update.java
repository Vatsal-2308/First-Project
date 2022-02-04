package database;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.Scanner;

public class Update {
	
	//This function is used to update the name of the book if there is some error in the name using its Old name.
	public static void books(Connection conn) throws SQLException {
		System.out.print("Enter the Name of the Book:");
		Scanner sc = new Scanner(System.in);
		String i = sc.nextLine();
		
		Scanner sc1 = new Scanner(System.in);
		System.out.print("Enter the Correct name of the book:");
		String n = sc1.nextLine();
		 		 String myQuery = "UPDATE Books "
				 + "SET Title = ?"
				 + " WHERE Title = ?";
		 PreparedStatement pstmt ;
		 pstmt = conn.prepareStatement(myQuery);
		 pstmt.setString(1, n);
		 pstmt.setString(2, i);
		 
		 pstmt.executeUpdate();
		 
		 
	}
	
	//By this function we can change the incorrect author name to its correct one.
	public static void Authors_name(Connection conn) throws SQLException {
		System.out.print("Enter the Incorrect Name of the Author:");
		Scanner sc = new Scanner(System.in);
		String i = sc.nextLine();
		Scanner sc1 = new Scanner(System.in);
		System.out.print("Enter the Correct name of the Author:");
		String n = sc1.nextLine();
		 String myQuery = "UPDATE authors "
		 + "SET Name = ?"
		 + " WHERE Name = ?";
		 PreparedStatement pstmt ;
		 pstmt = conn.prepareStatement(myQuery);
		 pstmt.setString(1, n);
		 pstmt.setString(2, i);
		 pstmt.executeUpdate();
	}
	
	//By this function we can change the country of the author using his name.
	public static void Authors_country(Connection conn) throws SQLException {
		System.out.print("Enter the Name of the Author:");
		Scanner sc = new Scanner(System.in);
		String i = sc.nextLine();
		System.out.println(" ");
		Scanner sc1 = new Scanner(System.in);
		System.out.print("Enter the name of the Country:");
		String n = sc1.nextLine();
		 String myQuery = "UPDATE authors "
		 + "SET Country = ?"
		 + " WHERE Name = ?";
		 PreparedStatement pstmt ;
		 pstmt = conn.prepareStatement(myQuery);
		 pstmt.setString(1, n);
		 pstmt.setString(2, i);
		 pstmt.executeUpdate();
	}
}
