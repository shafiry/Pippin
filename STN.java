package pippin;


public class STN extends Instruction{

	public STN(Processor cpu, Memory memory) {
		super(cpu, memory);
	}

	@Override
	public void execute(int arg) throws DataAccessException {
		    memory.setData(memory.getData(arg), cpu.getAccumulator());
		    cpu.incrementIP();
	}
	
}