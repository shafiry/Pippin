package pippin;


public class STO extends Instruction{

	public STO(Processor cpu, Memory memory) {
		super(cpu, memory);
	}

	@Override
	public void execute(int arg) throws DataAccessException {
		    memory.setData(arg, cpu.getAccumulator());
		    cpu.incrementIP();
	}
	
}