package pippin;

public class MUL extends Instruction {

	public MUL(Processor cpu, Memory memory) {
		super(cpu, memory);
		// TODO Auto-generated constructor stub
	}

	/**
	 * (multiply) multiply the contents of the accumulator by the data value
	 * stored at data memory location X. Increment the program counter.
	 * 
	 * @param arg the location of the operand to be used in the multiplication.
	 * @throws DataAccessException 
	 */
	@Override
	public void execute(int arg) throws DataAccessException {
		
		cpu.setAccumulator(cpu.getAccumulator() * memory.getData(arg));
		cpu.incrementIP();
	}

}
