package pippin;

public class ADDN extends ADD {

	public ADDN(Processor cpu, Memory memory) {
		super(cpu, memory);
		// TODO Auto-generated constructor stub
	}

	/**
	 * (indirect add) use the data value stored at data memory location X as the
	 * location of the data value to be added to the contents of the
	 * accumulator. Increment the program counter.
	 * 
	 * @param arg
	 *            the memory location of the memory location that contains the
	 *            operand used for addition.
	 * @throws DataAccessException 
	 */
	@Override
	public void execute(int arg) throws DataAccessException {
		super.execute(memory.getData(arg));
	}

}
