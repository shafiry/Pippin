package pippin;


public class LOD extends Instruction{

	public LOD(Processor cpu, Memory memory) {
		super(cpu, memory);
	}

	@Override
	public void execute(int arg) throws DataAccessException {
		    cpu.setAccumulator(memory.getData(arg));
		    cpu.incrementIP();
	}
	
}