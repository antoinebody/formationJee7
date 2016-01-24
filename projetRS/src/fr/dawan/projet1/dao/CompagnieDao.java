package fr.dawan.projet1.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import fr.dawan.projet1.ws.beans.CompagnieAerienne;


//@Singleton
@Named
@ApplicationScoped
public class CompagnieDao {

	private static List<CompagnieAerienne> lv;
	
	static{
		lv	= Collections.synchronizedList(new ArrayList<>());
		lv.add(new CompagnieAerienne(1L, "tomas cook"));
		lv.add(new CompagnieAerienne(2L, "fram"));
	}

	public List<CompagnieAerienne> lister() {
	  	Collections.sort(lv, (x,y) -> (int) (x.getId() - y.getId()));
		return lv;
	}
	
	public CompagnieAerienne rechercher(long id) {
//		int ind = lv.indexOf(new Voyage(id,null,null,null));
//		return (ind==-1) ? null : lv.get(ind);
		CompagnieAerienne res = lv.stream().filter( x -> x.getId()==id).findFirst().get();
		return res;

	}
	
	public boolean supprimer(long id) {
		int ind = lv.indexOf(new CompagnieAerienne(id,null));
		if(ind==-1)
			return false;
		lv.remove(ind);
		return true;
	}
	
	public boolean modifier(CompagnieAerienne v) {
		if(!lv.contains(v))
			return false;
		lv.remove(v);
		return lv.add(v);
	}
	

	public CompagnieAerienne ajouter(CompagnieAerienne v) {
		v.setId(lv.size()+1);
		lv.add(v);
		return v;
	}

}
