import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TTTView extends JFrame implements Observer{
	
	private ActionListener listener;
	private JButton[][] buttons;
	private JButton restart;
	private JFrame frame;
	private int size;
	private JPanel gridPanel;
    private JLabel label;
	
	public TTTView(ActionListener listener, int size){
		this.listener = listener;
		this.size = size;
		buttons = new JButton[size][size];
		
	}
	
	public TTTView(int size){
		buttons = new JButton[size][size];
	}
	
	
	/**
	 * @return the restart
	 */
	public JButton getRestart() {
		return restart;
	}

	public void goView(){
		  frame = new JFrame("TicTacToe");
	      frame.setSize(400, 300);
	     
	      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	      frame.setLayout(new BorderLayout());
	      gridPanel = new JPanel();
	      gridPanel.setLayout(new GridLayout(3,3));
	      restart = new JButton("Restart");
	      restart.setActionCommand("res");
	      restart.addActionListener(listener);
	      

	      for(int i = 0; i < size; i++){
				for(int j = 0; j < size; j++){
					buttons[i][j] = new JButton("");
					//buttons[i][j].setText(model.getEachTurnChar(i, j));
					gridPanel.add(buttons[i][j]);
					buttons[i][j].addActionListener(listener);
					buttons[i][j].setActionCommand(i + "," +j);
					
					
				}
			}
	      label = new JLabel("Start");
	      
	      frame.getContentPane().add(gridPanel, BorderLayout.CENTER);
	      frame.getContentPane().add(label, BorderLayout.NORTH);
	      frame.getContentPane().add(restart, BorderLayout.SOUTH);
	     // frame.pack();
	      frame.setVisible(true);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		TTTModel model = (TTTModel) arg0;
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				buttons[i][j].setText( model.getEachTurnChar(i, j));
			}
		}
		 label.setText(model.gameStatus()); 
	}
	
	public static void main(String[] args){
	   TTTView v = new TTTView(3);
	   v.goView();
	}

}
