package pippin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * @author Sgt. Dexter's Lonely Hearts Club Band
 * The Machine class for the pippin project. Runs a pippin simulator.
 */
public class Machine extends Observable {

	public final Instruction[] INSTRUCTION_SET = new Instruction[284]; // <<<<<<<CORRECTION>>>>>>>>>
	private Processor proc = new Processor();
	private Memory memory = new Memory();
	private boolean running = false;
	private boolean autoStepOn = false;
	private File currentlyExecutingFile = null;
	private States state;
	private CodeViewPanel codeViewPanel;
	private DataViewPanel dataViewPanel;
	private ControlPanel controlPanel;
	private ProcessorViewPanel processorPanel;
	private MenuBarBuilder menuBuilder;
	private JFrame frame;

	private String sourceDir;
	private String executableDir;
	private String eclipseDir;
	private Properties properties = null;
	private Loader loader;

	private static final int TICK = 500; // timer tick = 1/2 second

	/**
	 * Constructs the Machine
	 */
	public Machine() {
		INSTRUCTION_SET[0] = new NOP(proc, memory);
		INSTRUCTION_SET[1] = new LOD(proc, memory);
		INSTRUCTION_SET[2] = new LODI(proc, memory);
		INSTRUCTION_SET[3] = new STO(proc, memory);
		INSTRUCTION_SET[4] = new ADD(proc, memory);
		INSTRUCTION_SET[5] = new SUB(proc, memory);
		INSTRUCTION_SET[6] = new MUL(proc, memory);
		INSTRUCTION_SET[7] = new DIV(proc, memory);
		INSTRUCTION_SET[8] = new ADDI(proc, memory);
		INSTRUCTION_SET[9] = new SUBI(proc, memory);
		INSTRUCTION_SET[10] = new MULI(proc, memory);
		INSTRUCTION_SET[11] = new DIVI(proc, memory);
		INSTRUCTION_SET[16] = new AND(proc, memory);
		INSTRUCTION_SET[17] = new ANDI(proc, memory);
		INSTRUCTION_SET[18] = new NOT(proc, memory);
		INSTRUCTION_SET[19] = new CMPZ(proc, memory);
		INSTRUCTION_SET[20] = new CMPL(proc, memory);
		INSTRUCTION_SET[26] = new JUMP(proc, memory);
		INSTRUCTION_SET[27] = new JMPZ(proc, memory);
		INSTRUCTION_SET[31] = new HALT(proc, memory);
		INSTRUCTION_SET[257] = new LDN(proc, memory);
		INSTRUCTION_SET[259] = new STN(proc, memory);
		INSTRUCTION_SET[260] = new ADDN(proc, memory);
		INSTRUCTION_SET[261] = new SUBN(proc, memory);
		INSTRUCTION_SET[262] = new MULN(proc, memory);
		INSTRUCTION_SET[263] = new DIVN(proc, memory);
		INSTRUCTION_SET[282] = new JMPN(proc, memory);
		INSTRUCTION_SET[283] = new JMZN(proc, memory);
		loader = new Loader(memory); // <<<<<<<<<<<<<THIS IS A
										// CORRECTION>>>>>>>>>>>>>>>>>
		// CODE TO DISCOVER THE ECLIPSE DEFAULT DIRECTORY:
		File temp = new File("propertyfile.txt");
		if (!temp.exists()) {
			PrintWriter out;
			try {
				out = new PrintWriter(temp);
				out.close();
				eclipseDir = temp.getAbsolutePath();
				temp.delete();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			eclipseDir = temp.getAbsolutePath();
		}
		// change to forward slashes
		eclipseDir = eclipseDir.replace('\\', '/');
		int lastSlash = eclipseDir.lastIndexOf('/');
		eclipseDir = eclipseDir.substring(0, lastSlash + 1);
		System.out.println(eclipseDir);
		try { // load properties file "propertyfile.txt", if it exists
			properties = new Properties();
			properties.load(new FileInputStream("propertyfile.txt"));
			sourceDir = properties.getProperty("SourceDirectory");
			executableDir = properties.getProperty("ExecutableDirectory");
			// CLEAN UP ANY ERRORS IN WHAT IS STORED:
			if (sourceDir == null || sourceDir.length() == 0
					|| !new File(sourceDir).exists()) {
				sourceDir = eclipseDir;
			}
			if (executableDir == null || executableDir.length() == 0
					|| !new File(executableDir).exists()) {
				executableDir = eclipseDir;
			}
		} catch (Exception e) {
			// PROPERTIES FILE DID NOT EXIST
			sourceDir = eclipseDir;
			executableDir = eclipseDir;
		}
		createAndShowGUI();

	}

	/**
	 * Execute the machine, running the simlulator.
	 */
	public void execute() {
		while (running) {
			long binary;
			Instruction instr;
			try {

				binary = memory.getCode(this.getIP());

				// use the same code as in getExecutable to get instr from
				// binary
				int high = (int) (binary >> 32);
				int arg = (int) (-1 & binary);
				instr = INSTRUCTION_SET[high];

				if (instr.toString().equals("HALT")) {
					setRunning(false);
				} else {
					instr.execute(arg);
				}
			} catch (CodeAccessException e) {
				e.printStackTrace();
			} catch (DataAccessException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * Steps over to the next instruction.
	 */
	public void step() {

		long binary;
		Instruction instr;
		try {

			binary = memory.getCode(this.getIP());

			// use the same code as in getExecutable to get instr from binary
			int high = (int) (binary >> 32);
			int arg = (int) (-1 & binary);
			instr = INSTRUCTION_SET[high];

			if (instr.toString().equals("HALT")) {
				setRunning(false);
			} else {
				instr.execute(arg);
			}
		} catch (CodeAccessException e) {
			e.printStackTrace();
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		setChanged();
		notifyObservers("Step");
	}

	/**
	 * reloads the machines, resetting the loaded program.
	 */
	public void reload() {
		clearAll();
		finalLoad_ReloadStep();
	}

	/**
	 * Clears the machine. Removes all loaded instructions and data in the simulator.
	 */
	public void clearAll() {
		memory.clearData();
		memory.clearCode();
		state = States.NOTHING_LOADED;
		state.enter();
		setChanged();
		notifyObservers("Clear");
	}

	/**
	 * Gets the data in binary
	 * @param location integer for the location
	 * @return a String
	 * @throws DataAccessException
	 */
	public String getDataBinary(int location) throws DataAccessException {
		int value = memory.getData(location);
		String str = "00000000000000000000000000000000"
				+ Integer.toBinaryString(value);
		str = str.substring(str.length() - 32);
		return str;
	}

	/**
	 * Method that sets up the whole GUI and locates the individual components
	 * into place. Also sets up the Menu bar. Starts a swing timer ticking.
	 */
	private void createAndShowGUI() {
		codeViewPanel = new CodeViewPanel(this);
		dataViewPanel = new DataViewPanel(this);
		controlPanel = new ControlPanel(this);
		processorPanel = new ProcessorViewPanel(this);
		menuBuilder = new MenuBarBuilder(this);
		frame = new JFrame("Pippin Simulator");
		Container content = frame.getContentPane();
		content.setLayout(new BorderLayout(1, 1));
		content.setBackground(Color.BLACK);
		frame.setSize(800, 600); // <<<<<<<<<<<<<<<<CHANGE HERE>>>>>>>>>>>>

		frame.add(codeViewPanel.createCodeDisplay(), BorderLayout.CENTER);
		frame.add(dataViewPanel.createDataDisplay(), BorderLayout.LINE_END);
		frame.add(controlPanel.createControlDisplay(), BorderLayout.PAGE_END);
		frame.add(processorPanel.createProcessorDisplay(),
				BorderLayout.PAGE_START);

		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JMenuBar bar = new JMenuBar();
		frame.setJMenuBar(bar);

		bar.add(menuBuilder.createMenu());
		bar.add(menuBuilder.createMenu2()); // <<<<<<<<<<<<<<<<CHANGE
											// HERE>>>>>>>>>>>>

		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new ExitAdapter());
		state = States.NOTHING_LOADED;
		state.enter();
		setChanged();
		notifyObservers();
		javax.swing.Timer timer = new javax.swing.Timer(TICK,
				new TimerListener());
		timer.start();
		frame.setVisible(true);
	}

	/**
	 * getter for the State
	 * @return the state
	 */
	public States getState() {
		return state;
	}

	/**
	 * setter for the state
	 * @param state
	 */
	public void setState(States state) {
		this.state = state;
	}

	/**
	 * Exits the adapter
	 */
	private class ExitAdapter extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent arg0) {
			exit();
		}
	}

	/**
	 * Timer Listener class
	 */
	private class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (autoStepOn) {
				step();
			}
		}
	}

