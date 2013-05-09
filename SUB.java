package pippin;

public class SUB extends Instruction {

	public SUB(Processor cpu, Memory memory) {
		super(cpu, memory);
		// TODO Auto-generated constructor stub
	}

	/**
	 * (subtract) subtract the data value stored at data memory location X from
	 * the contents of the accumulator. Increment the program counter.
	 * 
	 * @param arg the location of the value to subtract from the accumulator.
	 * @throws DataAccessException 
	 */
	@Override
	public void execute(int arg) throws DataAccessException {
		cpu.setAccumulator(cpu.getAccumulator() - memory.getData(arg));
		cpu.incrementIP();
	}

}