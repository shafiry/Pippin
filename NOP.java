package pippin;

/**
 * Designed for Assignment 6
 * 
 * @author Shane Thompson aka sthomp10
 * 
 */
public class NOP extends Instruction {

	public NOP(Processor cpu, Memory memory) {
		super(cpu, memory);
		// TODO Auto-generated constructor stub
	}

	// Takes no args so we must override requiresArgument().

	@Override
	public boolean requiresArgument() {
		return false;
	}

	/**
	 * no operation, do nothing except increment the program counter
	 * 
	 * @param arg
	 *            does absolutely nothing and is only there so we can
	 *            successfully override the abstract execute(int arg) method in
	 *            Instruction.
	 */
	@Override
	public void execute(int arg) throws DataAccessException {
		cpu.incrementIP();
	}

}
