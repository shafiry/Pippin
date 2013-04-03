package pippin;

public class JMPZ extends Instruction {

	public JMPZ(Processor cpu, Memory memory) {
		super(cpu, memory);
	}

	@Override
	public void execute(int arg) throws DataAccessException {
		if (cpu.getAccumulator() == 0) {
			cpu.setInstructionPointer(arg);
		} else {
			cpu.incrementIP();
		}
	}
}