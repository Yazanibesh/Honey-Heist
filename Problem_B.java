import java.util.ArrayList;
import java.util.Scanner;

public class Problem_B {
	
	static ArrayList<Cell> cells=new ArrayList<>();
	public static void main(String[] args) {

		 ArrayList<Integer> blocked=new ArrayList<>();
		// TODO Auto-generated method stub
		String line_1=getInput();
		String line_2=getInput();
		String[] in_1=line_1.split(" ");
		String[] in_2=line_2.split(" ");
	
		int R=Integer.parseInt(in_1[0]);//length of cells in each grid of edge
		int N=Integer.parseInt(in_1[1]);//Max number of cell can be chewed
		int A=Integer.parseInt(in_1[2]);////Starting cell ID
		int B=Integer.parseInt(in_1[3]);//Cell ID for containing honey
		int X=Integer.parseInt(in_1[4]);//Number of wax hardened cells
	
		 int i=0;
		 while(i < X) {
			 blocked.add(Integer.parseInt(in_2[i]));
			 i=i+1;
		 }
		 
		 
		 int total_cells=(int) (Math.pow(R, 3) - Math.pow(R-1, 3));
		i=0;
		//Initializing all the cells with there ids and parameters
		
		while(i < total_cells) {
			int id=i+1;
			Cell c=new Cell(id,blocked.contains(id),B);
			cells.add(c);
			i=i+1;
		}
		//calling method to get if and can chew or not 
		int b=getK(A,B,R,N,total_cells);
		if(b==-1) {//if it can not be chewed
			
			System.out.println("No");
		}
		else {//otherwise number of cells which are chewed
			System.out.println(b);
		}
		
		

	}
	//method to get input from console 
	public static String getInput() {
		Scanner scan=new Scanner(System.in);
		String ret=null;
		while(ret==null) {
			ret=scan.nextLine();
		}
		return ret;
	}
	//method to calculate the cells chewed 
	public static int getK(int id,int dest,int R,int N,int total) {
		
		if(getLocation(id,R).equals("up_left")) {
		int temp=id;
		int chewed=0;
		int rows=(2*R)-1;
		int inc=1;
		int rowpointer=1;
		int outerpointer=rowpointer;
		int attempt=0;
		int previd=id;
		while(attempt < R) {//outer loop to change the cells at border
			if(attempt !=0) {//adjusting the starting cell for each iteration
				chewed=attempt+1;
			outerpointer=outerpointer+1;
				if(outerpointer <= (rows/2)+1) {
					if(outerpointer == (rows/2)+1) {
						inc=rows/2;
					}else {
						inc=outerpointer;
					}
					temp=previd+R+outerpointer-2;
					previd=temp;
				}
				else {
					break;
				}
			}
			rowpointer=outerpointer;
		while(temp <= total && rowpointer <= rows) {//Inner loop to dig into the cells under the starting cell until honey found or blocked cell found
			//System.out.println("Attempting "+temp);			
			if(chewed < N && cells.get(temp-1).honey) {//check if honey is found 
				return chewed;
			}
			if(cells.get(temp-1).block) {//breaks the loop if ant meets a blocked cell
				break;
			}
			//save variable states here first before entering into the loop
			//in this loop we are checking in right and left 
			int checker=N-chewed;
			int temp_b=temp;
			int chew=chewed;
			while(checker >= 0) {//loop to check right and left side of a cell which ant is chewing 
				
				if(dest > temp_b) {
					if(cells.get(temp_b).block) {
						break;
					}
					if(cells.get(temp_b).honey) {
						return chew;
					}else
						chew=chew+1;
					temp_b=temp_b+1;
				}
				else
					if(dest < temp_b) {}
				checker=checker-1;
				
			}
			
			
			//here we are calculating the next cell to be chewed 
			if(rowpointer < rows/2) {
				temp=temp+(R+inc);
				inc=inc+1;
				chewed=chewed+1;
				rowpointer=rowpointer+1;
				}
			else
				if(rowpointer == rows/2 || rowpointer == (rows/2)+1) {
					temp=temp+(R+inc);
					chewed=chewed+1;
					rowpointer=rowpointer+1;
					}
				else
					if(rowpointer > (rows/2)+1) {
						inc=inc-1;
						temp=temp+(R+inc);
						chewed=chewed+1;
						rowpointer=rowpointer+1;
									
					}
			//next cell calculation end here
			}
		//inner digger loop ends here
attempt=attempt+1;	
		}
	}
		
		else
			if(getLocation(id,R).equals("up")) {
				int temp=id;
				int chewed=0;
				int rows=(2*R)-2;
				int inc=1;
				int rowpointer=1;
				int outerpointer=rowpointer;
				int attempt=0;
				int previd=id;
				
				while(attempt < R-1) {//outer loop to change the cells at border
					if(attempt !=0) {//adjusting the starting cell for each iteration
						chewed=attempt+1;
					temp=previd+1;
					inc=1;
					previd=temp;
					rowpointer=1;
						
					}
					
						while(temp <= total && rowpointer <= rows-1) {//Inner loop to dig into the cells under the starting cell until honey found or blocked cell found
								
					//System.out.println("NO visiting "+temp);
					if(chewed < N && cells.get(temp-1).honey) {//check if honey is found 
						return chewed;
					}
					if(cells.get(temp-1).block) {//breaks the loop if ant meets a blocked cell
						break;
					}
					//save variable states here first before entering into the loop
					//in this loop we are checking in right and left 
					int checker=N-chewed;
					int temp_b=temp;
					int chew=chewed;
					while(checker >= 0) {//loop to check right and left side of a cell which ant is chewing 
						
						if(dest > temp_b) {
							if(cells.get(temp_b).block) {
								break;
							}
							if(cells.get(temp_b).honey) {
								return chew;
							}else
								chew=chew+1;
							temp_b=temp_b+1;
						}
						else
							if(dest < temp_b) {}
						checker=checker-1;
						
					}
					
					
					//here we are calculating the next cell to be chewed 
					if(rowpointer < rows/2) {
						temp=temp+(R+inc);
						inc=inc+1;
						chewed=chewed+1;
						rowpointer=rowpointer+1;
						}
					else
						if(rowpointer == rows/2 || rowpointer == (rows/2)+1) {
							temp=temp+(R+inc);
							chewed=chewed+1;
							rowpointer=rowpointer+1;
							}
						else
							if(rowpointer > (rows/2)+1) {
								inc=inc-1;
								temp=temp+(R+inc);
								chewed=chewed+1;
								rowpointer=rowpointer+1;
											
							}
					//next cell calculation end here
					}
				//inner digger loop ends here
		attempt=attempt+1;	
				}
			}
		
		
		
	//if honey can not be found 	
		return -1;
	}
	
	
	//Method to get the location of starting cell
	public static String getLocation(int id,int R) {
		String ret=null;
		if(id==1) {
			ret="up_left";
		}
		else
			if(id >= 1 || id <= R) {
				ret="up";
			}
		
		
		
		return ret;
	}

}
//class which is used as a cell it contains all the possible values a cell can have
// id, hone, blocked 
class Cell {
	int id;
	boolean block=false;
	boolean honey=false;
	public Cell(int cell_id,boolean blocked,int h) {
		this.id=cell_id;
		this.block=blocked;
		if(h==cell_id) {
			this.honey=true;
		}
		
		
	}
	
	
}

