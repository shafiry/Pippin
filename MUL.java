package pippin;

public class MUL extends Instruction{

	public MUL(Processor cpu, Memory memory) {
		super(cpu, memory);
	}

	@Override
	public void execute(int arg) throws DataAccessException {
		    cpu.setAccumulator(memory.getData(arg)*cpu.getAccumulator());
		    cpu.incrementIP();
	}
	
}