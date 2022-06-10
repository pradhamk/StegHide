class StegHide {
  public static void main(String[] args) {
    steg steghide;
    if(args.length == 0){
      System.out.println("Please Look at the help section.");
      return;
    }
    switch(args[0]){
      case "--hide":
        if(args.length == 4){
          System.out.println("Hiding file in " + args[2] + " with key " + args[3] + " inside " + args[1]);
          try{
            steghide = new steg(args[1], args[2], 0, args[3]);
            int fpoint = steghide.hide_file();
            System.out.println("File successfully hidden");
            System.out.println("The File Point is: " + fpoint);
          }
          catch(Exception e){
            System.out.println("An error has occured");
            e.printStackTrace();
          }
        }
        else{
          System.out.println("Not enough arguments. Please look at the help section.");
        }
        break;
      case "--extract":
        if(args.length == 5){
          System.out.println("Extracting file from " + args[1] + " with key " + args[4] + " to " + args[3]);
          try{
            steghide = new steg(args[1], Integer.parseInt(args[2]), args[3], args[4]);
            steghide.extract_file();
            System.out.println("File successfully extracted");
          }
          catch(Exception e){
            System.out.println("An error has occured");
            e.printStackTrace();
          }
        }
        else{
          System.out.println("Not enough arguments. Please look at the help section.");
        }
        break;
      default:
        System.out.println("StegHide");
        for(int i = 0; i < 10; i++){System.out.print("-");}
        System.out.println();
        System.out.println("Usage: java steg [--help|--hide|--extract]");
        System.out.println("--hide: Hide a file in an image");
        System.out.println("--extract: Extract a file from an image\n");
        System.out.println("Hide:\n\tjava steg --hide <OriginalFile> <FileToBeHidden> <key>\n");
        System.out.println("Extract:\n\tjava steg --extract <ModifiedFile> <file point> <output file> <key>\n");
        System.out.println("-h: Prints out the help menu");
        break;
    }
  }
}