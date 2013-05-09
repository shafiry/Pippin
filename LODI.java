package pippin;

/**
 * Designed for Assignment 6
 * 
 * @author Shane Thompson aka sthomp10
 * 
 */
public class LODI extends Instruction {

	public LODI(Processor cpu, Memory memory) {
		super(cpu, memory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isImmediate() {
		return true;
	}

	/**
	 * put Z into the accumulator. Increment the program counter.
	 * 
	 * @param arg
	 *            the Z to be shoved directly into the accumulator.
	 */
	@Override
	public void execute(int arg) {
		cpu.setAccumulator(arg);
		cpu.incrementIP();
	}

}
