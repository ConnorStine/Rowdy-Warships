/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rowdy.warships;

import static java.lang.Thread.sleep;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CASti
 */
public class RowdyWarships {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        char[][] yGrid = new char[11][25];
        int[] tempCoords = new int[3];
        int i, j, col, iRow, iDir;
        int yPoints = 0, aiPoints = 0;
        boolean overlapCheck = true;
        boolean collision = false;
        boolean playing = true;
        char row;
        String input;
        String[] coordParse;
        boolean[][] AIShips = new boolean[9][10];
        Scanner scan = new Scanner(System.in);
        //Initialize boolean array to false
        for(i = 0; i < 9; i++){
            for(j = 0; j < 10; j++){
                AIShips[i][j] = false;
            }
        }
        
        for(i = 0; i < 11; i++){
            if(i == 0 || i == 10){
                for(j = 0; j < 25; j++){
                    if(j == 0 && i > 0 && i < 10){
                        yGrid[i][0] = (char) (i + 64);
                    }
                    else if(j == 0){
                        yGrid[i][0] = '*';
                    }
                    else if(j == 1 || j == 13){
                        if(i == 0){
                            yGrid[i][j] = '╔';
                        }
                        else{
                            yGrid[i][j] = '╚';
                        }
                    }
                    else if(j == 12 || j == 24){
                        if(i == 0){
                            yGrid[i][j] = '╗';
                        }
                        else{
                            yGrid[i][j] = '╝';
                        }
                    }
                    else{
                        yGrid[i][j] = '═'; 
                    }
                }   
            }
            else{
                for(j = 0; j < 25; j++){
                    if(j == 0 && i > 0 && i < 10){
                        yGrid[i][0] = (char) (i + 64);
                    }
                    else if(j == 1 || j == 13){
                        yGrid[i][j] = '║';
                    }
                    else if(j == 12 || j == 24){
                        yGrid[i][j] = '║';
                    }
                    else{
                        yGrid[i][j] = '░'; 
                    }
                }  
            }
        }
        //Print grids
        System.out.println("     1 2  3 4  5 6  7 8  9 10       1 2  3 4  5 6  7 8  9 10");
        for(i = 0; i < 11; i++){
            for(j = 0; j < 25; j++){
                System.out.print(yGrid[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("     This is your Fleet Grid!    This is the enemy's Fleet Grid!"
                + "\n>>AI Loading...");
        
        //AI places Carrier
        iRow = (int)(Math.random()*5);
        col = (int)(Math.random()*6);
        iDir = (int)(Math.random()*2); //Between South(1) and East(0)
        switch(iDir){
            case 1:
                //Add to boolean array
                AIShips[iRow][col] = true;
                AIShips[iRow+1][col] = true;
                AIShips[iRow+2][col] = true;
                AIShips[iRow+3][col] = true;
                AIShips[iRow+4][col] = true;
                break;
            case 0:
            default:
                //Add to boolean array
                AIShips[iRow][col] = true;
                AIShips[iRow][col+1] = true;
                AIShips[iRow][col+2] = true;
                AIShips[iRow][col+3] = true;
                AIShips[iRow][col+4] = true;
                break;
        }
        
        //AI places Battleship
        while(overlapCheck){
            iRow = (int)(Math.random()*6);
            col = (int)(Math.random()*7);
            iDir = (int)(Math.random()*2); //Between South(1) and East(0)
            switch(iDir){
                case 1:
                    for(i = iRow; i < iRow + 4; i++){
                        if(AIShips[i][col]){
                            collision = true;
                        }
                    }
                    break;
                case 0:
                default:
                    for(i = col; i < col+4; i++){
                        if(AIShips[iRow][i]){
                            collision = true;
                        }
                    }
                    break;
            }
            if(!collision){
                overlapCheck = false;
            }
            collision = false;
            try {
                TimeUnit.MILLISECONDS.sleep(510);
            } catch (InterruptedException ex) {
                Logger.getLogger(RowdyWarships.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        collision = false;
        overlapCheck = true;
        switch(iDir){
            case 1:
                //Add to boolean array
                AIShips[iRow][col] = true;
                AIShips[iRow+1][col] = true;
                AIShips[iRow+2][col] = true;
                AIShips[iRow+3][col] = true;
                break;
            case 0:    
            default:
                //Add to boolean array
                AIShips[iRow][col] = true;
                AIShips[iRow][col+1] = true;
                AIShips[iRow][col+2] = true;
                AIShips[iRow][col+3] = true;
                break;
        }
        
        //AI places Submarine
        while(overlapCheck){
            iRow = (int)(Math.random()*7);
            col = (int)(Math.random()*8);
            iDir = (int)(Math.random()*2); //Between South(1) and East(0)
            switch(iDir){
                case 1:
                    for(i = iRow; i < iRow + 3; i++){
                        if(AIShips[i][col]){
                            collision = true;
                        }
                    }
                    break;
                case 0:
                default:
                    for(i = col; i < col+3; i++){
                        if(AIShips[iRow][i]){
                            collision = true;
                        }
                    }
                    break;
            }
            if(!collision){
                overlapCheck = false;
            }
            collision = false;
            try {
                TimeUnit.MILLISECONDS.sleep(432);
            } catch (InterruptedException ex) {
                Logger.getLogger(RowdyWarships.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        collision = false;
        overlapCheck = true;
        switch(iDir){
            case 1:
                //Add to boolean array
                AIShips[iRow][col] = true;
                AIShips[iRow+1][col] = true;
                AIShips[iRow+2][col] = true;
                break;
            case 0:     
            default:
                //Add to boolean array
                AIShips[iRow][col] = true;
                AIShips[iRow][col+1] = true;
                AIShips[iRow][col+2] = true;
                break;
        }
        
        //AI places Destroyer
        while(overlapCheck){
            iRow = (int)(Math.random()*7);
            col = (int)(Math.random()*8);
            iDir = (int)(Math.random()*2); //Between South(1) and East(0)
            switch(iDir){
                case 1:
                    for(i = iRow; i < iRow + 3; i++){
                        if(AIShips[i][col]){
                            collision = true;
                        }
                    }
                    break;
                case 0:
                default:
                    for(i = col; i < col+3; i++){
                        if(AIShips[iRow][i]){
                            collision = true;
                        }
                    }
                    break;
            }
            if(!collision){
                overlapCheck = false;
            }
            collision = false;
            try {
                TimeUnit.MILLISECONDS.sleep(621);
            } catch (InterruptedException ex) {
                Logger.getLogger(RowdyWarships.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        collision = false;
        overlapCheck = true;
        switch(iDir){
            case 1:
                //Add to boolean array
                AIShips[iRow][col] = true;
                AIShips[iRow+1][col] = true;
                AIShips[iRow+2][col] = true;
                break;
            case 0:   
            default:
                //Add to boolean array
                AIShips[iRow][col] = true;
                AIShips[iRow][col+1] = true;
                AIShips[iRow][col+2] = true;
                break;
        }
        //AI places Patrol Boat
        while(overlapCheck){
            iRow = (int)(Math.random()*8);
            col = (int)(Math.random()*9);
            iDir = (int)(Math.random()*2); //Between South(1) and East(0)
            switch(iDir){
                case 1:
                    for(i = iRow; i < iRow + 2; i++){
                        if(AIShips[i][col]){
                            collision = true;
                        }
                    }
                    break;
                case 0:
                default:
                    for(i = col; i < col+2; i++){
                        if(AIShips[iRow][i]){
                            collision = true;
                        }
                    }
                    break;
            }
            if(!collision){
                overlapCheck = false;
            }
            collision = false;
            try {
                TimeUnit.MILLISECONDS.sleep(497);
            } catch (InterruptedException ex) {
                Logger.getLogger(RowdyWarships.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        collision = false;
        overlapCheck = true;
        switch(iDir){
            case 1:
                //Add to boolean array
                AIShips[iRow][col] = true;
                AIShips[iRow+1][col] = true;
                break;
            case 0:    
            default:
                //Add to boolean array
                AIShips[iRow][col] = true;
                AIShips[iRow][col+1] = true;
                break;
        }
        
        System.out.println("\nYour enemy has already placed their ships, so go ahead and pick where yours will go!");
        
        //Pick place for Carrier
        System.out.println("Which coordinate do you want your Aircraft Carrier (5 long) to go? (Use the format A-1)");
        input = scan.nextLine();
        coordParse = input.split("-", 2);
        row = coordParse[0].charAt(0);
        col = Integer.parseInt(coordParse[1]);
        System.out.println("Alright, the coordinates are set for " + row + "-" + col + ", will the boat face North, South, East, or West?");
        input = scan.nextLine();
        switch(input){
            case "north":
                yGrid[row - 64][col + 1] = '█';
                yGrid[row - 65][col + 1] = '█';
                yGrid[row - 66][col + 1] = '█';
                yGrid[row - 67][col + 1] = '█';
                yGrid[row - 68][col + 1] = '█';
                break;
            case "south":
                yGrid[row - 64][col + 1] = '█';
                yGrid[row - 63][col + 1] = '█';
                yGrid[row - 62][col + 1] = '█';
                yGrid[row - 61][col + 1] = '█';
                yGrid[row - 60][col + 1] = '█';
                break;
            case "east":
                yGrid[row - 64][col + 1] = '█';
                yGrid[row - 64][col + 2] = '█';
                yGrid[row - 64][col + 3] = '█';
                yGrid[row - 64][col + 4] = '█';
                yGrid[row - 64][col + 5] = '█';
                break;
            case "west":
                yGrid[row - 64][col + 1] = '█';
                yGrid[row - 64][col + 0] = '█';
                yGrid[row - 64][col - 1] = '█';
                yGrid[row - 64][col - 2] = '█';
                yGrid[row - 64][col - 3] = '█';
                break;
        }
        //Print grids
        System.out.println("     1 2  3 4  5 6  7 8  9 10       1 2  3 4  5 6  7 8  9 10");
        for(i = 0; i < 11; i++){
            for(j = 0; j < 25; j++){
                System.out.print(yGrid[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.println("     This is your Fleet Grid!    This is the enemy's Fleet Grid!");
        
        //Pick place for Battleship
        System.out.println("Which coordinate do you want your Battleship (4 long) to go? (Use the format A-1)");
        input = scan.nextLine();
        coordParse = input.split("-", 2);
        row = coordParse[0].charAt(0);
        col = Integer.parseInt(coordParse[1]);
        System.out.println("Alright, the coordinates are set for " + row + "-" + col + ", will the boat face North, South, East, or West?");
        input = scan.nextLine();
        switch(input){
            case "north":
                yGrid[row - 64][col + 1] = '█';
                yGrid[row - 65][col + 1] = '█';
                yGrid[row - 66][col + 1] = '█';
                yGrid[row - 67][col + 1] = '█';
                break;
            case "south":
                yGrid[row - 64][col + 1] = '█';
                yGrid[row - 63][col + 1] = '█';
                yGrid[row - 62][col + 1] = '█';
                yGrid[row - 61][col + 1] = '█';
                break;
            case "east":
                yGrid[row - 64][col + 1] = '█';
                yGrid[row - 64][col + 2] = '█';
                yGrid[row - 64][col + 3] = '█';
                yGrid[row - 64][col + 4] = '█';
                break;
            case "west":
                yGrid[row - 64][col + 1] = '█';
                yGrid[row - 64][col + 0] = '█';
                yGrid[row - 64][col - 1] = '█';
                yGrid[row - 64][col - 2] = '█';
                break;
        }
        //Print grids
        System.out.println("     1 2  3 4  5 6  7 8  9 10       1 2  3 4  5 6  7 8  9 10");
        for(i = 0; i < 11; i++){
            for(j = 0; j < 25; j++){
                System.out.print(yGrid[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.println("     This is your Fleet Grid!    This is the enemy's Fleet Grid!");
        
        //Pick place for Destroyer
        System.out.println("Which coordinate do you want your Destroyer (3 long) to go? (Use the format A-1)");
        input = scan.nextLine();
        coordParse = input.split("-", 2);
        row = coordParse[0].charAt(0);
        col = Integer.parseInt(coordParse[1]);
        System.out.println("Alright, the coordinates are set for " + row + "-" + col + ", will the boat face North, South, East, or West?");
        input = scan.nextLine();
        switch(input){
            case "north":
                yGrid[row - 64][col + 1] = '█';
                yGrid[row - 65][col + 1] = '█';
                yGrid[row - 66][col + 1] = '█';
                break;
            case "south":
                yGrid[row - 64][col + 1] = '█';
                yGrid[row - 63][col + 1] = '█';
                yGrid[row - 62][col + 1] = '█';
                break;
            case "east":
                yGrid[row - 64][col + 1] = '█';
                yGrid[row - 64][col + 2] = '█';
                yGrid[row - 64][col + 3] = '█';
                break;
            case "west":
                yGrid[row - 64][col + 1] = '█';
                yGrid[row - 64][col + 0] = '█';
                yGrid[row - 64][col - 1] = '█';
                break;
        }
        //Print grids
        System.out.println("     1 2  3 4  5 6  7 8  9 10       1 2  3 4  5 6  7 8  9 10");
        for(i = 0; i < 11; i++){
            for(j = 0; j < 25; j++){
                System.out.print(yGrid[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.println("     This is your Fleet Grid!    This is the enemy's Fleet Grid!");
        
        //Pick place for Submarine
        System.out.println("Which coordinate do you want your Submarine (3 long) to go? (Use the format A-1)");
        input = scan.nextLine();
        coordParse = input.split("-", 2);
        row = coordParse[0].charAt(0);
        col = Integer.parseInt(coordParse[1]);
        System.out.println("Alright, the coordinates are set for " + row + "-" + col + ", will the boat face North, South, East, or West?");
        input = scan.nextLine();
        switch(input){
            case "north":
                yGrid[row - 64][col + 1] = '█';
                yGrid[row - 65][col + 1] = '█';
                yGrid[row - 66][col + 1] = '█';
                break;
            case "south":
                yGrid[row - 64][col + 1] = '█';
                yGrid[row - 63][col + 1] = '█';
                yGrid[row - 62][col + 1] = '█';
                break;
            case "east":
                yGrid[row - 64][col + 1] = '█';
                yGrid[row - 64][col + 2] = '█';
                yGrid[row - 64][col + 3] = '█';
                break;
            case "west":
                yGrid[row - 64][col + 1] = '█';
                yGrid[row - 64][col + 0] = '█';
                yGrid[row - 64][col - 1] = '█';
                break;
        }
        //Print grids
        System.out.println("     1 2  3 4  5 6  7 8  9 10       1 2  3 4  5 6  7 8  9 10");
        for(i = 0; i < 11; i++){
            for(j = 0; j < 25; j++){
                System.out.print(yGrid[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.println("     This is your Fleet Grid!    This is the enemy's Fleet Grid!");
        
        //Pick place for Patrol Boat
        System.out.println("Which coordinate do you want your Patrol Boat (2 long) to go? (Use the format A-1)");
        input = scan.nextLine();
        coordParse = input.split("-", 2);
        row = coordParse[0].charAt(0);
        col = Integer.parseInt(coordParse[1]);
        System.out.println("Alright, the coordinates are set for " + row + "-" + col + ", will the boat face North, South, East, or West?");
        input = scan.nextLine();
        switch(input){
            case "north":
                yGrid[row - 64][col + 1] = '█';
                yGrid[row - 65][col + 1] = '█';
                break;
            case "south":
                yGrid[row - 64][col + 1] = '█';
                yGrid[row - 63][col + 1] = '█';
                break;
            case "east":
                yGrid[row - 64][col + 1] = '█';
                yGrid[row - 64][col + 2] = '█';
                break;
            case "west":
                yGrid[row - 64][col + 1] = '█';
                yGrid[row - 64][col + 0] = '█';
                break;
        }
        //Print grids
        System.out.println("     1 2  3 4  5 6  7 8  9 10       1 2  3 4  5 6  7 8  9 10");
        for(i = 0; i < 11; i++){
            for(j = 0; j < 25; j++){
                System.out.print(yGrid[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.println("     This is your Fleet Grid!    This is the enemy's Fleet Grid!"
                + "\n\nAlright, with the ships deployed, the game can begin!");
        while(playing){
            //Player turn
            System.out.print("Please indicate where you would like to shoot: ");
            input = scan.nextLine();
            coordParse = input.split("-", 2);
            row = coordParse[0].charAt(0);
            col = Integer.parseInt(coordParse[1]);
            System.out.println("Firing all guns at " + row + "-" + col + "!");
            if(AIShips[row-65][col-1]){
                System.out.println("Hit!");
                yPoints++;
                yGrid[row - 64][col + 13] = '▓';
                //Print grids
                System.out.println("     1 2  3 4  5 6  7 8  9 10       1 2  3 4  5 6  7 8  9 10");
                for(i = 0; i < 11; i++){
                    for(j = 0; j < 25; j++){
                        System.out.print(yGrid[i][j] + " ");
                    }
                    System.out.print("\n");
                }
                if(yPoints == 17){
                    playing = false;
                    System.out.println("You win!");
                    break;
                }
            } else {
                System.out.println("Miss...");
                yGrid[row - 64][col + 13] = '╬';
                //Print grids
                System.out.println("     1 2  3 4  5 6  7 8  9 10       1 2  3 4  5 6  7 8  9 10");
                for(i = 0; i < 11; i++){
                    for(j = 0; j < 25; j++){
                        System.out.print(yGrid[i][j] + " ");
                    }
                    System.out.print("\n");
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(RowdyWarships.class.getName()).log(Level.SEVERE, null, ex);
            }
            //AI Turn
            iRow = (int)(Math.random()*9);
            col = (int)(Math.random()*10+1);
            System.out.println("The enemy fired at " + (char)(iRow+65) + "-" + col + "!");
            if(yGrid[iRow+1][col+1] == '█'){
                System.out.println("Oh no, they hit!");
                aiPoints++;
                yGrid[iRow+1][col + 1] = '▓';
                //Print grids
                System.out.println("     1 2  3 4  5 6  7 8  9 10       1 2  3 4  5 6  7 8  9 10");
                for(i = 0; i < 11; i++){
                    for(j = 0; j < 25; j++){
                        System.out.print(yGrid[i][j] + " ");
                    }
                    System.out.print("\n");
                }
                if(aiPoints == 17){
                    playing = false;
                    System.out.println("The enemy won...");
                    break;
                }
            } 
            else {
                System.out.println("Phew, they missed...");
                yGrid[iRow+1][col + 1] = '╬';
                //Print grids
                System.out.println("     1 2  3 4  5 6  7 8  9 10       1 2  3 4  5 6  7 8  9 10");
                for(i = 0; i < 11; i++){
                    for(j = 0; j < 25; j++){
                        System.out.print(yGrid[i][j] + " ");
                    }
                    System.out.print("\n");
                }
            }
            
        }
    }
    
}
