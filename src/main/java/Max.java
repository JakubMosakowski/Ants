import java.io.*;
import java.util.*;
	class Max{
		public static  int MAX_X;
		public static  int MAX_Y;
		public static int SIZE;
		public static  int MAX_ANTS;
		public static  int MOVES;
		public static int LEAVES;
		public static int FIELDS;
		static void setMax(int X,int Y,int ants,int moves,int size,int leaves){
			MAX_X=X;
			MAX_Y=Y;
			MAX_ANTS=ants;
			MOVES=moves;
			SIZE=size;
			LEAVES=leaves;
			FIELDS=SIZE*SIZE;
		}

	}
