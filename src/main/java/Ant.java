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
		String name;

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
			int movX=x;
			int movY=y;
			Random ranGen=new Random();
			int number =ranGen.nextInt(4);
			switch (number){
				case 0:
					movX=checkIfUnder(movX);
					break;
				case 1:
					movY=checkIfUnder(movY);
					break;
				case 2:
					movX=checkIfAbove(movX);
					break;
				case 3:
					movY=checkIfAbove(movY);
					break;
			}
			if(checkIfCanGoThere(movX,movY))
			{
				x=movX;
				y=movY;
			}

			move++;

		}
	private int checkIfUnder(int num){
			if(num==0)
				return num;
			else
				return num-1;
		}
		private int checkIfAbove(int num){
			if(num==(Max.SIZE-1))
				return num;
			else
				return num+1;
		}
		private boolean checkIfCanGoThere(int x,int y){

			if(Board.boardSquaresTags[x][y]!=Ant.TYPE
					&& Board.boardSquaresTags[x][y]!=Queen.TYPE
					&& Board.boardSquaresTags[x][y]!=Anthill.TYPE){
				return true;
			}else
				return false;

		}


	}
