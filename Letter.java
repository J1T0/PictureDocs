
public class Letter {
	private int x, y, width, height;
	private char val;
	
	public Letter(int x, int y, int width, int height, char val){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.val = val;
	}
	

	public int GetX(){
		return this.x;
	}
	public int GetY(){
		return this.y;
	}
	public int GetWidth(){
		return this.width;
	}
	public int GetHeight(){
		return this.height;
	}
	public char GetValue(){
		return this.val;
	}
}
