package pippin;

/**
 * Designed for Assignment 6
 * 
 * @author Shane Thompson aka sthomp10
 * 
 */
public class JMPN extends JUMP {

	public JMPN(Processor cpu, Memory memory) {
		super(cpu, memory);
		// TODO Auto-generated constructor stub
	}

	/**
	 * (indirect jump) use the data value stored at data memory location X as
	 * the value to be copied to the program counter
	 * 
	 * @param arg
	 *            the X used to locate the data value to toss into the program
	 *            counter.
	 * @throws DataAccessException 
	 */
	@Override
	public void execute(int arg) throws DataAccessException {
		super.execute(memory.getData(arg));
	}
}
