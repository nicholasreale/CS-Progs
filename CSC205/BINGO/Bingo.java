import java.util.*;
public class Bingo{
	private static int numspicked=0;
	private static String nums= "";
	private static String winType;
        public static void main(String[] args){
                Play();
        }
        static void Play(){
                Scanner in = new Scanner(System.in);
                System.out.println("how many players do you want?");
                int numPlayers = in.nextInt();
                int Card[][][] = genBingo(numPlayers);
				System.out.println(printCard(Card));
				int randInt, pwin=-1;
				while (pwin == -1) {
					randInt = randNum();
					Card = checkCard(Card, randInt);
					pwin = checkWin(Card);
				}
				System.out.println("The winner is Player " + (pwin+1) + ". It took " + numspicked +" tries to win");
				System.out.println("It was a " + winType + "Win." );
				System.out.println("The numbers that picked are as follows: ");
				System.out.println(nums);
				System.out.println("The cards look like:");
				System.out.println(printCard(Card));
				in.close();
        }
        static int randNum(){
    		int randInt;
        	while (true) {
        		randInt= (int) (Math.random() * 74+1);
        		BitSet numAllow = new BitSet(74);
        		if(numAllow.get(randInt)== false) {
        			numAllow.set(randInt);
        			numspicked++;
        			if(randInt < 9) {
        				nums+=" ";
        			}
        			nums += " " + Integer.toString(randInt);
        			if((numspicked % 10) == 0) {
        				nums+= "\r\n";
        			}
        			break;
        		}
        	}
        	return randInt;
        }
        static int[][][] genBingo(int numPlayer){
            int choice;    
        	BitSet numAllow = new BitSet(74);
                int output[][][] = new int[numPlayer][5][5];
                for(int z = 0; z < numPlayer; z++){
                        for(int y = 0; y < 5; y++){
                                for (int x = 0;x < 5;x++){
                                        while( true )
                                        {
                                                choice = (int) (Math.random() * 74 + 1);
                                                if(numAllow.get(choice) == false){
                                                        numAllow.set(choice);
                                                        break;
                                                }
                                        }
                                        output[z][y][x]=choice;
                                }
                        }
                        numAllow.clear();
                }
                return output;
        }
        static String printCard(int[][][] bcard){
                String output ="";
                for(int z=0; z< bcard.length;z++){
                        for (int y = 0; y < 5 ; y++){
                                for(int x = 0; x < 5 ;x++){
                                        if (bcard[z][y][x] != -1){
                                        		if (bcard[z][y][x] < 10) {
                                        			output += " ";
                                        		}
                                                output += " " + bcard[z][y][x];
                                        }
                                        else{
                                                output += "  X";
                                        }

                                }
                                output += "\r\n";
                        }
                        output += "card:" + (z+1) + "\r\n";
                }
                return output;
        }
        static int[][][] checkCard(int[][][] bcard,int numPick) {
            for(int z = 0; z < bcard.length ; z++){
            	for(int y = 0; y < 5; y++){
            		for (int x = 0;x < 5;x++){
                       if(numPick == bcard[z][y][x]) {
                    	   bcard[z][y][x]=-1;
                       }
                    }
            	}
            }
            return bcard;
        }
        static int checkWin(int[][][] bcard) {
        	int pWin=-1;
        	int collum,row;
        	int correct=0;
        	for(int z=0; z<bcard.length;z++) {
        		collum= 0;
        		row = 0;
        		correct=0;
            		for(int i=0; i!=5;i++) {
            			if(bcard[z][collum][row]==-1) {
            				correct++;
            			}
            			row++;
            			collum++;
            			if(correct==5) {
            				winType = "Diagonal ";
            				pWin= z;
            			}
            		}
            		row = 4;
            		collum = 0;
            		correct=0;
            		for(int i=0; i!=5;i++) {
            			if(bcard[z][collum][row]==-1) {
            				correct++;
            			}
            			row-=1;
            			collum++;
            			if(correct==5) {
            				winType = "Backwards Diagonal ";
            				pWin= z;
            			}
            		}
            		for (int r = 0; r != 5; r++) {
						collum = 0 ;
						correct = 0 ;
						for (int i = 0; i < 5; i++) {
							if (bcard[z][collum][r]== -1) {
								correct++;
							}
							collum++;
							if (correct == 5) {
								winType= "collum " + Integer.toString((i+1)) + " ";
								pWin=z;
							}
						}
					}
            		for (int r = 0; r != 5; r++) {
						collum = 0 ;
						correct = 0 ;
						for (int i = 0; i < 5; i++) {
							if (bcard[z][r][collum]== -1) {
								correct++;
							}
							collum++;
						if(correct==5) {
							winType= "row " + Integer.toString((i+1)) + " ";
							pWin=z;
						}
						}
					}
            }
        	return pWin;
        }
}