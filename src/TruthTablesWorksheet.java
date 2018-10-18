import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class TruthTablesWorksheet {

	public static void main(String[] args) throws ScriptException, FileNotFoundException {
		PrintStream out = new PrintStream(new FileOutputStream("out.txt", false));
		Scanner in = new Scanner(System.in);
		for (int i = 1; i <= 8; i++) {
			out.printf("Problem %d:%n", i);
			System.err.printf("Problem %d - ", i);
			twoVariable(in, out);
			out.println();
		}
		for (int i = 9; i <= 18; i++) {
			out.printf("Problem %d:%n", i);
			System.err.printf("Problem %d - ", i);
			threeVariable(in, out);
			out.println();
		
		}
		System.err.println("Done!");
	}

	public static void twoVariable(Scanner in, PrintStream out) throws ScriptException {
		System.err.print("How many columns: ");
		int n = in.nextInt();
		in.nextLine();
		String[] columns = new String[n];
		int l = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			System.err.printf("Column %d: ", i+1);
			columns[i] = in.nextLine();
			l = Math.max(l, columns[i].length());
		}
		StringBuilder header = new StringBuilder(String.format("| %s | %s ", "A", "B"));
		for (int i = 0; i < n; i++)
			header.append(String.format("| %s ", columns[i]));
		header.append('|');
		for(int i = 0; i < header.length(); i++)
			out.print('=');
		out.println();
		out.println(header);
		for(int i = 0; i < header.length(); i++)
			out.print('=');
		out.println();
		//EVAL CODE STARTS HERE
		ScriptEngine js = new ScriptEngineManager().getEngineByName("js");
		for (int i = 0; i <= 1; i++) {
			boolean a = bool(i);
			for (int j = 0; j <= 1; j++) {
				boolean b = bool(j);
				out.printf("| %s | %s ", str(a), str(b));
				for(int col = 0; col < n; col++) {
					Bindings bind = js.createBindings();
					bind.putAll(Map.of("A", a, "B", b));
					boolean x = (boolean) js.eval(columns[col], bind);
					out.printf("| %-"+columns[col].length()+"s ", str(x));
				}
				out.println("|");
			}
		}
		for(int i = 0; i < header.length(); i++)
			out.print('-');
		out.println();
	}
	
	public static void threeVariable(Scanner in, PrintStream out) throws ScriptException {
		System.err.print("How many columns: ");
		int n = in.nextInt();
		in.nextLine();
		String[] columns = new String[n];
		int l = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			System.err.printf("Column %d: ", i+1);
			columns[i] = in.nextLine();
			l = Math.max(l, columns[i].length());
		}
		StringBuilder header = new StringBuilder(String.format("| %s | %s | %s ", "A", "B", "C"));
		for (int i = 0; i < n; i++)
			header.append(String.format("| %s ", columns[i]));
		header.append('|');
		for(int i = 0; i < header.length(); i++)
			out.print('=');
		out.println();
		out.println(header);
		for(int i = 0; i < header.length(); i++)
			out.print('=');
		out.println();
		//EVAL CODE STARTS HERE
		ScriptEngine js = new ScriptEngineManager().getEngineByName("js");
		for (int i = 0; i <= 1; i++) {
			boolean a = bool(i);
			for (int j = 0; j <= 1; j++) {
				boolean b = bool(j);
				for(int k = 0; k <= 1; k++) {
					boolean c = bool(k);
					out.printf("| %s | %s | %s ", str(a), str(b), str(c));
					for(int col = 0; col < n; col++) {
						Bindings bind = js.createBindings();
						bind.putAll(Map.of("A", a, "B", b, "C", c));
						boolean x = (boolean) js.eval(columns[col], bind);
						out.printf("| %-"+columns[col].length()+"s ", str(x));
					}
					out.println("|");
				}
			}
		}
		for(int i = 0; i < header.length(); i++)
			out.print('-');
		out.println();
	}

	private static char str(boolean x) {
		return x ? 'T' : 'F';
	}

	private static boolean bool(int i) {
		if( i != 0 && i != 1)
			throw new RuntimeException("Error between keyboard and chair");
		return i == 0;
	}

}
