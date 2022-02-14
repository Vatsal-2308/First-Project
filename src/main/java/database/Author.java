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


@Path("/authors")
public class Author {


    // This function is used to show all the books in the library.


    @GET
    @Path("/bookfromauthor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response bookfromauthor(@NotNull
                                   @QueryParam("authorname") String authorname) throws SQLException
    {
        Connection conn = DBUtil.getConnection();
        String myQuery = "select * from authors";
        String i = authorname;
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
            String result = "Author name Does not exists";
            return Response.ok(result,MediaType.APPLICATION_JSON).build();
            //System.out.println("No author found");
            //System.exit(0);
        }
        myQuery = "select * from booksauthors";
        pstmt = conn.prepareStatement(myQuery);
        rst = pstmt.executeQuery();
        HashMap<Integer,String> b = new HashMap<>();
        int num=1;
        while(rst.next())
        {
            if(y1==rst.getInt(1))
            {
                int y=rst.getInt(2);
                String q = "select * from books";
                PreparedStatement pstmt1 = conn.prepareStatement(q);
                ResultSet rst1 = pstmt1.executeQuery();
                while(rst1.next())
                {
                    if(y==rst1.getInt(1))
                    {
                        b.put(num,rst1.getString(2));
                        num++;
                        //System.out.println(rst1.getString(2));
                        break;
                    }
                }
            }
        }
        return Response.ok(b,MediaType.APPLICATION_JSON).build();

    }




    @GET
    @Path("/authors_table")

    @Produces(MediaType.APPLICATION_JSON)
    // This function is used to show all the authors name and their country of origin.
    public Response authors() throws SQLException
    {
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
    @Path("/country")
    @Produces(MediaType.APPLICATION_JSON)
    public Response country(@NotNull
                            @QueryParam("name") String name) throws SQLException
    {
        Connection conn = DBUtil.getConnection();
        String myQuery = "select * from authors";
        String i= name;
        PreparedStatement pstmt = conn.prepareStatement(myQuery);
        ResultSet rst = pstmt.executeQuery();
        int y=1;
        HashMap<Integer,String> b = new HashMap<>();
        while(rst.next())
        {
            if(i.equals(rst.getString(3)))
            {
                b.put(y,rst.getString(2));
                y++;
                //System.out.println(rst.getString(2));
            }
        }
        if(y==1)
        {
            String result = "No authors from this country";
            return Response.ok(result,MediaType.APPLICATION_JSON).build();
        }
        else
        {
            return Response.ok(b,MediaType.APPLICATION_JSON).build();
        }
    }



    @GET
    @Path("/author_exists")
    @Produces(MediaType.APPLICATION_JSON)
    public Response author_exists(@NotNull
                                  @QueryParam("name") String name) throws SQLException
    {
        Connection conn = DBUtil.getConnection();
        String i = name;
        String myQuery = "select * from authors";
        PreparedStatement pstmt = conn.prepareStatement(myQuery);
        ResultSet rst = pstmt.executeQuery();
        while(rst.next())
        {
            if(i.equals(rst.getString(2)))
            {
                String result = "Author Exists";
                return Response.ok(result,MediaType.APPLICATION_JSON).build();
                //System.out.println("Author Exists");
                //System.exit(0);
            }
        }
        return Response.ok("No such Author Exists",MediaType.APPLICATION_JSON).build();

    }




    @POST
    @Path("/Update-Author_name")
    //By this function we can change the incorrect author name to its correct one.
    public Response Authors_name(@NotNull
                                 @QueryParam("incorrect") String incorrect,
                                 @NotNull
                                 @QueryParam("correct") String correct) throws SQLException
    {
        Connection conn = DBUtil.getConnection();

        //System.out.print("Enter the Incorrect Name of the Author:");
        String i = incorrect;

        String myQuery1 = "select * from authors";
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
            return Response.status(500).entity("Name Does not exists").build();
        }


        //System.out.print("Enter the Correct name of the Author:");
        String n = correct;
        if(i.equals(n))
        {
            return Response.status(500).entity("Same Name Entered").build();
        }
        String myQuery = "UPDATE authors "
                + "SET Name = ?"
                + " WHERE Name = ?";
        PreparedStatement pstmt ;
        pstmt = conn.prepareStatement(myQuery);
        pstmt.setString(1, n);
        pstmt.setString(2, i);
        pstmt.executeUpdate();
        return Response.status(200).entity("Author Name Changed!!! ").build();
    }



    @POST
    @Path("/Update-Author_city")
    //By this function we can change the country of the author using his name.
    public Response Authors_country(@NotNull
                                    @QueryParam("authorname") String authorname,
                                    @NotNull
                                    @QueryParam("cityname") String cityname) throws SQLException
    {
        Connection conn = DBUtil.getConnection();
        //System.out.print("Enter the Name of the Author:");
        //Scanner sc = new Scanner(System.in);
        String i = authorname;

        String myQuery1 = "select * from authors";
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
            return Response.status(500).entity("Author Name Does not exists").build();
        }


        //System.out.println(" ");
        //Scanner sc1 = new Scanner(System.in);
        //System.out.print("Enter the name of the Country:");
        String n = cityname;
        String myQuery = "UPDATE authors "
                + "SET Country = ?"
                + " WHERE Name = ?";
        PreparedStatement pstmt ;
        pstmt = conn.prepareStatement(myQuery);
        pstmt.setString(1, n);
        pstmt.setString(2, i);
        pstmt.executeUpdate();
        return Response.status(200).entity("City Name Changed!!! ").build();
    }

}
