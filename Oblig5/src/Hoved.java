import java.io.File;

public class Hoved {

	public static void main(String[] args) {
		File a = new File("1.in");
		Labyrint d = Labyrint.lesFraFil(a);
		System.out.println(d);
		Rute r = d.getRute(6, 1);
		System.out.println(r.getNord().tilTegn());
		System.out.println(r.getSyd().tilTegn());
		System.out.println(r.getVest().tilTegn());
		System.out.println(r.getOst().tilTegn());
		d.printMazeC();
//		d.finnUtveiFra(1, 2);
	}
	
}
