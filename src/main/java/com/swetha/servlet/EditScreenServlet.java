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

@WebServlet("/editScreen")

public class EditScreenServlet extends HttpServlet {
private static final String query= "SELECT BOOKNAME,BOOKEDITION,BOOKPRICE FROM BOOKDATA where id=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// get print writer 
		
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		
		//GET BOOK INFO
		int id = Integer.parseInt(req.getParameter("id"));
			
		

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
			ps.setInt(1,id);

			ResultSet rs = ps.executeQuery();
			rs.next();
			pw.println("<form action='editurl?id="+id+"' method='post'>");
			pw.println("<table align='center'>");
			pw.println("<tr>");
			pw.println("<td>Book Name</td>");
			pw.println("<td><input type='text' name='bookName' value='"+rs.getString(1)+"'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Book Edition</td>");
			pw.println("<td><input type='text' name='bookEdition' value='"+rs.getString(2)+"'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Book Price</td>");
			pw.println("<td><input type='text' name='bookPrice' value='"+rs.getFloat(3)+"'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td><input type='submit' value='Edit'></td>");
			pw.println("<td><input type='reset' value='cancel'></td>");
			pw.println("</tr>");
			pw.println("</table>");
			pw.println("</form>");			
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
