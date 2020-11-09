package th4;
// tao thread tu class co san 

import java.awt.Paint;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class Cach1 extends Thread {
	public static boolean isLoading = true;
	public static Scanner scanner = new Scanner(System.in);

	@Override
	public void run() {

		while (isLoading) {
			try {
				Thread.sleep(700);
				System.out.println("running...");

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		super.run();
	}

	public static void main(String[] args) throws InterruptedException {

		

		boolean abc = true;
		while (abc) {
			java.awt.event.KeyEvent key;
			System.out.println("video dang chay...");
			Cach1 t = new Cach1();
			t.start();

			String a = scanner.nextLine();
			System.out.println(a);
			if (isLoading == true && a.equals("")) {
				isLoading = false;
				System.out.println("pause video");

				t.stop();

			}

			String b = scanner.nextLine();
			System.out.println(b);
			if (isLoading == false && b.equals("")) {
				isLoading = true;

			}
		}

	}

}
