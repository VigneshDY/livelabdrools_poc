package poc;

import com.livelab.model.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Livelabdrools_poc {


     public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\672845\\Desktop\\personinput.psv"));
         String line = "";
         Person p = new Person();
         int i = 0;
         Integer l = 0;
         String count = "0";
         Map<String, String> map = new HashMap<String, String>();
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

                 int k = 0;
                 p.setId(arr1[Integer.parseInt(map.get(temp[k]))]);
                 p.setFirstName(arr1[Integer.parseInt(map.get(temp[k = k + 1]))]);
                 p.setLastName(arr1[Integer.parseInt(map.get(temp[k = k + 1]))]);
                 p.setLocation(arr1[Integer.parseInt(map.get(temp[k = k + 1]))]);
                 p.setTimeZone(null);

             }
         }
     }


 }
