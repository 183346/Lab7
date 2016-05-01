package it.polito.tdp.dizionario.dao;


	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;



	public class DBConnect {

		private static final String jdbcURL = "jdbc:mysql://localhost/dizionario?user=root";
		static private DBConnect instance = null ;
		
		private DBConnect () {
			instance = this ;
			//System.out.println("instance created") ;
		}
		
		public static DBConnect getInstance() {
			if(instance == null)
				return new DBConnect() ;
			else {
				//System.out.println("instance reused") ;
				return instance ;
			}
		}
		
		public Connection getConnection() {
			try {
				Connection conn = DriverManager.getConnection(jdbcURL) ;
				return conn ;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Cannot get connection "+jdbcURL, e) ;
			}	
		}

}
