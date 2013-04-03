package pippin;

public class DIVI extends Instruction {

		public DIVI(Processor cpu, Memory memory) {
			super(cpu, memory);
		}
	
		@Override
		public void execute(int arg) throws DataAccessException {
		    cpu.setAccumulator(cpu.getAccumulator()/arg);
		    cpu.incrementIP();
		}
		
}
