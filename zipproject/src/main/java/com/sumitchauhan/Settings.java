package com.sumitchauhan;

public class Settings {

	private static String input_dir = "input";
	private static String output_dir = "output";
	private static String work_dir = "workdir";

	public static String getWorkDir() {
		return work_dir;
	}

	public static void setWorkDir(String work_dir) {
		Settings.work_dir = work_dir;
	}

	public static String getInputDir() {
		return input_dir;
	}

	public static void setInputDir(String input_dir) {
		Settings.input_dir = input_dir;
	}

	public static String getOutputDir() {
		return output_dir;
	}

	public static void setOutputDir(String output_dir) {
		Settings.output_dir = output_dir;
	}

}
