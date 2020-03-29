import java.util.ArrayList;
import java.util.List;

public abstract class Rute {
	private Rute nord, syd, vest, ost;
	private int x, y;
	private char tegn;
	private Labyrint la;
	private List<Rute> currentRutelist = new ArrayList<Rute>();

	public Rute(int a, int b, char c, Labyrint g) {
//		this x is actually colNum, y is actually a row num
		x = a;
		y = b;
		tegn = c;
		setLabyrint(g);
	}

	public abstract char tilTegn();

	public String coordinate() {
		return "(" + x + "," + y + ")";
	}

	public void gaa(Liste<String> n, String currentString, Rute previousRute, Rute currentRute, String previousString) {
		if (!(previousRute == null)) {
			previousString = currentString;
			currentString += previousRute.coordinate() + "--> ";
		}
		if (currentRute.edge() && currentRute.tilTegn() == '.') {
			previousString = currentString;
			currentString += currentRute.coordinate();
			n.leggTil(currentString);
//			System.out.println("NEW STRING: " + currentString);
//			System.out.println(" Right now the size of ruteliste" + currentRutelist.size());
//			System.out.println();
//			for (Rute i : currentRutelist) {
//				System.out.print(i.coordinate());
//			}
//			System.out.println();
			currentRutelist.remove(currentRute);
			return;

		} else {
			if (currentRute.getNord().tilTegn() == '.' && currentRute.getNord().equals(previousRute) == false) {
				//check if the rute is going to a old path that it has already been to
				// if no, then add this Rute into the currentRudelist
				// if yes, then it means the Rute has been here before and need to go one step back????
				// when it goes to a dead end, this helps to go back to a next available intersection
				if (!currentRutelist.contains(currentRute.getNord())) {
					previousRute = currentRute;
					currentRutelist.add(currentRute.getNord());
					gaa(n, currentString, previousRute, previousRute.getNord(),  previousString);
					// check if this has a next, if not, then close this intersection, turn the sign into'#'
					//if it has a next, then go to next direction
					if(!CurrentRuteHasNext(currentRute)) {
						
						currentString = previousString;
						currentRutelist.remove(currentRute);
						currentRute.setTegn('#');
					}
				}
			}
			if (currentRute.getSyd().tilTegn() == '.' && currentRute.getSyd().equals(previousRute) == false) {

				if (!currentRutelist.contains(currentRute.getSyd())) {
					previousRute = currentRute;
					currentRutelist.add(currentRute.getSyd());
					gaa(n, currentString, previousRute, previousRute.getSyd(),  previousString);
					if(!CurrentRuteHasNext(currentRute)) {		
						currentString = previousString;
						currentRutelist.remove(currentRute);
						currentRute.setTegn('#');
					}
				}
			}
			if (currentRute.getVest().tilTegn() == '.' && currentRute.getVest().equals(previousRute) == false) {
				if (!currentRutelist.contains(currentRute.getVest())) {
					previousRute = currentRute;
					currentRutelist.add(currentRute.getVest());
					gaa(n, currentString, previousRute, previousRute.getVest(), previousString);
					if(!CurrentRuteHasNext(currentRute)) {					
						currentString = previousString;
						currentRutelist.remove(currentRute);
						currentRute.setTegn('#');
					}
				}

			}
			if (currentRute.getOst().tilTegn() == '.' && currentRute.getOst().equals(previousRute) == false) {
				if (!currentRutelist.contains(currentRute.getOst())) {
					previousRute = currentRute;
					currentRutelist.add(currentRute.getOst());
					gaa(n, currentString, previousRute, previousRute.getOst(),  previousString);
					if(!CurrentRuteHasNext(currentRute)) {
					currentString = previousString;
					currentRutelist.remove(currentRute);
					currentRute.setTegn('#');
					}
				}
			}
		}
		// kill and delete the dead end rute
		currentRutelist.remove(currentRute);
	}
	public boolean CurrentRuteHasNext(Rute c) {
		if (c.getSyd().tilTegn() == '.' || c.getVest().tilTegn() == '.'
				|| c.getOst().tilTegn() == '.' || c.getNord().tilTegn() == '.') {
			return true;
		}else {
			return false;
		}
	}
	public void finnUtvei(Liste<String> t) {
		String currentString = "";
		Rute previousRute = null;
		Rute currentRute = this;
		currentRutelist.add(currentRute);
		this.gaa(t, currentString, previousRute, currentRute,  currentString);
		currentRutelist.clear();
	}

	public boolean edge() {
		if (getX() == 0 || getY() == 0 || getX() == getLabyrint().getColNum() - 1
				|| getY() == getLabyrint().getRowNum() - 1) {
			return true;
		} else {
			return false;
		}
	}

	public Rute getNord() {
		return nord;
	}

	public void setNord(Rute nord) {
		this.nord = nord;
	}

	public Rute getSyd() {
		return syd;
	}

	public void setSyd(Rute syd) {
		this.syd = syd;
	}

	public Rute getVest() {
		return vest;
	}

	public void setVest(Rute vest) {
		this.vest = vest;
	}

	public Rute getOst() {
		return ost;
	}

	public void setOst(Rute ost) {
		this.ost = ost;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public char getTegn() {
		return tegn;
	}

	public void setTegn(char tegn) {
		this.tegn = tegn;
	}

	public Labyrint getLabyrint() {
		return la;
	}

	public void setLabyrint(Labyrint la) {
		this.la = la;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Rute))
			return false;
		Rute other = (Rute) obj;
		if (nord == null) {
			if (other.nord != null)
				return false;
		} else if (!nord.equals(other.nord))
			return false;
		if (ost == null) {
			if (other.ost != null)
				return false;
		} else if (!ost.equals(other.ost))
			return false;
		if (syd == null) {
			if (other.syd != null)
				return false;
		} else if (!syd.equals(other.syd))
			return false;
		if (tegn != other.tegn)
			return false;
		if (vest == null) {
			if (other.vest != null)
				return false;
		} else if (!vest.equals(other.vest))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}
