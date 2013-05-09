package pippin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * Assembler allows encoding pippin assembly instructions contained within a
 * text file.
 * 
 * Created for assignment 7.
 * 
 * @author Shane Thompson aka sthomp10
 * 
 */

public class Assembler {
	public static Map<String, Integer> opcodes = new HashMap<String, Integer>();
	public static Map<String, Boolean> requiresArgument = new HashMap<String, Boolean>();
	public static Map<String, String> indirectCode = new HashMap<String, String>();

	static {
		opcodes.put("LOD", 1);
		requiresArgument.put("LOD", true);
		opcodes.put("LDN", 256 + 1);
		requiresArgument.put("LDN", true);
		indirectCode.put("LOD", "LDN");
		opcodes.put("LODI", 2);
		requiresArgument.put("LODI", true);
		opcodes.put("STO", 3);
		requiresArgument.put("STO", true);
		opcodes.put("STN", 259);
		requiresArgument.put("STN", true);
		indirectCode.put("STO", "STN");
		opcodes.put("ADD", 4);
		requiresArgument.put("ADD", true);
		opcodes.put("ADDN", 256 + 4);
		requiresArgument.put("ADDN", true);
		indirectCode.put("ADD", "ADDN");
		opcodes.put("ADDI", 8);
		requiresArgument.put("ADDI", true);
		opcodes.put("SUB", 5);
		requiresArgument.put("SUB", true);
		opcodes.put("SUBN", 261);
		requiresArgument.put("SUBN", true);
		indirectCode.put("SUB", "SUBN");
		opcodes.put("SUBI", 9);
		requiresArgument.put("SUBI", true);
		opcodes.put("MUL", 6);
		requiresArgument.put("MUL", true);
		opcodes.put("MULN", 262);
		requiresArgument.put("MULN", true);
		indirectCode.put("MUL", "MULN");
		opcodes.put("MULI", 10);
		requiresArgument.put("MULI", true);
		opcodes.put("DIV", 7);
		requiresArgument.put("DIV", true);
		opcodes.put("DIVN", 263);
		requiresArgument.put("DIVN", true);
		indirectCode.put("DIV", "DIVN");
		opcodes.put("DIVI", 11);
		requiresArgument.put("DIVI", true);
		opcodes.put("AND", 16);
		requiresArgument.put("AND", true);
		opcodes.put("ANDI", 17);
		requiresArgument.put("ANDI", true);
		opcodes.put("NOT", 18);
		requiresArgument.put("NOT", false);
		// there is not indirect opcode for NOT
		opcodes.put("CMPZ", 19);
		requiresArgument.put("CMPZ", true);
		opcodes.put("CMPL", 20);
		requiresArgument.put("CMPL", true);
		opcodes.put("NOP", 0);
		requiresArgument.put("NOP", false);
		opcodes.put("HALT", 31);
		requiresArgument.put("HALT", false);
		opcodes.put("JUMP", 26);
		requiresArgument.put("JUMP", true);
		opcodes.put("JMPN", 282);
		requiresArgument.put("JMPN", true);
		indirectCode.put("JUMP", "JMPN");
		opcodes.put("JMPZ", 27);
		requiresArgument.put("JMPZ", true);
		opcodes.put("JMZN", 283);
		requiresArgument.put("JMZN", true);
		indirectCode.put("JMPZ", "JMZN");
	}

	public static boolean assembleFile(File inFile, File outFile) {
		boolean goodProgram = true;
		try {
			Scanner in = new Scanner(inFile);
			PrintWriter output = new PrintWriter(outFile);
			
			boolean inCode = true; // keep track that we are in the code
			int lineCounter = 0;
			while (in.hasNextLine() && goodProgram) {
				String line = in.nextLine();
				// increment lineCounter
				lineCounter++;
				if (line.trim().length() > 0) {
					if (line.charAt(0) == ' ' || line.charAt(0) == '\t') {
						goodProgram = false;
						JOptionPane.showMessageDialog(null,
								"Line starts with blank space: " + line,
								"Error on Line " + lineCounter,
								JOptionPane.WARNING_MESSAGE);
					} else if (line.trim().equals("--DATA--")) {
						inCode = false;
						output.println(-1);
					} else {
						String[] parts = line.trim().split("\\s+");
						if (inCode) {
							if (opcodes.containsKey(parts[0])) {
								if (requiresArgument.get(parts[0])) {
									if (parts.length > 2) {
										goodProgram = false;
										JOptionPane.showMessageDialog(null,
												"Too many arguments",
												"Error on Line " + lineCounter,
												JOptionPane.WARNING_MESSAGE);
									}
									else if (parts.length == 1) {
										goodProgram = false;
										JOptionPane.showMessageDialog(null,
												"Missing arguments",
												"Error on Line " + lineCounter,
												JOptionPane.WARNING_MESSAGE);
									}
									else if(parts[1].charAt(parts[1].length() - 1) == '&')  {
										parts[0] = indirectCode.get(parts[0]);
										parts[1] = parts[1].substring(0, parts[1].length() - 1);
									}
									try {
										int op = opcodes.get(parts[0]);
										int arg = Integer
												.parseInt(parts[1], 16);
										output.println(((long) op << 32) + arg);

									} catch (NumberFormatException e) {
										goodProgram = false;
										JOptionPane.showMessageDialog(null,
												"Argument is not an int",
												"Error on Line " + lineCounter,
												JOptionPane.WARNING_MESSAGE);
									}
								} 
								else {
									if(parts.length > 1) {
										goodProgram = false;
										JOptionPane.showMessageDialog(null,
												"Argument is not required",
												"Error on Line " + lineCounter,
												JOptionPane.WARNING_MESSAGE);
									}
									else {
									int op = opcodes.get(parts[0]);
									output.println((long) op << 32);
									}
								}
							} else {
								goodProgram = false;
								JOptionPane.showMessageDialog(null,
										"Unrecognized opcode",
										"Error on Line " + lineCounter,
										JOptionPane.WARNING_MESSAGE);
								
							}
								
						}
						else {
							if (parts.length != 2) {
								goodProgram = false;
								JOptionPane.showMessageDialog(null,
										"Data is not an address/value pair",
										"Error on Line " + lineCounter,
										JOptionPane.WARNING_MESSAGE);
							}
							else {
								try {
									int addr = Integer.parseInt(parts[0], 16);
									int arg = Integer.parseInt(parts[1], 16);
									output.println(((long) addr << 32) + arg);
								} catch (NumberFormatException e) {
									goodProgram = false;
									JOptionPane.showMessageDialog(null,
											"Non-integer address or argument",
											"Error on Line " + lineCounter,
											JOptionPane.WARNING_MESSAGE);
								}
							}
						}
					}
				}

			}
			output.close();
			in.close(); // this is the scanner used to open inFile
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Unable to open the necessary files", "File Error",
					JOptionPane.WARNING_MESSAGE);
		}
	return goodProgram;}

}
