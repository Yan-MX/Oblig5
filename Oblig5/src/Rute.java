import java.util.ArrayList;
import java.util.List;

public abstract class Rute {
	private Rute nord, syd, vest, ost;
	private int x, y;
	private char tegn;
	private Labyrint la;
	public List<Rute> golist = new ArrayList<Rute>();

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

	public void gaa(Liste<String> n, String s, Rute past, Rute go, Rute stay, String spast) {
		if (!(past == null)) {
			spast = s;
			s += past.coordinate() + "--> ";
		}
		if (go.edge() && go.tilTegn() == '.') {
			spast = s;
			s += go.coordinate();
			n.leggTil(s);
			golist.remove(go);
			return;

		} else {
			if (go.getNord().tilTegn() == '.' && go.getNord().equals(past) == false) {
				if (golist.contains(go.getNord()) && golist.contains(stay.getNord()) == false) {
				} else {
					past = go;
					if (!golist.contains(go.getNord())) {
						golist.add(go.getNord());
					}
					gaa(n, s, past, past.getNord(), stay, spast);
					if (go.getSyd().tilTegn() == '.' || go.getVest().tilTegn() == '.' || go.getOst().tilTegn() == '.') {

					} else {
						s = spast;
					}
				}
			}

		}

		if (go.getSyd().tilTegn() == '.' && go.getSyd().equals(past) == false) {

			if (golist.contains(go.getSyd()) && golist.contains(stay.getSyd()) == false) {

			} else {
				past = go;
				if (!golist.contains(go.getSyd())) {
					golist.add(go.getSyd());
				}
				gaa(n, s, past, past.getSyd(), stay, spast);
				if (go.getVest().tilTegn() == '.' || go.getOst().tilTegn() == '.') {

				} else {
					s = spast;
				}
			}
		}

		if (go.getVest().tilTegn() == '.' && go.getVest().equals(past) == false) {
			if (golist.contains(go.getVest()) && golist.contains(stay.getVest()) == false) {

			} else {
				past = go;
				if (!golist.contains(go.getVest())) {
					golist.add(go.getVest());
				}
				gaa(n, s, past, past.getVest(), stay, spast);
				if (go.getOst().tilTegn() == '.') {

				} else {
					s = spast;
				}
			}
		}
		// this is to check it is a hvitrute and check it is not the last stop we have
		// been to
		if (go.getOst().tilTegn() == '.' && go.getOst().equals(past) == false) {
			if (golist.contains(go.getOst()) && golist.contains(stay.getOst()) == false) {

			} else {
				past = go;
				if (!golist.contains(go.getOst())) {
					golist.add(go.getOst());
				}
				gaa(n, s, past, past.getOst(), stay, spast);
				s = spast;

			}
		}

	}

	public void finnUtvei(Liste<String> t) {
		String s = "";
		Rute past = null;
		Rute go = this;
		golist.add(go);
		this.gaa(t, s, past, go, go, s);
		golist.clear();
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
