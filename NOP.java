package pippin;


public class NOP extends Instruction {

	public NOP(Processor cpu, Memory memory) {
		super(cpu, memory);
	}

	@Override
	public void execute(int arg) throws DataAccessException {
		cpu.incrementIP();
	}
	
	@Override
	public boolean requiresArgument() { 
		return false; // ONLY override this method for the classes that 
					 // return false 
	} 
}