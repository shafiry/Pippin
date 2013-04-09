package pippin;

import java.util.HashMap;
import java.util.Map;

public class Tester {
	static Processor cpu = new Processor();
	static Memory memory = new Memory();

	public static Map<Integer, Instruction> decoder = new HashMap<Integer, Instruction>();
	static {
		decoder.put(0, new NOP(cpu, memory));
		decoder.put(1, new LOD(cpu, memory));
		decoder.put(2, new LODI(cpu, memory));
		decoder.put(3, new STO(cpu, memory));
		decoder.put(4, new ADD(cpu, memory));
		decoder.put(5, new SUB(cpu, memory));
		decoder.put(6, new MUL(cpu, memory));
		decoder.put(7, new DIV(cpu, memory));
		decoder.put(8, new ADDI(cpu, memory));
		decoder.put(9, new SUBI(cpu, memory));
		decoder.put(10, new MULI(cpu, memory));
		decoder.put(11, new DIVI(cpu, memory));
		decoder.put(16, new AND(cpu, memory));
		decoder.put(17, new ANDI(cpu, memory));
		decoder.put(18, new NOT(cpu, memory));
		decoder.put(19, new CMPZ(cpu, memory));
		decoder.put(20, new CMPL(cpu, memory));
		decoder.put(26, new JUMP(cpu, memory));
		decoder.put(27, new JMPZ(cpu, memory));
		decoder.put(31, new HALT(cpu, memory));
		decoder.put(257, new LDN(cpu, memory));
		decoder.put(259, new STN(cpu, memory));
		decoder.put(260, new ADDN(cpu, memory));
		decoder.put(261, new SUBN(cpu, memory));
		decoder.put(262, new MULN(cpu, memory));
		decoder.put(263, new DIVN(cpu, memory));
		decoder.put(282, new JMPN(cpu, memory));
		decoder.put(283, new JMZN(cpu, memory));

	}

	public static void main(String[] args) throws DataAccessException, CodeAccessException {
		for (int i = 0; i < Memory.DATA_SIZE; i++) {
			memory.setData(i, 5 * i);
		}

		for (int i = 0; i < 4; i++) {
			memory.setCode(i, (((long) 4 + i) << 32) + i + 20);
		}

		for (int i = 4; i < 8; i++) {
			memory.setCode(i, (((long) 256 + i) << 32) + i + 20);
		}

		for (int i = 8; i < 12; i++) {
			memory.setCode(i, (((long) i) << 32) + i + 20);
		}
		
		for (int i = 12; i < 16; i++) {
			memory.setCode(i, (((long) i+4) << 32) + i + 20);
		}

		System.out.println(cpu.getAccumulator());

		while (cpu.getInstructionPointer() < 8) {
			long instr = memory.getCode(cpu.getInstructionPointer());
			int op = (int) (instr >> 32);
			int arg = (int) (instr - ((long) op << 32));
			System.out.println(decoder.get(op).getClass().getSimpleName() + ""
					+ arg);
			decoder.get(op).execute(arg);
			System.out.println(cpu.getAccumulator());
		}

	}
}