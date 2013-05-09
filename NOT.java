package pippin;

public class NOT extends Instruction {

	public NOT(Processor cpu, Memory memory) {
		super(cpu, memory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean requiresArgument() {
		return false;
	}

	/**
	 * (not) if the accumulator contains 0, put 1 into the accumulator;
	 * otherwise put 0 into the accumulator. Increment the program counter.
	 * 
	 * @param arg
	 *            provided solely so that we can override the Instruction
	 *            class's execute method; it does nothing.
	 */
	@Override
	public void execute(int arg) throws DataAccessException {
		if (this.cpu.getAccumulator() == 0) {
			this.cpu.setAccumulator(1);
		}

		else {
			this.cpu.setAccumulator(0);
		}

		this.cpu.incrementIP();

	}

}
