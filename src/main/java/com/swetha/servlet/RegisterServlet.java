package com.swetha.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	
	private static final String query= "INSERT INTO BOOKDATA (BOOKNAME,BOOKEDITION,BOOKPRICE) VALUES(?,?,?)";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// get print writer 
		
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		
		//GET BOOK INFO
		
		String 	bookName = req.getParameter("bookname");
		String 	bookEdition = req.getParameter("bookedition");
	float bookPrice =Float.parseFloat(req.getParameter("bookprice"));
		

		//LOAD JDBC DRIVER
		try {
		 Class.forName("com.mysql.cj.jdbc.Driver");
		
		}catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
			System.out.println("Error in class loadng of jdbc driver");}
		//generate the connection
		 //Connection connection = DriverManager.getConnection("jdbc:bhmysql://localhost:3306/swetha_db","root","Chennai@63");

		try(//Connection con = DriverManager.getConnection("jdbc:mysql:///book","root","Chennai@63"
				Connection con = DriverManager.getConnection("jdbc:mysql:///book","root","Chennai@63"	
				);
				PreparedStatement ps = con.prepareStatement(query);) {
			ps.setString(1, bookName);
			ps.setString(2, bookEdition);
			ps.setFloat(3, bookPrice);
			int count= ps.executeUpdate();
			if(count==1) {
				pw.println("<h2> Record is registered succesffully</h2>");
				
					}
			else {
				pw.println("<h2> Record is not registered succesffully</h2>");

			}
			}catch(SQLException se) {
				
				se.printStackTrace();
				pw.println("<h1"+se.getMessage()+"</h2>");
				
			}catch(Exception e) {
				
				e.printStackTrace();
				pw.println("<h1"+e.getMessage()+"</h2>");
				
			}
		pw.println("<a href='home.html'>Home</a>");
       pw.println("<br>");
		pw.println("<a href='bookList'>Book List</a>");
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    doGet(req,res);
	}
}
