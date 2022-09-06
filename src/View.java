import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class View implements ActionListener, WindowListener{
	private Controller controller;
    private JFrame win;
    private Model cFile;
    private JButton menuBtns[];
    private final String password;

public View(Model cFile, String password, Controller controller){
		this.password = password;
		this.controller = controller;

		win = new JFrame();

		win.setTitle("Complaint Box");
		win.setSize(500, 600);
		win.addWindowListener(this);
		win.setLayout(new GridLayout(5, 1));
		
		menuBtns = new JButton[5];
		for (int i = 0; i < menuBtns.length; ++i) {
			menuBtns[i] = new JButton();
			win.add(menuBtns[i]);
			menuBtns[i].addActionListener(this);
		}
		menuBtns[0].setText("MAIN MENU");
		menuBtns[1].setText("1. Register a Complaint");
		menuBtns[2].setText("2. Status of Complaint");
		menuBtns[3].setText("3. Check Complaint Filed");
		menuBtns[4].setText("4. Report");
		menuBtns[0].setEnabled(false);

		win.setVisible(true);
    }

    @Override
	public void actionPerformed(ActionEvent e) {
		if (menuBtns[1] == e.getSource()) {
			compRegister(controller);
		} else if (menuBtns[2] == e.getSource()) {
			compStatus(controller);
		} else if (menuBtns[3] == e.getSource()) {
			String pwdEntered = JOptionPane.showInputDialog(win, "Enter Password : ");
			if (pwdEntered == null) {
				// dolce far niente
			} else if (pwdEntered.equals(password)) {
				compCheck(controller);
			} else {
				JOptionPane.showMessageDialog(win, "Wrong password");
			}
		} else if (menuBtns[4] == e.getSource()) {
			compReport(controller);
		}
	}
    
    public void compRegister(Controller controller){
        new compRegister(controller);
    }
    public void compStatus(Controller controller){
        new compStatus(controller);
    }
    public void compCheck(Controller controller){
        new compCheck(controller);
    }
    public void compReport(Controller controller){
        new compReport(controller);
    }

	@Override
	public void windowClosing(WindowEvent e) {
        win.dispose();
	}
    
	@Override
	public void windowClosed(WindowEvent e) {
        cFile.exit();
	}

	@Override
    public void windowOpened(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}


}
