package pippin;

public class CMPL extends Instruction {

	public CMPL(Processor cpu, Memory memory) {
		super(cpu, memory);
		// TODO Auto-generated constructor stub
	}

	/**
	 * (compare less) if the data value stored at data memory location X is less
	 * than 0, put 1 into the accumulator; otherwise put 0 into the accumulator.
	 * Increment the program counter.
	 * 
	 * @param arg
	 *            the memory address of the location, X, that contains the value
	 *            to be checked for a negative identity.
	 */
	@Override
	public void execute(int arg) throws DataAccessException {
		if (this.memory.getData(arg) < 0) {
			this.cpu.setAccumulator(1);
		}
		
		else {
			this.cpu.setAccumulator(0);
		}
		
		this.cpu.incrementIP();

	}

}
