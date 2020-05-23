package it.polito.tdp.extflightdelays.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		model.CreaGrafo(4900);
		System.out.println("Numero di veritci "+ model.nVertici());
		System.out.println("Numero di archi "+ model.nArchi());
		System.out.println("Elenco archi\n ");
		for(Rotta r:model.getRotte())System.out.println(r.toString());

	}

}
