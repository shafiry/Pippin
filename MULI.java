package pippin;

public class MULI extends Instruction {

	public MULI(Processor cpu, Memory memory) {
		super(cpu, memory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isImmediate() {
		return true;
	}

	/**
	 * (multiply immediate) multiply the contents of the accumulator by Z.
	 * Increment the program counter.
	 * 
	 * @param arg
	 *            the Z that the accumulator will be multiplied by.
	 */
	@Override
	public void execute(int arg) throws DataAccessException {
		this.cpu.setAccumulator(this.cpu.getAccumulator() * arg);
		this.cpu.incrementIP();

	}

}
