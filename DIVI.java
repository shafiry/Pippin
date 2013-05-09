package pippin;

public class DIVI extends Instruction {

	public DIVI(Processor cpu, Memory memory) {
		super(cpu, memory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isImmediate() {
		return true;
	}

	/**
	 * (divide immediate) do integer division on the accumulator by dividing by
	 * Z. Increment the program counter.
	 * 
	 * @param arg the Z that we will divide the accumulator by.
	 */
	@Override
	public void execute(int arg) throws DataAccessException {
		this.cpu.setAccumulator(this.cpu.getAccumulator() / arg);
		this.cpu.incrementIP();

	}

}
