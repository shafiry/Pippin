package pippin;

public class SUBN extends SUB {

		public SUBN(Processor cpu, Memory memory) {
			super(cpu, memory);
		}
	
		@Override
		public void execute(int arg) throws DataAccessException {
			super.execute(memory.getData(arg));
		}
		
}