package lab9_data_compression;

public class StringRLE {
    public static void printRLE(String string){
        String compress = "";
        int index;
        char currChar = string.charAt(0);
        int count = 1;

        for(index = 1; index < string.length(); index++){
            if (currChar == string.charAt(index)) {
                count++;
            }else{
                if(count == 1){
                    compress += currChar;
                }else{
                    compress += currChar + "" + count;
                }
                currChar = string.charAt(index);
                count = 1;
            }
        }

        if(count == 1){
            compress += currChar;
        }else{
            compress += currChar + "" + count;
        }

        System.out.println(compress);
    }

    public static void main(String[] args) {
        printRLE("wffffjjllllm");
    }
}
