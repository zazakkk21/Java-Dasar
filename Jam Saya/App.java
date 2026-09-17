import java.util.Scanner;


public class App {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String inputJam = scanner.nextLine().trim();
    int totalMenitPerintah = 0;
    int pergantianHari = 0;
    
    
    if(istJamValid(inputJam)){
      int totalMenitAwal = getTotalMenit(inputJam);
      int totalMenitSekarang = getTotalMenit(inputJam);
      while(true){
        String inputPerintah = scanner.nextLine().trim();
        if(inputPerintah.equals("---")){
          break;
        }

        if(isInputPerintahValid(inputPerintah)){

          if(getPerintah(inputPerintah) == '+'){
            totalMenitSekarang += getN(inputPerintah);
            totalMenitPerintah += getN(inputPerintah);
            if(totalMenitSekarang >= 1440){
              pergantianHari++;
              totalMenitSekarang -= 1440;
            }
          }else{
            totalMenitSekarang -= getN(inputPerintah);
            totalMenitPerintah -= getN(inputPerintah);
            if(totalMenitSekarang < 0){
              pergantianHari++;
              totalMenitSekarang = totalMenitSekarang + 1440;
            }
          }
        }
        
        else{
          System.out.println("Perintah tidak valid");
        }// =============end if isInputPerintahValid

      }
      tampilkan(totalMenitAwal, totalMenitSekarang, totalMenitPerintah, pergantianHari);
    } // ============== end if isJamValid
    else{
      System.out.println("Jam tidak valid");
    }

    scanner.close();
  }




  

  public static int getTotalMenit(String inputJam){
    int totalMenit = 0;
    String[] arrTotalMenitString = inputJam.split("\\:");
    int jam = Integer.parseInt(arrTotalMenitString[0]);
    int menit = Integer.parseInt(arrTotalMenitString[1]);
    totalMenit = (jam * 60) + menit;
    return  totalMenit;
  }

  public static boolean istJamValid(String inputJam){
    boolean valid = true;

    try{    
      String[] arrJamMenit = inputJam.split("\\:");
      if(arrJamMenit.length != 2){
        return false;
      }
      int totalMenit = getTotalMenit(inputJam);
      if(totalMenit > 1439 || totalMenit < 0 || Integer.parseInt(arrJamMenit[0]) > 23 ||  Integer.parseInt(arrJamMenit[1]) > 59 || Integer.parseInt(arrJamMenit[0]) < 0 || Integer.parseInt(arrJamMenit[1]) < 0){
        return false;
      }
    }catch(NumberFormatException e){
      valid = false;
    }
    return valid;
  }

  public static boolean isInputPerintahValid(String perintah){
    boolean isValid = true;
    try{
      Integer.parseInt(perintah.substring(1));
      if(perintah.charAt(0) != '-' && perintah.charAt(0) != '+'){
        return false;
      }
    }catch(NumberFormatException e){
      return false;
    }
    return isValid;
  }

  public static int getN(String perintah){
    return Integer.parseInt(perintah.substring(1));
  }

  public static char getPerintah(String perintah){
    char pertama = perintah.charAt(0);
    return pertama;
  }

  public static void tampilkan(int totalMenitAwal, int totalMenitSekarang, int totalMenitPerintah, int pergantianHari){
    System.out.printf("Jam Awal: %02d:%02d\n",(totalMenitAwal/60),(totalMenitAwal%60));
    System.out.printf("Jam Akhir: %02d:%02d\n",(totalMenitSekarang/60),(totalMenitSekarang%60));
    if(totalMenitPerintah > 0){
      System.out.printf("Total Menit: +%d\n",totalMenitPerintah);
    }else if(totalMenitPerintah == 0){
      System.out.printf("Total Menit: %d\n",totalMenitPerintah);
    }else{
      System.out.printf("Total Menit: %d\n",totalMenitPerintah);
    }

    System.out.printf("Pergantian Hari: %d\n",pergantianHari);
  }


}