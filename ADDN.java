package pippin;

public class ADDN extends ADD {

		public ADDN(Processor cpu, Memory memory) {
			super(cpu, memory);
		}
	
		@Override
		public void execute(int arg) throws DataAccessException {
			super.execute(memory.getData(arg));
		}
		
}
