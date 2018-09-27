

import java.util.*;

public class TandemSAIS {

    public static String lcp(String string1, String string2) {
        int n = Math.min(string1.length(), string2.length());
        for (int i = 0; i < n; i++) {
            if (string1.charAt(i) != string2.charAt(i)) {
                return string1.substring(0, i);
            }
        }
        return string1.substring(0, n);
    }

    public static void lrs(String sequence) {
        SAIS sais = new SAIS();
        int length = sequence.length();
        System.out.println("Length: " + length);
        int[] result = sais.makeSuffixArrayByInducedSorting(sequence, 256);
        int r[] = new int[length];
        String[] c = new String[length];
        System.out.println("Result:");
        System.out.println("============================================================");
        for (int j = 1; j <= length; j++) {
            c[j - 1] = String.valueOf(result[j]);
            System.out.println(result[j]);
        }
        System.out.println("============================================================");
        String[] suffixes = new String[length + 1];
        int r1 = 1;
        for (int i = 0; i < length; i++) {
            suffixes[i] = sequence.substring(result[r1], length);
            r1++;
        }
        System.out.println("Suffix");
        System.out.println("============================================================");
        for (int i = 0; i < length; i++) {
            System.out.println(suffixes[i]);
        }
        System.out.println("============================================================");
        String[] a = new String[suffixes.length];
        String[] b = new String[suffixes.length];

        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < length - 1; i++) {
            String commonString = lcp(suffixes[i], suffixes[i + 1]);
            a[i] = commonString;
            b[i] = String.valueOf(commonString.length());
        }
        String[][] arr = new String[suffixes.length + 1][8];
        int row = 0;
        for (int k = 0; k < length; k++) {
            arr[row][0] = row + "";
            arr[row][1] = c[row];
            arr[row][4] = suffixes[k] + "";
            arr[row][2] = a[row];
            arr[row][3] = b[row];
            if (arr[row][3] != null && Integer.parseInt(arr[row][3]) > 0) {
                arr[row][5] = c[row];
                if (row < length) {
                    arr[row][6] = c[row + 1];
                }
                arr[row][7] = arr[row][3];
            }
            row += 1;
        }
        //Code to pring array into tabular format
        System.out.println("============================================================================================================");
        System.out.printf("%5s %20s %5s %5s %20s %10s %10s %10s", "ID", "SA", "LCP", "LCPStr", "Suffixes", "NewSA", "NewSA+1", "NewLCP");
        System.out.println("\n=============================================================================================================\n");
        for (int i = 0; i < length; i++) {
            System.out.printf("%5s %20s %5s %5s %20s %10s %10s %10s", arr[i][0], arr[i][1], arr[i][2], arr[i][3], arr[i][4], arr[i][5], arr[i][6], arr[i][7]);
            System.out.println("");
        }
        //Code to calculate the occurance of word
        System.out.println("\n============================================================================================================\n");
        String[][] locArr = new String[suffixes.length + 1][3];
        int countNotNullRow = 0;
        for (int k=0; k<arr.length; k++) {
            if(arr[k][2] != null && !"".equals(arr[k][2])) {
                countNotNullRow++;
                locArr[k][0] = arr[k][2];
                StringBuilder location = new StringBuilder();
                int count = 0;
                int len = arr[k][2].length();
                int j = k;
                for (j = k; j < arr.length-1; j++) {
                    if(arr[j][2] != null) {
                        int nextLen = arr[j][2].length();
                        if (len <= nextLen) {
                            count++;
                            location.append(arr[j][1]+",");
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                count++;
                location.append(arr[j][1]);
                location.append(",");
                for (j=k-1;j>=0;j--) {
                    if(arr[j][2] != null) {
                        int nextLen = arr[j][2].length();
                        if (len <= nextLen) {
                            count++;
                            location.append(arr[j][1]+",");
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }

                locArr[k][1] = count+"";
                locArr[k][2] = location.toString();
            }
        }
        String newArr[][] = new String[suffixes.length][countNotNullRow];

        int k = 0;

        for(int i=0;i<locArr.length;i++) {
            if(locArr[i][0]!=null) {
                newArr[k][0] = locArr[i][0];
                newArr[k][1] = locArr[i][1];
                newArr[k][2] = locArr[i][2];
                k++;
            }
        }

        Arrays.sort(newArr, new Comparator<String[]>() {
            @Override
            public int compare(String[] a, String[] b) {
                if(a[0] != null) {
                    return Integer.compare(b[0].length(), a[0].length());
                } else {
                    return 0;
                }
            }
        });

        //print location array
        System.out.println("============================================================================================================");
        System.out.printf("%20s %20s %30s", "Word", "Count", "Location");
        System.out.println("\n=============================================================================================================\n");
        for (int i = 0; i < countNotNullRow; i++) {
            System.out.printf("%20s %20s %30s", newArr[i][0], newArr[i][1], newArr[i][2]);
            System.out.println("");
        }
        System.out.println("\n=============================================================================================================\n");

        // Remove Duplicate row from 2D array start
        String[][] distArr = new String[countNotNullRow][3];
        int distCount = 1;
        String preStr = newArr[0][0];
        for(int i=1;i<countNotNullRow;i++) {
            if (preStr.equals(newArr[i][0])) {
                continue;
            } else {
                distCount++;
                preStr = newArr[i][0];
            }
        }

        String[][] distArr1 = new String[distCount][3];
        distCount = 1;
        preStr = newArr[0][0];
        distArr1[0][0] = newArr[0][0];
        distArr1[0][1] = newArr[0][1];
        distArr1[0][2] = newArr[0][2];
        for(int i=1;i<countNotNullRow;i++) {
            if (preStr.equals(newArr[i][0])) {
                continue;
            } else {
                preStr = newArr[i][0];
                distArr1[distCount][0] = newArr[i][0];
                distArr1[distCount][1] = newArr[i][1];
                distArr1[distCount][2] = newArr[i][2];
                distCount++;
            }
        }

        // Duplicate element is removed and stored into distArr1 array

        //print distint array
        System.out.println("Removed duplicate elements");
        System.out.println("============================================================================================================");
        System.out.printf("%20s %20s %30s", "Word", "Count", "Location");
        System.out.println("\n=============================================================================================================\n");
        for (int i = 0; i < distArr1.length; i++) {
            System.out.printf("%20s %20s %30s", distArr1[i][0], distArr1[i][1], distArr1[i][2]);
            System.out.println("");
        }
        System.out.println("\n=============================================================================================================\n");

        // code to find out max repeated elements
        int maxCount = 0;

        Map<String, String> map = new LinkedHashMap<>();
        int[] checks = new int[distArr1.length];
        map.put(distArr1[0][0],distArr1[0][1]+":"+distArr1[0][2]);
        for (int i = 0;i<distArr1.length;i++) {
            String str = distArr1[i][0]!=null?distArr1[i][0].substring(1):null;
            if(checks[i]==0&&distArr1[i][0]!=null && !map.containsKey(distArr1[i][0])) {
                map.put(distArr1[i][0],distArr1[i][1]+":"+distArr1[i][2]);
            }
            for(int j=i+1;j<distArr1.length;j++){
                if(checks[j]==0 && str != null && str.equals(distArr1[j][0]) && Integer.parseInt(distArr1[i][1])<Integer.parseInt(distArr1[j][1])) {
                    map.put(str, distArr1[j][1]+":"+distArr1[j][2]);
                    break;
                } else  if(str !=null && str.equals(distArr1[j][0]) && Integer.parseInt(distArr1[i][1])>=Integer.parseInt(distArr1[j][1])) {
                    checks[j] = 1;
                    break;
                }
            }
        }

        String[][] maxArr = new String[map.size()][3];
        int count = 0;
        for (String str: map.keySet()) {
            maxArr[count][0]=str;
            maxArr[count][1]=map.get(str).split(":")[0];
            maxArr[count][2]=map.get(str).split(":")[1];
            count++;
        }
        System.out.println("Maximal repeats are:");
        System.out.println("============================================================================================================");
        System.out.printf("%20s %20s %30s", "Word", "Count", "Location");
        System.out.println("\n=============================================================================================================\n");
        for (int i = 0; i < maxArr.length; i++) {
            System.out.printf("%20s %20s %30s", maxArr[i][0], maxArr[i][1], maxArr[i][2]);
            System.out.println("");
        }
        System.out.println("\n=============================================================================================================\n");

        //Super Max repeat
        Map<String, String> superMax = new LinkedHashMap<>();
        for (int i = 0; i < maxArr.length; i++) {
            String curStr = maxArr[i][0];
            boolean flag = true;
           for(int j=0;j<maxArr.length;j++) {
               if(i==j) continue;
               if(maxArr[j][0].contains(curStr)) {
                   flag = false;
               }
           }
            if(flag) {
                superMax.put(maxArr[i][0],maxArr[i][1]+":"+maxArr[i][2]);
            }
        }

        String[][] maxSupArr = new String[superMax.size()][3];
        count = 0;
        for (String str: superMax.keySet()) {
            maxSupArr[count][0]=str;
            maxSupArr[count][1]=superMax.get(str).split(":")[0];
            maxSupArr[count][2]=superMax.get(str).split(":")[1];
            count++;
        }
        System.out.println("SuperMaximal repeats are:");
        System.out.println("============================================================================================================");
        System.out.printf("%20s %20s %30s", "Word", "Count", "Location");
        System.out.println("\n=============================================================================================================\n");
        for (int i = 0; i < maxSupArr.length; i++) {
            System.out.printf("%20s %20s %30s", maxSupArr[i][0], maxSupArr[i][1], maxSupArr[i][2]);
            System.out.println("");
        }
        System.out.println("\n=============================================================================================================\n");


    }

    public void start(HashMap<String, String> database) {
        System.out.println("From Suffix arrays induced sorting:");
        for (String key : database.keySet()) {
            //System.out.print("ID: "+key+" ");
            String sequence = database.get(key);
            lrs(sequence);
        }
    }
}
