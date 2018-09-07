package poc;


import com.livelab.model.Person;
import com.livelab.model.Rule;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.util.*;

public class Service {

    public void readExcel() {
        List<Rule> listRule = new ArrayList<Rule>();
        List<String> inputRule = new ArrayList<String>();
        List<String> outputRule = new ArrayList<String>();
        Map<String, String> map = new LinkedHashMap<String, String>();
        try {
            FileInputStream excelFile = new FileInputStream(new File(""));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = (Sheet) workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            String t = "";
            String line = "";
            String flag = "0";
            Integer l = 0;
            int i = 0;
            Integer count=0;
            Rule rule = new Rule();

            while (iterator.hasNext()) {
                Row currentRow = iterator.next();

                Iterator<Cell> cellIterator = currentRow.iterator();
                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    String temp1 = currentCell.toString();
                    String temp2 = "Input";
                    String temp3 = "Output";
                    if (temp1.contains(temp2)) {
                        map.put(temp1, temp2+count.toString());
                        count++;
                    } else {
                        map.put(temp1, temp3+count.toString());
                        count++;
                    }
                }
                break;
            }

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();

                Iterator<Cell> cellIterator = currentRow.iterator();
                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    t = t + currentCell.toString() + " ";

                }
                Rule rule1 = new Rule();
                String b[] = t.split(" ");

                rule1.setLocation(b[0]);
                rule1.setTimeZone(b[1]);

                t = "";

                listRule.add(rule1);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Rule p3 : listRule) {
            System.out.println(p3);

        }
        for(Map.Entry<String,String> m2:map.entrySet())
        {
            System.out.println(m2.getKey()+" "+m2.getValue());
        }

    }

    public void readPsv() throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(""));
        String line = "";
        Person p = new Person();
        int i = 0;
        Integer l = 0;
        String count = "0";
        Map<String, String> map = new LinkedHashMap<String, String>();
        List<Person> listPerson = new ArrayList<Person>();
        String[] temp = new String[5];
        while ((line = br.readLine()) != null) {

            if (i == 0) {
                String arr[] = line.split("\\|");
                p.setId(arr[0]);
                p.setFirstName(arr[1]);
                p.setLastName(arr[2]);
                p.setLocation(arr[3]);
                p.setTimeZone(arr[4]);
                temp[0] = p.getId();
                temp[1] = p.getFirstName();
                temp[2] = p.getLastName();
                temp[3] = p.getLocation();
                temp[4] = p.getTimeZone();
                for (int j = 0; j < arr.length; j++) {

                    map.put(arr[j], count);
                    l++;
                    count = l.toString();

                }
                i++;
            } else {

                String arr1[] = line.split("\\|");
                Person p1 = new Person();
                int k = 0;
                p1.setId(arr1[Integer.parseInt(map.get(temp[k]))]);
                p1.setFirstName(arr1[Integer.parseInt(map.get(temp[k = k + 1]))]);
                p1.setLastName(arr1[Integer.parseInt(map.get(temp[k = k + 1]))]);
                p1.setLocation(arr1[Integer.parseInt(map.get(temp[k = k + 1]))]);
                p1.setTimeZone(null);
                listPerson.add(p1);

            }
        }

        for (Person p2 : listPerson) {
            System.out.println(p2.toString());
        }

    }

}