	/**
	 * Exits the machine
	 */
	public void exit() { // method executed when user exits the program
		int decision = JOptionPane.showConfirmDialog(frame,
				"Do you really wish to exit?", "Confirmation",
				JOptionPane.YES_NO_OPTION);
		if (decision == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	/**
	 * setter for autostep
	 * @param b boolean for autostep
	 */
	public void setAutoStepOn(boolean b) {
		autoStepOn = b;
		if (autoStepOn) {
			state = States.AUTO_STEPPING;
			state.enter();
			setChanged();
			notifyObservers();
		} else {
			state = States.PROGRAM_LOADED_NOT_AUTOSTEPPING;
			state.enter();
			setChanged();
			notifyObservers();
			// same as above, except the state changes to
			// PROGRAM_LOADED_NOT_AUTOSTEPPING;
		}
	}

	/*
	 * getter for autostep
	 * @return boolean if autostep is on
	 */
	public boolean isAutoStepOn() {
		return this.autoStepOn;
	}

	/**
	 * setter for running
	 * @param bolean for running
	 */
	public void setRunning(boolean b) {
		running = b;
		if (running) {
			state = States.PROGRAM_LOADED_NOT_AUTOSTEPPING;
			state.enter();
			notifyObservers("New Program");
			// same as below but do NOT change autoStepOn and the new
			// state is PROGRAM_LOADED_NOT_AUTOSTEPPING;
			// also call notifyObservers with the argument "New Program"
		} else {
			autoStepOn = false;
			state = States.PROGRAM_HALTED;
			state.enter();
			setChanged();
			notifyObservers();
		}
	}

	/**
	 * clears the memory
	 */
	public void clearMemory() {
		this.memory.clearCode();
		this.memory.clearData();
	}

	/**
	 * assembles the file
	 */
	public void assembleFile() {
		File source = null;
		File outputExe = null;
		JFileChooser chooser = new JFileChooser(sourceDir);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Pippin Source Files", "pips");
		chooser.setFileFilter(filter);
		// CODE TO LOAD DESIRED FILE
		int openOK = chooser.showOpenDialog(null);
		if (openOK == JFileChooser.APPROVE_OPTION) {
			source = chooser.getSelectedFile();
		}
		if (source != null && source.exists()) {
			// CODE TO REMEMBER WHICH DIRECTORY HAS THE pipe FILES
			// WHICH WE WILL ALLOW TO BE DIFFERENT
			sourceDir = source.getAbsolutePath();
			sourceDir = sourceDir.replace('\\', '/');
			int lastDot = sourceDir.lastIndexOf('.');
			String outName = sourceDir.substring(0, lastDot + 1) + "pipe";
			int lastSlash = sourceDir.lastIndexOf('/');
			sourceDir = sourceDir.substring(0, lastSlash + 1);
			outName = outName.substring(lastSlash + 1);
			filter = new FileNameExtensionFilter("Pippin Executable Files",
					"pipe");
			if (executableDir.equals(eclipseDir)) {
				chooser = new JFileChooser(sourceDir);
			} else {
				System.out.println(executableDir);
				System.out.println(outName);
				chooser = new JFileChooser(executableDir);
			}
			chooser.setFileFilter(filter);
			chooser.setSelectedFile(new File(outName));
			int saveOK = chooser.showSaveDialog(null);
			if (saveOK == JFileChooser.APPROVE_OPTION) {
				outputExe = chooser.getSelectedFile();
			}
			if (outputExe != null) {
				executableDir = outputExe.getAbsolutePath();
				executableDir = executableDir.replace('\\', '/');
				lastSlash = executableDir.lastIndexOf('/');
				executableDir = executableDir.substring(0, lastSlash + 1);
				try {
					properties.setProperty("SourceDirectory", sourceDir);
					properties
							.setProperty("ExecutableDirectory", executableDir);
					properties.store(new FileOutputStream("propertyfile.txt"),
							"File locations");
				} catch (Exception e) {
					System.out.println("Error writing properties file");
				}
				if(Assembler.assembleFile(source, outputExe)) {
				JOptionPane.showMessageDialog(frame,
						"The source was assembled to an executable", "Success",
						JOptionPane.INFORMATION_MESSAGE);
				}
			} else {// outputExe still null
				JOptionPane.showMessageDialog(frame,
						"The output file has problems.\n"
								+ "Cannot assemble the program", "Warning",
						JOptionPane.OK_OPTION);
			}
		} else {// outputExe does not exist
			JOptionPane.showMessageDialog(frame,
					"The source file has problems.\n"
							+ "Cannot assemble the program", "Warning",
					JOptionPane.OK_OPTION);
		}
	}

	/**
	 * loads the file
	 */
	public void loadFile() {
		JFileChooser chooser = new JFileChooser(executableDir);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Pippin Executable Files", "pipe");
		chooser.setFileFilter(filter);
		// CODE TO LOAD DESIRED FILE
		int openOK = chooser.showOpenDialog(null);
		if (openOK == JFileChooser.APPROVE_OPTION) {
			currentlyExecutingFile = chooser.getSelectedFile();
		}
		if (currentlyExecutingFile.exists()) {
			// CODE TO REMEMBER WHICH DIRECTORY HAS THE pipe FILES
			executableDir = currentlyExecutingFile.getAbsolutePath();
			executableDir = executableDir.replace('\\', '/');
			int lastSlash = executableDir.lastIndexOf('/');
			executableDir = executableDir.substring(0, lastSlash + 1);
			try {
				properties.setProperty("SourceDirectory", sourceDir);
				properties.setProperty("ExecutableDirectory", executableDir);
				properties.store(new FileOutputStream("propertyfile.txt"),
						"File locations");
			} catch (Exception e) {
				System.out.println("Error writing properties file");
			}
		}
		finalLoad_ReloadStep();
		setChanged();
		notifyObservers();
	}

	/**
	 * Final load and reload step.
	 */
	private void finalLoad_ReloadStep() {
		try {
			loader.load(currentlyExecutingFile);
			setRunning(true);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame,
					"The file being selected has problems.\n"
							+ "Cannot load the program", "Warning",
					JOptionPane.OK_OPTION);
		} catch (CodeAccessException e) {
			JOptionPane.showMessageDialog(frame,
					"The was an error trying to access Code.\n"
							+ "Cannot load the program", "Warning",
					JOptionPane.OK_OPTION);
		}

		catch (DataAccessException e) {
			JOptionPane.showMessageDialog(frame,
					"There was an error trying to access Data.\n"
							+ "Cannot load the program", "Warning",
					JOptionPane.OK_OPTION);
		}
		proc.setInstructionPointer(0);
		proc.setAccumulator(0);
		setChanged();
		notifyObservers();
		
	}

	/**
	 * gets the executable
	 * @param i integer 
	 * @return A string
	 * @throws CodeAccessException
	 */
	public String getExecutable(int i) throws CodeAccessException {
		long binary = memory.getCode(i);
		int high = (int) (binary >> 32);
		int arg = (int) (-1 & binary);
		Instruction inst = INSTRUCTION_SET[high];
		return inst.toString() + " " + arg;
	}

	/**
	 * getter for the source
	 * @param i integer
	 * @return the source as a String
	 * @throws CodeAccessException
	 */
	public String getSource(int i) throws CodeAccessException {
		// if (high > 256) this is an indirect instruction, so now the return
		// string is INSTRUCTION_SET[high - 256].toString() followed by arg&
		// otherwise the return is the same as the method above.

		long binary = memory.getCode(i);
		int high = (int) (binary >> 32);
		int arg = (int) (-1 & binary);
		Instruction inst = INSTRUCTION_SET[high];
		String source = inst.toString() + " " + arg;
		if (high > 256) {
			source = INSTRUCTION_SET[high - 256].toString() + " " + arg + "&"; //Might need the &
		}
		return source;

	}

	/**
	 * checks if the assemble file is active
	 * @return boolean
	 */
	public boolean getAssembleFileActive() {
		// Implement later.
		return false;
	}

	/**
	 * getter for load file active
	 * @return boolean
	 */
	public boolean getLoadFileActive() {
		// Implement later.
		return false;
	}

	/**
	 * getter for step active
	 * @return boolean
	 */
	public boolean getStepActive() {
		// Implement later.
		return false;
	}
	
	/**
	 * getter for acc
	 * @return int accumulator
	 */
	public int getAcc() {
		return this.proc.getAccumulator();
	}

	/**
	 * getter for IP
	 * @return int Instruction Pointer
	 */
	public int getIP() {
		return this.proc.getInstructionPointer();
	}

	/**
	 * getter for data
	 * @return int data
	 */
	public int getData(int i) throws DataAccessException {
		return memory.getData(i);
	}

	/**
	 * main method of the machine
	 * @param args
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Machine();
			}
		});
	}
}