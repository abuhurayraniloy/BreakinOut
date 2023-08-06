package BlockBreaker;




import java.awt.*;

public class mapGenerator {
    protected int map[][];
    public int brickWidth;
    public int brickHeight;
    
    public mapGenerator(int row, int col){
    	
    	
    	
        map= new int[row][col];
        for(int i=0 ; i< map.length ; i++){
            for(int j=0 ; j<map[0].length ; j++){
            	
            	
            	
            	if(i==6 || i== 7 || i==0 || i==1 )
            		map[i][j]=0;
            	else 
            	{
            		if((i*i+j)%5==0 && (i+j)%2==0)
            			map[i][j]=4;
                	else if((i*i+j)%8==0 )
                		map[i][j]=2;
                	else if((i*i+j)%6==0  )
                		map[i][j]=3;
          
                	else 
                		map[i][j]=1;
            		if((j+1)%10==0)
            			map[i][j]=0;
            	}
                
                
                
                
                
                
                
                
            }
        }
        
        
        
        brickWidth= 580/col;
        brickHeight= 220/row;


    }
    
    
    
    
    
    
    
    
    // every brick er color change %3,5,8 diye change  
    
    public void draw(Graphics2D g){
        for(int i=0 ; i< map.length ; i++){
            for(int j=0 ; j<map[0].length ; j++){ 
                if(map[i][j]>0){
                	if((i*i+j)%5==0 && (i+j)%2==0)
                		g.setColor(Color.green);
                	else if((i*i+j)%8==0 )
                		g.setColor(Color.red);
                	else if((i*i+j)%6==0  )
                		g.setColor(Color.blue);
                	else 
                    g.setColor(Color.white);
                    
                    g.fillRect(j*brickWidth+80, i*brickHeight+50 , brickWidth, brickHeight);
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j*brickWidth+80, i*brickHeight+50 , brickWidth, brickHeight);
                }
            }
        }
    }

    public void setBrickValue( int row, int col){
         
    	map[row][col]--;

    }
    
    
    
    
}
