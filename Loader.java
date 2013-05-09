package pippin;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Loader {
	private Memory memory;

	public Loader(Memory memory) {
		this.memory = memory;
	}

	public void load(File file) throws IOException, CodeAccessException,
			DataAccessException {
		Scanner input = new Scanner(file);
		try {
			boolean incode = true;
			int count = 0;
			while (input.hasNextLong()) {
				long ln = input.nextLong();
				if (ln == -1) {
					incode = false;
				} else {
					if (incode) {
						memory.setCode(count++, ln);
					} else {
						int addr = ((int) (ln >> 32));
						int val = ((int) (-1 & ln));
						memory.setData(addr, val);
					}
				}
			}
		}

		finally {
			input.close();
		}
	}
}
