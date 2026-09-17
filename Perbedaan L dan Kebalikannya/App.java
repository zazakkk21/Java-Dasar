import java.util.Scanner;

public class App{
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int ukuran = scanner.nextInt();
    int[][] matriks = getMatriks(ukuran, scanner);
    int nilaiL = getNilaiL(matriks);
    int nilaiKebalikanL = getNilaiKebalikanL(matriks);
    int nilaiTengah = getNilaiTengah(matriks, ukuran);
    int perbedaan = getPerbedaan(nilaiL, nilaiKebalikanL);
    int dominan = getDominan(nilaiL, nilaiKebalikanL, perbedaan, nilaiTengah, ukuran);

    tampilkan(nilaiL, nilaiKebalikanL, perbedaan, nilaiTengah, dominan, ukuran);
    scanner.close();
  }

  public static int getNilaiL(int[][] matriks){
    int sum = 0;
    if(matriks.length == 1 || matriks.length == 2){
      return 0;
    }
    for(int i = 0; i < matriks.length; i++){
      if(i == (matriks.length - 1)){
        for(int j = 0; j < (matriks.length-1); j++){
          sum += matriks[i][j];
        }
      }else{
        sum += matriks[i][0];
      }
    }
    return sum;
  }

  public  static int getNilaiKebalikanL(int[][] matriks){
    int sum = 0;
    if(matriks.length == 1 || matriks.length == 2){
      return 0;
    }
    for(int i = 0; i < matriks.length; i++){
      if(i == 0){
        for(int j = matriks.length-1; j > 0; j--){
          sum += matriks[i][j];
        }
      }else {
      sum += matriks[i][matriks.length-1];
      }
    }
    return sum;
  }

  public static int[][] getMatriks(int ukuran, Scanner scanner){
    int[][] matriks = new int[ukuran][ukuran];
    for(int i = 0; i < ukuran; i++){
      for(int j = 0; j < ukuran; j++){
        matriks[i][j] = scanner.nextInt();
      }
    }
    return matriks;
  }

  public static int getNilaiTengah(int[][] matriks, int ukuran){
    int nilaiTengah = 0;
    if(ukuran == 1){
      nilaiTengah = matriks[0][0];
    }
    if(ukuran % 2 != 0){
      nilaiTengah = matriks[ukuran/2][ukuran/2];
    }else{
      nilaiTengah = matriks[(ukuran/2)-1][(ukuran/2)-1] + matriks[(ukuran/2)-1][ukuran/2] + matriks[ukuran/2][(ukuran/2)-1] + matriks[ukuran/2][ukuran/2];
    }
    return nilaiTengah;
  }

  public static int getPerbedaan(int nilaiL, int nilaiKebalikanL){
    int perbedaan = Math.max(nilaiL, nilaiKebalikanL) - Math.min(nilaiL, nilaiKebalikanL);
    return perbedaan;
  }

  public static int getDominan(int nilaiL, int nilaiKebalikanL, int perbedaan, int nilaiTengah, int ukuran){
    int dominan = 0;
    if(ukuran == 1){
      dominan = nilaiTengah;
    }else if(ukuran == 2){
      dominan = nilaiTengah;
    }
    if(perbedaan == 0){
      dominan = nilaiTengah;
    }else{
      dominan = Math.max(nilaiL, nilaiKebalikanL);
    }
    return dominan;
  }

  public static void tampilkan(int nilaiL, int nilaiKebalikanL, int perbedaan, int nilaiTengah, int dominan, int ukuran){
    if(ukuran == 1 || ukuran == 2){
      System.out.println("Nilai L: Tidak Ada");
    }else{
      System.out.printf("Nilai L: %d\n",nilaiL);
    }

    if(ukuran == 1 || ukuran == 2){
      System.out.println("Nilai Kebalikan L: Tidak Ada");
    }else{
      System.out.printf("Nilai Kebalikan L: %d\n",nilaiKebalikanL);
    }

    System.out.printf("Nilai Tengah: %d\n",nilaiTengah);

    if(ukuran == 1 || ukuran == 2){
      System.out.println("Perbedaan: Tidak Ada");
    }else{
      System.out.printf("Perbedaan: %d\n",perbedaan);
    }

    System.out.printf("Dominan: %d\n",dominan);
  }
}