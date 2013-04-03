package pippin;

public class MULI extends ADD {

		public MULI(Processor cpu, Memory memory) {
			super(cpu, memory);
		}
	
		@Override
		public void execute(int arg) throws DataAccessException {
		    cpu.setAccumulator(cpu.getAccumulator()*arg);
		    cpu.incrementIP();
		}
		
}