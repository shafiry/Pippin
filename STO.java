package pippin;

/**
 * Designed for Assignment 6
 * 
 * @author Shane Thompson aka sthomp10
 * 
 */
public class STO extends Instruction {

	public STO(Processor cpu, Memory memory) {
		super(cpu, memory);
		// TODO Auto-generated constructor stub
	}

	/**
	 * (store) store the contents of the accumulator in data memory location X.
	 * Increment the program counter.
	 * 
	 * @param arg
	 *            the X memory location wherein to slam the contents of the
	 *            accumulator.
	 * @throws DataAccessException 
	 */
	@Override
	public void execute(int arg) throws DataAccessException {
		memory.setData(arg, cpu.getAccumulator());
		cpu.incrementIP();

	}

}
