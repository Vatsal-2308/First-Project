package database;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.Scanner;

public class Update {
	public static void books(Connection conn) throws SQLException {
		System.out.print("Enter the ID of the Book:");
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();
		
		Scanner sc1 = new Scanner(System.in);
		System.out.print("Enter the name of the book:");
		String n = sc1.nextLine();
		 		 String myQuery = "UPDATE Books "
				 + "SET Title = ?"
				 + " WHERE ID = ?";
		 PreparedStatement pstmt ;
		 pstmt = conn.prepareStatement(myQuery);
		 pstmt.setString(1, n);
		 pstmt.setInt(2, i);
		 
		 pstmt.executeUpdate();
		 
		 
	}
	public static void Authors_name(Connection conn) throws SQLException {
		System.out.print("Enter the ID of the Author:");
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();
		Scanner sc1 = new Scanner(System.in);
		System.out.print("Enter the name of the Author:");
		String n = sc1.nextLine();
		 String myQuery = "UPDATE authors "
		 + "SET Name = ?"
		 + " WHERE Id = ?";
		 PreparedStatement pstmt ;
		 pstmt = conn.prepareStatement(myQuery);
		 pstmt.setString(1, n);
		 pstmt.setInt(2, i);
		 pstmt.executeUpdate();
	}
	public static void Authors_country(Connection conn) throws SQLException {
		System.out.print("Enter the ID of the Author:");
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();
		System.out.println(" ");
		Scanner sc1 = new Scanner(System.in);
		System.out.print("Enter the name of the Country:");
		String n = sc1.nextLine();
		 String myQuery = "UPDATE authors "
		 + "SET Country = ?"
		 + " WHERE Id = ?";
		 PreparedStatement pstmt ;
		 pstmt = conn.prepareStatement(myQuery);
		 pstmt.setString(1, n);
		 pstmt.setInt(2, i);
		 pstmt.executeUpdate();
	}
}
