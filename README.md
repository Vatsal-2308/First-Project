# First-Project
Here in this project I have took a library database which has 3 tables. First table is of Authors where it has information about the author name, author city and author ID. Second table is of books
Which has two columns of bookId and book name. The third is a relation of both of them where it has authorId and BookId in it for making a connection between book and author.

I have used dropwizard framework for implementing jersey and jetty and all the related code can be found in the repository.

The urls for the operation are:-

Delete:-
https://www.getpostman.com/collections/57d757c1a47b764d4768
Here If we want to delete a book from the database then we have give the book name as input. Then if the book would not exist then it will give response "Book does not exists". 
If the book exists in the database then it will look for the author name and if the author has written only this book then the book along with its author will get deleted from their respective tables.

Update:-
https://www.getpostman.com/collections/b3659c43452803461524
Here there are three query:-
1) if the book name is incorrect then we can use update-book and type the incorrect book name and the correct book name for changing them. If both the names which are entered are same then it will give response as same names given
and if the incorrect name does not exists in the table then it will give result as no such book and in the last case it will change the book name.

2) if the author name is incorrect then in the same manner as book name it can be changed and the responses remain same as above.

3) if we want to change the author country then the third url can be used where we have to give author name as input and country name as another input and the name will be changes if the author name exists and if it does not exists then will show no such author.


Insert:- 
https://www.getpostman.com/collections/0391c98a414a0e31d609

-> Here we will give the book name, author name, city name as input.
-> then if same book exists then it will not store it again otherwise will create a new entry.
-> If the author has already a book under his name in the library then a new entry for the author will not created, the same authorid will get associated with the new book's Id.

Info(For seeing the tables):- 
https://www.getpostman.com/collections/7d4399c9db3e71f9f5f2

1) By using the book get api, we can get the name of all the books in the library along with a serial number.
2) By using the author get api, we can get information about all the authors ad their country.
3) By using the bookauthor get api, we can get information about which book is written by which author.
