package pippin;

public class HALT extends Instruction {

	public HALT(Processor cpu, Memory memory) {
		super(cpu, memory);
	}

	@Override
	public void execute(int arg) throws DataAccessException {

	}
}