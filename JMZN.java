package pippin;


public class JMZN extends Instruction {

	public JMZN(Processor cpu, Memory memory) {
		super(cpu, memory);
	}

	@Override
	public void execute(int arg) throws DataAccessException {
		if (cpu.getAccumulator() == 0) {
		    cpu.setInstructionPointer(memory.getData(arg));
		} else {
			cpu.incrementIP();
		}
	}
}