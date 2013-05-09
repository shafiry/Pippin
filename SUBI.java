package pippin;

public class SUBI extends Instruction {

	public SUBI(Processor cpu, Memory memory) {
		super(cpu, memory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isImmediate() {
		return true;
	}

	/**
	 * (subtract immediate) subtract Z from the contents of the accumulator.
	 * Increment the program counter.
	 * 
	 * @param arg
	 *            the Z integer value the be subtracted from the program
	 *            counter.
	 */
	@Override
	public void execute(int arg) throws DataAccessException {
		this.cpu.setAccumulator(this.cpu.getAccumulator() - arg);
		this.cpu.incrementIP();

	}

}
