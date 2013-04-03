package pippin;
public class ADD extends Instruction{

	public ADD(Processor cpu, Memory memory) {
		super(cpu, memory);
	}

	@Override
	public void execute(int arg) throws DataAccessException {
		    cpu.setAccumulator(cpu.getAccumulator()+memory.getData(arg));
		    cpu.incrementIP();
	}
	
}