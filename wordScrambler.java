/*
1) Word Scrambler Game: 
Requirements:
• It should have an array of words [apple, banana, etc...]
• Use Random to get any word from the array
• Scramble it using String operations
• Print the scrambled word
• Get user input for the answer
• Validate the answer
• If answer is correct +1 else -1 
• Reached 5 points --> winner
*/
import java.util.Random;
import java.util.Scanner;
public class wordScrambler {
    public static String getWord(){
        String[] words={"Happy", "Funny", "Quick", "Brave", "Lucky", "Smart", "Kind"
            , "Dream", "Quiet", "Silly","Run", "Jump", "Swim", "Race", "Skate", "Bike", "Goal", "Score"
            , "Kick", "Game","Pizza", "Bread", "Apple", "Sugar", "Mango", "Carrot"
            ,"Candy", "Lemon", "Rice", "Berry","Cat", "Dog", "Lion", "Tiger", "Zebra", "Panda", "Mouse", "Shark", "Snake", "Eagle"
            ,"Glide","Quote","Cattle","Bolt","Steel","Sell","Effect","Trunk","Owl","Linger","Metal","Sign","Back","Drive"
            ,"Wash","Rocket","Agent","Shatter","Tone","Master","Speed","Silver","Fate","Dignity","Achieve","Venus","Snub","Poll","Gravel"
            ,"Defend","Suffer","Update","Return","Cute","Navy","Bench","Lobby","Frown","Window","Whole","Squeeze","Rice","Lineage","Problem"
            ,"Tear","Runner","Delay","Nerve","Glasses"};
        Random random=new Random();
        int i=random.nextInt(words.length);
        return words[i];
    }
    public static String shuffleWord(String s){
        char[] characters = s.toCharArray();
        Random random=new Random();
        for(int i=0;i<characters.length;i++){
            int index=random.nextInt(characters.length);
            char temp = characters[i];
            characters[i] = characters[index];
            characters[index] = temp;
        }
        return new String(characters);
    }
    public static void main (String args[]){
        System.out.println("Welcome to WordScrambler");
        Scanner sc=new Scanner(System.in);
        int score=0;
        while(score<5){
            String sample=getWord();
            String shuffled=shuffleWord(sample);
            System.out.println("The scrambled word is : "+shuffled);
            String input=sc.nextLine();
            if(sample.equalsIgnoreCase(input)){
                score++;
            }else{
                score--;
                System.out.println("The correct word was: "+sample);
            }
            if(score<0){
                System.out.println("You have Lost");
                break;
            }
        }
        if (score==5){
            System.out.println("!!!WINNER!!!");
        }
        sc.close();
    }
}
