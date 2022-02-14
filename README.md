# Library-Management
-> Here in this project I have took a library database which has 3 tables. First table is of Authors where it has information about the author name, author city and author ID. Second table is of books Which has two columns of bookId and book name. The third is a relation of both of them where it has authorId and BookId in it for making a connection between book and author.

-> I have used dropwizard framework for implementing jersey and jetty and all the related code can be found in the repository.



The HTTPS method used here are:-

GET ->
GET http://localhost:8080/books/books_table           
-> By using the book get api, we can get the name of all the books in the library along with a serial number.

GET http://localhost:8080/authors/authors_table
-> By using the author get api, we can get information about all the authors ad their country.

GET http://localhost:8080/books/bookauthors   
-> By using the bookauthor get api, we can get information about which book is written by which author.

GET http://localhost:8080/books/authorfrombook?bookname=The Great Gatsby
->In this api we have to give the bookname as input and in return it would give us the author name as output and if the book would not exists then will give a message of book does not exists.

GET http://localhost:8080/authors/bookfromauthor?authorname=J.D. Salinger
-> Here we have to give the author name as input and in return we will get all the books written by him as output and if there are no books under his name then it will give a message that author name does not exists.

GET http://localhost:8080/authors/country?name=USA
-> Here we have to give the country as input and we will get all the authors from that country and if there are no authors from the country then it will display that no authors from the country.

GET http://localhost:8080/authors/author_exists?name=Jane Austen
-> Here by giving the author name as input we can check whether the author name exists or not.

GET http://localhost:8080/books/book_exists?name=The Catcher in the Rye
-> We can use this api if we are searching for a specific book in the library as here we can give the book name as input and it will show whether it exists or not.


DELETE->
DELETE http://localhost:8080/books/delete-book?Name=Pride_and_Prejudic   

-> Here If we want to delete a book from the database then we have give the book name as input. Then if the book would not exist then it will give response "Book does not exists". If the book exists in the database then it will look for the author name and if the author has written only this book then the book along with its author will get deleted from their respective tables.



POST->
POST http://localhost:8080/books/Insert-book?Name=Pride and Prejudice&author_name=Jane Austen&city_name=USA 

-> Here we will give the book name, author name, city name as input.
-> then if same book exists then it will not store it again otherwise will create a new entry.
-> If the author has already a book under his name in the library then a new entry for the author will not created, the same authorid will get associated with the new book's Id.



POST http://localhost:8080/books/Update-book?bookname=The Great Gatsby&correct=The Great Gatsby

-> if the book name is incorrect then we can use update-book and type the incorrect book name and the correct book name for changing them. If both the names which are entered are same then it will give response as same names given and if the incorrect name does not exists in the table then it will give result as no such book and in the last case it will change the book name.



POST http://localhost:8080/authors/Update-Author_name?incorrect=Jane_Austen&correct=Jane Austen

->  if the author name is incorrect then in the same manner as book name it can be changed and the responses remain same as above.



POST http://localhost:8080/authors/Update-Author_city?authorname=Jane Austen&cityname=USA

-> if we want to change the author country then the third url can be used where we have to give author name as input and country name as another input and the name will be changes if the author name exists and if it does not exists then will show no such author.


