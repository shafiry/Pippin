package pippin;

import java.util.Arrays;

public class Memory {
	final int DATA_SIZE = 512;
	final int CODE_SIZE = 256;

	private Integer[] data = new Integer[DATA_SIZE];
	private Long[] code = new Long[CODE_SIZE];

	public int getData(int i) {
		return data[i];

	}

	public long getCode(int i) {
		return code[i];
	}

	public void setData(int i, int value) {
		data[i] = value;
	}

	public void setCode(int i, long instruction) {
		code[i] = instruction;
	}

	Integer[] getData() {
		return data;
	}

	public void clearCode() {
		Arrays.fill(code, 0);
	}

	public void clearData() {
		Arrays.fill(data, 0);
	}

}
