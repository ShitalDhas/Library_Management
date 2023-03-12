package com.main;

import java.util.Scanner;

import com.book.Book;
import com.book.BookManager;
import com.exception.BookNotFoundException;
import com.exception.StudentNotFoundException;
import com.person.StudentManager;
import com.person.Student;
import com.transaction.BookTransactionManager;

public class Main {
	public static void main(String[] args) throws StudentNotFoundException, BookNotFoundException {
		int choice;
		Scanner sc = new Scanner(System.in);
		BookManager bm = new BookManager();
		StudentManager sm = new StudentManager();
		BookTransactionManager btm = new BookTransactionManager();
		
		do {
			System.out.println("\nEnter 1 if Student \nEnter 2 if Librarian \nEnter 3 if want to Exit");
			choice = sc.nextInt();
			
			if(choice == 1) 	//if user is student
			{
				System.out.println("\nEnter Your Roll Number");
				int rollNumber = sc.nextInt();
				
				try {
					Student s = sm.get(rollNumber);
					if(s == null)
						throw new StudentNotFoundException();
					int student_choice;
					do {
						System.out.println("Enter 1 to View All Books \nEnter 2 to Search Book by ISBN \nEnter 3 to List Books By Subject \nEnter 4 To Issue a Book \nEnter 5 To Return a Book \nEnter 99 To Exit");
						student_choice = sc.nextInt();
						
						switch(student_choice) {
						case 1:
							System.out.println("\nAll the book records are ");
							bm.viewAllBooks();
							break;
						case 2:
							System.out.println("\nPlease enter ISBN to serach");
							int search_isbn = sc.nextInt();
							Book book = bm.searchBookByIsbn(search_isbn);
							if(book == null) {
								System.out.println("\nNo book eith this ISBN Exists in our Library");
							}
							else
								System.out.println(book);
							
							break;
						case 3:
							System.out.println("\nENter the Subject to search");
							sc.nextLine();
							String search_subject = sc.nextLine();
							bm.listBooksBySubject(search_subject);
							break;
						case 4:
							System.out.println("\nEnter the ISBN to Issue a Book");
							int issue_isbn = sc.nextInt();
							book = bm.searchBookByIsbn(issue_isbn);
							try {
								if(book != null) {
									throw new BookNotFoundException();
								}
								if(book.getAvailable_quentity() > 0) {
									if(btm.issueBook(rollNumber, issue_isbn)) {
										book.setAvailable_quentity(book.getAvailable_quentity()-1);
										System.out.println("\nBook has been Issued");
									}
								}
								else {
									System.out.println("\nThe book has been Issued...");
								}
							}
							catch(BookNotFoundException bnfe) {
								System.out.println(bnfe);
							}
								
							break;
						case 5:
							System.out.println("\nPlease Enter the ISBN to Return a Book");
							int return_isbn = sc.nextInt();
							book = bm.searchBookByIsbn(return_isbn);
							if(book == null) {
								if(btm.returnBook(rollNumber, return_isbn)) {
									book.setAvailable_quentity(book.getAvailable_quentity()+1);;
									System.out.println("\nThank you for returning the book");
								}
								else
									System.out.println("\nCould not return the book");
							}
							else
								System.out.println("\nBook with this ISBN does not exists");
							break;
						case 99:
							System.out.println("\nThank you for Using Library");
							break;
						default:
							System.out.println("\nInvalid Choice");
							sc.nextLine();
						}
					} while (student_choice != 99);
				}
				catch(StudentNotFoundException snfe) {
					System.out.println(snfe);
				}
			}
			else if (choice == 2) 		//user is a Librarian
			{
				int librarian_choice;
				do {
					
					System.out.println("\nEnter 11 to View all Students\nEnter 12 to Print a Student by Roll Number\nEnter 13 to Ragister a Student\nEnter 14 to Update a Student\nEnter 15 to Delete a Student");
					System.out.println("Enter 21 to View all Books\nEnter 22 to Print a Book by Isbn\nEnter 23 to Add a New Book\nEnter 24 to update a Book\nEnter 25 to Delete a Book");
					System.out.println("Enter 31 to View all Transactions");
					System.out.println("Enter 99 to exit");
					librarian_choice = sc.nextInt();
				
					switch(librarian_choice) {
					case 11:		//view all Students
						System.out.println("\nAll the Students Records");
						sm.viewAllStudents();					
						break;
					
					case 12:		//Search a students based on roll number
						System.out.println("\nEnter Roll Number to fetch Student's Record");
						int get_rollNumber = sc.nextInt();
					Student students = sm.get(get_rollNumber);
					if(students == null) {
						System.out.println("\nNo Record matches this Roll Numbers");
					}
					else
						System.out.println(students);
					break;
					
					case 13:		//Add Student details
						System.out.println("\nEnter Student Details to add");
						String name; 
						String emailId;
						String phoneNumber; 
						String address; 
						String dob;
						int rollNumber; 
						int std;
						String division;
						
						sc.nextLine();
						System.out.println("Student Name:");
						name = sc.nextLine();
						
						System.out.println("EmailId:");
						emailId = sc.nextLine();
						
						System.out.println("Phone Number:");
						phoneNumber = sc.nextLine();
						
						System.out.println("Address:");
						address = sc.nextLine();
						
						System.out.println("Date Of Birth:");
						dob = sc.nextLine();
						
						System.out.println("Roll Number:");
						rollNumber = sc.nextInt();
						sc.nextLine();
						
						System.out.println("Standard:");
						std = sc.nextInt();
						sc.nextLine();
						
						System.out.println("Division:");
						division = sc.nextLine();
						
						students = new Student(name, emailId, phoneNumber, address, dob, rollNumber, std, division);
						sm.addStudent(students);
						System.out.println("\nStudent is added");
						break;
						
					case 14:		//Upadating Students to roll number
						System.out.println("\nEnter the Student Roll Number to modify the records");
						int modify_rollNumber;
						modify_rollNumber = sc.nextInt();
						students = sm.get(modify_rollNumber);
						try {
							if(students == null)
								throw new StudentNotFoundException();
							
							sc.nextLine();
							System.out.println("Student Name:");
							name = sc.nextLine();
							
							System.out.println("EmailId:");
							emailId = sc.nextLine();
							
							System.out.println("Phone Number:");
							phoneNumber = sc.nextLine();
							
							System.out.println("Address:");
							address = sc.nextLine();
							
							System.out.println("Date Of Birth:");
							dob = sc.nextLine();
							
							System.out.println("Standard:");
							std = sc.nextInt();
							sc.nextLine();
							
							System.out.println("Division:");
							division = sc.nextLine();
							
							sm.updateStudent(modify_rollNumber, name, emailId, phoneNumber, address, dob, std, division);
							System.out.println("\nStudent Record is Updated ");
						}
						catch(StudentNotFoundException snfe) {
							System.out.println();
							System.out.println(snfe);
						}
						break;
						
					case 15:		//Delete Student Records
						System.out.println("Enter the sudent Roll Number to Delete the Records");
						int delete_rollNumber;
						delete_rollNumber = sc.nextInt();
						if(sm.deleteStudent(delete_rollNumber))
							System.out.println("\nStudent Record is Removed");
						else
							System.out.println("\nNo Record with given Roll Number Exists");
						
						break;
						
					case 21:		//To View all Books List
						bm.viewAllBooks();
						break;
						
					case 22:		//Search a book by ISBN
						int search_isbn;
						System.out.println("\nEnter ISBN of the Book to search");
						search_isbn = sc.nextInt();
						Book book = bm.searchBookByIsbn(search_isbn);
						if(book == null) {
							System.out.println("\nNo book eith this ISBN Exists in our Library");
						}
						else
							System.out.println(book);
						break;
						
					case 23:		//Add a new Book
						System.out.println("\nPlease Enter Book Details to add");
						int isbn;
						String title;
						String author;
						String publisher;
						int edition;
						String subject;
						int available_quentity;
						
						System.out.println("ISBN");
						isbn = sc.nextInt();
						sc.nextLine();
						
						System.out.println("Title");
						title = sc.nextLine();
						
						System.out.println("author");
						author = sc.nextLine();
						
						System.out.println("Publisher");
						publisher = sc.nextLine();
						
						System.out.println("Edition");
						edition = sc.nextInt();
						sc.nextLine();
						
						System.out.println("Subject");
						subject = sc.nextLine();
						
						System.out.println("Available Quentity");
						available_quentity = sc.nextInt();
						sc.nextLine();
						
						book = new Book(isbn, title, author, publisher, edition, subject, available_quentity);
						bm.addBook(book);
						System.out.println("\nA book record is added");
						break;
						
					case 24:		//Update the record
						System.out.println("\nPlease Enter the ISBN to update the records");
						int update_isbn;
						update_isbn = sc.nextInt();
						book = bm.searchBookByIsbn(update_isbn);
						try {
							if(book == null)
								throw new BookNotFoundException();
							System.out.println("\nEnter Book Details to Modify");
							
							sc.nextLine();
							
							System.out.println("Title");
							title = sc.nextLine();
							
							System.out.println("author");
							author = sc.nextLine();
							
							System.out.println("Publisher");
							publisher = sc.nextLine();
							
							System.out.println("Edition");
							edition = sc.nextInt();
							sc.nextLine();
							
							System.out.println("Subject");
							subject = sc.nextLine();
							
							System.out.println("Available Quentity");
							available_quentity = sc.nextInt();
							sc.nextLine();
							
							bm.updateBook(update_isbn, title, author, publisher, edition, subject, available_quentity);
					
						}
						catch(BookNotFoundException bnfe) {
							System.out.println(bnfe);
						}
						break;
						
					case 25:		//Delete the record
						System.out.println("\nPlease Enter the ISBN to Delete the Records");
						int delete_isbn;
						delete_isbn = sc.nextInt();
						
						try {
							book = bm.searchBookByIsbn(delete_isbn);
							if(book == null)
								throw new BookNotFoundException();
							bm.deleteBook(delete_isbn); 
							System.out.println("\nBook Record is deleted");
						}
						catch(BookNotFoundException bnfe) {
							System.out.println(bnfe);
						}
						break;
						
					case 31:		//Niew All Transaction
						System.out.println("\nAll the transaction are");
						btm.showAll();
						break;
						
					case 99:
						System.out.println("\nThank you for Using Library");
						break;
					default:
						System.out.println("\nInvalid Choice");
						sc.nextLine();
						break;
						
					}
					
				}while(librarian_choice != 99);
			}
			
		} while (choice !=3);
		sm.writeToFile();
		bm.writeToFile();
		btm.writeToFile();
		sc.close();
	}
	
}
