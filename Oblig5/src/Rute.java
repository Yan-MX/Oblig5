import java.util.ArrayList;
import java.util.List;

public abstract class Rute {
	private Rute nord, syd, vest, ost;
	private int x, y;
	private char tegn;
	private Labyrint la;
	public List<Rute> currentRutelist = new ArrayList<Rute>();

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

	public void gaa(Liste<String> n, String currentString, Rute previousRute, Rute currentRute, Rute OriginalRute, String previousString) {
		if (!(previousRute == null)) {
			previousString = currentString;
			currentString += previousRute.coordinate() + "--> ";
		}
		if (currentRute.edge() && currentRute.tilTegn() == '.') {
			previousString = currentString;
			currentString += currentRute.coordinate();
			n.leggTil(currentString);
			currentRutelist.remove(currentRute);
			return;

		} else {
			if (currentRute.getNord().tilTegn() == '.' && currentRute.getNord().equals(previousRute) == false) {
				if (currentRutelist.contains(currentRute.getNord()) && currentRutelist.contains(OriginalRute.getNord()) == false) {
				} else {
					previousRute = currentRute;
					if (!currentRutelist.contains(currentRute.getNord())) {
						currentRutelist.add(currentRute.getNord());
					}
					gaa(n, currentString, previousRute, previousRute.getNord(), OriginalRute, previousString);
					if (currentRute.getSyd().tilTegn() == '.' || currentRute.getVest().tilTegn() == '.' || currentRute.getOst().tilTegn() == '.') {

					} else {
						currentString = previousString;
					}
				}
			}

		}

		if (currentRute.getSyd().tilTegn() == '.' && currentRute.getSyd().equals(previousRute) == false) {

			if (currentRutelist.contains(currentRute.getSyd()) && currentRutelist.contains(OriginalRute.getSyd()) == false) {

			} else {
				previousRute = currentRute;
				if (!currentRutelist.contains(currentRute.getSyd())) {
					currentRutelist.add(currentRute.getSyd());
				}
				gaa(n, currentString, previousRute, previousRute.getSyd(), OriginalRute, previousString);
				if (currentRute.getVest().tilTegn() == '.' || currentRute.getOst().tilTegn() == '.') {

				} else {
					currentString = previousString;
				}
			}
		}

		if (currentRute.getVest().tilTegn() == '.' && currentRute.getVest().equals(previousRute) == false) {
			if (currentRutelist.contains(currentRute.getVest()) && currentRutelist.contains(OriginalRute.getVest()) == false) {

			} else {
				previousRute = currentRute;
				if (!currentRutelist.contains(currentRute.getVest())) {
					currentRutelist.add(currentRute.getVest());
				}
				gaa(n, currentString, previousRute, previousRute.getVest(), OriginalRute, previousString);
				if (currentRute.getOst().tilTegn() == '.') {

				} else {
					currentString = previousString;
				}
			}
		}
		// this is to check it is a hvitrute and check it is not the last stop we have
		// been to
		if (currentRute.getOst().tilTegn() == '.' && currentRute.getOst().equals(previousRute) == false) {
			if (currentRutelist.contains(currentRute.getOst()) && currentRutelist.contains(OriginalRute.getOst()) == false) {

			} else {
				previousRute = currentRute;
				if (!currentRutelist.contains(currentRute.getOst())) {
					currentRutelist.add(currentRute.getOst());
				}
				gaa(n, currentString, previousRute, previousRute.getOst(), OriginalRute, previousString);
				currentString = previousString;

			}
		}

	}

	public void finnUtvei(Liste<String> t) {
		String currentString = "";
		Rute previousRute = null;
		Rute currentRute = this;
		currentRutelist.add(currentRute);
		this.gaa(t, currentString, previousRute, currentRute, currentRute, currentString);
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
