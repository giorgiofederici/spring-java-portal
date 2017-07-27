package com.giorgiofederici.sjp.showcases.datascience.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.jdom2.JDOMException;
import org.json.simple.parser.ParseException;

public interface DataScienceService {

	public Set<File> listFiles(File rootDir);

	public List<File> listFilesApacheCommonsIO(String rootDir);

	public Stream<String> readTextFileWithJava8(String file) throws IOException;

	public String readTextFileWithApacheCommonsIO(String file) throws IOException;

	public String convertPdf(String fileName) throws IOException;

	public String cleanText(String text);

	public List<String[]> parseCsv(String fileName);

	public List<String[]> parseTsv(String fileName);

	public List<String> parseXml(String fileName) throws JDOMException, IOException;

	public String writeJson();

	public List<String> readJson(String inFileName) throws FileNotFoundException, IOException, ParseException;

	public List<String> extractDataWithJsoup(String href) throws IOException;

	public String extractDataWithSelenium(String url, String chromeDriverPath);

	// Indexing and Searching Data

	public List<String> executeIndexing(String indexPath, String docsPath, boolean isCreate) throws IOException;

}
