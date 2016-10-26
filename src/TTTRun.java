
public class TTTRun {

	
	public static void main(String[] args){
		TTTModel model = new TTTModel(3);
		TTTController con = new TTTController(model);
		TTTView view = new TTTView(con, model.getDim());
		con.setView(view);
		model.addObserver(view);
		view.goView();
	}
	
}
