



import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;



@SuppressWarnings({ "unused", "serial" })
public class Main extends JFrame {

	/*
	 * JPANELS
	 */
	Article article;
	OutputSummary outputSummary;
	btnSummary buttonPanel;
	/*
	 *ProcessSummary
	 */
	private ProcessSummary ns;
	/*
	 * Main Frame 
	 */
	public Main() throws IOException
	{		
		setLayout(new BorderLayout());
		article = new Article();
		add(article, BorderLayout.WEST);
		
		outputSummary = new OutputSummary();
		add(outputSummary, BorderLayout.EAST);
		
		buttonPanel = new btnSummary(this);
		add(buttonPanel, BorderLayout.CENTER);
		
		try {
			ns = new ProcessSummary();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame main;
		try {
			main = new Main();
			main.setVisible(true);
			main.setTitle("Natural Language Processing Project: Article ProcessSummary");
			main.setSize(800,600);
			main.setResizable(true);
			main.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		} catch (IOException e) {
			
			System.out.println(e.getMessage());
		}
	}

	
	
	/*
	 * Process
	 */
	public void process() throws IOException, ClassNotFoundException
	{
		String input = article.getInput();
		ns.readInputFromGUI(input);
		outputSummary.setOutput(ns.returnsReportHTML());
	}
}
