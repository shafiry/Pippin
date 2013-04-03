package pippin;
public class LODI extends Instruction{

	public LODI(Processor cpu, Memory memory) {
		super(cpu, memory);
	}

	@Override
	public void execute(int arg) throws DataAccessException {
		    cpu.setAccumulator(arg);
		    cpu.incrementIP();
	}
	
}