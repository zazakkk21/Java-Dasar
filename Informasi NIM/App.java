import java.util.Scanner;

public class App{
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String NIM = scanner.nextLine().trim();
    int panjangNim = NIM.length();

    if(panjangNim != 8){
      System.out.println("NIM harus 8 karakter");
      scanner.close();
      return;
    }
    if(getProdi(NIM).equals("x")){
      System.out.println("Kode tidak tersedia");
      scanner.close();
      return;
    }
    tampilkan(NIM);
    scanner.close();

  }

  public static String getProdi(String Nim){
    String prefixNimString = Nim.substring(0,3);
    String prodi = new String();
    switch (prefixNimString) {
      case "11S" :
        prodi = "Sarjana Informatika";
        break;
      
      case "12S" :
        prodi = "Sarjana Sistem Informasi";
        break;

      case "13S" :
        prodi = "Sarjana Teknik Elektro";
        break;

      case "21S" :
        prodi = "Sarjana Manajemen Rekayasa";
        break;

      case "22S" :
        prodi = "Sarjana Teknik Metalurgi";
        break;

      case "31S" :
        prodi = "Sarjana Teknik Bioproses";
        break;

      case "32S" :
        prodi = "Sarjana Bioteknologi";
        break;

      case "114" :
        prodi = "Diploma 4 Teknologi Rekayasa Perangkat Lunak";
        break;

      case "113" :
        prodi = "Diploma 3 Teknologi Informasi";
        break;

      case "133" :
        prodi = "Diploma 3 Teknologi Komputer";
        break;
      
      default :
        return "x";
    }
    return prodi;
  }

  public static int getTahun(String Nim){
    String tahunString = "20" + Nim.substring(3,5);
    int tahun = Integer.parseInt(tahunString);
    return tahun;
  }

  public static int getUrutan(String Nim){
    String urutanString = Nim.substring(5,8);
    int urutan = Integer.parseInt(urutanString);
    return urutan;
  }

  public static void tampilkan(String Nim){
    String prodi = getProdi(Nim);
    int tahun = getTahun(Nim);
    int urutan = getUrutan(Nim);

    System.out.printf("Informasi NIM %s: \n",Nim);
    System.out.println(">> Program Studi: " + prodi);
    System.out.println(">> Angkatan: " + tahun);
    System.out.println(">> Urutan: " + urutan);
  }
}