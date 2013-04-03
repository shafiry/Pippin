package pippin;

public class ADDI extends Instruction {

		public ADDI(Processor cpu, Memory memory) {
			super(cpu, memory);
		}
	
		@Override
		public void execute(int arg) throws DataAccessException {
		    cpu.setAccumulator(cpu.getAccumulator()+arg);
		    cpu.incrementIP();
		}
		
}
