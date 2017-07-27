package com.giorgiofederici.sjp.showcases.datascience.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletContext;

import org.jdom2.JDOMException;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.giorgiofederici.sjp.showcases.datascience.service.DataScienceService;

@Controller
@RequestMapping(value = "showcases/data-science")
public class DataScienceController {

	@Autowired
	private ServletContext context;

	@Autowired
	private DataScienceService dataScienceService;

	@GetMapping(value = "1/1")
	public String getDataScience11(Model model) {

		String realPath = context.getRealPath("/");

		Set<File> listFiles = this.dataScienceService.listFiles(new File(realPath));

		model.addAttribute("listFiles", listFiles);

		return "showcases/data-science";

	}

	@GetMapping(value = "1/2")
	public String getDataScience12(Model model) {

		String realPath = context.getRealPath("/");

		List<File> listFiles = this.dataScienceService.listFilesApacheCommonsIO(realPath);

		model.addAttribute("listFilesApacheCommonsIO", listFiles);

		return "showcases/data-science";

	}

	@GetMapping(value = "1/3")
	public String getDataScience13(Model model) throws IOException {

		Stream<String> stream = this.dataScienceService
				.readTextFileWithJava8(context.getRealPath("/WEB-INF/classes/showcases/data-science/dummy.txt"));

		List<String> java8StreamList = stream.collect(Collectors.toList());

		model.addAttribute("java8StreamList", java8StreamList);

		return "showcases/data-science";

	}

	@GetMapping(value = "1/4")
	public String getDataScience14(Model model) throws IOException {

		String apacheCommonsIODummyText = this.dataScienceService.readTextFileWithApacheCommonsIO(
				context.getRealPath("/WEB-INF/classes/showcases/data-science/dummy.txt"));

		model.addAttribute("apacheCommonsIODummyText", apacheCommonsIODummyText);

		return "showcases/data-science";

	}

	@GetMapping(value = "1/5")
	public String getDataScience15(Model model) throws IOException {

		String dummyPDFText = this.dataScienceService
				.convertPdf(context.getRealPath("/WEB-INF/classes/showcases/data-science/dummy.txt"));

		model.addAttribute("dummyPDFText", dummyPDFText);

		return "showcases/data-science";

	}

	@GetMapping(value = "1/6")
	public String getDataScience16(Model model) throws IOException {

		String dummyASCIIToCleanText = this.dataScienceService.readTextFileWithApacheCommonsIO(
				context.getRealPath("/WEB-INF/classes/showcases/data-science/asciiToClean.txt"));

		model.addAttribute("dummyASCIIToCleanText", dummyASCIIToCleanText);

		String dummyASCIICleanText = this.dataScienceService.cleanText(dummyASCIIToCleanText);

		model.addAttribute("dummyASCIICleanText", dummyASCIICleanText);

		return "showcases/data-science";

	}

	@GetMapping(value = "1/7")
	public String getDataScience17(Model model) throws IOException {

		String csvFileName = context.getRealPath("/WEB-INF/classes/showcases/data-science/dummyCSV.csv");

		List<String[]> csvResult = this.dataScienceService.parseCsv(csvFileName);

		model.addAttribute("csvResult", csvResult);

		return "showcases/data-science";

	}

	@GetMapping(value = "1/8")
	public String getDataScience18(Model model) throws IOException {

		String tsvFileName = context.getRealPath("/WEB-INF/classes/showcases/data-science/dummyTSV.tsv");

		List<String[]> tsvResult = this.dataScienceService.parseTsv(tsvFileName);

		model.addAttribute("tsvResult", tsvResult);

		return "showcases/data-science";

	}

	@GetMapping(value = "1/9")
	public String getDataScience19(Model model) throws IOException, JDOMException {

		String xmlFileName = context.getRealPath("/WEB-INF/classes/showcases/data-science/dummyXML.xml");

		model.addAttribute("xmlResult", this.dataScienceService.parseXml(xmlFileName));

		return "showcases/data-science";

	}

	@GetMapping(value = "1/10")
	public String getDataScience110(Model model) throws IOException, JDOMException {

		model.addAttribute("jsonWriterResult", this.dataScienceService.writeJson());

		return "showcases/data-science";

	}

	@GetMapping(value = "1/11")
	public String getDataScience111(Model model) throws FileNotFoundException, IOException, ParseException {

		String jsonFileName = context.getRealPath("/WEB-INF/classes/showcases/data-science/dummyJSON.json");

		model.addAttribute("jsonReaderResult", this.dataScienceService.readJson(jsonFileName));

		return "showcases/data-science";

	}

	@GetMapping(value = "1/12")
	public String getDataScience112(Model model) throws IOException {

		model.addAttribute("jsoupResult", this.dataScienceService.extractDataWithJsoup("https://jsoup.org/"));

		return "showcases/data-science";

	}

	@GetMapping(value = "1/13")
	public String getDataScience113(Model model) {

		String chromeDriverPath = context.getRealPath("/WEB-INF/classes/showcases/data-science/chromedriver.exe");

		model.addAttribute("seleniumResult",
				this.dataScienceService.extractDataWithSelenium("http://giorgiofederici.it", chromeDriverPath));

		return "showcases/data-science";

	}

	@GetMapping(value = "2/1")
	public String getDataScience21(Model model) throws IOException {

		String indexPath = context.getRealPath("/WEB-INF/classes/showcases/data-science/index/");
		String docsPath = context.getRealPath("/WEB-INF/classes/showcases/data-science/docs/");

		model.addAttribute("executeIndexingScreenLogs",
				this.dataScienceService.executeIndexing(indexPath, docsPath, true));

		return "showcases/data-science";

	}

}
