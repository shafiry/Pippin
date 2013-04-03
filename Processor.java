package pippin;

public class Processor {
	 private int accumulator;
	 private int instructionPointer;
	
	 public int getAccumulator() {
		return accumulator;
	}
	
	 public void setAccumulator(int accumulator) {
		this.accumulator = accumulator;
	}
	
	public int getInstructionPointer() {
		return instructionPointer;
	}
	
	public void setInstructionPointer(int instructionPointer) {
		this.instructionPointer = instructionPointer;
	}
	
	public void incrementIP() {
		this.instructionPointer++;
	}

}
