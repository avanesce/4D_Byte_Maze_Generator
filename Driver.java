import java.util.Random;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Driver {
	static int n = 35;
	static Node[][][][] nodes = new Node[n][n][n][n];
	static int nodeCount=0;
	static PrintWriter writer;
	/************************************************/
	public static void main(String args[]) throws IOException{
		setNodes();
		
		mergeNodes();
		
		printNodes();
		System.out.println("Total Nodes "+nodeCount);
	}
	/************************************************/
	public static void setNodes(){
		for(int x = 0; x<n; x++){
			for(int y = 0; y<n; y++){
				for(int z = 0; z<n; z++){
					for(int t = 0; t<n; t++){
						nodes[x][y][z][t] = new Node();
						nodes[x][y][z][t].makeSet(nodes[x][y][z][t]);
						nodeCount++;
					}
				}
			}
		}
	}
	
	public static void mergeNodes(){
		boolean merged = false;
		int direction = 0;
		int magnitude = 0;
		int mergeCount=0;
		Random rand = new Random();
		
		for(int x = 0; x<n; x++){
			for(int y = 0; y<n; y++){
				for(int z = 0; z<n; z++){
					for(int t = 0; t<n; t++){
						//if at very last space, end
						if(x==n-1&&y==n-1&&z==n-1&&t==n-1)break;
						
						merged = false;
						while (!merged){
							//select a node and magnitude
							direction = rand.nextInt(4)+1;
							magnitude = rand.nextInt(2);
							if (magnitude==0)magnitude=(-1);
							System.out.println(direction +" " + magnitude);
							switch(direction){
								case 1:
									if(x==0)magnitude=1;
									if(x==n-1)magnitude=-1;
									merged = nodes[x][y][z][t].union(nodes[x+magnitude][y][z][t],nodes[x][y][z][t]);
									if(merged){
										if(magnitude==-1){
											nodes[x][y][z][t].walls-=128;
											nodes[x+magnitude][y][z][t].walls-=64;		
										}
										else if(magnitude==1){
											nodes[x][y][z][t].walls-=64;
											nodes[x+magnitude][y][z][t].walls-=128;
										}
									}
									break;
								case 2:
									if(y==0)magnitude=1;
									if(y==n-1)magnitude=-1;
									merged = nodes[x][y][z][t].union(nodes[x][y+magnitude][z][t],nodes[x][y][z][t]);
									if(merged){
										if(magnitude==-1){
											nodes[x][y][z][t].walls-=32;
											nodes[x][y+magnitude][z][t].walls-=16;
										}
										else if(magnitude==1){
											nodes[x][y][z][t].walls-=16;
											nodes[x][y+magnitude][z][t].walls-=32;		
										}
									}
									break;
								case 3:
									if(z==0)magnitude=1;
									if(z==n-1)magnitude=-1;
									merged = nodes[x][y][z][t].union(nodes[x][y][z+magnitude][t],nodes[x][y][z][t]);
									if(merged){
										if(magnitude==-1){
											nodes[x][y][z][t].walls-=8;
											nodes[x][y][z+magnitude][t].walls-=4;		
										}
										else if(magnitude==1){
											nodes[x][y][z][t].walls-=4;
											nodes[x][y][z+magnitude][t].walls-=8;
										}
									}
									break;
								case 4:
									if(t==0)magnitude=1;
									if(t==n-1)magnitude=-1;
									merged = nodes[x][y][z][t].union(nodes[x][y][z][t+magnitude],nodes[x][y][z][t]);
									if(merged){
										if(magnitude==-1){
											nodes[x][y][z][t].walls-=2;
											nodes[x][y][z][t+magnitude].walls-=1;
										}
										else if(magnitude==1){
											nodes[x][y][z][t].walls-=1;
											nodes[x][y][z][t+magnitude].walls-=2;
										}
									}
									break;
							}
							if(merged)mergeCount++;
						}
					}
				}
			}
		}
		System.out.println("times merged" + mergeCount);
	}
	
	public static void printNodes() throws IOException{
		String wallASCII;
		writer = new PrintWriter("maze.byte");
		
		for(int t = 0; t<n; t++){
			for(int z = 0; z<n; z++){
				for(int y = 0; y<n; y++){
					for(int x = 0; x<n; x++){
						wallASCII = Character.toString((char) nodes[x][y][z][t].walls);
						writer.print(wallASCII);
					}
				}
			}
		}
		writer.close();
	}
	
	
}
