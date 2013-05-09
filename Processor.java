package pippin;

import java.util.Observable;

public class Processor extends Observable {
	private int accumulator;
	private int instructionPointer;
	
	public int getAccumulator() {
		return accumulator;
	}
	public void setAccumulator(int accumulator) {
		this.accumulator = accumulator;
		setChanged();
		notifyObservers();
	}
	public int getInstructionPointer() {
		return instructionPointer;
	}
	public void setInstructionPointer(int instructionPointer) {
		this.instructionPointer = instructionPointer;
		setChanged();
		notifyObservers();
	}
	
	public void incrementIP() {
		instructionPointer++;
	}
}
