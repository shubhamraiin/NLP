import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class btnSummary extends JPanel implements ActionListener{
	
	JButton button;
	Main but;
	
	public btnSummary(Main butpanel)
	{
		setBorder(BorderFactory.createRaisedBevelBorder());
		this.setPreferredSize(new Dimension(100, 600));
		this.setVisible(true);
		button = new JButton("Summarize");
		button.setSize(new Dimension(90,100));
		button.addActionListener(this);
		this.add(button);
		but = butpanel; 
		setPreferredSize(new Dimension(100,600));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			but.process();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
