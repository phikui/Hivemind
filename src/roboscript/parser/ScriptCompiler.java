package roboscript.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.io.FileUtils;

import roboscript.executer.Executable;
import roboscript.interpreter.exceptions.ParsingException;

public class ScriptCompiler {

	private static final String key = "aHvXDPR3ByT9mMtQW565XtuQ"; // key used
																	// for DES
																	// encryption

	public static void compileAndSave(String source, String target) {
		savetoFile(target, compile(source));
	}

	public static void compileAndSave(String source) {
		String[] splitted = source.split(".");
		compileAndSave(source, splitted[0] + ".rb");
	}

	// uses parser class to generate an Executable class
	public static Executable compile(String source) {
		String str = "";
		File myFile = new File(source);

		try {
			str = FileUtils.readFileToString(myFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Executable x;

		try {
			x = Parser.multiLineParse(str);
			x.setSourceFile(source);
		} catch (ParsingException e) {
			System.err.println("Could not parse " + source);
			System.err.println(e.getMessage());
			System.exit(0);
			return null;
		}
		return x;

	}

	// uses the saved path of source file for saving location. Only use if sure
	// which value this has
	public static boolean savetoFile(Executable x) {

		if (!x.getSourceFile().equals("NONE_DEFINED")) {
			String path = x.getSourceFile().replaceAll(".rs", "");
			path = path + ".rb";
			if (savetoFile(path, x)) {
				return true;
			} else {
				return false;
			}
		} else {
			System.err.println("No file name");
		}
		return false;
	}

	// save Executable to the given location, and Encrypt it with DES for
	// security;
	public static boolean savetoFile(String path, Executable x) {
		String tmp = path + ".tmp";
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(tmp);
			ObjectOutputStream out;
			out = new ObjectOutputStream(fileOut);
			out.writeObject(x);
			out.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			new File(tmp).delete();
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			new File(tmp).delete();
			e.printStackTrace();
			return false;
		}

		DESCipher.encrypt_file(key, tmp, path);
		new File(tmp).delete();
		return true;
	}

	// read from file and decrypt with DES
	public static Executable readFromFile(String path) {
		// TODO check validity of Object
		String tmp = path + ".tmp";
		DESCipher.decrypt_file(key, path, tmp);
		try {
			FileInputStream fileIn = new FileInputStream(tmp);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Executable x = (Executable) in.readObject();
			in.close();
			fileIn.close();
			new File(tmp).delete();
			return x;
		} catch (Exception e) {
			new File(tmp).delete();
			//e.printStackTrace();
			System.err.println("Incorrect File");
			return null;
		}

	}
}
