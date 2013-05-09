package pippin;

/**
 * Designed for Assignment 6
 * @author Shane Thompson aka sthomp10
 *
 */
public class LOD extends Instruction {

	public LOD(Processor cpu, Memory memory) {
		super(cpu, memory);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * (load) put the data value stored at data memory location X into the accumulator. Increment the program counter.
	 * @param arg the location of the value to be shoved into the accumulator.
	 * @throws DataAccessException 
	 */
	@Override
	public void execute(int arg) throws DataAccessException {
		cpu.setAccumulator(memory.getData(arg));
		cpu.incrementIP();
	}

}
