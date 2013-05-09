package pippin;

public class AND extends Instruction {

	public AND(Processor cpu, Memory memory) {
		super(cpu, memory);
		// TODO Auto-generated constructor stub
	}

	/**
	 * (and) if the contents of the accumulator and the data value stored at
	 * data memory location X are both non-zero, put 1 into the accumulator;
	 * otherwise, put 0 into the accumulator. Increment the program counter.
	 * 
	 * @param arg
	 *            the X that is the memory location of the data value to be
	 *            checked for non-zero identity.
	 */
	@Override
	public void execute(int arg) throws DataAccessException {
		if (this.memory.getData(arg) != 0 && this.cpu.getAccumulator() != 0) {
			this.cpu.setAccumulator(1);
		} else {
			this.cpu.setAccumulator(0);
		}

		this.cpu.incrementIP();

	}

}
