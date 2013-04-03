package pippin;
public class JMPN extends Instruction{

	public JMPN(Processor cpu, Memory memory) {
		super(cpu, memory);
	}

	@Override
	public void execute(int arg) throws DataAccessException {
		    cpu.setInstructionPointer(memory.getData(arg));
	}
}