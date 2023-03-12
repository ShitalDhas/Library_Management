package com.transaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class BookTransactionManager {
	ObjectOutputStream oos_book_transaction = null;
	ObjectInputStream ois_book_transaction = null;
	
	File book_transaction_file = null;
	
	ArrayList<BookTransaction> book_transaction_list = null;
	
	@SuppressWarnings("unchecked")
	public BookTransactionManager() {
		book_transaction_file = new File("book_transaction.dat");
		book_transaction_list = new ArrayList<BookTransaction>();
		
		if(book_transaction_file.exists()) {
			try {
				ois_book_transaction = new ObjectInputStream(new FileInputStream(book_transaction_file));
				book_transaction_list = (ArrayList<BookTransaction>) ois_book_transaction.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean issueBook(int rollNumber, int isbn) {
		int total_books_issued = 0;
		
		for (BookTransaction bookTransaction : book_transaction_list) {
			
			if( (bookTransaction.getRollNumber() == rollNumber) && (bookTransaction.getReturnDate() == null) ) 
				total_books_issued +=1;
			if(total_books_issued >=3)	
				return false;	
		}
		
		String issue_date = new SimpleDateFormat("dd-mm-yyyy").format(new Date());
		BookTransaction book_transaction = new BookTransaction(isbn,rollNumber,issue_date,null);
		
		book_transaction_list.add(book_transaction);
		return true;
	}
	public boolean returnBook(int rollNumber, int isbn) {
		for (BookTransaction bookTransaction : book_transaction_list) {
			if((bookTransaction.getRollNumber() == rollNumber) && (bookTransaction.getIsbn() == isbn) && (bookTransaction.getReturnDate() == null)) {
			
				String return_date = new SimpleDateFormat("dd-mm-yyyy").format(new Date());
				bookTransaction.setReturnDate(return_date);
				return true;
			}				
		}
		return false;
	}
	public void showAll() {
		for (BookTransaction bookTransaction : book_transaction_list) {
			System.out.println(bookTransaction);
		}
	}

	public void writeToFile() {
		try {
			oos_book_transaction = new ObjectOutputStream(new FileOutputStream(book_transaction_file));
			oos_book_transaction.writeObject(book_transaction_list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
