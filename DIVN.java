package pippin;

public class DIVN extends DIV {

	public DIVN(Processor cpu, Memory memory) {
		super(cpu, memory);
		// TODO Auto-generated constructor stub
	}

	/**
	 * (indirect divide) use the data value stored at data memory location X as
	 * the location of the data value for integer division of the accumulator
	 * divided by the data value. Increment the program counter.
	 * 
	 * @param arg
	 *            the memory location containing the memory location of the
	 *            denominator to be used in the division operation.
	 * @throws DataAccessException 
	 */
	@Override
	public void execute(int arg) throws DataAccessException {
		super.execute(memory.getData(arg));
	}

}
