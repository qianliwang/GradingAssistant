package model.cs106x.cs.iastate.edu;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class Zipper {
	
	public static void zipHWByFile(String inputFile,String outputFolderPath){
		BufferedReader br;
		String line;
		String columns[];
		String titleColumn[];
		String tempFolderName;
		String tempOutputFolderName;
		
		File toZipFolder = null;
		File destFolder = null;
		
		int deductPoints;
		try {
			br = new BufferedReader(new FileReader(inputFile));
			line = br.readLine();
			titleColumn = line.split(",");
			while (((line = br.readLine()) != null) && (line != "")) {
				
				columns = line.split(",");
				deductPoints = 0;
				for(int i=1;i<columns.length;i++){
					deductPoints = deductPoints + Integer.parseInt(columns[i]);
				}
				if(deductPoints != 0){
					tempFolderName = columns[0];
					tempOutputFolderName = tempFolderName + ".zip";
					zipFolder(tempFolderName+File.separator,tempOutputFolderName);
					toZipFolder = new File(tempOutputFolderName);
					destFolder = new File(outputFolderPath);
					FileUtils.moveFileToDirectory(toZipFolder, destFolder, true);
				}
			}
			br.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}	
	}
	
	public static void unzipHWs(String inputFolderPath,String outputFolderPath){
		
		File folder = new File(inputFolderPath);
		
		File[] listOfFiles = folder.listFiles();
		String fileName = "";
		String tempSection;
		String inputZipFile;
		String outputFolder;
		String tempFolderName = "";
		int tempPointer = 0;
		for (int i = 0; i < listOfFiles.length; i++) {

			if (listOfFiles[i].isFile()) {
				
				fileName = listOfFiles[i].getName();
				System.out.println(fileName);

				inputZipFile =listOfFiles[i].getAbsolutePath();
				tempSection = GlobalFunction.getSection(fileName);
				tempPointer = fileName.lastIndexOf(".");
				tempFolderName = fileName.substring(0, tempPointer);
				outputFolder = outputFolderPath + tempSection + File.separator+ tempFolderName;
				
				unzipSingleFile(inputZipFile,outputFolder);
				organizeFolder(outputFolder);
			}
		}
	}
	

	private static void zipFolder(String inputFolderPath,String outputFilePath){
		
		File inputFolder = new File(inputFolderPath);
		File[] listOfFiles = inputFolder.listFiles();
		
		System.out.println(outputFilePath);
		
		FileOutputStream fos;
		
		byte[] buffer = new byte[1024];
		
		try {
			fos = new FileOutputStream(outputFilePath);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ZipOutputStream zos = new ZipOutputStream(bos);
			
			for (int i = 0; i < listOfFiles.length; i++) {
				
				if(listOfFiles[i].isFile()){
					zos.putNextEntry(new ZipEntry(listOfFiles[i].getName()));
			        
			        FileInputStream in = new FileInputStream(inputFolderPath+File.separator+listOfFiles[i].getName());
		 
		        	int len;
		        	while ((len = in.read(buffer)) > 0) {
		        		zos.write(buffer, 0, len);
		        	}
		 
		        	in.close();
			        zos.closeEntry();
		
				}
				
    	    }
			zos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void unzipSingleFile(String inputZipFile, String outputPath) {
		
		ZipFile zipFile;
		try {
			zipFile = new ZipFile(inputZipFile);
			Enumeration<? extends ZipEntry> entries = zipFile.entries();
		    while (entries.hasMoreElements()) {
		        ZipEntry entry = entries.nextElement();
		        File entryDestination = new File(outputPath+File.separator+ entry.getName());
		        entryDestination.getParentFile().mkdirs();
		        if (entry.isDirectory())
		            entryDestination.mkdirs();
		        else {
		            InputStream in;
		            OutputStream out;
					try {
						in = zipFile.getInputStream(entry);
						out = new FileOutputStream(entryDestination);
						IOUtils.copy(in, out);
			            IOUtils.closeQuietly(in);
			            IOUtils.closeQuietly(out);
					} catch (IOException e) {
						e.printStackTrace();
					}
		        }
		    }
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private static void getFileList(String filePath,ArrayList<File> fileList){ 
		File folder = new File(filePath);

		// String fileName = file.getName();
		File[] files = folder.listFiles();
		for (File file:files) {
			if (!file.getName().startsWith("_") && !file.getName().startsWith(".")) {
				if (!file.isDirectory()) {
					// System.out.println(files[i].getName());
					fileList.add(file);
				} else if (file.isDirectory()) {
					getFileList(file.getAbsolutePath(), fileList);
				}
			}
		}
    } 
	private static void organizeFolder(String inputFolderPath){
		ArrayList<File> fileList = new ArrayList<File>();
		getFileList(inputFolderPath,fileList);
		File outputFolder = new File(inputFolderPath);
		
		try {
			String parentFolder;
			for(File file:fileList){
				parentFolder = file.getParent();
				if(!parentFolder.equals(inputFolderPath)){
					FileUtils.moveFileToDirectory(file, outputFolder, false);
				}	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
/*	
 
	
	private void organizeFolder(String inputFolderPath){
		
		File childFolder = new File(inputFolderPath);
		
		File[] listOfChildFiles = childFolder.listFiles();
		
		File subFolder;
		File destDir = new File(inputFolderPath);
		File[] listofSubfolderFiles;
		
		for(int j = 0; j<listOfChildFiles.length;j++){
			
			if(listOfChildFiles[j].isDirectory()){

				subFolder = listOfChildFiles[j].getAbsoluteFile();
				
				listofSubfolderFiles = subFolder.listFiles();
				
				for(int k=0; k<listofSubfolderFiles.length; k++){
					
					if(listofSubfolderFiles[k].isFile()){
						try {
							FileUtils.moveFileToDirectory(listofSubfolderFiles[k].getAbsoluteFile(), destDir, false);
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}
					
				}
				
			}
			
		}
		
		for(int j = 0; j<listOfChildFiles.length;j++){
			
			if(listOfChildFiles[j].isDirectory()){
				if(listOfChildFiles[j].getAbsoluteFile().listFiles().length == 0){
					listOfChildFiles[j].getAbsoluteFile().delete();
				}
			}
		}
	}
	 
 
 
	private void unzipSingleFile(String inputZipFile, String outputPath) {
		
		byte[] buffer = new byte[1024];
		
		try {

			// get the zip file content
			ZipInputStream zis = new ZipInputStream(
					new FileInputStream(inputZipFile));
			// get the zipped file list entry
			ZipEntry ze = zis.getNextEntry();
			String fileName;
			while (ze != null) {

				fileName = ze.getName();
				File newFile = new File(outputPath + File.separator
						+ fileName);

//				System.out.println("file unzip : " + newFile.getAbsoluteFile());

				// create all non exists folders
				// else you will hit FileNotFoundException for compressed folder
				new File(newFile.getParent()).mkdirs();

				FileOutputStream fos = new FileOutputStream(newFile);

				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}

				fos.close();
				ze = zis.getNextEntry();
			}

			zis.closeEntry();
			zis.close();
			
			System.out.println("Done");

		} catch (IOException ex) {
			
			File tempFolder = new File(outputPath);
			File[] tempList = tempFolder.listFiles();
			
			for(int j = 0; j<tempList.length;j++){
				tempList[j].delete();
			}
			
			File tempFile = new File(inputZipFile);
			File tempPath = new File(outputPath);
			System.err.println("Can't unzip "+ tempFile.getName());
			try {
				FileUtils.copyFileToDirectory(tempFile, tempPath);
			} catch (IOException e) {
				System.err.println("Can't move "+ tempFile.getName()+" to the directory");
			}
		}
	}
*/	
	

}
