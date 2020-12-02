package Server;

import java.util.Scanner;

public class ScannerMain {

    private Scanner scanner;

    public ScannerMain(){
        scanner = new Scanner(System.in);
    }

    public int main(){
        int ret = 0;

        System.out.println("Choose the way Useraccounts are saved.");
        System.out.println("1 = [MySql-Database], 2 = [File]");
        ret = scanner.nextInt();
        return ret;
    }

}
