package pippin;

public class ANDI extends Instruction {

	public ANDI(Processor cpu, Memory memory) {
		super(cpu, memory);
	}

	@Override
	public void execute(int arg) throws DataAccessException {
		if (cpu.getAccumulator() != 0 && arg != 0) {
			cpu.setAccumulator(1);
		} else {
			cpu.setAccumulator(0);
		}
		cpu.incrementIP();
	}

}