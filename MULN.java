package pippin;

public class MULN extends MUL {

		public MULN(Processor cpu, Memory memory) {
			super(cpu, memory);
		}
	
		@Override
		public void execute(int arg) throws DataAccessException {
			super.execute(memory.getData(arg));
		}
		
}