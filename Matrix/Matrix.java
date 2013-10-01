
import java.util.Vector;
import java.lang.Double;
import java.lang.AssertionError;

public class Matrix{
	public Matrix(int row,int col){
		assert (row>0 && col>0):error.matrix;
		this.row=row;this.col=col;
		elem=new Vector<Double>();
		elem.setSize(row*col);
	}

	public Matrix(int row,int col,double init){
		assert (row>0 && col>0):error.matrix;
		this.row=row;this.col=col;
		elem=new Vector<Double>();
		elem.setSize(row*col);
		for(int i=0;i<row*col;++i)
			elem.set(i,init);			
	}

	public int GetRow(){return row;}
	public int GetCol(){return col;}
	public double GetElem(int r,int c){		
		assert (r>=0 && c>=0):error.elem1;
		assert (r<this.row && c<this.col):error.elem2;
		return elem.get(r*row+c);
		
	}
	
	public void SetElem(int r,int c,double elem){
		assert (r>=0 && c>=0):error.elem1;
		assert (r<this.row && col<this.col):error.elem2;
		this.elem.set(r*row+c,elem);
	}
	
	public Matrix Add(Matrix mat){
		assert (mat.GetRow()==row && mat.GetCol()==col):error.add;
		Matrix m=new Matrix(row,col);
		for(int i=0;i<row;++i)
			for(int j=0;j<col;++j)
				m.SetElem(i,j,mat.GetElem(i,j)+GetElem(i,j));
		return m;
	}
	
	public Matrix Sub(Matrix mat){
		assert (mat.GetRow()==row && mat.GetCol()==col):error.sub;
		Matrix m=new Matrix(row,col);
		for(int i=0;i<row;++i)
			for(int j=0;j<col;++j)
				m.SetElem(i,j,mat.GetElem(i,j)-GetElem(i,j));
		return m;
	}

	public Matrix Transform(){
		Matrix m=new Matrix(col,row);
		for(int i=0;i<row;++i)
			for(int j=0;j<col;++j)
				m.SetElem(j,i,GetElem(i,j));
		return m;
	}

	public Matrix Inverse(){
		assert (row==col):error.inverse;
		Matrix m=new Matrix(row,col);
		//gaussian-jordan
		this.CopyInto(m);
		
		int is[]=new int[row];
		int js[]=new int[col];
		
		for(int k=0;k<row;++k){
			double fMax=0.0f;
			// one step
			for(int i=k;i<row;++i){
				for(int j=k;j<row;++j){
					double f=Math.abs(m.GetElem(i,j));
					if(f>fMax){
						fMax=f;
						is[k]=i;
						js[k]=j;
					}
				}
			}
			//two step
			if(is[k]!=k){
				for(int j=0;j<col;++j){
					double temp=m.GetElem(k,j);
					m.SetElem(k,j,m.GetElem(is[k],j));
					m.SetElem(is[k],j,temp);
				}
			}
			if(js[k]!=k){
				for(int i=0;i<row;++i){
					double temp=m.GetElem(i,k);
					m.SetElem(i,k,m.GetElem(i,js[k]));
					m.SetElem(i,js[k],temp);	
				}
			}
			//three step
			m.SetElem(k,k,1.0f/m.GetElem(k,k));
			for(int j=0;j<col;++j)
				if(j!=k)
					m.SetElem(k,j,m.GetElem(k,j)*m.GetElem(k,k));
			//four step
			for(int i=0;i<row;++i){
				if(i!=k){
					for(int j=0;j<col;++j){
						if(j!=k)
							m.SetElem(i,j,m.GetElem(i,j)-m.GetElem(i,k)*m.GetElem(k,j));
					}
				}
			}
			//five step
			for(int i=0;i<row;++i){
				if(i!=k)
					m.SetElem(i,k,m.GetElem(i,k)-m.GetElem(k,k));
			}
		}
                //six step
		for(int k=row-1;k>=0;--k){
                      	if(js[k]!=k){
				for(int j=0;j<col;++j){
 					double temp=m.GetElem(k,j);
					m.SetElem(k,j,m.GetElem(js[k],j));
					m.SetElem(js[k],j,temp);
                        	}
			}
			 if(is[k]!=k){
                        	for(int i=0;i<row;++i){
					double temp=m.GetElem(i,k);
					m.SetElem(i,k,m.GetElem(i,is[k]));
					m.SetElem(i,is[k],temp);
				} 
			}
                 }
		return m;
	}
		
	public Matrix Mult(Matrix mat){
		assert (col==mat.GetRow()):error.mult;
		Matrix m=new Matrix(row,mat.GetCol());
		for(int i=0;i<row;++i)
			for(int j=0;j<mat.GetCol();++j)
				for(int k=0;k<col;++k)
					m.SetElem(i,j,this.GetElem(i,k)*mat.GetElem(k,j));
		return m;
	}	
	public void CopyInto(Matrix mat){
		assert (row==mat.GetRow() && col==mat.GetCol()):error.copyInto;
		for(int i=0;i<row;++i)
			for(int j=0;j<col;++j)
				mat.SetElem(i,j,this.GetElem(i,j));
	}
	private Vector<Double> elem;
	private int row;
	private int col; 
	
	private class Error{
		public String copyInto="row and col are not identical";
		public String mult="column of matrix is not identical to the row of matrix which will be multipled";
		public String inverse="row is not identical to col";		
		public String matrix="row or column of matrix if illegal";
		public String add="can't add two matrix with different row or col";
		public String sub="can't substract two matrix with different row or col";
		public String elem1="arguments of row or column are negative value";
		public String elem2="arguments are out of the range of this matrix";
	}
	private Error error;
}
