package pippin;


public class CMPZ extends Instruction {

	public CMPZ(Processor cpu, Memory memory) {
		super(cpu, memory);
	}

	@Override
	public void execute(int arg) throws DataAccessException {
		if (memory.getData(arg) == 0) {
			cpu.setAccumulator(1);
		} else {
			cpu.setAccumulator(0);
		}
		cpu.incrementIP();
	}

}