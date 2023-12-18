 package com.swetha.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {
	
private static final String query= "SELECT ID,BOOKNAME,BOOKEDITION,BOOKPRICE FROM BOOKDATA";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// get print writer 
		
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		
		//GET BOOK INFO
		
		

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
			ResultSet rs = ps.executeQuery();
			pw.println("<table border='1' align='center'>");
			pw.println("<tr>");
			pw.println("<th>Book Id</th>");
			pw.println("<th>Book Name</th>");
			pw.println("<th>Book Edition</th>");
			pw.println("<th>Book Price</th>");
			pw.println("<th>Edit</th>");
			pw.println("<th>Delete</th>");
			pw.println("</tr>");
			while(rs.next()) {
				pw.println("<tr>");
				pw.println("<td>"+rs.getInt(1)+"</td>");
				pw.println("<td>"+rs.getString(2)+"</td>");
				pw.println("<td>"+rs.getString(3)+"</td>");
				pw.println("<td>"+rs.getFloat(4)+"</td>");
				pw.println("<td><a href='editScreen?id="+rs.getInt(1)+"'>Edit</a></td>");
				pw.println("<td><a href='deleteurl?id="+rs.getInt(1)+"'>Delete</a></td>");
				pw.println("</tr>");
			}
			pw.println("</table>");
		}catch(SQLException se) {
				
				se.printStackTrace();
				pw.println("<h1"+se.getMessage()+"</h2>");
				
			}catch(Exception e) {
				
				e.printStackTrace();
				pw.println("<h1"+e.getMessage()+"</h2>");
				
			}
		pw.println("<a href='home.html'>Home</a>");

	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    doGet(req,res);
	}


}
