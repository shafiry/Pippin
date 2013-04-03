package pippin;

public class DIVN extends DIV {

		public DIVN(Processor cpu, Memory memory) {
			super(cpu, memory);
		}
	
		@Override
		public void execute(int arg) throws DataAccessException {
			super.execute(memory.getData(arg));
		}
		
}