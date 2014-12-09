/**
 * Igrica Brodovi se igra tako sto racunar napravi 5 random mjesta gdje bi se brodovi napravili i korisnik pogadja mjesta
 * Brodovi se ne mogu doticati i zauzimaju samo jedno mjesto
 * Korisnik unosi jednu po jednu kooordinatu. Prvo broj kolone, pa onda broj reda
 * @author User Emina Muratovic
 *
 */
public class Brodovii {
	public static void main(String[] args) {
		int[][] mat = new int[5][5];
		int[][] m = new int [5][5];
		mat = napraviMatricu(5, 5);
		ispisiMatricu(mat);
		mat = rasporediBrodove(mat);
		ispisiMatricu(mat);
		igraj(mat, m);
		
	}
	/**
	 * pravi matricu
	 * @param red int broj redova
	 * @param kolone int broj kolona
	 * @return vraca matricu
	 */
	public static int[][] napraviMatricu(int red, int kolone)
	{
		int[][] mat = new int[red][kolone];
		
		return mat;
	}
	/**
	 * ispisuje matricu
	 * @param mat int dvodimenzionalni niz, matrica
	 */
	public static void ispisiMatricu(int[][] mat)
	{
		System.out.print("   ");
		for(int i = 0; i < mat.length; i++)
			System.out.printf(" %2d ", i);
		System.out.println();
		for(int i = 0; i < mat.length; i++)
			{
			System.out.printf(" %d ", i);
			System.out.print("|");
			for(int j = 0; j < mat[0].length; j++)
				System.out.printf("%2d |", mat[i][j]);
			System.out.println();
			}
		System.out.println();
	}
	/**
	 * nalazi neki random broj u zadanom intervalu
	 * @param poc int pocetak intervala
	 * @param kraj int kraj intervala
	 * @return neki random broj
	 */
	public static int getRandom(int poc, int kraj)
	{
		int broj = (int)(poc+Math.random() * (kraj-poc));
		return broj;
	}
	/**
	 * rasporedjuje brodove, ukupno ih je 5, ne smiju dodirivati jedan drugog
	 * @param mat int dvodimenzionalni niz, matrica, tabela brodova
	 * @return nova matrica sa brodovima
	 */
	public static int[][] rasporediBrodove(int[][] mat)
	{
		int brBrodova=5;
		int x, y;
		while(brBrodova > 0)
		{
			x = getRandom(0, 5);
			y = getRandom(0, 5);
			if(mat[x][y] == 0 && okolo(mat, x, y) == true)
			{
				mat[x][y] = -1;
				brBrodova--;
			}
		}
		return mat;
	}
	/**
	 * Funkcija prati da li su oko nekog polja sve nule
	 * @param mat zadana matrica
	 * @param x prva koordinata, broj kolone
	 * @param y druga koordinata, broj reda
	 * @return true ili false
	 */
	private static boolean okolo(int[][] mat, int x, int y) {
		
		if(x != 0 && y != 0 && x != mat.length-1 && y != mat.length-1) // sredina tabele
		{
			if(mat[x-1][y-1] == 0 && mat[x-1][y] == 0 && mat[x-1][y+1] == 0 && mat[x][y-1] == 0 && mat[x][y+1] == 0 && mat[x+1][y-1] == 0 && mat[x+1][y] == 0 && mat[x+1][y+1] == 0) 
			return true;
		}
		if(x == 0 && y == 0) // prvi element
			{
			if(mat[x][y+1] == 0 && mat[x+1][y] == 0 && mat[x+1][y+1] == 0)
				return true;
			}
		if(y != mat.length-1 && x == 0 && y != 0) // ostatak prvog reda bez zadnjeg i prvog elementa
		{
			if(mat[x][y+1] == 0 && mat[x+1][y] == 0 && mat[x+1][y+1] == 0 && mat[x][y-1] == 0 && mat[x+1][y-1] == 0)
				return true;
		}
		if(x == 0 && y == mat.length-1) // prva linija, zadnji elemenat
		{
			if(mat[x][y-1] == 0 && mat[x+1][y] == 0 && mat[x+1][y-1] == 0)
				return true;
		}
		if(x != mat.length-1 && y == 0 && x != 0 ) //prva kolona, bez zadnjeg i prvog elementa
		{
			if(mat[x][y+1] == 0 && mat[x-1][y] == 0 && mat[x-1][y+1] == 0 && mat[x+1][y] == 0 && mat[x+1][y+1] == 0)
				return true;
		}
		if(x == mat.length-1 && y == 0) // zadnji elemenat prve kolone
		{
			if(mat[x-1][y] == 0 && mat[x-1][y+1] == 0 && mat[x][y+1] == 0)
				return true;
		}
		if(x== mat.length-1 && y!=0 && y!=mat.length-1) // zadnji red bez prvog i zadnjeg elementa
		{
			if(mat[x-1][y-1] == 0 && mat[x-1][y] == 0 && mat[x-1][y+1] == 0 && mat[x][y-1] == 0 && mat[x][y+1] == 0)
				return true;
		}
		if(x == mat.length-1 && y == mat.length-1) // zadnji elemenat zadnjeg reda
		{
			if(mat[x-1][y-1] == 0 && mat[x][y-1] == 0 && mat[x-1][y] == 0)
				return true;
		}
		if(y == mat.length-1 && x != 0 && x != mat.length-1) // zadnja kolona bez prvog i zadnjeg elementa
		{
			if(mat[x-1][y-1] == 0 && mat[x-1][y] == 0 && mat[x][y-1] == 0 && mat[x+1][y-1] == 0 && mat[x+1][y] == 0)
				return true;
		}
		return false;
		
	}
	/**
	 * Korisnik pomocu ove funkcije igra igricu
	 * Bira polje i ukoliko pogodi brod ispisuje se poruka
	 * Ukoliko korisnik pogodi prazno mjesto, igra se nastavlja sve dok se ne pogode svi brodovi
	 * Na kraju igrice korisnik vidi sa koliko je pokusaja zavrsio igru
	 * @param mat tabela sa brodovima
	 */
	public static void igraj(int[][] mat, int[][] m)
	{
		int x, y, pokusaj=0, pogodak=0;
		do{
			do{ System.out.println("Unesite broj kolone : ");
			x = TextIO.getInt();
			System.out.println("Unesite broj reda : ");
			y = TextIO.getInt();
			ispisiTabelu(x, y, mat);
			if(provjeraOtvorenogPolja(x, y, mat, m) == true)
			{
				System.out.println("To polje ste vec jednom unijeli! Birajte ponovo! ");
			}
			
			
			else
				{
				pokusaj++;
				if(mat[x][y] == -1) 
				{
				pogodak++;
				System.out.println("Bravo ! Pogodili ste brod! Ostalo vam je jos " + (5-pogodak) + " brodova!");
				}
			if(pogodak == 5) System.out.println("Cestitamo! Pobijedili ste! Imali ste " + pokusaj + " pokusaja.");
			if(mat[x][y] == 0)
			{
				System.out.println("Niste pogodili brod! Nastavite pokusavati! ");
			}}
			}while(provjeraOtvorenogPolja(x, y, mat, m) == true);
		}while(pokusaj != 5);
	}
	/**
	 * funkcija pamti unesena polja
	 * @param x prva koordinata, broj kolone
	 * @param y druga koordinata, broj reda
	 * @param mat tabela sa brodovima
	 * @param m prazna tabela u koju se spasavaju unesena polja
	 * @return vraca zadano polje 
	 */
	public static int matricaSaUnesenimPoljima(int x, int y, int[][] mat, int[][] m)
	{
		if(m[x][y] != 2) 
		{
			m[x][y] = 2;
			return mat[x][y];
		}
		return m[x][y];
	}
	/**
	 * funkcija provjerava da li je polje vec otvoreno
	 * @param x prva koordinata, broj kolone
	 * @param y druga koordinata, broj reda
	 * @param mat tabela sa brodovima
	 * @param m prazna tabela u koju se spasavaju unesena polja
	 * @return true ili false
	 */
	public static boolean provjeraOtvorenogPolja(int x, int y, int[][] mat, int[][] m)
	{
		if(matricaSaUnesenimPoljima(x, y, mat, m) == 2) return true;
		return false;
	}
	/**
	 * ispisuje praznu tabelu koja se koristi za igranje
	 * @param x prva koordinata, broj kolone
	 * @param y druga koordinata, broj reda
	 * @param mat vraca tabelu sa unesenim poljem
	 */
	private static void ispisiTabelu(int x, int y, int[][]  mat) {
		int broj;
		String[][] m = new String[mat.length][mat.length];
		System.out.print("   ");
		for(int i = 0; i < mat.length; i++)
			System.out.printf(" %2d ", i);
		System.out.println();
		for(int i = 0; i < m.length; i++)
		{
			System.out.printf(" %d ", i);
			System.out.print("|");
		for(int j = 0; j < m[0].length; j++)
			{
			
			m[i][j] = " ";
			if(i == x && j == y) 
				{
				broj = mat[i][j];
				m[i][j] = "";
				m[i][j] += broj;
				}
			
			System.out.printf("%2s |", m[i][j]);
			
			}
		System.out.println();
		}
	System.out.println();
	}
		
	}