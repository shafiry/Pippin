package pippin;

public class DIV extends Instruction {

	public DIV(Processor cpu, Memory memory) {
		super(cpu, memory);
		// TODO Auto-generated constructor stub
	}

	/**
	 * (divide) do integer division on the accumulator by dividing by the data
	 * value stored at data memory location X. Increment the program counter.
	 * 
	 * @param arg
	 *            the memory location of the denominator.
	 * @throws DataAccessException 
	 */
	@Override
	public void execute(int arg) throws DataAccessException {
		cpu.setAccumulator(cpu.getAccumulator() / memory.getData(arg));
		cpu.incrementIP();
	}

}
