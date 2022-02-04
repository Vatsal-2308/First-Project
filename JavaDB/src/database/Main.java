package database;
import java.sql.Connection;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		 
		 Connection conn = DBUtil.getConnection();
		Insert.books(conn);
		Info.bookauthors(conn);
	}
		
	

}
