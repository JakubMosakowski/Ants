import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class Ant{
		protected int x;
		protected int y;
		protected int move;
		protected static final AtomicInteger count = new AtomicInteger(0);
		private final int id;
		protected boolean holdsLeaf;
		 static final   char TYPE='A';
		int health;
		int attack;
		String name="ImieMrówkiNr";

		public int getMove(){
			return move;
		}
		public int getX(){
			return x;
		}
		public int getY(){
			return y;
		}
		Ant(){
			holdsLeaf=false;
			randomLocation();
			id=count.incrementAndGet();
			name=name+String.valueOf(id);
			//TODO remove line below, only checking
			System.out.println(name);
		}
		protected void setStats(int hp,int dmg){
			health=hp;
			attack=dmg;
		}
		protected void setName(String n){
			name=n;
		}


		protected void randomLocation() {
			Random gen = new Random();
			x=gen.nextInt(Max.SIZE);
			y=gen.nextInt(Max.SIZE);
		}


		public boolean antDies(){
			if(move>=Max.MOVES)
				return true;
			else
				return false;
		}
		public void move(){
			Random ranGen=new Random();
			int number =ranGen.nextInt(5);
			switch (number){
			case 0:
				break;
			case 1:
				x=checkIfUnder(x);
				break;
			case 2:
				y=checkIfUnder(y);
				break;
			case 3:
				x=checkIfAbove(x);
				break;
			case 4:
				y=checkIfAbove(y);
				break;
			}
			move++;

		}
		public int checkIfUnder(int num){
			if(num==0)
				return num;
			else
				return num-1;
		}
		public int checkIfAbove(int num){
			if(num==(Max.SIZE-1))
				return num;
			else
				return num+1;
		}

	}
