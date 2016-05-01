package it.polito.tdp.dizionario.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;



public class DizionarioDAO {
	//ottenere la lista di stringhe lunghe numero
	public List<String> ottieniLIsta(int numero) {
		List<String> parole = new LinkedList<String>();
		final String sql = "SELECT * FROM parola where LENGTH(nome)=?";
		
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, numero);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String s = 
						rs.getString("nome");
						
				parole.add(s);
			}
			st.close();
			conn.close();
			return parole;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public boolean controlloNome(String text) {
		boolean controllo=false;
		int contatore=0;
		final String sql = "SELECT nome FROM parola where nome=?";
		
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, text);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				contatore++;
						
				
			}
			if(contatore==0){controllo=false;}else{controllo=true;}
			st.close();
			conn.close();
			
			return controllo;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	
		
	}

}
