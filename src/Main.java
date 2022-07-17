import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

class Main{
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
enum Movementkey{
    LEFT("a"),DOWN("s"),RIGHT("d"),UP("w");
    private String key;

    Movementkey(String key) {
        this.key = key;
    }
}
class Game{
    Scanner sc = new Scanner(System.in);

    private int pY, pX;
    private String name;
    private int difficulty = 5;
    private boolean isAlive;
    boolean[][][] doors = new boolean[5][5][4];

    public void start(){
        pY = 0; pX = 0;
        isAlive=true;

        game();
    }
    private void game(){
        boolean isPossible = false;
        String movement;
        doors = createMap();
        int count = 0;
        while(!isPossible){
            doors = createMap();
            isPossible=testPossible(difficulty-1,difficulty-1,new boolean[difficulty][difficulty]);
            count++;
        }
        System.out.println("Failed: "+count);
        while(isAlive){
            map();
            if(pX == difficulty-1 && pY == difficulty-1){
                isAlive=false;
            }
            movement = sc.next();

            if(Objects.equals(movement, "a")){
                if(doors[pY][pX][3]==false){
                    System.out.println("Please pick a door");
                } else {
                    pX--;
                }
            } else if(Objects.equals(movement, "d")){
                if(doors[pY][pX][1]==false){
                    System.out.println("Please pick a door");
                } else {
                    pX++;
                }
            } else if(Objects.equals(movement, "w")){
                if(doors[pY][pX][0]==false){
                    System.out.println("Please pick a door");
                } else {
                    pY--;
                }
            } else if(Objects.equals(movement, "s")){
                if(doors[pY][pX][2]==false){
                    System.out.println("Please pick a door");
                } else {
                    pY++;
                }
            } else{
               // System.out.println("Please pick a right key");

            }
        }
    }
    private boolean[][][] createMap(){
        for(int roomsY = 0; roomsY< doors.length;roomsY++){
            for(int roomsX = 0; roomsX < doors.length;roomsX++){
                if(roomsY!=0){
                    doors[roomsY][roomsX][0]=true;
                } if(roomsY!=doors.length-1){
                    doors[roomsY][roomsX][2]=true;
                } if(roomsX!=0){
                    doors[roomsY][roomsX][3]=true;
                } if(roomsX!=doors.length-1){
                    doors[roomsY][roomsX][1]=true;
                }

            }
        }
        for(int roomsY = 0; roomsY< doors.length;roomsY++){
            for(int roomsX = 0; roomsX < doors.length;roomsX++){
                createMaze(roomsX,roomsY);

            }
        }
        return doors;
    }
    private boolean[][][] createMaze(int x,int y){
        double side = Math.random();
        if(side<=0.25){
            if(doors[y][x][0]==false){
                return doors;
            }
            doors[y][x][0]=false;
            doors[y-1][x][2]=false;
        } else if (side<=0.5) {
            if(doors[y][x][1]==false){
                return doors;
            }
            doors[y][x][1]=false;
            doors[y][x+1][3]=false;
        } else if (side<=0.75) {
            if(doors[y][x][2]==false){
                return doors;
            }
            doors[y][x][2]=false;
            doors[y+1][x][0]=false;
        } else {
            if(doors[y][x][3]==false){
                return doors;
            }
            doors[y][x][3]=false;
            doors[y][x-1][1]=false;
        }
//        if(x== doors.length-1 && y== doors.length-1){
//            return doors;
//        }
//        int count = 0;
//        double random = 0;
//        if(doors[x][y][0]){
//            count++;
//        } if(doors[x][y][1]){
//            count++;
//        } if(doors[x][y][2]){
//            count++;
//        } if(doors[x][y][3]){
//            count++;
//        }
//        if(count==2){
//            return doors;
//        } else if (count==1) {
//            if(x!=doors.length-1 && y!= doors.length-1) {
//                random = Math.random();
//                if (random > 0.5) {
//                    doors[x][y][1] = true;
//                    doors[x + 1][y][3] = true;
//                } else {
//                    doors[x][y][2] = true;
//                    doors[x][y + 1][0] = true;
//                }
//            } else{
//                if(x== doors.length-1){
//                    doors[x][y][2] = true;
//                    doors[x][y + 1][0] = true;
//                } else{
//                    doors[x][y][1] = true;
//                    doors[x + 1][y][3] = true;
//                }
//            }
//        } else {
//            if(x== doors.length-1){
//                doors[x][y][2] = true;
//            } if(y== doors.length-1){
//                doors[x][y][1] = true;
//            } else{
//                doors[x][y][1]=true;
//                doors[x][y][2]=true;
//                doors[x+1][y][3]=true;
//                doors[x][y+1][0]=true;
//            }
//        }
        return doors;
    }
    private void map(){
        System.out.println();
        if(pY==difficulty-1 && pX==difficulty-1){
            System.out.println("GOAL REACHED!");
            System.out.println("GOAL REACHED!");
            System.out.println("GOAL REACHED!");
            System.out.println("GOAL REACHED!");
            Game game = new Game();
            game.start();
        }
        if(doors[pY][pX][0]) {
            System.out.println("----------- ----------");
        } else{
            System.out.println("----------------------");
        }
        System.out.println("|                     |");
        System.out.println("|                     |");
        if(doors[pY][pX][1] && doors[pY][pX][3]) {
            System.out.println("          "+pY+" "+pX+"            ");
        } else if (doors[pY][pX][1]) {
            System.out.println("|         "+pY+" "+pX+"            ");
        } else if (doors[pY][pX][3]) {
            System.out.println("          "+pY+" "+pX+"         |");
        } else{
            System.out.println("|         "+pY+" "+pX+"         |");
        }
        System.out.println("|                     |");
        System.out.println("|                     |");
        if(doors[pY][pX][2]){
            System.out.println("----------- ----------");
        } else{
            System.out.println("----------------------");
        }

    }

    private boolean testPossible(int y, int x, boolean[][] went){
        went[y][x]=true;
        if(went[0][0]==true){
            return true;
        } if(doors[y][x][0] && went[y-1][x]==false){

            return testPossible(y-1,x,went);
        } if(doors[y][x][1] && went[y][x+1]==false){

            return testPossible(y,x+1,went);
        } if(doors[y][x][2] && went[y+1][x]==false){

            return testPossible(y+1,x,went);
        } if(doors[y][x][3] && went[y][x-1]==false){

            return testPossible(y,x-1,went);
        }
        return false;
    }
}