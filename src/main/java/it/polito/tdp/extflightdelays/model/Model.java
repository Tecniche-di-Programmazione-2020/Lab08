package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	

	private SimpleWeightedGraph<Airport, DefaultWeightedEdge> grafo;
	private ExtFlightDelaysDAO dao;
	Map <Integer,Airport> aereoporti;
	List<Rotta> rotte ;
	
	public Model() {
		dao= new ExtFlightDelaysDAO();
		//aereoporti= new LinkedHashMap <Integer,Airport> (dao.loadAllAirports());
		aereoporti= dao.loadAllAirports();
		
	}


	public void CreaGrafo(int distanzaMedia) {
		// Creo il grafo
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		//Aggiungo i vertici 
		Graphs.addAllVertices(grafo, aereoporti.values());
		
		//Richiedo al DB la lista delle rotte. Posso avere massimo media A->B e media B->A
		rotte = new ArrayList<Rotta> (dao.getRotte(aereoporti, distanzaMedia));
		
		//Aggiungo le rotte alla mappa
		for(Rotta rotta:rotte) {
		//Cerco ilpercorso per verificare se esiste
		DefaultWeightedEdge edge = grafo.getEdge(rotta.getA1(), rotta.getA2());
		if(edge==null) {
			//Se non esiste lo aggiungo 
			Graphs.addEdge(grafo, rotta.getA1(), rotta.getA2(), rotta.getPeso());
		}
		else {
			//Se esiste modifico solo il peso facendo media andata e ritorno
			double peso = grafo.getEdgeWeight(edge);
			double newPeso = (peso + rotta.getPeso())/2;
			grafo.setEdgeWeight(edge, newPeso);
			
		}
		}
		
		
		}
		
		public int nVertici() {
			return this.grafo.vertexSet().size();
		}
		
		public int nArchi() {
			return this.grafo.edgeSet().size();
		}


		public List<Rotta> getRotte() {
			return rotte;
		}


		
		
		
	
		
		
		
		
	}

