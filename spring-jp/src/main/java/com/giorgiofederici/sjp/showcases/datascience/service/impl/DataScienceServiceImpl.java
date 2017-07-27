package com.giorgiofederici.sjp.showcases.datascience.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import com.giorgiofederici.sjp.showcases.datascience.service.DataScienceService;
import com.univocity.parsers.common.processor.RowListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;

@Service
public class DataScienceServiceImpl implements DataScienceService {

	private final static Logger LOGGER = Logger.getLogger(DataScienceServiceImpl.class);

	@Override
	public Set<File> listFiles(File rootDir) {
		Set<File> fileSet = new HashSet<File>();
		if (rootDir == null || rootDir.listFiles() == null) {
			return fileSet;
		}

		for (File fileOrDir : rootDir.listFiles()) {
			if (fileOrDir.isFile()) {
				fileSet.add(fileOrDir);
			} else {
				fileSet.addAll(listFiles(fileOrDir));
			}

		}

		return fileSet;
	}

	@Override
	public List<File> listFilesApacheCommonsIO(String rootDir) {
		File dir = new File(rootDir);

		/*
		 * The FileUtils class of the Apache Commons library contains a method named listFiles(). Use this method to
		 * retrieve all the file names, and put the names in a list variable with <File> generics. Use
		 * TrueFileFilter.INSTANCE to match all directories
		 */

		return (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
	}

	@Override
	public Stream<String> readTextFileWithJava8(String file) throws IOException {
		return Files.lines(Paths.get(file));
	}

	@Override
	public String readTextFileWithApacheCommonsIO(String file) throws IOException {
		File newFile = new File(file);
		return FileUtils.readFileToString(newFile, "UTF-8");
	}

	@Override
	public String convertPdf(String fileName) throws IOException {

		try (InputStream stream = new FileInputStream(fileName);) {
			AutoDetectParser parser = new AutoDetectParser();

			// Create a handler to handle the body content of the file. Note the -1 as the parameter of the constructor.
			// Usually, Apache Tika is limited to handling files with at most 100,000 characters. The -1 value ensures
			// that this limitation is overlooked by the body handler
			BodyContentHandler handler = new BodyContentHandler(-1);

			Metadata metadata = new Metadata();
			parser.parse(stream, handler, metadata, new ParseContext());
			return handler.toString();
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	@Override
	public String cleanText(String text) {

		// strip off non-ASCII characters
		text = text.replaceAll("[^\\p{ASCII}]", "");
		// replace continuous white spaces with a single white space
		text = text.replaceAll("\\s+", " ");
		// erase all the ASCII control characters
		text = text.replaceAll("\\p{Cntrl}", "");
		// strip off the ASCII non-printable characters
		text = text.replaceAll("[^\\p{Print}]", "");
		// remove non-printable characters from Unicode
		text = text.replaceAll("\\p{C}", "");
		return text;
	}

	@Override
	public List<String[]> parseCsv(String fileName) {
		CsvParserSettings parserSettings = new CsvParserSettings();
		// configure the parser to automatically detect what line separator sequence is in the input
		parserSettings.setLineSeparatorDetectionEnabled(true);
		// RowListProcessor that stores each parsed row in a list
		RowListProcessor rowProcessor = new RowListProcessor();
		parserSettings.setProcessor(rowProcessor);
		// if the CSV file that you are going to parse contains headers, you can consider the first parsed row as the
		// headers of each column in the file
		parserSettings.setHeaderExtractionEnabled(true);
		CsvParser parser = new CsvParser(parserSettings);
		// the parse() method will parse the file and delegate each parsed row to the RowProcessor
		parser.parse(new File(fileName));

		List<String[]> csvResult = new ArrayList<String[]>();

		// If you have parsed the headers, the headers can be found as follows
		String[] headers = rowProcessor.getHeaders();
		// the row values can be found in a list
		List<String[]> rows = rowProcessor.getRows();

		csvResult.add(headers);
		csvResult.addAll(rows);

		return csvResult;

	}

	@Override
	public List<String[]> parseTsv(String fileName) {
		TsvParserSettings settings = new TsvParserSettings();
		// The line separator for the TSV file in this recipe is a newline character or n
		settings.getFormat().setLineSeparator("\n");
		TsvParser parser = new TsvParser(settings);
		return parser.parseAll(new File(fileName));
	}

	@Override
	public List<String> parseXml(String fileName) throws JDOMException, IOException {

		List<String> resultXml = new ArrayList<String>();

		SAXBuilder builder = new SAXBuilder();
		File file = new File(fileName);
		Document document = builder.build(file);

		// When you are parsing an XML, as it is tree structured, you need to know the root element of the file to start
		// traversing the tree. So, you are creating a rootNode object
		// of type Element to hold the root element
		Element rootNode = document.getRootElement();
		List list = rootNode.getChildren("author");
		for (int i = 0; i < list.size(); i++) {
			Element node = (Element) list.get(i);
			resultXml.add("First Name : " + node.getChildText("firstname") + "; Last Name : "
					+ node.getChildText("lastname"));
		}

		return resultXml;

	}

	@Override
	public String writeJson() {
		JSONObject obj = new JSONObject();
		obj.put("book", "Harry Potter and the Philosopher's Stone");
		obj.put("author", "J. K. Rowling");
		JSONArray list = new JSONArray();

		list.add(
				"There are characters in this book that will remind us of all the people we have met. Everybody knows or knew a spoilt, overweight boy like Dudley or a bossy and interfering (yet kind-hearted) girl like Hermione");

		list.add(
				"Hogwarts is a truly magical place, not only in the most obvious way but also in all the detail that the author has gone to describe it so vibrantly.");

		list.add(
				"Parents need to know that this thrill-a-minute story, the first in the Harry Potter series, respects kids' intelligence and motivates them to tackle its greater length and complexity, play imaginative games, and try to solve its logic puzzles. ");

		obj.put("messages", list);

		return obj.toJSONString();

	}

	@Override
	public List<String> readJson(String inFileName) throws FileNotFoundException, IOException, ParseException {

		List<String> jsonResult = new ArrayList<String>();

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(inFileName));
		JSONObject jsonObject = (JSONObject) obj;
		jsonResult.add("Book: " + (String) jsonObject.get("book"));
		jsonResult.add("Author: " + (String) jsonObject.get("author"));
		JSONArray reviews = (JSONArray) jsonObject.get("messages");
		Iterator<String> iterator = reviews.iterator();
		while (iterator.hasNext()) {
			jsonResult.add(iterator.next());
		}

		return jsonResult;
	}

	@Override
	public List<String> extractDataWithJsoup(String href) throws IOException {

		List<String> jsoupResult = new ArrayList<String>();

		org.jsoup.nodes.Document doc = null;
		doc = Jsoup.connect(href).timeout(10 * 1000).userAgent("Mozilla").ignoreHttpErrors(true).get();
		if (doc != null) {
			jsoupResult.add("Titolo: " + doc.title());
			jsoupResult.add("Text: " + doc.body().text());
			Elements links = doc.select("a[href]");
			for (org.jsoup.nodes.Element link : links) {
				jsoupResult.add(link.attr("href"));
				jsoupResult.add(link.text());
				jsoupResult.add(link.outerHtml());
				jsoupResult.add(link.html());
			}
		}

		return jsoupResult;
	}

	@Override
	public String extractDataWithSelenium(String url, String chromeDriverPath) {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		WebElement webElement = driver.findElement(By.xpath("//*[@id=\"homesection\"]"));
		return webElement.getText();
	}

	// Indexing and Searching

	@Override
	public List<String> executeIndexing(String indexPath, String docsPath, boolean isCreate) throws IOException {

		List<String> executeIndexingScreenLogs = new ArrayList<String>();

		String message = "";

		final Path docDir = Paths.get(docsPath);
		Date start = new Date();

		message = "Indexing to directory '" + indexPath + "'...";
		executeIndexingScreenLogs.add(message);
		LOGGER.debug(message);

		// For indexing, Create a directory and create an analyzer (in this case, you will be using a basic, standard
		// analyzer and an index writer configurer)

		Directory dir = FSDirectory.open(Paths.get(indexPath));
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

		// With the index writer configured and based on the input regarding the creation or update of the index, set
		// the open modes for the indexing. If you choose to create a new index, the open mode will be set to CREATE.
		// Otherwise, it will be CREATE_OR_APPEND

		if (isCreate) {
			iwc.setOpenMode(OpenMode.CREATE);
		} else {
			iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
		}
		IndexWriter writer = new IndexWriter(dir, iwc);
		indexDocs(writer, docDir);

		writer.close();

		Date end = new Date();

		message = end.getTime() - start.getTime() + " total milliseconds";

		executeIndexingScreenLogs.add(message);
		LOGGER.debug(message);

		return executeIndexingScreenLogs;

	}

	// The method indexes any given file using the given index writer. If a directory is provided as argument, the
	// method recursively iterates over files and directories found under the given directory. This method indexes one
	// document per input file

	// writer is the index writer that writes index where the given file or directory information will be stored
	// path is the file to index, or the directory containing the files for which index will be created

	private void indexDocs(IndexWriter writer, Path path) throws IOException {

		// If a directory is provided, the directory will be iterated or traversed recursively
		if (Files.isDirectory(path)) {
			Files.walkFileTree(path, new SimpleFileVisitor<Path>() {

				// overriding a method named visitFile to visit the file or directory based on the given path and basic
				// file attributes

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					try {
						indexDoc(writer, file, attrs.lastModifiedTime().toMillis());
					} catch (IOException ignore) {
					}
					return FileVisitResult.CONTINUE;
				}
			});
		} else { // dealing with files, not directories
			indexDoc(writer, path, Files.getLastModifiedTime(path).toMillis());
		}
	}

	// method to deal with indexing of a single document

	private void indexDoc(IndexWriter writer, Path file, long lastModified) throws IOException {
		try (InputStream stream = Files.newInputStream(file)) {

			// block to create a new empty document
			org.apache.lucene.document.Document doc = new org.apache.lucene.document.Document();

			// add the path of the file as a field. As a field name, type "path". The field will be searchable or
			// indexed. However, note that you do not tokenize the field and do not index term frequency or positional
			// information
			Field pathField = new StringField("path", file.toString(), Field.Store.YES);
			doc.add(pathField);

			// Add the last modified date of the file, a field named "modified"
			doc.add(new LongPoint("modified", lastModified));

			// Add the contents of the file to a field named "contents". The reader that you specify will make sure that
			// the text of the file is tokenized and indexed, but not stored
			doc.add(new TextField("contents",
					new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))));

			// Create an index for the file
			if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
				LOGGER.debug("Adding " + file);
				writer.addDocument(doc);
			} else {
				// here is a chance that the document might have been indexed already. Use updateDocument instead of
				// replacing the old one matching the exact path, if present
				LOGGER.debug("updating " + file);
				writer.updateDocument(new Term("path", file.toString()), doc);
			}
		}
	}

}
