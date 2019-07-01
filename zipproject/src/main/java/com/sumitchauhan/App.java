package com.sumitchauhan;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import com.sumitchauhan.Settings;
import com.sumitchauhan.FileDetails;

public class App {
	public static final String PATH_SEPARATOR = String.valueOf(File.separatorChar);

	static Map<String, List<FileDetails>> categorymap = new HashMap<>();

	public static void main(String[] args) throws IOException {
		initProps();

		// Check all zip files in myinput
		List<File> allZipInputs = readInputZipFiles();
		
		// create map with key as category and value as list of file info objects
		if(allZipInputs.size()<11) {
		for (File zipFile : allZipInputs) {

			processZipFile(zipFile);
			//System.out.println(zipFile.getAbsolutePath());

		
		}
		//System.out.println(categorymap);
		// Extract files / Get file names from each zip file
		// For each file extract category and create info object with details
		createCategorywisezip();
		;
		}	
	}

	private static void createCategorywisezip() throws IOException {
		// TODO Auto-generated method stub
		for (Entry<String, List<FileDetails>> e : categorymap.entrySet()) {
			// create zip here
			String category = e.getKey();
			ZipOutputStream zos = new ZipOutputStream(
					new FileOutputStream(Settings.getOutputDir() + PATH_SEPARATOR +"Cat_"+ category + ".zip"));

			List<String> csvcontent=new ArrayList<String>();
			csvcontent.add("Filename"+" "+"SourceFileName" +"\n");
			for (FileDetails fileDetails : e.getValue())
			{
				String fileName = fileDetails.getFilename();
				String sourceFileName = fileDetails.getSourcefilename();

				ZipFile sourceFile = new ZipFile(sourceFileName);
				ZipEntry zipEntry = sourceFile.getEntry(fileName);

				InputStream is = sourceFile.getInputStream(zipEntry);

				zos.putNextEntry(zipEntry);
				int data ;
				while ((data = is.read())!= -1) {
					zos.write(data);
				}
				// Creating mapping file
				System.out.println(fileName+" "+sourceFileName);
				csvcontent.add(fileName+","+sourceFileName+"\n");							
				zos.closeEntry();
			}
			
			ZipEntry entry = new ZipEntry("Mapping.csv");
			zos.putNextEntry(entry);
			
			byte[] data=csvcontent.toString().getBytes();
			
			zos.write(data);
			zos.closeEntry();
			
			zos.close();
		}


	}

	

	private static List<File> readInputZipFiles() {
		File inputfolder = new File(Settings.getInputDir());
		String[] listofzipfiles = inputfolder.list();

		List<File> f1 = new ArrayList<>();
		for (int i = 0; i < listofzipfiles.length; i++) {
			f1.add(new File(Settings.getInputDir() + PATH_SEPARATOR + listofzipfiles[i]));
		}
		return f1;
	}

	private static void processZipFile(File zipfile) throws IOException {
		// TODO Auto-generated method stub

		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(zipfile));
		final ZipInputStream is = new ZipInputStream(bis);
		try {
			ZipEntry entry;
			while ((entry = is.getNextEntry()) != null) {
				System.out.printf("Filename:", entry.getName());
				String filename = entry.getName();
				String category = getcategoryfromname(filename);
				String source = zipfile.toString();
				
//checking if category is not null
				if(category!=null) {
				if (!categorymap.containsKey(category)) {
					categorymap.put(category, new ArrayList<FileDetails>());

				} // extractEntry(entry, is);
				categorymap.get(category).add(new FileDetails(filename, category, source));
				}

			}
//    	//System.out.printf("Zip file %s extracted successfully in %s", FILE_NAME, OUTPUT_DIR); 
		} finally {
			is.close();
		}

	}

	public static String getcategoryfromname(String filename) {
		// TODO Auto-generated method stub

		String[] temp =filename.split("-");
		
		if (temp.length==3){
		String temp1 = filename.split("-")[2];

		return temp1.substring(0, temp1.indexOf("."));}
		
		return null;
	}

	private static void initProps() {
		try {
			InputStream is = new FileInputStream("config.properties");
			Properties properties = new Properties();
			properties.load(is);

			String inputPathInProps = properties.getProperty("app.path.input");
			if (inputPathInProps != null) {
				Settings.setInputDir(inputPathInProps);
			}

			String outputPathInProps = properties.getProperty("app.path.output");
			if (outputPathInProps != null) {
				Settings.setOutputDir(outputPathInProps);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
