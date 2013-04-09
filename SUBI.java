package pippin;


public class SUBI extends Instruction {

		public SUBI(Processor cpu, Memory memory) {
			super(cpu, memory);
		}
	
		@Override
		public void execute(int arg) throws DataAccessException {
		    cpu.setAccumulator(cpu.getAccumulator()-arg);
		    cpu.incrementIP();
		}
		
		@Override
		public boolean isImmediate() {
			    return true;
		}
		
}
