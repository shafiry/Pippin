package pippin;

public class ADD extends Instruction {

	public ADD(Processor cpu, Memory memory) {
		super(cpu, memory);
		// TODO Auto-generated constructor stub
	}

	/**
	 * (add) add the data value stored at data memory location X to the contents
	 * of the accumulator. Increment the program counter. Increment the program
	 * counter.
	 * 
	 * @param arg the X from where to get an operand for addition.
	 * @throws DataAccessException 
	 */
	@Override
	public void execute(int arg) throws DataAccessException {
		cpu.setAccumulator(cpu.getAccumulator() + memory.getData(arg));
		cpu.incrementIP();
	}

}