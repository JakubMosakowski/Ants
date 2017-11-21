import java.io.*;
import java.util.*;

	class Ant{
		int x=0;
		int y=0;
		int move=0;
		int id=0;
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
		Ant(int A,int B){
			x=A;
			y=B;
			id++;
			name=name+String.valueOf(id);
		}

		Ant(){}
		public void spawn(){
			Random ranGen=new Random();
			x=ranGen.nextInt(Max.MAX_X-2);
			x++;
			y=ranGen.nextInt(Max.MAX_Y-2);
			y++;
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
				x=checkIfAbove(x,Max.MAX_X);
				break;
			case 4:
				y=checkIfAbove(y,Max.MAX_Y);
				break;
			}
			move++;

		}
		public int checkIfUnder(int num){
			if(num==1)
				return num;
			else
				return num-1;
		}
		public int checkIfAbove(int num,int max){
			if(num==(max-2))
				return num;
			else
				return num+1;
		}

	}
