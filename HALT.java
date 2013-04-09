package pippin;


public class HALT extends Instruction {

	public HALT(Processor cpu, Memory memory) {
		super(cpu, memory);
	}

	@Override
	public void execute(int arg) throws DataAccessException {

	}
	
	@Override
	public boolean requiresArgument() { 
		return false; // ONLY override this method for the classes that 
					 // return false 
	} 
	
}