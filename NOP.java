package pippin;

public class NOP extends Instruction {

	public NOP(Processor cpu, Memory memory) {
		super(cpu, memory);
	}

	@Override
	public void execute(int arg) throws DataAccessException {
		cpu.incrementIP();
	}
}