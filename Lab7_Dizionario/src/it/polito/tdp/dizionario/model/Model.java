package it.polito.tdp.dizionario.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import it.polito.tdp.dizionario.dao.DizionarioDAO;

public class Model {
	//definizione variabili
	List<String> parole = new LinkedList<String>();
	private SimpleGraph<String, DefaultEdge> grafo = null;
	BreadthFirstIterator<String, DefaultEdge> visita = null;
	Set<DefaultEdge> unici = new HashSet<DefaultEdge>();
	
	
	//fine definizione variabili

	public boolean controlloNumero(String text) {
		 Pattern p = Pattern.compile("^\\d+$");
			// il punto interrogativo indica OPZIONALE
			 //IL SIMBOLO indica anche più di uno o zero
			 //esempio per numeri reali
			 // il carattere | indica la OR
			 //le due sbarre \\ si mettono davanti ai caratteri speciali
			// Pattern p = Pattern.compile("^[+-]?\\d+\\.\\d+|\\d+|\\.\\d+|\\d+\\.$");
			 Matcher m = p.matcher(text);
			 boolean b = m.matches();
		return b;
	}
	public boolean controlloParola(String text,int numero) {
		Pattern p = Pattern.compile("^[a-zA-Z]+$");
		 Matcher m = p.matcher(text);
		 boolean b = m.matches();
		 if(numero!=text.length()){b=false;}
		return b;
	}

	public boolean creaGrafo(int numero) {
		boolean creato=false;
		unici.clear();
		parole.clear();
		grafo = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
		DizionarioDAO dao = new DizionarioDAO();
		//riempimento lista
		parole = dao.ottieniLIsta(numero);
		//inserimento di tutti i vertici
		Graphs.addAllVertices(grafo, parole);
		//creazione degli archi
		for(int j=0;j<parole.size();j++){
		for(String s:parole){
			int contatore=0;
			for(int k=0;k<s.length();k++){
				if(s.substring(k, k+1).compareTo(parole.get(j).substring(k, k+1))==0){contatore++;}
				}
				if(contatore==numero-1 && !unici.contains(grafo.getEdge(s, parole.get(j)))){
				grafo.addEdge(s, parole.get(j));
				unici.add(grafo.getEdge(s, parole.get(j)));
				//System.out.println("arco:  "+grafo.getEdge(s, parole.get(j)));
				contatore=0;
		}
		}
		}
		creato=true;
		return creato;
	}
	public String listaVicini(String text) {
		String result="";
		DizionarioDAO dao = new DizionarioDAO();
		boolean controlloNome=dao.controlloNome(text);
		if(!controlloNome){result="nome non esistente in dizionario"; return result;}
		
		List<String> vicini = new LinkedList<String>();
		vicini=Graphs.neighborListOf(grafo, text);
		//stampa
		for(String s:vicini){
			result=result+"nodo : "+s+"\n";
		}
		
		
		return result;
	}
	public String listaConnessi(String text) {
		String result="";
		DizionarioDAO dao = new DizionarioDAO();
		boolean controlloNome=dao.controlloNome(text);
		if(!controlloNome){result="nome non esistente in dizionario"; return result;}
		visita= new BreadthFirstIterator<String, DefaultEdge>(grafo,text);
		while(visita.hasNext()){
			result=result+"nodo : "+visita.next()+"\n";
		}
			
		return result;
	}

	

}
