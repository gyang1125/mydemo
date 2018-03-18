package mytest.basis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestCVSReader {

	public static void main(String[] args) {
		String csvFile = "src/resources/data.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			int count = 0;
			while ((line = br.readLine()) != null) {
				count++;
				// use comma as separator
				String[] position = line.split(cvsSplitBy);

				System.out.println(count + " Position [timestamp= " + position[0] + " , vin=" + position[1] + " , session="
						+ position[2] + " , latitude=" + position[3] + " , longitude=" + position[4] + " , heading="
						+ position[5] + "]");

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
