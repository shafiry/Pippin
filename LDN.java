package pippin;

/**
 * Designed for Assignment 6
 * 
 * @author Shane Thompson aka sthomp10
 * 
 */
public class LDN extends LOD {

	public LDN(Processor cpu, Memory memory) {
		super(cpu, memory);
		// TODO Auto-generated constructor stub
	}

	/**
	 * (indirect load) use the data value stored at data memory location X as
	 * the location of the data and load that data into the accumulator.
	 * Increment the program counter.
	 * 
	 * @param arg
	 *            the X used to locate the locator of the value you wish to
	 *            stuff into the accumulator.
	 * @throws DataAccessException 
	 */
	@Override
	public void execute(int arg) throws DataAccessException {
		super.execute(memory.getData(arg));
	}

}
