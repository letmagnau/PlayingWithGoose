package it.susca.bitrock.game;

public class Player {

	public final String name;
	public int position;
	public int score;
	public int previuscore;
	public int last;
	public int block;

	public Player(String name, int position) {
	      super();
	      this.name = name;
	      this.position = position;
	      this.last = 0;
	      this.score = 0;
	      this.previuscore=0;
	      this.block = 0;
	    }

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getPreviuscore() {
		return previuscore;
	}

	public void setPreviuscore(int previuscore) {
		this.previuscore = previuscore;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
	}

	
 
	
	

}
