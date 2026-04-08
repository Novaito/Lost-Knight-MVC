package up.l3info.LostKnight.model;

import up.l3info.LostKnight.model.core.character.Hero;
import up.l3info.LostKnight.mvc.Model;

public class HeroModel implements Model {

	private Hero hero;

	@Override
	public void run() {}

	public HeroModel(Hero hero) {
		this.hero = hero;
	}

	public int posXValue() {
		return hero.getPosX();

	}

	public int posYValue() {
		return hero.getPosY();
	}

	public double hpPercentage() {
		return (double)hero.getHp() / hero.getMaxHp();
	}

	public int hpValue() {
		return hero.getHp();

	}

	public String nameValue() {
		return hero.getName();
	}

	public String dialogValue() {
		return hero.getDialog();
	}
}
