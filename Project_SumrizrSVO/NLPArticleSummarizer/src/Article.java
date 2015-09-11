

import java.awt.Dimension;
import java.awt.TextArea;
import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings({ "serial", "unused" })
public class Article extends JPanel{
	
	private JEditorPane textArea;
	
	public Article()
	{
		setBorder(BorderFactory.createRaisedBevelBorder());
		this.setPreferredSize(new Dimension(200, 600));
		this.setVisible(true);
		textArea = new JEditorPane();
		textArea.setText("<Enter Article here>");
		JScrollPane editorScrollPane = new JScrollPane(textArea);
		editorScrollPane.setVerticalScrollBarPolicy(
		                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		editorScrollPane.setPreferredSize(new Dimension(190, 600));
		
		this.add(editorScrollPane);
	}
	public String getInput()
	{
		return textArea.getText();
	}
	
}
