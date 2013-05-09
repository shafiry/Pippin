package pippin;

public class SUBN extends SUB {

	public SUBN(Processor cpu, Memory memory) {
		super(cpu, memory);
		// TODO Auto-generated constructor stub
	}

	/**
	 * (indirect subtract) use the data value stored at data memory location X
	 * as the location of the data value to be subtracted from the contents of
	 * the accumulator. Increment the program counter.
	 * 
	 * @param arg
	 *            the memory location that has the memory location of the value
	 *            to be subtracted from the accumulator.
	 * @throws DataAccessException 
	 */
	@Override
	public void execute(int arg) throws DataAccessException {
		super.execute(memory.getData(arg));
	}

}