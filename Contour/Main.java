public class Main{
	public static void main(String args[]){
		Matrix mat=new Matrix(8,9,9);
		for(int i=0;i<mat.GetRow();++i){
			for(int j=0;j<mat.GetCol();++j)
				System.out.print(" "+mat.GetElem(i,j));
			System.out.print("\n");
		}
	}
}
