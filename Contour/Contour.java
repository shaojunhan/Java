import java.util.List;

public class Contour{
	
	public static List<Pair> GetContour(Matrix mat){

		List<Pair> contour=new LinkedList<Pair>();

		//scan every row and column
		for(int i=0;i<mat.GetRow();++i){
			for(int j=0;j<mat.GetCol();++j){
				if(mat.GetElem(i,j)==0)
					continue;
				if(j>0 && mat.GetElem(i,j-1)==0)
					contour.add(Pair(i,j));
				else if(j<mat.GetCol()-1 &&  mat.GetElem(i,j+1)==0)
					contour.add(Pair(i,j));
			}
		}
		return contour;
	}
}
