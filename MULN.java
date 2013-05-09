package pippin;

public class MULN extends MUL {

	public MULN(Processor cpu, Memory memory) {
		super(cpu, memory);
		// TODO Auto-generated constructor stub
	}

	/**
	 * (indirect multiply) use the data value stored at data memory location X
	 * as the location of the data value that will multiply the contents of the
	 * accumulator. Increment the program counter.
	 * 
	 * @param arg
	 *            the memory location in which lies the memory location of the
	 *            value to use as an operand in multiplication.
	 * @throws DataAccessException 
	 */
	@Override
	public void execute(int arg) throws DataAccessException {
		super.execute(memory.getData(arg));
	}

}
