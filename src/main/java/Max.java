import java.io.*;
import java.util.*;
	class Max{
		public static  int MAX_X;
		public static  int MAX_Y;
		public static int SIZE;
		//public static  int MAX_ANTS;
		public static int MAX_ANTS_USER;
		public static int DELAY_USER;
		public static  int MOVES;
		//public static int LEAVES;

		public static void setLeavesUser(int leavesUser) {
			LEAVES_USER = leavesUser;
		}

		public static int LEAVES_USER;
		public static int FIELDS;
		//public static int DELAY;
		static void setMax(int X,int Y,int ants,int moves,int size,int leaves,int delay){
			MAX_X=X;
			MAX_Y=Y;
			//MAX_ANTS=ants;
			MOVES=moves;
			SIZE=size;
			LEAVES_USER=leaves;
			FIELDS=SIZE*SIZE;
			//DELAY=delay;
			DELAY_USER=delay;
			MAX_ANTS_USER=ants;
		}
		static void setDelayUser(int delay){
			DELAY_USER=delay;
		}
		static void setMaxAntsUser(int maxAnts){
			MAX_ANTS_USER=maxAnts;
		}
	}
