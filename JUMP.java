package pippin;

/**
 * Designed for Assignment 6
 * 
 * @author Shane Thompson aka sthomp10
 * 
 */
public class JUMP extends Instruction {

	public JUMP(Processor cpu, Memory memory) {
		super(cpu, memory);
		// TODO Auto-generated constructor stub
	}

	/**
	 * (jump) put X in the program counter, causing the instruction at location
	 * X to be the next instruction executed
	 * 
	 * @param arg
	 *            the X to gently slide into the program counter.
	 * 
	 */
	@Override
	public void execute(int arg) throws DataAccessException {
		cpu.setInstructionPointer(arg);
	}

}
