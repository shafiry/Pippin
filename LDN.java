package pippin;
public class LDN extends Instruction{

	public LDN(Processor cpu, Memory memory) {
		super(cpu, memory);
	}

	@Override
	public void execute(int arg) throws DataAccessException {
		    cpu.setAccumulator(memory.getData(memory.getData(arg)));
		    cpu.incrementIP();
	}
	
}