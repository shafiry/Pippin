package pippin;

public class ADDI extends Instruction {

	public ADDI(Processor cpu, Memory memory) {
		super(cpu, memory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isImmediate() {
		return true;
	}

	/**
	 * (add immediate) add Z to the contents of the accumulator. Increment the
	 * program counter.
	 * 
	 * @param arg the Z value to be added to the accumulator.
	 */
	@Override
	public void execute(int arg) throws DataAccessException {
		this.cpu.setAccumulator(this.cpu.getAccumulator() + arg);
		this.cpu.incrementIP();

	}

}
