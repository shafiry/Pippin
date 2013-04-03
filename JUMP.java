package pippin;
public class JUMP extends Instruction{

	public JUMP(Processor cpu, Memory memory) {
		super(cpu, memory);
	}

	@Override
	public void execute(int arg) throws DataAccessException {
		    cpu.setInstructionPointer(arg);
	}
	
}