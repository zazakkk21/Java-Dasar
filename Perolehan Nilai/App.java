import java.util.Scanner;

public class App{

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int bobotPartisipatif = scanner.nextInt();
    int bobotTugas = scanner.nextInt();
    int bobotKuis = scanner.nextInt();
    int bobotProyek = scanner.nextInt();
    int bobotUts = scanner.nextInt();
    int bobotUas = scanner.nextInt();
    scanner.nextLine();
    
    int[] bobot = {bobotPartisipatif,bobotTugas,bobotKuis,bobotProyek,bobotUts,bobotUas};
    int JumlahBobot = bobot(bobotKuis,bobotPartisipatif,bobotProyek,bobotTugas,bobotUas,bobotUts);
    if(JumlahBobot != 100){
      System.out.println("Total bobot harus 100");
      scanner.close();
      return ;
    }

    
    int totalPA = 0;
    int totalT = 0;
    int totalK = 0;
    int totalP = 0;
    int totalUTS = 0;
    int totalUAS = 0;

    int totalPerolehanPA = 0;
    int totalPerolehanT = 0;
    int totalPerolehanK = 0;
    int totalPerolehanP = 0;
    int totalPerolehanUTS = 0;
    int totalPerolehanUAS = 0;
      
    while(true){ 
      String getInput = scanner.nextLine().trim();
      if(getInput.equals("---")){
        break;
      }

      String[] arrString = getInput.split("\\|");
      if(arrString.length != 3){
        System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
        continue;
      }

      for(int i = 0; i < 3; i++){
        arrString[i] = arrString[i].trim();
      }

      try{
      // ====== isi total bobot =======

      switch (arrString[0]){
        case "PA" :
          totalPA  += Integer.parseInt(arrString[1]);
          break;
        
        case "T" :
          totalT += Integer.parseInt(arrString[1]);
          break;

        case "K" :
          totalK += Integer.parseInt(arrString[1]);
          break;

        case "P" :
          totalP += Integer.parseInt(arrString[1]);
          break;

        case "UTS" :
          totalUTS += Integer.parseInt(arrString[1]);
          break;

        case "UAS" :
          totalUAS += Integer.parseInt(arrString[1]);
          break;
      }

      // =================================


      // ===== isi total perolehan
      switch (arrString[0]){
        case "PA" :
          totalPerolehanPA  += Integer.parseInt(arrString[2]);
          break;
        
        case "T" :
          totalPerolehanT += Integer.parseInt(arrString[2]);
          break;

        case "K" :
          totalPerolehanK += Integer.parseInt(arrString[2]);
          break;

        case "P" :
          totalPerolehanP += Integer.parseInt(arrString[2]);
          break;

        case "UTS" :
          totalPerolehanUTS += Integer.parseInt(arrString[2]);
          break;

        case "UAS" :
          totalPerolehanUAS += Integer.parseInt(arrString[2]);
          break;
        
        default :
          System.out.println("Simbol tidak dikenal");
          continue;
      }
    }catch(NumberFormatException e){
      System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
      continue;
    }
    } // ===> end of while

      int[] totalPerolehanX = {totalPerolehanPA,totalPerolehanT,totalPerolehanK,totalPerolehanP,totalPerolehanUTS,totalPerolehanUAS};
      int[] totalBobotX = {totalPA,totalT,totalK,totalP,totalUTS,totalUAS};

      for(int i = 0; i < 6; i++){
        totalPerolehanX[i] = clamp(totalPerolehanX[i], totalBobotX[i]);
      }

      int[] persentasePerolehanX = new int[6];
      for(int i = 0; i < 6; i++){
        persentasePerolehanX[i] = perolehanX100(totalPerolehanX[i], totalBobotX[i]);
      }

      double[] kontribusiX = new double[6];
      for(int i = 0; i < 6; i++){
        kontribusiX[i] = kontribusi(persentasePerolehanX[i], bobot[i]);
      }

      float nilaiAkhir = 0;
      for(int i = 0; i < 6; i++){
        nilaiAkhir += kontribusiX[i];
      }

      String hasilAkhir = getGreade(nilaiAkhir);

      tampilkan(persentasePerolehanX, kontribusiX, nilaiAkhir, bobot, hasilAkhir);
      scanner.close();
    }

  public static int bobot(int a, int b, int c, int d, int e, int f){
    int sum = a+b+c+d+e+f;
    return sum;
  }

  public static int perolehanX100(int perolehan, int bobot){
    int hasil;
    if (bobot == 0){
      hasil = 0;
    }else{
    hasil = (perolehan * 100) / bobot;}
    return  hasil;
  }
  public static double kontribusi(int perolehan, int bobot){
    double hasil = (perolehan / 100.0) * bobot;
    return  hasil;
  }

  public static int clamp(int perolehan, int bobot){
    int hasil = perolehan;
    if(hasil > bobot){
      hasil = Math.min(perolehan,bobot);
    }else if (hasil < 0){
      hasil = Math.max(0,perolehan);
    }
    return hasil;
  }

  public static String getGreade(double nilai){
    String grade = "";
    if(nilai >= 79.5){
      grade = "A";
    }else if(nilai >= 72 && nilai < 79.5){
      grade = "AB";
    }else if(nilai >= 64.5 && nilai < 79.5){
      grade = "B";
    }else if(nilai >= 57 && nilai < 64.5){
      grade = "BC";
    }else if(nilai >= 49.5 && nilai < 57){
      grade = "C";
    }else if (nilai >= 34 && nilai < 49.5){
      grade = "D";
    }else if (nilai < 34){
      grade = "E";
    }
    return grade;
  }

  public static void tampilkan(int[] perolehan, double[] kontribusi, float nilaiAkhir, int[] bobot,String grade){
    System.out.println("Perolehan Nilai:");
    System.out.printf(">> Partisipatif: %d/100 (%.2f/%d)\n",perolehan[0],kontribusi[0],bobot[0]);
    System.out.printf(">> Tugas: %d/100 (%.2f/%d)\n",perolehan[1],kontribusi[1],bobot[1]);
    System.out.printf(">> Kuis: %d/100 (%.2f/%d)\n",perolehan[2],kontribusi[2],bobot[2]);
    System.out.printf(">> Proyek: %d/100 (%.2f/%d)\n",perolehan[3],kontribusi[3],bobot[3]);
    System.out.printf(">> UTS: %d/100 (%.2f/%d)\n",perolehan[4],kontribusi[4],bobot[4]);
    System.out.printf(">> UAS: %d/100 (%.2f/%d)\n",perolehan[5],kontribusi[5],bobot[5]);

    System.out.printf("\n>> Nilai Akhir: %.2f\n",nilaiAkhir);
    System.out.printf(">> Grade: %s",grade);
  }
}