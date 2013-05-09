package pippin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class CodeViewPanel implements Observer {

	private JTextField[] actualCodeField = new JTextField[Memory.DATA_SIZE];
	private JTextField[] sourceCodeField = new JTextField[Memory.DATA_SIZE];
	private Machine machine;
	private int currentLocation = 0;

	public CodeViewPanel(Machine machine) {
		this.machine = machine;
		machine.addObserver(this);
	}

	// Write the public method JComponent createDataDisplay()
	// as follows
	// Create a JPanel called returnPanel
	// set its layout to new BorderLayout()
	// Add a centered label to the returnPanel using:
	// returnPanel.add(new JLabel("Data Memory View", JLabel.CENTER),
	// BorderLayout.PAGE_START);
	// Create a JPanel called panel
	// set its layout to new BorderLayout()
	// Create a JPanel called numPanel
	// Create a JPanel called intPanel
	// Create a JPanel called binaryPanel
	// Set the layout of all of these three to new GridLayout(0,1)
	// add numPanel to panel at location BorderLayout.LINE_START
	// add intPanel to panel at location BorderLayout.CENTER
	// add binaryPanel to panel at location BorderLayout.LINE_END
	// Next instantiate and insert all the JTextFields:
	// In a for loop up to the sizes of the arrays:
	// first put
	// numPanel.add(new JLabel(" "+i+": ", JLabel.RIGHT));
	// then make the i-th intField equal to new JTextField(10);
	// and add it to intPanel
	// next the i-th binaryField equal to new JTextField(30);
	// and add it to binaryPanel
	// That completes the for loop
	// Create Scroll bars using:
	// returnPanel.add(new JScrollPane(panel));
	// and return returnPanel;

	public JComponent createCodeDisplay() {
		JPanel returnPanel = new JPanel();
		returnPanel.setLayout(new BorderLayout());
		returnPanel.add(new JLabel("Code Memory View", JLabel.CENTER),
				BorderLayout.PAGE_START);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel numPanel = new JPanel();
		JPanel intPanel = new JPanel();
		JPanel binaryPanel = new JPanel();
		numPanel.setLayout(new GridLayout(0, 1));
		intPanel.setLayout(new GridLayout(0, 1));
		binaryPanel.setLayout(new GridLayout(0, 1));
		panel.add(numPanel, BorderLayout.LINE_START);
		panel.add(intPanel, BorderLayout.CENTER);
		panel.add(binaryPanel, BorderLayout.LINE_END);
		for (int i = 0; i < Memory.DATA_SIZE; i++) {
			numPanel.add(new JLabel(" " + i + ": ", JLabel.RIGHT));
			actualCodeField[i] = new JTextField(10);
			intPanel.add(actualCodeField[i]);
			sourceCodeField[i] = new JTextField(30);
			binaryPanel.add(sourceCodeField[i]);
		}
		returnPanel.add(new JScrollPane(panel));
		return returnPanel;
	}

	@Override
	public void update(Observable o, Object msg) {
		if ("New Program".equals(msg)) {
			for (int i = 0; i < Memory.CODE_SIZE; i++) {
				try {
					actualCodeField[i].setText(machine.getExecutable(i));
					sourceCodeField[i].setText(machine.getSource(i));
				} catch (CodeAccessException e) {
					e.printStackTrace(); // i is always in bounds here
				}
			}
			currentLocation = 0;
			actualCodeField[currentLocation].setBackground(Color.YELLOW);
			sourceCodeField[currentLocation].setBackground(Color.YELLOW);
		}
		// I got rid of a "currentLocation = 0" statement and set all background
		// colors to White. 05/07 12:57
		else if ("Clear".equals(msg)) {
			// set all the fields to the empty String "" and all the background
			// colors to WHITE
			for (int i = 0; i < Memory.CODE_SIZE; i++) {
				actualCodeField[i].setText("");
				actualCodeField[i].setBackground(Color.WHITE);
				sourceCodeField[i].setText("");
				sourceCodeField[i].setBackground(Color.WHITE);
			}
		} else {
			// set the currentLocation field background to WHITE
			actualCodeField[currentLocation].setBackground(Color.WHITE);
			sourceCodeField[currentLocation].setBackground(Color.WHITE);
			// change currentLocation to machine.getIP();
			currentLocation = machine.getIP();
			// set the currentLocation field background to YELLOW
			actualCodeField[currentLocation].setBackground(Color.YELLOW);
			sourceCodeField[currentLocation].setBackground(Color.YELLOW);
			for(int i = 0; i < Memory.DATA_SIZE; i++) {
                try {
                    String val = machine.getExecutable(i);
                    String binary = machine.getSource(i);
                    if(binary.length() > 0) {
                        actualCodeField[i].setText(val);
                        sourceCodeField[i].setText(binary);
                    }
                } catch (CodeAccessException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
            }
		}
	}
}
