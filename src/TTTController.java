import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class TTTController implements ActionListener{
	private TTTModel model;
	private TTTView view;
	
	public TTTController(TTTModel model){
		this.model = model;
	}
	
	/**
	 * @param view the view to set
	 */
	public void setView(TTTView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String res = view.getRestart().getActionCommand();
		String com = arg0.getActionCommand();
		
		if(res.equals(com)){
			model.restart();
			return;
		}
		
		String[] parts = com.split(",");
		

		int row = Integer.parseInt(parts[0]);
		int col = Integer.parseInt(parts[1]);
		
		model.play(row, col);
		
	}

}
