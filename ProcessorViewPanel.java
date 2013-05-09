package pippin;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProcessorViewPanel implements Observer {
	private JTextField accumulator = new JTextField();
	private JTextField programCounter = new JTextField();
	private Machine machine;

	public ProcessorViewPanel(Machine machine) {
		this.machine = machine;
		if (machine != null)
			machine.addObserver(this);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		accumulator.setText("" + machine.getAcc());
		programCounter.setText("" + machine.getIP());
	}

	public JComponent createProcessorDisplay() {
		JPanel retVal = new JPanel();

		// set it up with labels Assembler and Instruction Pointer and the text
		// fields above.

		JLabel assembler = new JLabel("Accumulator: ", JLabel.RIGHT);

		JLabel intrPntr = new JLabel("Instruction Pointer: ", JLabel.RIGHT);

		retVal.setLayout(new GridLayout(1, 0));

		retVal.add(assembler);

		retVal.add(accumulator);

		retVal.add(intrPntr);
		retVal.add(programCounter);
		// create a JPanel called retVal
		// set it up with labels Assembler and Instruction Pointer and the text
		// fields above.
		// to get the image above, when added to the Machine's frame
		return retVal;
	}
}
