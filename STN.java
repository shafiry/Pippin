package pippin;

/**
 * Designed for Assignment 6
 * 
 * @author Shane Thompson aka sthomp10
 * 
 */
public class STN extends STO {

	public STN(Processor cpu, Memory memory) {
		super(cpu, memory);
		// TODO Auto-generated constructor stub
	}

	/**
	 * (indirect store) use the data value stored at data memory location X as
	 * the location of where to store the contents of the accumulator. Increment
	 * the program counter.
	 * 
	 * @param arg
	 *            the aforemention X from which we will harvest the location of
	 *            the locator that, in turn, we will use to find the accumulator
	 *            content shoving location
	 * @throws DataAccessException
	 */
	@Override
	public void execute(int arg) throws DataAccessException {
		super.execute(memory.getData(arg));
	}
}
