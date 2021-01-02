package com.ygl.java8;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.apache.commons.lang3.StringUtils;

public class calculator {

	enum Operation {
		PLUS, MINUS, TIMES, DIVIDE;

		String calculate(String x, String y) {
			switch (this) {
			case PLUS:
				return x + " + " + y + " = ";
			case MINUS:
				return x + " - " + y + " = ";
			case TIMES:
				return x + " * " + y + " = ";
			case DIVIDE:
				return x + " / " + y + " = ";
			default:
				throw new AssertionError("Unknown operations " + this);
			}
		}

	}

	private static final String[] number = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	private static LocalDateTime today = LocalDateTime.now();
	private static LocalDate localDate = LocalDate.now();
	private static final String FILE_NAME_TXT = localDate + "_exam.txt";
	private static final String FILE_NAME_PDF = localDate + "_exam.pdf";

	public static void main(String[] args) {
		createExamPaper();
	}

	private static void createExamPaper() {

		List<String> allData = getAllData();
		writeToPdf(convertConent(allData.subList(0, 58)), convertConent(allData.subList(59, 118)));
	}

	private static String convertConent(List<String> column) {
		final StringBuffer contentBuffer = new StringBuffer();
		column.forEach(s -> {
			contentBuffer.append(s);
			contentBuffer.append(System.lineSeparator());
		});
		return contentBuffer.toString();
	}

	private static List getAllData() {

		List<String> data = new ArrayList<String>();
		for (int i = 0; i < number.length; i++) {
			for (int j = 0; j < number.length; j++) {
				if (Integer.valueOf(number[i]) + Integer.valueOf(number[j]) <= 10) {
					String plus = number[i] + " + " + number[j] + " = ";
					data.add(plus);
				}
			}
		}

		for (int i = number.length - 1; i >= 0; i--) {
			for (int j = i; j >= 0; j--) {
				String minus = number[i] + " - " + number[j] + " = ";
				data.add(minus);
			}
		}
		System.out.println(data.size());
		data.forEach(x -> {
			System.out.println(x);
		});

		Collections.shuffle(data, new Random());
		return data;
	}

	private static void writeToFile(String content) {
		// If the file doesn't exists, create and write to it
		// If the file exists, truncate (remove all content) and write to it
		try (FileWriter writer = new FileWriter(FILE_NAME_TXT); // set true will be appended
				BufferedWriter bw = new BufferedWriter(writer)) {

			bw.write(content);

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
	}

	private static void writeToPdf(String plusColumn, String minusColumn) {
		Document document = new Document();

		try {

			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME_PDF)));

			// open
			document.open();

			Paragraph p = new Paragraph();
			p.add("Math Exam at " + today.now());
			p.setAlignment(Element.ALIGN_CENTER);

			document.add(p);

			// content
			PdfPTable table = new PdfPTable(2); // 2 columns.
			table.setWidthPercentage(100); // Width 100%
			table.setSpacingBefore(10f); // Space before table
			table.setSpacingAfter(10f); // Space after table

			// Set Column widths
			float[] columnWidths = { 1f, 1f };
			table.setWidths(columnWidths);

			PdfPCell cell1 = new PdfPCell(new Paragraph(plusColumn));
			cell1.setBorderColor(BaseColor.BLUE);
			cell1.setPaddingLeft(10);
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell2 = new PdfPCell(new Paragraph(minusColumn));
			cell2.setBorderColor(BaseColor.GREEN);
			cell2.setPaddingLeft(10);
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

			table.addCell(cell1);
			table.addCell(cell2);

			document.add(table);

			// close
			document.close();
			writer.close();

			System.out.println("Done");

		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
