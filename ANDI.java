package pippin;

public class ANDI extends Instruction {

	public ANDI(Processor cpu, Memory memory) {
		super(cpu, memory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isImmediate() {
		return true;
	}

	/**
	 * (and immediate) if the contents of the accumulator and Z are both
	 * non-zero, put 1 into the accumulator; otherwise, put 0 into the
	 * accumulator. Increment the program counter.
	 * 
	 * @param arg the Z to be checked for a non-zero identity.
	 */
	@Override
	public void execute(int arg) throws DataAccessException {
		if(arg != 0 && this.cpu.getAccumulator() != 0) {
			this.cpu.setAccumulator(1);
		}
		else {
			this.cpu.setAccumulator(0);
		}
		this.cpu.incrementIP();

	}

}
