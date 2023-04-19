# IN GOD WE TRUST
![logo](Logo.png)

# THIRD ASSIGNMENT
## Rana Rokni 401222066
1. Introduction

In this assignment I built a terminal based program named Library which is a virtual library app users can login, rent book, return book and log out. There are also librarians who would do librarian tasks as in real world.

2. Design and Implementation

The app is written in Java programming, follows OOP and encapsulation principles to better protect the data.
HashMap and ArraLists are the data structures being used in the program.
To have secure passwords, they would be hashed with MD5 hashing algorithm and by the help of [Java MessageDigest library](https://docs.oracle.com/javase/7/docs/api/java/security/MessageDigest.html). The MD5 (message-digest algorithm) hashing algorithm is a one-way cryptographic function that accepts a message of any length as input and returns as output a fixed-length digest value to be used for authenticating the original message.

User's input information would be checked to be valid, for example user can not borrow a book which isn't available or return ones which hadn't have borrowed, add existed books, users and librarians is prevented and only the librarians can make changes in library storage information(add/remove/update books/users/other librarians). Valid inputs to choose from menus is being checked, for instance.

There are 4 classes in this program: Library, Librarian, User, Book besides the Main class which mostly include different menus.
As said before, the attributes of each are private and you can only access them by function and methods written.
Library class is mostly like the program database which keeps the records of books\users\librarians. Options to change these records are only showed and can only be accessed by librarians' menu. As shown in UML bellow, Librarian class has different attributes and methods, boolean methods will return true value if only the operation being done successfully.
![uml](uml.png)


3. Testing and Evaluation

The program has been tested in traditional way and by handy entering the values, besides some minor problem in menu, the rest of the program runs properly.
Main problem is that there isn't any database framework to save data, so you should enter data each time you run the program.


4. Conclusion
The report concludes the aspects of the library app project, which can be improved by adding GUI, and having database to save data.
And also, program would be more useful if there had been a product manager to define project details.