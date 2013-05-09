package pippin;

/**
 * Designed for Assignment 6
 * 
 * @author Shane Thompson aka sthomp10
 * 
 */
public class JMZN extends JMPZ {

	public JMZN(Processor cpu, Memory memory) {
		super(cpu, memory);
		// TODO Auto-generated constructor stub
	}

	/**
	 * (indirect jump on zero) if the accumulator contains 0, use the data value
	 * stored at data memory location X as the location to be copied to the
	 * program counter; otherwise increment the program counter
	 * 
	 * @param arg
	 *            the X to use to locate the value that might be gently placed
	 *            into the program counter.
	 * @throws DataAccessException
	 */
	@Override
	public void execute(int arg) throws DataAccessException {
		super.execute(memory.getData(arg));
	}

}
