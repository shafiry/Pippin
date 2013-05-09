package pippin;

/**
 * Designed for Assignment 6
 * 
 * @author Shane Thompson aka sthomp10
 * 
 */
public class JMPZ extends JUMP {

	public JMPZ(Processor cpu, Memory memory) {
		super(cpu, memory);
		// TODO Auto-generated constructor stub
	}

	/**
	 * (jump on zero) if the accumulator contains 0, put X in the program
	 * counter, causing the instruction at location X to be the next instruction
	 * executed; otherwise increment the program counter
	 * 
	 * @param arg
	 *            the X that we may or may not slam dunk into the program
	 *            counter.
	 */
	@Override
	public void execute(int arg) throws DataAccessException {
		if (cpu.getAccumulator() == 0) {
			super.execute(arg);
		} else {
			cpu.incrementIP();
		}
	}

}
