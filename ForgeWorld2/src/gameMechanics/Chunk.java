package gameMechanics;

public class Chunk {
	//A class for a sub-region oft the total map with a predefined size. This is the basic building block of the map.
	
	public static final int size=8;//how many spots should be in a chunk along each direction (chunk has to be quadratic right now)
	
	/*int posX;
	int posY;*/
	
	private Spot[][] spots;//A two dimensional array of all spots in this chunk
	
	Chunk(){
		/*posX=x;
		posY=y;*/
		
		spots=new Spot[size][size];
		for(int i=0;i<size;++i){
			for(int j=0;j<size;++j){
				spots[i][j]=new Spot();//initialize spot in each position
			}
		}
	}
	
	//return a spot by its position in the array
	Spot getSpot(int x,int y){
		try{
			return spots[x][y];
		}catch(IndexOutOfBoundsException e){
			return null;
		}
	}
}
