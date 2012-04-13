package tp;

import java.util.ArrayList;
import java.util.List;

import parallelisation.client.Maitre;

public abstract class TP {
	
	/** La liste des expériences à effectuer pour le TP */
	private List<Experience> todoList;
	
	/**
	 * Basic constructor
	 */
	public TP() {
		super();
		todoList = new ArrayList<Experience>();
	}
	
	public void lancerTP(){
		if( ! todoList.isEmpty() ){
			Maitre m = todoList.get(0).getMaitre();
			m.initialiserRequetes();
			m.lancerRequetes();
		}
	}
	
	public void nextExp(){
		if( todoList.size() > 1 ){
			/*On attend les derniers arrivés */
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Experience oldExp = todoList.get(0);
			System.out.println(oldExp.printPretty());
			todoList.remove(0);
			
			
			
			Maitre m = todoList.get(0).getMaitre();
			m.initialiserRequetes();
			m.lancerRequetes();
		}else{
			System.out.println("=======================================================");
			System.out.println(" 				LE TP EST TERMINE !");
			System.out.println("=======================================================");
		}
	}

	public void ajouterExperience( Experience exp){
		todoList.add(exp);
	}
	
	public List<Experience> getTodoList() {
		return todoList;
	}

	public void setTodoList(final List<Experience> newtodoList) {
		todoList = newtodoList;
	}

}
