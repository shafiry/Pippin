package pippin;

public class HALT extends Instruction {

	public HALT(Processor cpu, Memory memory) {
		super(cpu, memory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean requiresArgument() {
		return false;
	}
	/**
	 * Halts program execution.
	 * 
	 * @param arg
	 *            supplied solely to allow successful implementation of
	 *            Instruction; it does nothing.
	 */
	@Override
	public void execute(int arg) throws DataAccessException {
		// TODO Auto-generated method stub

	}

}
